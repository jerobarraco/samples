#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import utils
import base
class LZMJ22Dec(base.Base):
	ifname = ""
	ofname = ""

	def __init__(self, ifname, ofname):
		super().__init__()
		# self.max_data ideally is exactly the same as in the encoder. it might break (currently depends on lzm_bounds, which also needs to be exactly the same to be able to decompress)
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

			# TODO this longrep eof stuff needs improving
			if len(buff)< lLrep0: continue
			if len(buff)< lLrep1: continue
			# these are actually larger, i cant read 1 bit extra into buff so i have to be extra careful
			isLongRep0 = buff[:lLrep0] == utils.Packets.LONG_REP_0
			isLongRep1 = (not isLongRep0) and buff[:lLrep1] == utils.Packets.LONG_REP_1  # the not is an optimization
			isLongRep2 = False
			isLongRep = isLongRep0 or isLongRep1
			isEof = False
			if not isLongRep:
				if len(buff)< lLrep2: continue
				# done this way to make it easier.
				if len(buff)< lEof: continue

				isLongRep2 = (not (isLongRep0 or isLongRep1)) and buff[:lLrep2] == utils.Packets.LONG_REP_2
				isLongRep = isLongRep or isLongRep2
				isEof = (not isLongRep) and buff[:lEof] == utils.Packets.EOF

			l = lLrep2 if (isLongRep2 or isEof) else lLrep0
			i = 2 if isLongRep2 else (1 if isLongRep1 else 0)
			if isLongRep:
				buff = buff[l:]
				yield self._LongRep(bins, i)
				continue

			if isEof:
				# buff = buff[lEof:] doesnt even matter
				print ("Reached eof")
				break
			print("Error, reached an unrecognized opcode")
			buff = buff[lEof:]
		print ("Commands=", count)

	def _Pointer(self, bins):
		minLen = utils.POINTER_MIN_LEN
		off = utils.SNumDec(bins) + minLen# minimum 2 chars
		l = utils.SNumDec(bins) + minLen
		pos = len(self.data) - off
		text = self._matchProcess(pos, l)
		return text

	def _ShortRep(self):
		if len(self.data)<1:
			return b""
		return utils.Int2Byte(self.data[-1])

	def _LongRep(self, bins, i):
		pos = self.matches[i]
		l = utils.SNumDec(bins) + utils.POINTER_MIN_LEN
		text = self._matchProcess(pos, l)
		return text

	def _matchProcess(self, pos, l):
		if pos <0:
			print ("Warning! match process with negative pos", pos, l)
		# warning, if this results in one char it will return an int. but that shouldn't (tm) happen
		matchText = self.data[pos:pos + l]
		self._matchAdd(pos)
		return matchText
