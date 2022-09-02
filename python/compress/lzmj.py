#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import sys

import utils

class LZMJ22:
	# 22 because it's 2022, but also LZ77, domination 88, and galaxy express 99?
	fname = ""
	def __init__(self, fname):
		self.fname = fname

	def Encode(self):
		chars = utils.SRFile(self.fname)

		# out
		packs = self._SPackets(chars)

		schunks = utils.SChunk(packs)
		sbytes = utils.SByte(schunks)
		ofile = utils.SWFile('out.txt', sbytes)

		it = ofile
		# force processing
		for o in it:
			p(o)
			p(" ")

	def _SPackets(self, sbytes):
		"""should return a list of bits"""
		for b in sbytes:
			# todo try to encode the packet
			# literals
			# TODO return bits
			bits = utils.Bin(b)
			yield "0" + bits

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