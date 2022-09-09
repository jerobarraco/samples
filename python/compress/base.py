#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import utils
# import multiprocessing

MAX_MATCHES = 3
class Base:
	"""This class is the base for encode and decode. it should only contain things that are relative to both enc and decode. and that its not independent enough to be in utils"""
	max_data = 1024
	nexts = b""
	data = b""
	matches = []

	def __init__(self):
		self.max_data = utils.getMaxData()
		print(self.max_data, "max_data")

	def _readNexts(self, sbytes):
		toRead = max(1, len(self.nexts) - self.max_data)
		while toRead> 0:
			toRead += 1
			n = next(sbytes, None)
			if n is None: break
			self.nexts += n

	def _matchProcess(self, match):
		# actually encode the thing
		pos = match[0]
		l = match[1]

		matchText = self.data[pos:pos+l]
		self._advance(l)
		self._dataAdd(matchText)
		self._matchAdd(pos)

	def _matchAdd(self, pos):
		i = -1 if pos not in self.matches else self.matches.index(pos)
		if i>=0:
			self.matches.pop(i)

		self.matches.append(pos)
		if len(self.matches) > MAX_MATCHES:
			self.matches = self.matches[-MAX_MATCHES:]

	def _dataAdd(self, b):
		self.data += b
		# trim to only the lasts one
		dif = len(self.data) - self.max_data
		if dif>0 :
			self.data = self.data[dif:]
			self.matches = [i-dif for i in self.matches if i>=0]

	def _next(self):
		if len(self.nexts)<1: return None
		return utils.Int2Byte(self.nexts[0]) # ensure is a byte not an int

	def _advance(self, by=1):
		self.nexts = self.nexts[by:]

	def _getOne(self):
		n = self._next()
		self._advance()
		return n
