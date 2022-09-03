#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import sys

import utils

USE_LZMA = False
MAX_DICT = 1024**1
LZM_BOUNDS = (3,4,8)
#LZM_BOUNDS = (4,8,14)
class LZMJ22:
	# 22 because it's 2022, but also LZ77, domination 88, and galaxy express 99?
	fname = ""
	ofname = ""

	max_data = min(MAX_DICT, USE_LZMA and utils.Num_LZM_Max(*LZM_BOUNDS) or MAX_DICT)
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
			p(o)
			p("\n")

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
							print("Reached end of stream!")
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

		binOff = USE_LZMA and utils.Num_LZM(off, *LZM_BOUNDS) or utils.Num_JMan(off)
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
			if not self.nexts:
				b = next(sbytes, None)
				if b is None:
					print ("were done")
					break

				self.nexts += b

			n = self.nexts[0].to_bytes(1, "big") # ensure is a byte not an int
			self.nexts = self.nexts[1:]
			# try the different command in order of priority
			for f in [self._Pointer, self._ShortRep, self._Literal]:
				val = f(n, sbytes)
				if val is not None:
					yield val
					break


class LZMJ22Dec:
	ifname = ""
	ofname = ""
	data = b""
	def __init__(self, ifname, ofname):
		self.ifname = ifname
		self.ofname = ofname

	def Decode(self):
		chars = utils.SRFile(self.ifname)
		bins = utils.SBin(chars)
		#sbits = utils.SItem(bins)

		sbytes = self._SDec(bins)
		sproc = self._SDecProc(sbytes)
		obyte = utils.SItem(sproc)

		ofile = utils.SWFile(self.ofname, obyte)

		it = ofile
		# force processing
		for o in it:
			p(o)
			p("\n")

	def _SDecProc(self, decs):
		for d in decs:
			if decs is None : return
			self.data += d
			yield d

	def _SDec(self, bins):
		buff = ""
		for b in bins:
			buff += b
			if len(buff) < 9: continue

			if buff[0] == "0":
				bits = buff[1:9]
				buff = buff[9:]
				byte = utils.Byte(bits)
				yield byte
				continue

			# pointer, 10
			if buff[:2] == "10":
				buff = buff[2:]
				yield self._Pointer(buff, bins)
				continue

			# shortrep, 110
			if buff[:3] == "110":
				buff = buff[3:]
				yield self._ShortRep(buff, bins)
				continue

	def _Pointer(self, buff, bins):
		return b""
	def _ShortRep(self, buff, bins):
		if len(self.data)<1: return b""
		return self.data[-1].to_bytes(1, "big")

	# ill take a small break now
def p(o):
	#sys.stdout.write(str(o))
	pass

def main():
	fname = ""
	op = "x"
	if len(sys.argv) < 3:
		fname0 = '/home/nande/work/repos/samples/python/compress/compnotes.txt'
		fname ='/storage/emulated/0/.sstmp'
		fname2='/storage/emulated/0/Download/1366px-Competence_Hierarchy_adapted_from_Noel_Burch_by_Igor_Kokcharov.svg.png'
		fname3='/storage/emulated/0/Download/14 inner critic - 1 sarah.mp3'
		fname4='/storage/emulated/0/qpython/Ideas.txt'
		fname = op == "x" and "out.txt" or fname0
	else:
		op = sys.argv[1].lower()
		fname = sys.argv[2]

	if len(sys.argv) < 4:
		ofname = op == "x" and "out_dec.txt" or "out.txt"
	else:
		ofname = sys.argv[3]

	if op == "a":
		enc = LZMJ22(fname, ofname) #
		enc.Encode()
	elif op == "x":
		dec = LZMJ22Dec(fname, ofname)
		dec.Decode()

if __name__ == "__main__":
	main()