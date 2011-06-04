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
# Organizacion Contable de la Empresa #
#######################
def printTabbed(s, size=15):
	print s.expandtabs(size)
	
def InteresSimple(Capital, Interes, Periodos=1, imprimir = True):
	i = Capital*Interes
	renta = i*Periodos
	if imprimir:
		printTabbed("Capital\tInteres\tRenta")
		printTabbed("%s\t%s\t%s"%(Capital, i, renta))
	return renta

def InteresCompuesto(capital, Interes, periodos=1):
	printTabbed("Capital\tInteres\tInteres Cap.\t")
	renta = 0
	for j in range(1, periodos+1):
		i = InteresSimple(capital, Interes, 1, False)
		renta += i
		ci = capital + i
		printTabbed("%s\t%s\t%s"%(capital, i, ci))
		capital = ci
	print "Renta %s" % renta
	
#######################
# Sistema de representacion de datos #
#######################

	"""
	Conversion  de Numeros entre bases:
	en el caso de hexadecimal es mejor usar la funcion hex
	o numeros prefijando 0x, pero esto ayuda a entender el 'algoritmo' que usamos
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
x=[4,6,11,3,16]
y=[50,50,40,60,30]

def Media(arr):
	#devuelve la media (promedio) de un array de numeros
	return sum(arr)/float(len(arr))

def Mediana(nums):
	#Devuelve la mediana de un array de numeros
	nums.sort()
	l = len(nums)
	if l % 2:
		return nums[l/2]
	else:
		l= l/2
		return (nums[l]+nums[l-1])/2.0

def Var(arr):
	"""dado un array, devuelve la Varianza (poblacional (n-1))
	@arr array"""
	suma =0
	p= Media(arr)
	for x in arr:
		suma += (x-p)**2
	return suma/ (float(len(arr))-1.0)

def DEst(arr):
	"""dado un array, devuelve la Desviación Estandar (poblacional (n-1))
	@arr array"""
	import math
	return math.sqrt(Var(arr))

def Covar(x, y):
	"""Devuelve la Covarianza de dos array de numeros
	(deberia ser una matriz pero es mas facil manejarlos asi :P)
	@x, y cada uno un array de numeros, han de tener la misma longitud,
	y el orden de cada uno importa (ya que deben corresponderse tal matriz)"""
	#covarianza
	mx =Media(x)
	my =Media(y)
	s=0
	for i,j in zip(x,y):
		s+= (i-mx)*(j-my)
	return float(s)/(len(x)-1.0)

def CCorr(x, y):
	"""Devuelve el Coeficiente de Correspondencia entre 2 vectores
	(deberia ser una matriz pero es mas facil manejarlos asi :P)
	@x, y cada uno un array de numeros, han de tener la misma longitud,
	y el orden de cada uno importa (ya que deben corresponderse tal matriz)"""
	return Covar(x,y)/(DEst(x) * DEst(y))

def F(c):
	"""factorial de un numero
	@c: entero"""
	sum =1
	for i in xrange(c):
		sum *= 1+i
	return sum

def nPr(n, r):
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
	#return nPr(n, r)/F(r)
	return F(n)/(F(r)*F(n-r))#este es apenas mas rapido

def Distrib(x, n, p):
	"""Distribución binaria
	@x: valor
	@n: tamaño de la muestra
	@p: probabilidade de acierto
	"""
	return nCr(n, x)*(p**x)*((1.0-p)**(n-x))

def SDistrib(x, n, p):
	"""Sumatoria de distribucion binaria
	@x: valor
	@n: tamaño de la muestra
	@p: probabilidade de acierto
	"""
	z=0
	for i in range(x+1):
		z+= Distrib(i,n,p)
	return z

distrib_normal =[
0.0000, 0.0040, 0.0080, 0.0120, 0.0160, 0.0199, 0.0239, 0.0279, 0.0319, 0.0359,
0.0398, 0.0438, 0.0478, 0.0517, 0.0557, 0.0596, 0.0636, 0.0675, 0.0714, 0.0753,
0.0793, 0.0832, 0.0871, 0.0910, 0.0948, 0.0987, 0.1026, 0.1064, 0.1103, 0.1141,
0.1179, 0.1217, 0.1255, 0.1293, 0.1331, 0.1368, 0.1406, 0.1443, 0.1480, 0.1517,
0.1554, 0.1591, 0.1628, 0.1664, 0.1700, 0.1736, 0.1772, 0.1808, 0.1844, 0.1879,
0.1915, 0.1950, 0.1985, 0.2019, 0.2054, 0.2088, 0.2123, 0.2157, 0.2190, 0.2224,
0.2257, 0.2291, 0.2324, 0.2357, 0.2389, 0.2422, 0.2454, 0.2486, 0.2517, 0.2549,
0.2580, 0.2611, 0.2642, 0.2673, 0.2704, 0.2734, 0.2764, 0.2794, 0.2823, 0.2852,
0.2881, 0.2910, 0.2939, 0.2967, 0.2995, 0.3023, 0.3051, 0.3078, 0.3106, 0.3133,
0.3159, 0.3186, 0.3212, 0.3238, 0.3264, 0.3289, 0.3315, 0.3340, 0.3365, 0.3389,
0.3413, 0.3438, 0.3461, 0.3485, 0.3508, 0.3531, 0.3554, 0.3577, 0.3599, 0.3621,
0.3643, 0.3665, 0.3686, 0.3708, 0.3729, 0.3749, 0.3770, 0.3790, 0.3810, 0.3830,
0.3849, 0.3869, 0.3888, 0.3907, 0.3925, 0.3944, 0.3962, 0.3980, 0.3997, 0.4015,
0.4032, 0.4049, 0.4066, 0.4082, 0.4099, 0.4115, 0.4131, 0.4147, 0.4162, 0.4177,
0.4192, 0.4207, 0.4222, 0.4236, 0.4251, 0.4265, 0.4279, 0.4292, 0.4306, 0.4319,
0.4332, 0.4345, 0.4357, 0.4370, 0.4382, 0.4394, 0.4406, 0.4418, 0.4429, 0.4441,
0.4452, 0.4463, 0.4474, 0.4484, 0.4495, 0.4505, 0.4515, 0.4525, 0.4535, 0.4545,
0.4554, 0.4564, 0.4573, 0.4582, 0.4591, 0.4599, 0.4608, 0.4616, 0.4625, 0.4633,
0.4641, 0.4649, 0.4656, 0.4664, 0.4671, 0.4678, 0.4686, 0.4693, 0.4699, 0.4706,
0.4713, 0.4719, 0.4726, 0.4732, 0.4738, 0.4744, 0.4750, 0.4756, 0.4761, 0.4767,
0.4772, 0.4778, 0.4783, 0.4788, 0.4793, 0.4798, 0.4803, 0.4808, 0.4812, 0.4817,
0.4821, 0.4826, 0.4830, 0.4834, 0.4838, 0.4842, 0.4846, 0.4850, 0.4854, 0.4857,
0.4861, 0.4864, 0.4868, 0.4871, 0.4875, 0.4878, 0.4881, 0.4884, 0.4887, 0.4890,
0.4893, 0.4896, 0.4898, 0.4901, 0.4904, 0.4906, 0.4909, 0.4911, 0.4913, 0.4916,
0.4918, 0.4920, 0.4922, 0.4925, 0.4927, 0.4929, 0.4931, 0.4932, 0.4934, 0.4936,
0.4938, 0.4940, 0.4941, 0.4943, 0.4945, 0.4946, 0.4948, 0.4949, 0.4951, 0.4952,
0.4953, 0.4955, 0.4956, 0.4957, 0.4959, 0.4960, 0.4961, 0.4962, 0.4963, 0.4964,
0.4965, 0.4966, 0.4967, 0.4968, 0.4969, 0.4970, 0.4971, 0.4972, 0.4973, 0.4974,
0.4974, 0.4975, 0.4976, 0.4977, 0.4977, 0.4978, 0.4979, 0.4979, 0.4980, 0.4981,
0.4981, 0.4982, 0.4982, 0.4983, 0.4984, 0.4984, 0.4985, 0.4985, 0.4986, 0.4986,
0.4987, 0.4987, 0.4987, 0.4988, 0.4988, 0.4989, 0.4989, 0.4989, 0.4990, 0.4990,
0.499
]

def PEst(z):
	"""devuelve la probabilidad estandard a partir de -infinito hasta z
	@z es un valor TIPIFICADO (curva estandar)"""

	z = int(round(z*100)) # lo convertimos a un entero , usando solo dos decimales
	if z<0:
		s = -1
		z = -z
	else:
		s = 1

	if z > 400: z=400
	return 0.5 + (s*distrib_normal[z])

def PDNormal(x1,  x2,  u,  d):
	"""Devuelve el valor de la probabilidad en una
	distribución normal
	para un valor de x entre X1 y X2 (x1<x<x2)
	@x2 : x mayor del rango
	@x1 =0 x menor del rango
	@u : media
	@d : desviación estandar
	x2 TIENE que ser mayor que x1
	"""
	d = float(d)
	z1 = (x1-u)/d
	z2 = (x2-u)/d
	return PEst(z2)-PEst(z1)

def Gauss(x, u, d):
	"""devuelve el valor (imagen (y))
		de un PUNTO x para una campana de gauss.
		@x variable aleatoria
		@u media
		@d desviación estandar
		"""
	import math
	d=float(d)
	z = (x-u)/d#Tipificamos
	a=(z**2)/-2.0
	b=math.e**a
	return (1.0/math.sqrt(2))*b

def RSNormal(x1, x2, u, d, div=1000.0):
	"""Como PDNorm pero sin usar tabla,
	excesivamente lento y probablemente no mas exacto
	@x1 x inicial
	@x2 x final (ha de ser mayor que x1)
	@u media
	@d desviacion estandar
	@div cantidad de divisiones por entero
	"""
	base = 1.0/div
	z=0.0
	x=x1
	while x < x2:
		z+= Gauss(x,u,d)*base
		x += base
	return z

#print PDNormal(45, 55, 50, 5)
#print RSNormal(45, 55, 50, 5)

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
#cada proceso es un array con dos arrays
#uno para los recursos asignados otro para los solicitados
p2 = [ [1,  0,  1],  [0,  1,  1] ]
p3 = [[1,  1,  1],  [1,  0,  0]]

#lista de procesos
pr = [ p1,  p2,  p3]

#recursos libres
recs  = [0,  1,  1]
for i in PorRecursos(pr,  recs,  debug=True): print i
"""
#Ejemplo de una tabla de asignacion de paginas
#Array de arrays, el 1º item es la pagina virtual, el 2º es el marco (pagina fisica) donde está alojada, o None si no lo esta

