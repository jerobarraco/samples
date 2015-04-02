import pyb
import mpr
import ffft
lcd = pyb.LCD('X')
lcd.light(True)
lcd.write('Hello uPy!\n')
lcd.write("Mpr imported\n")
m = mpr.MPR121(pyb.I2C(1, pyb.I2C.MASTER))
THUP = 20
THDW = 125#256 max
#m.threshold(0, THDW, THUP)
#m.threshold(1, THDW, THUP)
#m.threshold(2, THDW, THUP)
#m.threshold(3, THDW, THUP)
m.debounce(7, 7)#7 is max

lcd.write("fft imported\n")

adc = pyb.ADC('Y11')
sw = pyb.Switch()


def printValuesOnScreen(values, offset=0):
   x = 0
   xcoord = offset
   while xcoord < 200:
      #print(x)
      if x < len(values):
         #print(values[x]/10)
         xval = 0
         for i in range(0,1):
            xval += int(values[x+i]/10)
         lcd.pixel(xcoord,27 - int(xval) ,1)
         #lcd.pixel(xcoord,54 - int(xval),1)
      lcd.pixel(xcoord,15,1)
      x+=1
      xcoord += 1
   lcd.show()

def printDFFTOnScreen(values, width=30):
   x = 0
   xcoord = 0
   while xcoord < width:
      #print(x)
      if x < len(values):
         #print(values[x]/10)
         xval = 0
         for i in range(0,1):
            xval += int(values[x+i]/5)
         #lcd.pixel(xcoord,27 - int(xval) ,1)
         for y in range(int(xval)):
            lcd.pixel(xcoord,30 - y,1)
      lcd.pixel(xcoord,30,1)
      x+=1
      xcoord += 1
   lcd.show()
   
def run():
	buf = bytearray(200)
	while not sw():
		adc.read_timed(buf, 640000)
		lcd.fill(0)
		coefs = [buf[i] for i in range(0,180,6)]
		fft.fft(coefs)
		arr = list(map(abs, coefs))
		#print(arr)
		printDFFTOnScreen(arr)
		printValuesOnScreen(buf, 30)