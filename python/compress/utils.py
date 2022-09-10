#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import sys

POINTER_MIN_LEN = 2
# TODO make a class to store all this lzma stuff
USE_LZMA = True
MAX_DICT = 1024**2
# going above 3 bits on the 1st level, and 7 on the 2nd and 3rd will result in compressions with no saving
LZM_BOUNDS = (3,7,7) # gave the best compression
#LZM_BOUNDS = (3,5,7)
#LZM_BOUNDS = (4,4,8)

#TODO modify the S functions to use Deques, so that they can be run in different threads.

class Packets:
	LITERAL = "0"
	POINT = "10"
	SHORT_REP = "1100"
	LONG_REP_0 = "1101"
	LONG_REP_1 = "1110"
	LONG_REP_2 = "11110"
	EOF = "11111"

	L_LITERAL = len(LITERAL)
	L_POINT = len(POINT)
	L_SHORT_REP = len(SHORT_REP)
	L_LONG_REP_0 = len(LONG_REP_0)
	L_LONG_REP_1 = len(LONG_REP_1)
	L_LONG_REP_2 = len(LONG_REP_2)
	L_EOF = len(EOF)

def getMaxData():
	return min(MAX_DICT, Num_LZM_Max() if USE_LZMA else MAX_DICT)

def p(o):
	#sys.stdout.write(str(o))
	pass

def Num(n):
	return Num_LZM(n) if USE_LZMA else Num_JMan(n)

# highly important that the generator yeilds ONE bit at a time
def SNumDec(bins):
	return SNum_LZM_Dec(bins) if USE_LZMA else SNum_JMan_Dec(bins)

def SNum_JMan_Dec(bins):
	# TODO fix this, or the enc
	cur = ""
	bitc = 0
	while cur != "1":
		cur = next(bins, None)
		if cur is None: break
		if cur == "1":
			break
		bitc += 1

	buff = "1"
	cur = Chunk(bins, bitc, "")
	if cur is not None:
		buff += cur

	num = Int(buff)
	num -= 1 # compensate encoder shifting to be able to encode a 0
	return num

def Num_JMan(num):
	"""this one is good for small numbers"""
	# 0 based. it uses very few bits for the small ones, but large bits for large ones, also has no lenght limit
	if num < 0 :
		print ('No!')
		return ''

	num += 1 # handles the fact we can´t encode a 0
	if num == 1: return '1'

	# pseudo huffman to be used by lzmj
	bincount = bin(num)[2:] #it always starts with one. well seize that
	bitlen = '0'*(len(bincount)-1) # don't count the 1
	return bitlen + bincount

def Num_LZM_Maxes():
	# TODO make this a loop or something
	maxes = [2**i for i in LZM_BOUNDS]
	# 0 [0]+[1]
	bases = [sum(maxes[:i]) for i in range(len(maxes)+1)]
	bounds = [sum(maxes[:i+1]) for i in range(len(maxes))]
	return maxes, bases, bounds

def Num_LZM_Max():
	maxes, bases, bounds = Num_LZM_Maxes()
	bo = sum(maxes)
	return bo-1

def SNum_LZM_Dec(bins):
	buff = ""
	num = 0
	cur = next(bins, None)
	if cur is None: return num

	lvl = 0
	buff += cur
	if buff != "0":
		# read 10
		cur = next(bins, None)
		if cur is None: return num
		buff += cur
		lvl = 1 if buff == "10" else 2#10 or 11

	bitc = LZM_BOUNDS[lvl]
	maxs, bases, bounds = Num_LZM_Maxes()
	cur = Chunk(bins, bitc, "")
	if cur is None:
		return 0

	num = Int(cur)
	num += bases[lvl]
	return num

def Num_LZM(l):
	"""this one is good for big numbers"""
	# like in lzm
	if l < 0: # error
		return None
	maxes, bases, bounds = Num_LZM_Maxes()

	bb = bounds[0]
	bc = bounds[1]
	bo = bounds[2]
	res = ''
	bitl =1
	lvl = 0
	if l<bb:
		res ='0'
		lvl = 0
	elif l<bc:
		l -= bb
		res ='10'
		lvl = 1
	elif l<bo:
		l-= bc
		res ='11'
		lvl = 2
	else:
		print('lzm num overflow')
		return None

	bitl = LZM_BOUNDS[lvl]
	res += bin(l)[2:].zfill(bitl)
	return res

def SureByte(b):
	# todo use
	"ensures a byte is a byte (avoids issues when slicing). also compatible with strings (on purpose)"
	return Int2Byte(b) if isinstance(b, int) else b

def Bin(c, pad=8): # todo rename ToBin
	"""encodes a char or int to a binary with a specific size (min), can overflow"""
	if isinstance(c, int):
		cord = c
	else:
		cord = ord(c)
	cbin = bin(cord)
	#2: removes 0b prefix
	cbits = cbin[2:].zfill(pad)
	return cbits

def SBin(chars):
	# in 'c'... out '10101010'...
	return (Bin(c) for c in chars)
	#for c in chars:
	#	cbits = Bin(c)
	#	yield cbits

def SDelta(bits):
	# creates a delta encoding
	last = next(bits, None)
	if last is None: return

	yield last
	for b in bits:
		if b == last:
			yield '0'
			continue
		last = b
		yield '1'


# Dangerous function
def Chunk(gen, size=8, pad=""):
	# this NEEDS to be sure that all elements are of size one, otherwise a buffer will be lost
	return next(SChunk(gen, size, pad), None)

def SChunk(gen, size=8, pad=""):
	# returns chunks for a given iterator
	buff = '' # i know there's stream buffer but overkill
	for i in gen:
		buff += i
		while len(buff) >= size: #i might contain more than 1 char
			ret = buff[:size]
			yield ret
			buff = buff[size:] #prolly a better way with pack or smth
	# at this point the generator has nothing, so flush the buffer
	buffLen = len(buff)
	if buffLen > 0:
		buff += pad*(size-buffLen)
		yield buff
	print("extra buff", buff)

def Int(str_bin):# TODO Rename Bin2Int
	"Returns an int from a string of bits, try to ensure they are 8 digits at most"
	return int('0b' + str_bin, 2)

def Int2Byte(num):
	"Returns a byte from an int, make sure the int fits on a byte"
	return num.to_bytes(1, 'big')

def Byte(str_bin):# TODO rename Bin2Byte
	# turns a string of bits into a byte, must be 8 bits long
	return Int2Byte(Int(str_bin))

def SByte(str_bytes):# TODO rename SBin2Byte
	# turns a string of bits into a byte, must be 8 bits long
	return (Byte(b) for b in str_bytes)
	#for b in str_bytes:
	#	yield Byte(b)

def SItem(strg): # somewhat opposite to chunk
	"""in 'abc...x' out 'a'... Careful, it's inefficient"""
	# harder to debug and to read
	#s = (s for s in strg)
	#return (SureByte(c) for c in s)
	for s in strg:
		for c in s:
			# preserve type, fix iterating bytes returning an int
			yield SureByte(c)

def SRFile(fname):
	"""Reads from a file byte by byte"""
	f = open(fname, 'rb')
	while True:
		c = f.read(1)
		if c is None or len(c) == 0: break
		yield c
	f.close()

def SWFile(fname, sbytes):
	"""Reads a sequence of bytes and writes to a file, yields each byte"""
	f = open(fname, 'wb')
	for i in sbytes:
		f.write(i)
		yield i
	f.close()