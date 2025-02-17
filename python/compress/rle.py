#-*-coding:utf8;-*-
#qpy:console
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import utils

def SRFile(fname):
	# in fname out 'c'...
	f = open(fname, 'rb')
	while True:
		c = f.read(1)
		if not c: break
		yield c

def SRBin(chars):
	# in 'c'... out '10101010'...
	for c in chars:
		cord = ord(c)
		cbin = bin(cord)
		#2: removes 0b prefix
		cbits = cbin[2:].zfill(8)
		yield cbits

# nice but very inefficient
def SChar(strg):
	# in 'abc...x' out 'a'...
	for s in strg:
		for c in s:
			yield c

def SRDelta(bits):
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

def SRRLEG(gen):
	# very naive rle just to test stuff
	count = 0
	last = None
	for i in gen:
		if last == i:
			count +=1
			continue
		yield str(i)+'x'+str(count) + ' '
		last = i
		count = 1

def RLE0(l, nbits=2):
	# with constant bit size
	#tested the sweetspot is 2 bits
	if l <= 0: # error
		return None
	res = ''
	opt=2
	maxl = (2**nbits)-1+opt #-1 maxval, +3 optim below
	while l>0:
		if l < opt: # optimization. 2 000 is shorter than 001000
			res+= '000'
			l-=1
			continue
		count = min(maxl, l)
		l-= count
		#-3 opt above
		bcount = bin(count-opt)[2:].zfill(nbits)#
		bres = '001' + bcount
		res += bres
	return res

def RLE2(num):
	# using lmzc from wikipedia
	# has fixed slots with different bit sizes
	if num <=0 : return None
	num-=1
	return '00' + utils.Num_LZM(num, 1, 2, 3)

def RLE1(l):
	# with variable count bitsize
	if l <= 0: # error
		return None
	# 1 is 00 1, 2 = 00 010, 3= 00 011, 4= 00 00100
	l-=1 # starts at 1
	return '00'+ utils.Num_JMan(l) # jman always start with 0. so warranties is mot 001

def RLD1(bits):
	# to be reused by lzmj
	c = next(bits, None)
	if c is None: return 0
	r = 0
	bitl = 0
	res = '0b1'
	while c is not None:
		if c == '1':
			break
		bitl += 1
		c = next(bits, None)

	for i in range(bitl):
		c = next(bits, None)
		if c is None: break
		res +=c
	return int(res, 2)

def SRRLE(gen):
	count = 0
	m = 0
	prev = None # todo rename last to cur
	while True:
		lasta = next(gen, None)
		lastb = next(gen, None)
		if lasta == None: return
		if lastb == None : lastb = '0' #pad
		last = lasta+lastb

		if last != '00':
			rle = RLE0(count)
			if rle is not None:
				m = max(m, count)
				if count >3: print(m, count, rle)
				yield rle
			# yield ' '
			yield last
			#yield ' '
			count = 0
			# dont rle non 0
			continue
		count +=1

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

def SWFile(fname, gen):
	f = open(fname, 'wb')
	for i in gen:
		f.write(i)
		yield i #TODO DEBUG, REMOVE

def encode(fname):
	chars = SRFile(fname)
	bins = SRBin(chars)
	bits = utils.SItem(bins)
	delta = SRDelta(bits)
	rle = SRRLE(delta)#bits)

	# out
	ochunks = SChunk(rle, 8)#bits)#rle)
	obytes = SByte(ochunks)
	ofile = SWFile('out.txt', obytes)
	it = ofile
	for o in it:
		p(o)

def p(o):
	#sys.stdout.write(str(o))
	pass

def main():
	for i in range(1, 33):
		rle = RLE1(i)
		rld = RLD1(iter(rle))
		print( i, '\t', rle, '\n',
			 rld, '\t', RLE0(i), '\n',
			i, '\t', RLE2(i))
	#return
	fname0 = '/home/nande/work/repos/samples/python/compress/compnotes.txt'
	fname ='/storage/emulated/0/.sstmp'
	fname2='/storage/emulated/0/Download/1366px-Competence_Hierarchy_adapted_from_Noel_Burch_by_Igor_Kokcharov.svg.png'
	fname3='/storage/emulated/0/Download/14 inner critic - 1 sarah.mp3'
	fname4='/storage/emulated/0/qpython/Ideas.txt'
	encode(fname0)

if __name__ == "__main__":
	main()