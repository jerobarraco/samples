import pyb
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
CANT = 512
HALF = int(CANT/2.0)-1
FREQ = 44800
VPOINTS = 30
MAXVAL = 1024.0
SCALE = VPOINTS/MAXVAL#0.007326007#30/4095# read value, 0-4095
buf = bytearray(CANT)#creates buffer
while not sw():
    adc.read_timed(buf, FREQ) #44100)#read at 2200hz
    cof = list(buf)
    fft(cof)
    #lcd.fill(0)
    for x, i in enumerate(cof[1:HALF]):
        r = abs(i)*SCALE
        for j in range(VPOINTS):
            lcd.pixel(x, j, (VPOINTS-j)<r)# 1 if i<r else 0)
    lcd.show()
    #pyb.delay(50)
lcd.light(False)