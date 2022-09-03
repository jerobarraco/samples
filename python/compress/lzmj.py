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
	nexts = b""
	data = b""

	def __init__(self, fname, max_data = 100):
		self.fname = fname
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

	def _Literal(self, byte, bytes):
		bits = utils.Bin(byte)
		return "0" + bits

	def _ShortRep(self, b, bytes):
		# 1 + 1 + 0 + 0
		# slicing a bytes structure returns an int D:
		return "110" if self.data and self.data[-1] == ord(b) else None

	def _Pointer(self, b, bytes):
		cur = b # holds the current tested bytes, separate from nexts to not mess around
		matches = []
		minLen = 2

		# find all matches
		for i, d in enumerate(self.data):
			# when a match is found keep testing to find the length
			if d == cur[0]:
				# this could be extracted to a function
				l = 1 # num chars
				while True:
					pos = i+l
					# not enough data stranger
					if len(self.data) <= pos: break

					# read ahead, todo do something to put back a non match thing
					if len(cur) <= l:
						n = next(bytes, None)
						if n is None:
							print("Reached end of stream!")
							break
						cur += n
					if self.data[pos] != cur[l]: break
					l+=1
				#end while
				match = (i, l) # new match
				if l >= minLen:
					matches.append(match)

		# find the longest match
		maxMatch = None
		for m in matches:
			if maxMatch is None or maxMatch[1]> m[1]:
				maxMatch = m

		# restore unused bytes. if not a good match has been found
		if maxMatch is None or maxMatch[1] < minLen:
			self.nexts += maxMatch and cur[maxMatch[1]:] or cur[1:]
			return None

		# actually encode the thing
		pos, l = maxMatch
		off = len(self.data) - pos

		# optimization since we will never have an offset smaller than that
		off -= minLen
		l -= minLen

		binOff = utils.Num_LZM(off, 2, 3, 8)
		binL = utils.Num_JMan(l) #utils.Bin(l, 4)
		'''
		111110000000000
		01234
		15 - 4 = 11
		'''
		return "10" + binOff + binL

	# taking a break brb soon
	def _dataAdd(self, b):
		self.data += b
		# trim to only the lasts one
		if len(self.data) > self.max_data :
			self.data = self.data[-self.max_data:]
			# TODO modify the pointer lists

	def _SPackets(self, sbytes):
		"""should return a list of bits, variable undefined length"""
		while True:
			if not self.nexts:
				b = next(sbytes, None)
				if b is None:
					print ("were done")
					break

				self.nexts += b

			n = self.nexts[0].to_bytes(1, "big") # ensure is a byte not an int
			self.nexts = self.nexts[1:]
			# try the different command in order of priority
			for f in [self._Pointer, self._ShortRep, self._Literal]:
				val = f(n, sbytes)
				if val is not None:
					yield val
					break
			self._dataAdd(n)

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