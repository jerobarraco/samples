#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import utils

class LZMJ22Dec:
	ifname = ""
	ofname = ""
	data = b""
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
		for o in it:
			utils.p(o)
			utils.p("\n")

	def _SDecProc(self, decs):
		for d in decs:
			if decs is None : return
			self.data += d
			yield d

	def _SDec(self, bins):
		# receives bits, make sure its one bit at a time
		buff = ""
		for b in bins:
			buff += b
			if buff[0] == "0":
				buff = buff[1:]
				# this needs to be sure that all elements are of size one, otherwise we would need to wrap with utils.SItem
				bits = utils.Chunk(bins, 8)
				if bits is None: return
				byte = utils.Byte(bits)
				yield byte
				continue

			if len(buff) < 2: continue
			# pointer, 10
			if buff[:2] == "10":
				buff = buff[2:]
				yield self._Pointer(bins)
				continue

			if len(buff) < 3: continue
			# shortrep, 110
			if buff[:3] == "110":
				buff = buff[3:]
				yield self._ShortRep()
				continue
			print ("111 not implemented!")
			buff = buff[3:]

	def _Pointer(self, bins):
		off = utils.SNum_JMan_Dec(bins) + 2# minimum 2 chars
		l = utils.SNum_JMan_Dec(bins) +2
		end = -off + l
		return self.data[-off:end]

	def _ShortRep(self):
		if len(self.data)<1: return b""
		return utils.Int2Byte(self.data[-1])