import math
#lechuga tomate papas zapallitos y huevo
@micropython.bytecode
def time (f, *args):
    ta=pyb.millis()
    r = f(*args)
    tb =pyb.millis()
    tf = tb-ta
    print('lasted %sms'%tf)
    return r

@micropython.native

import math
me = math.e
c = 299792458
def fft(x):
    n = len(x)
    if n < 2: return x
    k = -2j*math.pi/n
    other = ( me**(k*s)*o for s, o in enumerate(fft(x[1::2])) )
    data = tuple(zip( fft(x[::2]), other ))
    return tuple(e+o for e, o in data) + tuple( e-o for e, o in data)


a=fft(list(range(4)))
    #for e, o in data: yield e + o
    #for e, o in data: yield e - o
		
def fft(x):
    n = len(x)
    if n <= 1: return x
    even = fft([x[i] for i in range(0,n,2)])
    odd = fft([x[i] for i in range(1,n,2)])
    c = lambda m: math.e**(-2j*math.pi*m/n)*odd[m]
    return [even[m] + c(m) for m in range(int(n/2))] + [even[m] - c(m) for m in range(int(n/2))]
	
	
def xx(x):
	if len(x)<2:return x
	a = xx(x[::2])
	b = xx(x[1::2])
	for i in a: yield i
	for i in b: yield b