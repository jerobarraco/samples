# -*- coding: utf-8 -*-
"""
    Copyright: Jerónimo Barraco Mármol 2010
    email : [ JeroBarraco arroba yahoo dot com dot ar ] or [ Nande arroba Nande dot com dot ar]
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.


This library contains mostly the stuff we do at the university.
The code is not intended to be efficient or "new" (in the python world)
but to be a reminder or example of how the formulas and techniques are used.
Undocumented stuff is probably not meant to be used outside the library.

Esta biblioteca contiene mayormente las cosas que hacemos en la universidad.
La idea no es que el codigo sea eficiente o algo "nuevo" (en el mundo de python)
sino que ayuden a entender y recordar como usamos las formulas y tecnicas
Las cosas sin documentacion no se supone que se usen fuera de la biblioteca.
"""

#######################
# Sistema de representacion de datos #
#######################

"""
Conversion  de Numeros entre bases:
en el caso de hexadecimal es mejor usar la funcion hex 
o numeros prefijando 0x, pero esto ayuda a entender el "algoritmo" que usamos
"""
digits =['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f']
def DecToBase(number, base):
	"""Devuelve un String con la representacion del numero en otra Base
	@number = numero entero en decimal a convertir
	@base = numero entero indicando la base (ej 2:binario 8:octal 16:hexadecimal)
	"""
	d=number
	s='' 
	while d >= base:
			d, m =divmod (d, base)
			s= digits[m]+s
	s= digits [d]+s
	return s

def BaseToDec(number, base):
	"""devuelve un numero en decimal partiendo de un numero en otra base representado como string
	@number string con la representacion del numero en otra base
	@base entero con la base original 
	"""
	res=0
	number = number.lower()
	for i in number:
		res= res*base + digits.index(i)
	return res

def Comp10(num): 
	"Intento de complemento a la base (para sistemas decimales)"
	return (10**len(str(num)))-num
	
def Comp9(num):
	"Intento de complemento a la base-1 (para sistemas decimales)"
	return Comp10(num)-1 #:D

#########
# Estadistica #
#########

def Mediana(nums):
	#Devuelve la mediana de un array de numeros
	nums.sort() 
	l = len(nums)
	if l % 2:
		return nums[l/2]
	else:
		l= l/2
		return (nums[l]+nums[l-1])/2.0

def F(c):
	"""factorial de un numero
	@c: entero"""
	sum =1
	for i in xrange(c):
		sum*= 1+i
	return sum

def nPr(n,  r):
	""""Permutaciones
	@n : int tamaño de la muestra
	@r : int tamaño del grupo
	"""
	return F(n)/F(n-r)
	
def nCr(n, r): 
	"""
	Combinatoria
	@n : int, tamaño de la muestra
	@r : int, tamaño del grupo
	"""
	return nPr(n, r )/F(r)
	#return fac(n)/( fac(r)*fac(n-r))


#####################
# Sistema y Arquitectura de datos #
#####################

#####Para calcular los procesos
#Calculo de tiempo de procesos
#procs ha de ser un array con un array por cada proceso indicando:
#cuantums consumidos, prioridad, tiempo de inicio (en ese orden)
#ejemplo
procs = [
[20, 1, 0],
[15, 1, 10],
[10, 2, 0],
[15, 2, 10]
]

def a2proc(procarr, num = 0):
	#interno: por un array devuelve un proceso
	class p:
		cpu = prio  = ini = fin = num = 0
		q = 1
		def __str__(self):
			return "P%2d: %2d -> %3d" % (self.num , self.ini,  self.fin)
			
	tp = p()
	tp.cpu, tp.prio, tp.ini = procarr
	tp.num = num
	tp.fin = 0
	return tp

def aa2aproc(arr):
	#interno
	#por un array de array devuelve un array de procesos
	#por_duracion indica si se ordenan por duracion o por inicio
	
	def getini(p): return p.ini
	#pasamos un array de array a un array de procs
	a= [a2proc(j, i+1) for i, j in enumerate(arr)]
	#los ordenamos
	a.sort(key=getini)
	return a
	