ta=[ [0,2], [1,4], [2,0], [3,None], [4,10], [5,None], [6,1], [7,6], [8,3],[9,None],[10,None]]

def TransDir(ta, dir, bs=4, fisica=False ):
	"""
	Transforma una direccion de virtual a fisica y viceversa
	@ta = tabla de asignacion de paginas (ver "ta" mas arriba)
	@dir = direccion a transformar (ej: 30488)
	@bs=4 tamaño de cada pagina (en KB) (default 4 o sea 4096)
	@fisica=False indica si la direccion es fisica o virtual. (default fisica)
	@return int con la direccion o "Fallo" si hay fallo de pagina
	"""
	def getini(p): return p[0]
	#multiplicamos el blocksize por un K
	bs*=1024
	#obtenemos la pagina (truncamos con int)
	pag=int(dir/bs)
	#calculamos el offset
	off=dir-(pag*bs)
	#invertimos el array si la direccion es física
	if fisica:
		ta = [[j,i] for [i,j] in ta]
	#lo ordenamos
	ta.sort(key=getini,reverse=True)
	print "pag %s, ini %s, offset %s" %( pag, pag*bs, off)
	for i, j in ta: #esta revertida
		if i==pag: #por el orden asumimos que el anterior es mayor
			#en caso de ser una direccion fisica que no tiene ninguna pagina
			#nunca entraria aca, porque i==None
			if j is None:
				#si j==None entonces rompemos el for para que de fallo
				break
			else:
				#si J no es None, entonces esta alocada en memoria
				#calculamos la direccion
				d =(j*bs)+off
				#informamos
				print 'pag %s, dir %s' %(j,d)
				#devolvemos
				return d
	#Si llegamos acá, es porque la pagina no esta alocada.
	return 'Fallo'
