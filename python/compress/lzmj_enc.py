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
	matches = []
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

	def _LongRep(self):
		# start from the oldest one, so we rotate them frequently and keep them in memory.
		maxMatch = None
		maxI = -1
		dataL = len(self.data)
		for i, off in enumerate(self.matches):
			pos = dataL - off
			match = self._matchFind(pos)
			if match[1] < utils.POINTER_MIN_LEN : continue
			if maxMatch is None or maxMatch[1]< match[1]:
				maxMatch = match
				maxI = i

		if maxMatch is None:
			return None

		bOff, bLen = self._matchProcess(maxMatch)
		codes = [utils.Packets.LONG_REP_0, utils.Packets.LONG_REP_1, utils.Packets.LONG_REP_2]
		return codes[maxI] + bLen

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
			match = self._matchFind(i)
			if match[1] >= minLen:
				matches.append(match)

		# find the longest match
		maxMatch = None
		for m in matches:
			if maxMatch is None or maxMatch[1]> m[1]:
				maxMatch = m

		if maxMatch is None:
			return None

		# process the match
		binOff, binL = self._matchProcess(maxMatch)
		# actually encode the thing
		return utils.Packets.POINT + binOff + binL

	def _matchProcess(self, match):
		# actually encode the thing
		off, l = match
		end = off - l
		matchText = self.data[-off:-end]
		self._advance(l)
		self._dataAdd(matchText)
		self._matchAdd(off)

		# optimization since we will never have an offset smaller than that
		minLen = utils.POINTER_MIN_LEN
		off -= minLen
		l -= minLen

		binOff = utils.Num(off)
		binL = utils.Num(l)
		return binOff, binL

	def _matchFind(self, i):
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
		off = dataLen - i
		match = (off, l)  # new match
		return match

	def _matchAdd(self, off):
		i = -1 if off not in self.matches else self.matches.index(off)
		if i>=0:
			self.matches.pop(i)

		self.matches.append(off)
		maxMatches = 3
		if len(self.matches) > maxMatches:
			self.matches = self.matches[-maxMatches:]
			# need to debug this

	def _dataAdd(self, b):
		self.data += b
		# trim to only the lasts one
		if len(self.data) > self.max_data :
			self.data = self.data[-self.max_data:]
			self.matches = [i+self.max_data for i in self.matches if i<self.max_data]

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
			# self._LongRep, # wip
			for f in [self._Pointer, self._ShortRep, self._Literal]:
				val = f()
				if val is not None:
					yield val
					break
		#eof
		yield utils.Packets.EOF
		print ("Packets=", count)