class Plan:
	p = None
	def __init__(self,  procs,  q,  dbug=False,  keysort=None, changeprio=False):
		#Creamos la lista de procesos, activos y terminados
		#la variable con el tiempo, el quantum
		#los flags para cambiar la prioridad y depurar y la funcion para ordenar
		self.procs = aa2aproc(procs)
		self.act = []
		self.terminados = []
		self.time=0
		self.q = q
		self.cp = changeprio
		self.dbug = dbug
		self.keysort = keysort
		self.procs.sort(key=lambda p: p.ini)
		#esto es importante si hay 2 procesos que entran en tiempos parecidos pero 
		#diferentes, y en desorden (pej, dos procesos que entran antes de que termine el quantum
		# el primero con tiempo 13 y el segundo con tiempo 11
		
	def activos(self):
		#devuelve los procesos activos, para RRobbin
		#procs ha de ser un array de procesos (como en aa2aproc)
		#p = el proceso que se estaba ejecutando
		for i in self.procs: 
			if i.ini <self.time :
				self.act.append(i)
				
		if self.p:
			if self.cp : #si el flag para cambiar la prioridad esta, lo hacemos
				self.p.prio+=1
				self.p.q *=2
			self.act.append(self.p)
			
		for i in self.procs :
			if i.ini == self.time:
				self.act.append(i)
				
		for i in self.act:
			if i in self.procs:
				self.procs.remove(i)
				
		if self.keysort: #ordenamos si llega a ser necesario
			self.act.sort(key=self.keysort)#siempre hay un keysort default
			
	def __dbg(self):
		#funcion que imprime datos si se requieren
		if self.dbug:
			p = self.p
			print "T: %3d -> P%2d (%2d:%d/%d)" % (self.time,  p.num,  p.cpu,  p.prio,  p.q)
			
	def Quedan(self):
		#devuelve verdadero si aun hay procesos por ejecutarse
		return bool(self.procs or self.act or self.p)
		
	def BasicLap(self):
		"""Realiza una vuelta rapida, 
		en donde el primer proceso se 
		ejecuta hasta q termina ej: Fifo/Primero+Corto"""
		#Calculamos los activos
		self.activos()
		#Si hay activos
		if self.act:
			#Lo procesamos
			self.p = self.act[0]
			self.__dbg()
			self.time += self.p.cpu
			self.p.fin = self.time
			self.terminados.append(self.p)
			self.act.remove(self.p)
			#solo para las RRLap, borramos self.p para que el activo no lo vuelva a meter
			self.p = None 
		else:
			#Sino, aumentamos el tiempo
			self.time += self.q
		
	def RRLap(self):
		"""Realiza una vuelta RoundRobin"""
		self.activos()
		#Si hay activos
		if self.act:
			#Lo procesamos
			self.p = self.act[0]
			self.__dbg()
			q = self.q*self.p.q
			self.p.cpu -= q
			self.time += q
			self.act.remove(self.p)
			if self.p.cpu <= 0:
				#Si se termino el tiempo de cpu, lo eliminamos
				self.p.fin = self.time
				self.terminados.append(self.p)
				self.p = None
		else: 
			#sino aumentamos el tiempo
			self.time += self.q
		
		

"""Estas son las funciones a llamar, los parametros son:
@procs : array de procesos como explica mas arriba
@q : int (o float a tu riesgo) : tamaño del quantum (infiere en Fifo y MasCorto si ningun proceso entra en el tiempo 0)
@debug: True o False, indica si imprime los pasos
@mcolas: True o False, solo para Prio, es lo mismo que llamar a MColas"""
def Fifo(procs,  q=5,  debug=False,  sorter=None):
	"""
	Fifo (First In First Out (primero el primero))
	"""
	plan = Plan(procs,  q,  dbug=debug,  keysort=sorter)
	while plan.procs or plan.act:
		plan.BasicLap()
	return plan.terminados

