#-*-coding:utf8;-*-
__author__ = "Jeronimo Barraco-Marmol"
__copyright__ = "Copyright 2022, Jeronimo Barraco-Marmol"
__license__ = "AGPLv1"

import sys

import lzmj_dec, lzmj_enc

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
		enc = lzmj_enc.LZMJ22(fname, ofname) #
		enc.Encode()
	elif op == "x":
		dec = lzmj_dec.LZMJ22Dec(fname, ofname)
		dec.Decode()

if __name__ == "__main__":
	main()