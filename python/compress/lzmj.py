#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import sys

import utils

class LZMJ22:
	# 22 because it's 2022, but also LZ77, domination 88, and galaxy express 99?
	fname = ""
	max_data = 100 #1024**2 # TODO this is for testing

	def __init__(self, fname, max_data = 100):
		self.fname = fname
		self.data = b""
		self.max_data = max_data

	def Encode(self):
		chars = utils.SRFile(self.fname)

		# out
		packs = self._SPackets(chars)
		schunks = utils.SChunk(packs)
		sbytes = utils.SByte(schunks)
		ofile = utils.SWFile('out.txt', sbytes)

		it = packs
		# force processing
		for o in it:
			p(o)
			p("\n")

	def _Literal(self, byte):
		bits = utils.Bin(byte)
		return "0" + bits

	def _ShortRep(self, b):
		# 1 + 1 + 0 + 0
		return "1100" if self.data and self.data[-1] == b[0] else None

	def _dataAdd(self, b):
		self.data += b
		# trim to only the lasts one
		if len(self.data) > self.max_data :
			self.data = self.data[-self.max_data:]
			# TODO modify the pointer lists

	def _SPackets(self, sbytes):
		"""should return a list of bits, variable undefined length"""
		for b in sbytes:
			# try the repeat
			shortRep = self._ShortRep(b)
			if shortRep is not None:
				yield shortRep

			# todo try to encode the packet
			# literals
			yield self._Literal(b)

			self._dataAdd(b)

def p(o):
	sys.stdout.write(str(o))
	pass

def main():
	fname0 = '/home/nande/work/repos/samples/python/compress/compnotes.txt'
	fname ='/storage/emulated/0/.sstmp'
	fname2='/storage/emulated/0/Download/1366px-Competence_Hierarchy_adapted_from_Noel_Burch_by_Igor_Kokcharov.svg.png'
	fname3='/storage/emulated/0/Download/14 inner critic - 1 sarah.mp3'
	fname4='/storage/emulated/0/qpython/Ideas.txt'
	enc = LZMJ22(fname0)
	enc.Encode()

if __name__ == "__main__":
	main()