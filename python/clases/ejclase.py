class Tiempo:
	h = 0
	m = 0
	s = 0
	ms = 0
	def __init__(self, h=0, m=0, s=0, ms=0):
		self.h = h
		self.m = m
		self.s = s
		self.ms = ms

	def __str__(self):
		return "{} horas {} minutos {} segundos".format(self.h, self.m, self.s, self.ms)
	
	def __int__(self):
		minutos = (self.h *60)  + self.m
		segundos = (minutos *60) + self.s
		ms = (segundos*1000) + self.ms
		return ms
	
	def __lt__(self, other):
		return int(self)<int(other)
	
	def __eq__(self, other):
		return int(self)==int(other)
	
	def __add__(self, other):
		res = Tiempo()
		ms = int(self)+int(other)
		s, res.ms = divmod(ms, 1000)
		m, res.s = divmod(s, 60)
		h, res.m = divmod(m, 60)
		d, res.h = divmod(h, 24)
		if(d>0): print("There has been an overflow!")
		return res
	
hora1 = Tiempo(10, 30, 42)
hora2 = Tiempo()
hora3 = Tiempo()

print (hora1)
print ("Son las "+str(hora1))

print (int(hora1))
print (hora1<hora2)
print (hora2<hora1)
print (hora1>hora2)

"""
Traceback (most recent call last):
  File "ejclase.py", line 27, in <module>
    print (hora1<hora2)
TypeError: unorderable types: Tiempo() < Tiempo()
"""

print (hora3 == hora2)

res = hora1 + Tiempo(9, 10, 30)
print(res)
print(hora1)


#Ej2

import math
class Circulo:
	rad = 0
	def __init__(self, rad=0):
		print("circulo ini")
		self.rad = rad
	def area(self):
		print("area circulo")
		return (self.rad**2)*math.pi
	def per(self):
		print("perimetro circulo")
		return self.rad*2*math.pi

class Esfera(Circulo):
	def area(self):
		print("area esfera")
		return 4*super().area()
	
	def volumen(self):
		print("volumen esfera")
		return 4*self.area()
		#return ((self.rad**3)*math.pi*4)/3.0#4188.790204786391

class Cilindro(Circulo):
	altura = 0
	def __init__(self, radio =0, altura=0):
		super(Cilindro, self).__init__(radio)
		self.altura = altura
		
	def area(self):
		print("area cilindro")
		ac = super().area()
		return (2*ac)+(self.per()*self.altura)
	
	def volumen(self):
		print("volumen cilindro")
		return self.altura*super().area()
	
c = Circulo(10)
print(c.area())
print(c.per())

es = Esfera(10)
print(es.area())
print(es.volumen())

ci = Cilindro(10, 10)
print(ci.area())
print(ci.volumen())
