#!/bin/python
#coding: utf-8
__author__ = "Jeronimo Barraco-Marmol"


"""
Alternative version of "The sieve of Eratosthenes"
based of code by Professor Thorsten Altenkirch from video from Computerphile
 https://www.youtube.com/watch?v=5jwV3zxXc8E
  Laziness in Python - Computerphile 
"""

def nats(n):# looks like range to me...
	while True:
		yield n
		n+=1

def sieve(s):
	v = next(s)
	yield v

	yield from sieve((i for i in s if i % v!=0))
	#this also works but for an unexpected reason the maximum recursion limit is reached 10 or 100 times before
	# while True:
	#	s = (i for i in s if i%v!=0)
	#	v = next(s)
	#	yield v

def main():
	for i in sieve(nats(2)):
		print(i)

if __name__ == "__main__":
	main()
