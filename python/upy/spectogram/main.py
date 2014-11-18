import pyb
import math
from log import log

lcd = pyb.LCD('X')
lcd.light(True)
def l(p): log(p, lcd)

l("Hello Python")

from fft import fft 
l("fft ready")

adc = pyb.ADC(pyb.Pin('Y11'))
l("adc ready")

sw = pyb.Switch()
l("switch ready")
x = 0
CANT = 64
HALF = int(CANT/2.0)-1
FREQ = 4800 #44800
VPOINTS = 32
MAXVAL = 0.001
HPOINTS = int(130/HALF)
MAXIN = 500.0
OFF=-30
SCALE = VPOINTS/(CANT*MAXVAL)#0.007326007#30/4095# read value, 0-4095
SCALEIN = VPOINTS/MAXIN
M = int(math.log2(CANT))
buf = bytearray(CANT)#creates buffer

def doScale(vec):
    return [OFF+(i*SCALEIN) for i in vec]

while not sw():
    adc.read_timed(buf, FREQ) #44100)#read at 2200hz
    cof = doScale(buf)
    #cof = list(buf)
    fft(cof, CANT, M, True, True)
    #lcd.fill(0)
    for xi, i in enumerate(cof[1:HALF]):
        r = abs(i)*SCALE
        for j in range(VPOINTS):
            for x in range(0, HPOINTS):
                lcd.pixel((xi*HPOINTS)+x, j, (VPOINTS-j)<r)# 1 if i<r else 0)
    lcd.show()
    #pyb.delay(50)
lcd.light(False)