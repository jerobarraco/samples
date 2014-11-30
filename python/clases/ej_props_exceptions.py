"""
Ejercicios de properties y exceptions:

Cree una copia de los Ejercicios anteriores ( clases Tiempo, Cilindro, Circulo y Esfera) (no recomiendo modificarlos diréctamente a menos que le gusten las cefaleas).
1) Modifique la clase Tiempo para que lance excepciones*1 en caso de:
	A) La suma de dos Tiempos resulte en un Tiempo con más de 24 horas.
	B) Se intente sumar un Tiempo a otro elemento que no sea un Tiempo.
		Protip: Utilice "isinstance" y luego tome un baño para quitarse la suciedad de la atrocidad cometida.
	*1 Protip: Utilice la excepción "Exception" para lanzar excepciones. (pej 'raise Exception ("Hola")' ).

2) 
	A) Modifique la clase Circulo para que sus atributos sean "privados", e implemente las propiedades (property) necesarias para leer/asignar su radio, área y perímetro. De manera que se realicen los cálculos (pej de área y perímetro) solo al cambiar su radio. 
	Protip: reutilice estos atributos en el constructor de la clase.
	B) Haga lo mismo para las clases Esfera y Cilindro. Agregando lo que sea necesario en cada caso.
	C) Modifique la clase Cilindro para que lance una excepción en caso de que se le intente asignar un radio negativo o cero. Lo mismo para las clases Esfera y Cilindro en caso de ser necesario.

3) En el algoritmo que utiliza las clases del punto 2 (que permite al usuario cargar varias instancias). Realice la captura de dichas excepciones, y muestre un mensaje al usuario. 
	Extra: En caso que haya creado una función que para la creación de cada instancia (anotese un punto extra y) examine la diferencia entre dónde se realiza la captura de la excepción  (Si dentro de cada función o en el bucle principal).
	ProTip : 'try: except Excetion, e:'
	
	Extra++: Modifique el algoritmo para que la carga termine al capturar la excepción del tipo "KeyboardInterrupt". (Que es lanzada al presionar CTRL+C )
	
	ProTip: puede obtener el "mensaje" de cada excepción si se la convierte a string "str(e)".
	
Extra 4) Si se siente con confianza implemente una excepción personalizada para cada uno de los errores en el ejercicio. 
	En este caso muestre un mensaje alusivo más detallado al capturar cada excepción.
	
	ProTip Recuerde que una excepción es solo una clase que hereda de Exception. puede implementar sus propios atributos y/o mensajes de error. 
	(Pasando el mensaje al constructor de la clase padre o implementando su propio __str__).
	ProTip Recuerde que para capturar una excepción se debe indicar el tipo de la excepcion. Note que pasa si solo indica la excepción "Exception".
"""
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
	
	#el __int__ termina siendo muy importante, al implementarlo me permite usar int(x) en __lt__ y __eq__
	#y de esa manera poder comparar con cualquier tipo de dato convertible a entero
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
		if not isinstance(other, Tiempo):
			raise Excetion ("Can add Tiempo to "+str(type(other)))
		res = Tiempo()
		ms = int(self)+int(other)
		s, res.ms = divmod(ms, 1000)
		m, res.s = divmod(s, 60)
		h, res.m = divmod(m, 60)
		d, res.h = divmod(h, 24)
		if(d>0): 
			raise Excetpion ("There has been an overflow!")
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

class Circulo2:
	__area = 0
	__rad = 0
	__per = 0
	def __init__(self, r=0):
		self.radio = r 
		#al utilizar el property para asignar el valor pasan cosas muy interesantes
		#1) se "hereda el comportamiento" de __setRad (incluyendo verificaciones y excepciones)
		
	def __setRad(self, r):
		print("soy el setRad de Circulo2")
		if r<=0 : raise Exception("El radio debe ser mayor a 0")
		self.__rad = r
		self.__per = 6.28*r
		self.__area = 3.14*r*r
		
	def __getRad(self):
		return self.__rad
	
	radio = property(__getRad, __setRad)
	
	def __getPer(self):
		return self.__per
	
	perimetro = property(__getPer)
	
	def __getArea(self):
		return self.__area
	
	area = property(fget=__getArea)
	
class Esfera2(Circulo2):
	__vol = 0
	#En el caso de la esfera el area es diferente a la del círculo. es 4 veces el area del circulo. 
	#Para recalcular al momento de asignar se deben tener en cuenta varias cosas
	#primero podemos redefinir la property radio
	#radio = property()
	#al redefinir self.radio el __init__ de la clase CIRCULO utilizará este property y no el definido por la clase Circulo2 (que se sobreescribirá por ende, se perderá)
	#esto es similar a las funciones virtuales y polimorfismo de c++
	#El getter lo podemos reutilizar, y acá comienza lo interesante. como utilizamos nombres protegidos (pej "__rad") python en realidad renombra los atributos a:
	#_Clase__atributo ( o sea que __rad en realidad es _Circulo2__rad.
	#Si nosotros asignaramos __rad en realidad estaríamos creando otro atributo llamado _Esfera2__rad, y el getter intentaría leer _Circulo2__rad
	#lo mismo pasa con los getters y setters de la clase Circulo2
	#Si podemos crear un nuevo setter, que reutilice el setter de la clase padre
	
	def __setRad(self, r):
		#notar el nombre de la funcion
		#a pesar que la funcion reescribe __per y __area a un valor incorrecto para Esfera2, y que es ineficiente, suele ser un comportamiento mas aceptable que 
		#arriesgarse a tener errores, y vale la pena la reutilizacion de comportamiento
		self._Circulo2__setRad(r)#notar que esto lanzará una excepcion
		self.__vol = self._Circulo2__area = 4*self._Circulo2__area 
		
	radio = property(Circulo2._Circulo2__getRad, __setRad)#notar los nombres
	#notar tambien que _Circulo2__getRad intentara leer _Circulo2__rad
	def __getVol(self): return self.__vol
	volumen = property(__getVol)

c = Circulo2(10)
print(c.area)
print(c.perimetro)

es = Esfera2(10)
print(es.area)
print(es.volumen)
print(es.perimetro)

es = Esfera2(0)
print(es.area)
print(es.volumen)
print(es.perimetro)