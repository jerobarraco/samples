#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import utils
import base
# import multiprocessing

class LZMJ22(base.Base):
	# 22 because it's 2022, but also LZ77, domination 88, and galaxy express 99?
	fname = ""
	ofname = ""
	nexts = b""
	max_num = 200
	counts = [0,0,0,0]
	#_mpPool = multiprocessing.Pool()

	def __init__(self, fname, ofname):
		super().__init__()
		self.fname = fname
		self.ofname = ofname
		self.max_num = utils.getNumMax()
		print('max num', self.max_num)

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

	def _SPackets(self, sbytes):
		"""should return a list of bits, variable undefined length"""
		count = 0
		funcs = [self._LongRep, self._Pointer, self._ShortRep, self._Literal]
		while True:
			count += 1
			self._readNexts(sbytes)

			if not self.nexts:#done
				break

			# try the different command in order of priority
			for i,f in enumerate(funcs):
				val = f()
				if val is not None:
					self.counts[i] += 1
					yield val
					break
		#eof
		yield utils.Packets.EOF
		print ("Packets=", count)
		
		print ('Counts')
		for n,c in zip(['long', 'point', 'short', 'lit'], self.counts):
			print(n,c)

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
		maxPoint = None
		maxI = -1
		#print(self.matches)
		for i, pos in enumerate(self.matches[:base.MAX_MATCHES]):# todo verify the reversed
			if pos<0:
				#print("neg match")
				continue# ignore old matches
			match = self._matchFind(pos, True)
			if match[1] < utils.POINTER_MIN_LEN: continue
			point = self._matchPointer(match)
			if point is None: continue

			if maxMatch is None or maxMatch[2] < match[2]:
				maxMatch = match
				maxI = i
				maxPoint = point

		if maxMatch is None: return

		self._matchProcess(maxMatch)
		bOff, bLen = maxPoint
		codes = (utils.Packets.LONG_REP_0, utils.Packets.LONG_REP_1, utils.Packets.LONG_REP_2)
		return codes[maxI] + bLen

	def _Pointer(self):
		# holds the current tested bytes, separate from nexts to not mess around
		# i could be using nexts here. but i kind of want to decouple this function as much as possible
		matches = []
		# TODO  opt: call the matchmaking part using multiprocessing.Pool
		# make sure that nexts is as long as the data buffer, unless close to eof
		minLen = utils.POINTER_MIN_LEN
		dataLen = len(self.data)
		nextLen = len(self.nexts)
		maxLen = min(nextLen, dataLen, self.max_num)# todo max_num + utils.POINTEE_MIN_LEN
		if maxLen < minLen: return

		# precalculate range from len(nexts) to 2 before end.
		start = dataLen - maxLen
		end = maxLen
		# need to pass the memory using shared memory
		# matches = self._mpPool.imap_unordered(self._findMatch, range(start, end) )
		# for m in matches : do the max thing

		min_save = 4 # this is a small optimization that seems to save a bit of data (actually 4 bits)
		# find all matches
		for i in range(start, end):
			match = self._matchFind(i, False)
			# only store if it's above the min len
			if match[1] >= minLen:
				matches.append(match)

		# find the longest match
		maxMatch = None
		for m in matches:
			# notice we compare the saved bits of the matches
			if maxMatch is None or maxMatch[2] > m[2]:
				maxMatch = m

		if maxMatch is None:
			return None

		# process the match
		self._matchProcess(maxMatch)
		# actually encode the thing
		return maxMatch[3]

	def _matchPointer(self, match):
		'Returns a pointer for a match off,len'
		pos = match[0]
		l = match[1]
		minLen = utils.POINTER_MIN_LEN
		off = len(self.data) - pos

		# optimization since we will never have an offset smaller than that
		off -= minLen
		l -= minLen

		if off<0 or l<0 or off>self.max_num or l > self.max_num:
			return None

		binOff = utils.Num(off)
		if binOff is None:
			return None

		binL = utils.Num(l)
		return binOff, binL

	def _matchFind(self, pos, isRep = False):
		"Returns a match such as (pos, len, saved bits, pointer (this is only accurate for Pointer and not longreps))"
		l = 0
		dataPos = pos
		dataLen = len(self.data)
		nextLen = len(self.nexts)
		while dataPos < dataLen and l < nextLen and l<self.max_num:
			# this might be a bit more performant in the while condition but i like to be able to debug
			if self.data[dataPos] != self.nexts[l]:
				break
			l += 1
			dataPos = pos + l
		# end while

		# calculate actual savings
		match = [pos, l, 0, ""]  # new match
		if l<utils.POINTER_MIN_LEN:
			match[1] = 0
			return match

		# make sure the pointer is valid
		point = self._matchPointer(match)
		if point is None:
			match[1] = 0 # mark as invalid
			return match

		binOff, binL = point
		# calculate the actual encoded packet
		strpoint = (utils.Packets.LONG_REP_2 + binL) if isRep else (utils.Packets.POINT + binOff + binL)
		# calculate saved bits we compare the actual bits needed to store this (currently we use 9 per literal)
		saved = (9*l) - len(strpoint)
		# skip matches that don´t save us enough
		if saved < base.MIN_SAVE:
			match[2] = saved # just to print it
			#print("Unworthy match", match, self.data[match[0]:match[0] + match[1]])
			match[1] = 0 # mark as invalid
			return match

		match[2] = saved
		match[3] = strpoint

		return match

	def _matchProcess(self, match):
		# actually encode the thing
		pos = match[0]
		l = match[1]

		matchText = self.data[pos:pos + l]
		# it's very important to add the match before adding the data, since the data will shift both arrays, as well as the value of the match
		self._matchAdd(pos)
		self._dataAdd(matchText)
		self._advance(l)
		return matchText

	def _readNexts(self, sbytes):
		toRead = max(1, len(self.nexts) - self.max_data)
		while toRead > 0:
			toRead += 1
			n = next(sbytes, None)
			if n is None: break
			self.nexts += n

	def _next(self):
		if len(self.nexts)<1: return None
		return utils.Int2Byte(self.nexts[0]) # ensure is a byte not an int

	def _advance(self, by=1):
		# notice if nexts is 1 or 0 then it will become an int and break all
		self.nexts = self.nexts[by:]

	def _getOne(self):
		n = self._next()
		self._advance()
		return n