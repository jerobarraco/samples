#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import utils
# import multiprocessing

MAX_MATCHES = 3
MIN_SAVE = 4
# TODO save at the beginning of the file the LZM_Bounds (how? using the JMan?)
# TODO load on decode
# TODO use crc
class Base:
	"""This class is the base for encode and decode. it should only contain things that are relative to both enc and decode. and that its not independent enough to be in utils"""
	max_data = 500
	data = b""
	matches = []

	def __init__(self):
		self.max_data = utils.MAX_DICT
		print('max data', self.max_data)

	def _matchAdd(self, pos):
		i = -1 if pos not in self.matches else self.matches.index(pos)
		if i>=0:
			self.matches.pop(i)

		self.matches.insert(0, pos)
		if len(self.matches) > MAX_MATCHES:
			self.matches = self.matches[:MAX_MATCHES]

	def _dataAdd(self, b):
		self.data += b
		# trim to only the lasts one
		dif = len(self.data) - self.max_data
		if dif>0 :
			self.data = self.data[dif:]
			# note we let the matches go below 0, to avoid a problem when the compress and decompress uses different sized data buffers
			self.matches = [i-dif for i in self.matches]
