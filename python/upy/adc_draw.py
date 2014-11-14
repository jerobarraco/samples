import pyb
lcd = pyb.LCD('X')
lcd.light(True)


adc = pyb.ADC(pyb.Pin('Y11'))
sw = pyb.Switch()
x = 0
lcd.write('Hello uPy!\n')
while not sw():
    r = adc.read()*0.007326007#30/4095# read value, 0-4095
    for i in range(30):
        lcd.pixel(x, i, i<r)# 1 if i<r else 0)
    for i in range(1, 2):
        for j in range(30):
            lcd.pixel(x+i, j, 0)
    lcd.show()
    x +=1
    if x>127: x = 0
    #print (x, r)
    pyb.delay(100)

lcd.light(False)