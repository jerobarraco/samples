#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import utils

class LZMJ22Dec:
	ifname = ""
	ofname = ""
	data = b""
	max_data = utils.getMaxData()
	def __init__(self, ifname, ofname):
		self.ifname = ifname
		self.ofname = ofname

	def Decode(self):
		chars = utils.SRFile(self.ifname)
		bins = utils.SBin(chars)
		sbits = utils.SItem(bins)

		sbytes = self._SDec(sbits)
		sproc = self._SDecProc(sbytes)
		obyte = utils.SItem(sproc)

		ofile = utils.SWFile(self.ofname, obyte)

		it = ofile
		# force processing
		count = 0
		for o in it:
			utils.p(o)
			count+=1
			# utils.p("\n")
		print()
		print("iterations=", count)
		print("All Done!")
		pass

	def _dataAdd(self, b):
		self.data += b
		# trim to only the lasts one
		if len(self.data) > self.max_data:
			self.data = self.data[-self.max_data:]
			# TODO modify the pointer lists (we need to have a pointer list first)

	def _SDecProc(self, decs):
		for d in decs:
			if decs is None:
				print ("proc was none")
				continue
			self._dataAdd(d)
			yield d

	def _SDec(self, bins):
		lLit = utils.Packets.L_LITERAL
		lShort = utils.Packets.L_SHORT_REP
		lPoint = utils.Packets.L_POINT
		lLrep0 = utils.Packets.L_LONG_REP_0
		lLrep1 = utils.Packets.L_LONG_REP_1
		lLrep2 = utils.Packets.L_LONG_REP_2
		lEof = utils.Packets.L_EOF

		# receives bits, make sure its one bit at a time
		buff = ""
		count = 0
		for b in bins:
			buff += b
			count +=1
			if buff[:lLit] == utils.Packets.LITERAL:
				buff = buff[lLit:]
				# this needs to be sure that all elements are of size one, otherwise we would need to wrap with utils.SItem
				# this is dangerous, find another way
				bits = utils.Chunk(bins, 8, "0")# params makes sure we read completely
				if bits is None:
					return

				byte = utils.Byte(bits)
				yield byte
				continue

			if len(buff) < lPoint: continue
			# pointer, 10
			if buff[:lPoint] == utils.Packets.POINT:
				buff = buff[lPoint:]
				yield self._Pointer(bins)
				continue


			if len(buff) < lShort: continue
			# shortrep
			if buff[:lShort] == utils.Packets.SHORT_REP:
				buff = buff[lShort:]
				yield self._ShortRep()
				continue

			if len(buff)< lLrep0: continue
			if len(buff)< lLrep1: continue
			if len(buff)< lLrep2: continue
			if len(buff)< lEof: continue
			if buff[:lEof] == utils.Packets.EOF:
				print ("Reached eof")
				break

			print ("Longrep not implemented!")
			buff = buff[lLrep0:]
		print ("Commands=", count)

	def _Pointer(self, bins):
		minLen = utils.POINTER_MIN_LEN
		off = utils.SNumDec(bins) + minLen# minimum 2 chars
		l = utils.SNumDec(bins) + minLen
		end = -off + l
		# warning, if this results in one char it will return an int. but that shouldn't (tm) happen
		return self.data[-off:end]

	def _ShortRep(self):
		if len(self.data)<1:
			return b""
		return utils.Int2Byte(self.data[-1])
