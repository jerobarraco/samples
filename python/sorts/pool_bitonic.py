#!/bin/python
#coding: utf-8
__author__ = "Jeronimo Barraco-Marmol"
__licence__ = "AGPL v3"
"""
	Simple test of an idea. of a bitonic sort using pool and as much functional as i can. for the lulz. no fancy stuff.
	the overhead of the pool will probably make it much slower in total/overall. 
	but the paralellization might make it faster relative to the wallclock. (not that i care. for speed should use assembly)
"""


import random
import multiprocessing as mp

def cell(a, b, d):
	swap_less = a<b
	swap_great = b<a
	swap = (swap_great, swap_less)[d]
	r = b if swap else a
	print(a,b,d,r)
	return r

def flipFlop():
	while True:
		yield 0
		yield 1

def idxs(s, e):
	n2 = s+1
	while s<e:
		yield s, n2, 0
		yield s, n2, 1
		s += 2
		n2 = (s+1)%e

def repeat(p):
	while True:
		yield p

def boundSort(la, lb, odd):
	l, l2 = ((la, lb), (lb, la))[odd]
	offs = ((1, -1), (-1, 1))
	ooffs = offs[odd]
	#cmp = ((0, 1), (1, 0))[odd]
	ln = len(l)
	def sort(i):
		mod = i%2
		meoff = ooffs[mod]
		o = ((i + meoff) + ln) % ln
		li = l[i]
		lo = l[o]
		changed = (li>lo) if (i<o) else (lo>li)
		# ca = (li, lo)[cmp[mod]]
		# cb = (lo, li)[cmp[mod]]
		# changed = ca > cb

		l2[i] = lo if changed else li
		return changed

	return sort

def main2():
	nums = list(range(10))
	indexes = nums[:]
	random.shuffle(nums)
	nums2 = nums[:]

	do = True
	odds = (1, 0)
	odd = 0
	i = 0
	bsorts = (boundSort(nums, nums2, 0), boundSort(nums2, nums, 1))
	while do:
		bsort = boundSort(nums, nums2, odd)
		r = list(map(bsort, indexes))
		do = any(r)
		odd = odds[odd]
		i+=1
	print(nums, i)

def main():
	la = list(range(10))
	li = la[:]# index
	random.shuffle(la)
	lb = la[:]
	ln = len(la)
	print(la)
	f = flipFlop()
	for a, f1 in zip(range(ln), f):
		print(la, f1)
		la = [
			cell(la[i], la[i2], d)
			for i, i2, d in idxs(f1, ln)
		]
	print(lb)
	print(ln)

if __name__ == "__main__":
	main()
