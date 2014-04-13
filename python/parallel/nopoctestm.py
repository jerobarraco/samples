#coding:utf-8

import math


def f(x):
	return math.sin(x*0.00000314)

#numpy.arange([start], stop[, step], dtype=None)
#vector = numpy.arrange(0, 10000, 1, cl.array.vec.float4)
def go():
	from multiprocessing import Pool
	#import numpy
	#vector = numpy.zeros(10000000, numpy.float32)
	vector = list(range(10000000))
	p = Pool(processes=8)
	#p.map(f, vector)
	#print(vector)
	result = p.map_async(f, vector)    # evaluate "f(10)" asynchronously
	r= result.get(timeout=10)
	print "result got me ", r[:10]
	
def go2():
	from multiprocessing import Pool
	#import numpy
	#vector = numpy.zeros(10000000, numpy.float32)
	vector = list(range(10000000))
	#p = Pool(processes=8)
	#p.map(f, vector)
	#print(vector)
	#result = p.map_async(f, vector)    # evaluate "f(10)" asynchronously
	#r= result.get(timeout=10)
	r = map(f, vector)
	print "result got me ", r[:10]
"""
import visvis as vv
app = vv.use()
f = vv.clf()
a = vv.cla()
legends = []
vv.plot(numpy.arange(0, 1, 0.00001), vector, lc='c', mc='c', ms='.', ls='', mw=1,lw=0)
vv.title("Lol")
app.Run()"""