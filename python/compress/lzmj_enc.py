#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import utils
# import multiprocessing

class LZMJ22:
	# 22 because it's 2022, but also LZ77, domination 88, and galaxy express 99?
	fname = ""
	ofname = ""

	max_data = utils.getMaxData()
	nexts = b""
	data = b""
	#_mpPool = multiprocessing.Pool()

	def __init__(self, fname, ofname):
		self.fname = fname
		self.ofname = ofname

	def Encode(self):
		chars = utils.SRFile(self.fname)

		# out
		packs = self._SPackets(chars)

		# params makes sure we flush the buffer
		schunks = utils.SChunk(packs, 8, "0")
		sbytes = utils.SByte(schunks)
		ofile = utils.SWFile(self.ofname, sbytes)

		it = ofile
		# force processing
		for o in it:
			utils.p(o)
			utils.p("\n")

	def _Literal(self):
		byte = self._next()
		bits = utils.Bin(byte)
		self._advance()
		self._dataAdd(byte)
		return utils.Packets.LITERAL + bits

	def _ShortRep(self):
		# 1 + 1 + 0 + 0
		# slicing a bytes structure returns an int D:
		b = self._next()
		if self.data and b is not None and self.data[-1] == ord(b):
			self._advance()
			self._dataAdd(b)
			return utils.Packets.SHORT_REP

		return None

	def _Pointer(self):
		# holds the current tested bytes, separate from nexts to not mess around
		# i could be using nexts here. but i kind of want to decouple this function as much as possible
		matches = []
		'''
		TODO 
			* make the matchmaking part into a function
			* opt: call the matchmaking part using multiprocessing.Pool
		'''
		# make sure that nexts is as long as the data buffer, unless close to eof
		minLen = utils.POINTER_MIN_LEN
		dataLen = len(self.data)
		nextLen = len(self.nexts)
		maxLen = min(nextLen, dataLen)
		if maxLen < minLen: return

		# precalculate range from len(nexts) to 2 before end.
		start = dataLen - maxLen
		end = maxLen
		# need to pass the memory using shared memory
		# matches = self._mpPool.imap_unordered(self._findMatch, range(start, end) )
		# for m in matches : do the max thing

		# find all matches
		for i in range(start, end):
			match = self._findMatch(i)
			if match[1] >= minLen:
				matches.append(match)

		# find the longest match
		maxMatch = None
		for m in matches:
			if maxMatch is None or maxMatch[1]> m[1]:
				maxMatch = m

		if maxMatch is None:
			return None

		# actually encode the thing
		dataPos, l = maxMatch
		off = dataLen - dataPos
		end = off - l
		matchText = self.data[-off:-end]
		self._advance(l)
		self._dataAdd(matchText)
		# optimization since we will never have an offset smaller than that
		off -= minLen
		l -= minLen

		binOff = utils.Num(off)
		binL = utils.Num(l)
		return utils.Packets.POINT + binOff + binL

	def _findMatch(self, i):
		l = 0
		dataPos = i
		dataLen = len(self.data)
		nextLen = len(self.nexts)
		while dataPos < dataLen and l < nextLen:
			if self.data[dataPos] != self.nexts[l]:
				break
			l += 1
			dataPos = i + l
		# end while
		match = (i, l)  # new match
		return match

	def _dataAdd(self, b):
		self.data += b
		# trim to only the lasts one
		if len(self.data) > self.max_data :
			self.data = self.data[-self.max_data:]
			# TODO modify the pointer lists (we need to have a pointer list first)

	def _next(self):
		if len(self.nexts)<1: return None
		return utils.Int2Byte(self.nexts[0]) # ensure is a byte not an int

	def _advance(self, by=1):
		self.nexts = self.nexts[by:]

	def _getOne(self):
		n = self._next()
		self._advance()
		return n

	def _SPackets(self, sbytes):
		"""should return a list of bits, variable undefined length"""
		count = 0
		while True:
			count += 1
			# todo create function for readahead
			toRead = max(1, len(self.data) - len(self.nexts))
			while toRead >0:
				n = next(sbytes, None)
				if n is None: break
				self.nexts += n

			if not self.nexts:
				break

			# try the different command in order of priority
			for f in [self._Pointer, self._ShortRep, self._Literal]:
				val = f()
				if val is not None:
					yield val
					break
		#eof
		yield utils.Packets.EOF
		print ("Packets=", count)