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
		#sbits = utils.SItem(bins)

		sbytes = self._SDec(bins)
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
		buff = ""
		for b in bins:
			buff += b
			if len(buff) < 9: continue

			if buff[0] == "0":
				bits = buff[1:9]
				buff = buff[9:]
				byte = utils.Byte(bits)
				yield byte
				continue

			# pointer, 10
			if buff[:2] == "10":
				buff = buff[2:]
				yield self._Pointer(buff, bins)
				continue

			# shortrep, 110
			if buff[:3] == "110":
				buff = buff[3:]
				yield self._ShortRep(buff, bins)
				continue

	def _Pointer(self, buff, bins):
		return b""
	def _ShortRep(self, buff, bins):
		if len(self.data)<1: return b""
		return self.data[-1].to_bytes(1, "big")
