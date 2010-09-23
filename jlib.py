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
			return "P%d\t%d\t-> %d" % (self.num , self.ini,  self.fin)
			
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
	def __init__(self,  procs,  q,  dbug=False, changeprio=False):
		self.procs = aa2aproc(procs)
		self.act = []
		self.terminados = []
		self.time=0
		self.q = q
		self.cp = changeprio
		self.dbug = dbug
		
	def activos(self):
		#devuelve los procesos activos, para RRobbin
		#procs ha de ser un array de procesos (como en aa2aproc)
		#p = el proceso que se estaba ejecutando
		for i in self.procs: 
			if i.ini <self.time :
				self.act.append(i)
		if self.p:
			if self.cp :
				self.p.prio+=1
				self.p.q *=2
			self.act.append(self.p)
		for i in self.procs :
			if i.ini == self.time:
				self.act.append(i)
		for i in self.act:
			if i in self.procs:
				self.procs.remove(i)
				
	def __dbg(self):
		if self.dbug:
			print "T: %d\t-> P%d" % (self.time,  self.p.num)
			
	def BasicLap(self):
		"""Realiza una vuelta rapida, 
		en donde el primer proceso se 
		ejecuta hasta q termina ej: Fifo/Primero+Corto"""
		if self.act:
			#no lo guardamos en self.p para que activos no lo meta d nuevo
			self.p = self.act[0]
			self.__dbg()
			self.time += self.p.cpu
			self.p.fin = self.time
			self.terminados.append(self.p)
			self.act.remove(self.p)
			self.p = None #solo para las RRLap
		self.activos()
		if not self.act : self.time += self.q
		
	def RRLap(self,  sort=False):
		"""Realiza una vuelta RoundRobin"""
		if self.act:
			self.p = self.act[0]
			self.__dbg()
			q = self.q*self.p.q
			self.p.cpu -= q
			self.time += (q-self.q)
			self.act.remove(self.p)
			if self.p.cpu <= 0:
				self.p.fin = self.time
				self.terminados.append(self.p)
				self.p = None
		self.activos()
		self.time += self.q

"""Estas son las funciones a llamar, los parametros son:
@procs : array de procesos como explica mas arriba
@q : int (o float a tu riesgo) : tamaño del quantum (infiere en Fifo y MasCorto si ningun proceso entra en el tiempo 0)
@debug: True o False, indica si imprime los pasos
@mcolas: True o False, solo para Prio, es lo mismo que llamar a MColas"""
def Fifo(procs,  q=5,  debug=False):
	"""
	Fifo (First In First Out (primero el primero))
	"""
	plan = Plan(procs,  q,  dbug=debug)
	while plan.procs or plan.act:
		plan.BasicLap()
	return plan.terminados

def MasCorto(procs,  q=5,  debug=False):
	"""Primero el mas corto"""
	def getcpu(p): return p.cpu
	plan = Plan(procs,  q,  dbug=debug)
	while plan.procs or plan.act:
		plan.BasicLap()
		plan.act.sort(key=getcpu)
	return plan.terminados
		
def RRobin(procs,  q=5,  debug=False):
	"""Round Robin"""
	plan = Plan(procs,  q,  dbug=debug)
	while plan.procs or plan.act:
		plan.RRLap()
	return plan.terminados
	
def Prio(procs,  q=5,  debug=False,  mcolas=False):
	"""Por prioridad"""
	plan = Plan(procs,  q,  dbug=debug,  changeprio=mcolas)
	def getprio(p): return p.prio
	while plan.procs or plan.act:
		plan.RRLap()
		plan.act.sort(key=getprio)
	return plan.terminados

def MColas(procs,  q=5,  debug=False):
	"""Colas multiples"""
	return Prio(procs,  q=q,  debug=debug,  mcolas=True)

for i in MColas(procs,  debug=True): print i
"""
Ejemplos :
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
