 
from ctypes import *
i = c_int(0)
a = c_int(0)
one = c_int(1)
m = c_int(1000000000)

while (i<m):
	a = a +one
	i = i +one