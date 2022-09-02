#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

def Num_JMan(num):
	# undefined for 0 and 1. always start with 0
	# it uses very few bits for the small ones, but large bits for large ones, also has no lenght limit
	if num < 0 :
		print ('No!')
		return ''

	if num == 0: return '0'
	if num == 1: return '1'

	# pseudo huffman to be used by lzmj
	bincount = bin(num)[2:] #it always starts with one. well seize that
	bitlen = '0'*(len(bincount)-1) # don't count the 1
	return bitlen + bincount

def Num_LZM(l, bits_a = 2, bits_b = 3, bits_c = 8):
	# like in lzm
	if l <= 0: # error
		return None

	l-=1
	m2 = (2**bits_a)
	m3 = (2**bits_b)
	m8 = (2**bits_c)
	b3 = m2
	b8 = m2+m3
	bo = m2+m3+m8
	res = ''
	bitl =1
	if l<b3:
		res ='0'
		bitl = bits_a
	elif l<b8:
		l -= b3
		res ='10'
		bitl=bits_b
	elif l<bo:
		l-= b8
		res ='11'
		bitl=bits_c
	else:
		print('overflow')
		bitl = 0

	res+=bin(l)[2:].zfill(bitl)
	return res

def SChunk(gen, size=8):
	# returns chunks for a given iterator
	buff = '' # i know there's stream buffer but overkill
	for i in gen:
		buff += i
		if len(buff) >= size: #i might contain more than 1 char
			ret = buff[:size]
			yield ret
			buff = buff[size:] #prolly a better way with pack or smth

def SByte(str_bytes):
	for b in str_bytes:
		byte = int('0b'+b, 2)
		yield byte.to_bytes(1,'big')

def SChar(strg):
	"""in 'abc...x' out 'a'... Careful, it's inefficient"""
	for s in strg:
		for c in s:
			yield c

def SRFile(fname):
	"""Reads from a file byte by byte"""
	f = open(fname, 'rb')
	while True:
		c = f.read(1)
		if not c: break
		yield c

def SWFile(fname, sbytes):
	"""Reads a sequence of bytes and writes to a file, yields each byte"""
	f = open(fname, 'wb')
	for i in sbytes:
		f.write(i)
		yield