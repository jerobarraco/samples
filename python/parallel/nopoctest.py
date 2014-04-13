#coding:utf-8
import numpy
import math
#numpy.arange([start], stop[, step], dtype=None)
#vector = numpy.arrange(0, 10000, 1, cl.array.vec.float4)
vector = numpy.zeros(1000000, numpy.float32)
for i in xrange(vector.size):
	vector[i] = math.sin(i*0.00000314)
print(vector)
"""
import visvis as vv
app = vv.use()
f = vv.clf()
a = vv.cla()
legends = []
vv.plot(numpy.arange(0, 1, 0.00001), vector, lc='c', mc='c', ms='.', ls='', mw=1,lw=0)
vv.title("Lol")
app.Run()"""