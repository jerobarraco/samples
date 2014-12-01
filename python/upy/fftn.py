import math
me = math.e
def fft(x):
    n = len(x)
    if n < 2: return x
    k = -2j*math.pi/n
    other = ( me**(k*s)*o for s, o in enumerate(fft(x[1::2])) )
    data = tuple(zip( fft(x[::2]), other ))
    return tuple(e+o for e, o in data) + tuple( e-o for e, o in data)

a=fft(list(range(4)))