def MasCorto(procs,  q=5,  debug=False):
	"""Primero el mas corto"""
	sorter = lambda p: p.cpu
	return Fifo(procs,  q,  debug,  sorter)
	
def RRobin(procs,  q=5,  debug=False,  sorter=None,  mcolas=False):
	"""Round Robin"""
	plan = Plan(procs,  q,  debug,  sorter,  mcolas)
	while plan.Quedan():
		plan.RRLap()
	return plan.terminados
	
def Prio(procs,  q=5,  debug=False,  mcolas=False):
	"""Por prioridad"""
	sort = lambda p: p.prio
	return RRobin(procs,  q,  debug,  sort,  mcolas)

def MColas(procs,  q=5,  debug=False):
	"""Colas multiples"""
	return Prio(procs,  q=q,  debug=debug,  mcolas=True)
	
	
	
"""
Ejemplos :
for i in MColas(procs,  debug=True): print i


procs = [
[20, 1, 0],
[15, 1, 10],
[10, 2, 0],
[15, 2, 10]
]

for i in MColas(procs,  debug=True): print i

for i in Fifo(procs):	print i.num,  i.fin

for i in RRobbin(procs):	print i.num,  i.fin

for i in MasCorto(procs): print i.num,  i.fin

for i in Prio(procs): print i.num ,  i.fin

for i in MColas(procs):    print i.num,  i.fin

"""

###Recursos
def PorRecursos(procesos,  recursos,  debug = False):
	"""
	@procs: array con procesos
	@recursos: array con recursos disponibles"""
	#definicion de clases y procedimientos internos es mala practica :D
	#pero mantiene el namespace limpio
	
	class p: #una clase para cada proceso
		def __init__(self, parr=([],[]), num=0):
			self.num = num 
			self.asign = parr[0][:]
			self.solic = parr[1][:]
			
		def __str__(self):
			return "P%2d: Asignados: %s Solicita: %s" % (self.num ,  self.asign ,  self.solic)
			
		def CanRun(self,  libres):
			#Devuelve True si el proceso puede ejecutarse (estan los recursos que solicita)
			#sino False
			for (i, j) in zip(self.solic, libres):
				if i > j: return False
			return True	

	def fkey(p):
		#funcion para ordenar los procesos
		return sum(p.asign)
		
	procs = [ p(arr,  num) for (num,  arr) in enumerate(procesos)]
	#Creamos un array de instancias de procesos
	
	procs.sort(key=fkey,  reverse=True)
	#y los ordenamos
	#copiamos los recuros libres y creamos una lista de procesos terminados
	libres = recursos[:]
	terminados = []
	
	#Flag para saber si se ejecuto alguno (=True para que entre en el while)
	ran = True 
	
	while procs and ran:
		ran = False
		for i in procs:#buscamos un proceso
			if i.CanRun(libres):#que se pueda ejecutar
				if debug: print "Se ejecutara: " + str(i)
				#se coloca el flag
				ran = True
				#se actualizan los recursos disponibles
				libres = [j+k for j,k in zip(i.asign, libres)]
				#y se pasa el proceso a la lista de terminados
				terminados.append(i)
				procs.remove(i)
				if debug: print "Recursos libres: " + str(libres)
				#se sale del for para que se comienze desde el primer item
				break
				
	if not ran:
		print "Sistema Lockeado!!"
	return terminados

"""
Ejemplos:
p1 = [ [2, 0,  1] ,  [1,  1, 0] ]
p2 = [ [1,  0,  1],  [0,  1,  1] ]
p3 = [[1,  1,  1],  [1,  0,  0]]
pr = [ p1,  p2,  p3]
recs  = [0,  1,  1]
for i in PorRecursos(pr,  recs,  debug=True): print i
"""
