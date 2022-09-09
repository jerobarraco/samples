#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import utils
# import multiprocessing

class Base:
	"""This class is the base for encode and decode. it should only contain things that are relative to both enc and decode. and that its not independent enough to be in utils"""
	max_data = 1024
	nexts = b""
	data = b""
	matches = []

	def __init__(self):
		max_data = utils.getMaxData()

	def _matchProcess(self, match):
		# TODO change it to use pos
		# actually encode the thing
		off, l = match
		end = off - l
		matchText = self.data[-off:-end]
		self._advance(l)
		self._dataAdd(matchText)
		self._matchAdd(off)

		# TODO make this a function

		# optimization since we will never have an offset smaller than that
		minLen = utils.POINTER_MIN_LEN
		off -= minLen
		l -= minLen

		binOff = utils.Num(off)
		binL = utils.Num(l)
		return binOff, binL


	def _matchAdd(self, off):
		i = -1 if off not in self.matches else self.matches.index(off)
		if i>=0:
			self.matches.pop(i)

		self.matches.append(off)
		maxMatches = 3
		if len(self.matches) > maxMatches:
			self.matches = self.matches[-maxMatches:]
			# TODO need to debug this

	def _dataAdd(self, b):
		self.data += b
		# trim to only the lasts one
		if len(self.data) > self.max_data :
			self.data = self.data[-self.max_data:]
			self.matches = [i-self.max_data for i in self.matches if i>=0]

	def _next(self):
		if len(self.nexts)<1: return None
		return utils.Int2Byte(self.nexts[0]) # ensure is a byte not an int

	def _advance(self, by=1):
		self.nexts = self.nexts[by:]

	def _getOne(self):
		n = self._next()
		self._advance()
		return n
