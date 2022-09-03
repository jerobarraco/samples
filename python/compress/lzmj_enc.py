#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import utils

class LZMJ22:
	# 22 because it's 2022, but also LZ77, domination 88, and galaxy express 99?
	fname = ""
	ofname = ""

	max_data = min(utils.MAX_DICT,
				   utils.USE_LZMA and
				   utils.Num_LZM_Max(*utils.LZM_BOUNDS) or utils.MAX_DICT)
	nexts = b""
	data = b""

	def __init__(self, fname, ofname):
		self.fname = fname
		self.ofname = ofname
		#self.max_data = min ( max_data, )

	def Encode(self):
		chars = utils.SRFile(self.fname)

		# out
		packs = self._SPackets(chars)
		schunks = utils.SChunk(packs)
		sbytes = utils.SByte(schunks)
		ofile = utils.SWFile(self.ofname, sbytes)

		it = ofile
		# force processing
		for o in it:
			utils.p(o)
			utils.p("\n")

	def _Literal(self, byte, bytes):
		bits = utils.Bin(byte)
		self._dataAdd(byte)
		return "0" + bits

	def _ShortRep(self, b, bytes):
		# 1 + 1 + 0 + 0
		# slicing a bytes structure returns an int D:
		if self.data and self.data[-1] == ord(b):
			self._dataAdd(b)
			return "110"

		return None

	def _Pointer(self, b, bytes):
		# holds the current tested bytes, separate from nexts to not mess around
		# i could be using nexts here. but i kind of want to decouple this function as much as possible
		cur = b+self.nexts
		self.nexts = b""
		matches = []
		minLen = 2
		'''
		TODO 
			* dont use the bytes. self.nexts should be already set 
			* make the matchmaking part into a function
			* make sure that nexts is as long as the data buffer, unless close to eof
			* precalculate range from len(nexts) to 2 before end.
			* opt: call the matchmaking part using multiprocessing.Pool
		'''
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

		self.nexts += maxMatch and cur[maxMatch[1]:] or cur[1:]
		# restore unused bytes. if not a good match has been found
		if maxMatch is None or maxMatch[1] < minLen:
			return None

		# actually encode the thing
		pos, l = maxMatch
		off = len(self.data) - pos
		matchText = self.data[-off:][:l]
		self._dataAdd(matchText)
		# optimization since we will never have an offset smaller than that
		off -= minLen
		l -= minLen

		binOff = utils.USE_LZMA and utils.Num_LZM(off, *utils.LZM_BOUNDS) or utils.Num_JMan(off)
		binL = utils.Num_JMan(l) #utils.Bin(l, 4)
		return "10" + binOff + binL

	def _dataAdd(self, b):
		self.data += b
		# trim to only the lasts one
		if len(self.data) > self.max_data :
			self.data = self.data[-self.max_data:]
			# TODO modify the pointer lists (we nteed to have a pointer list first)

	def _SPackets(self, sbytes):
		"""should return a list of bits, variable undefined length"""
		while True:
			# todo create function for readahead
			toRead = max(1, len(self.data) - len(self.nexts))
			while toRead >0:
				n = next(sbytes, None)
				if n is None: break
				self.nexts += n

			if not self.nexts:
				break

			n = self.nexts[0].to_bytes(1, "big") # ensure is a byte not an int
			self.nexts = self.nexts[1:]
			# try the different command in order of priority
			for f in [self._Pointer, self._ShortRep, self._Literal]:
				val = f(n, sbytes)
				if val is not None:
					yield val
					break
