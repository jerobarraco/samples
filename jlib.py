# -*- coding: utf-8 -*-
#cosas que hacemos en la facu


#conversion  de Numeros entre bases
#en el caso de hexadecimal es mejor usar la funcion hex 
#o numeros prefijando 0x
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

def Comp9(num):
	z=10*len(str(num))-1-num
def Comp10(num): 
	return (10**len(str(num)))-num

def Mediana(nums):
#Devuelve la mediana de un array de numeros
	nums.sort() 
	l = len(nums)
	if l % 2:
		return nums[l/2]
	else:
		l= l/2
		return (nums[l]+nums[l-1])/2.0


#####Para calcular los procesos
#Calculo de tiempo de procesos
#procs ha de ser un array con un array por cada proceso indicando:
#cuantums consumidos, prioridad, tiempo de inicio
#ejemplo
procs = [
[20, 1, 0],
[15, 1, 10],
[10, 2, 0],
[15, 2, 10]
]

def a2proc(procarr, num = 0):
	#por un array devuelve un proceso
	class p:
		pass
		
	tp = p()
	tp.cpu, tp.prio, tp.ini = procarr
	tp.num = num
	tp.q = 1
	tp.fin = 0
	return tp

def aa2aproc(arr):
	#por un array de array devuelve un array de procesos
	#por_duracion indica si se ordenan por duracion o por inicio
	
	def getini(p): return p.ini
	#pasamos un array de array a un array de procs
	a= [a2proc(j, i+1) for i, j in enumerate(arr)]
	#los ordenamos
	a.sort(key=getini )
	return a
	
class Plan:
	p = None
	def __init__(self,  procs,  q,  changeprio=False):
		self.procs = aa2aproc(procs)
		self.act = []
		self.terminados = []
		self.time=0
		self.q = q
		self.cp = changeprio
		
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
				
	def BasicLap(self):
		if self.act:
			#no lo guardamos en self.p para que activos no lo meta d nuevo
				p = self.act[0]
				self.time += p.cpu
				p.fin = self.time
				self.terminados.append(p)
				self.act.remove(p)
		self.activos()
		if not self.act : self.time += self.q
		
	def RRLap(self,  sort=False):
		if self.act:
			self.p = self.act[0]
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
		
def Fifo(procs,  q=5):
	plan = Plan(procs,  q)
	while plan.procs or plan.act:
		plan.BasicLap()
	return plan.terminados

def MasCorto(procs,  q=5):
	def getcpu(p): return p.cpu
	plan = Plan(procs,  q)
	while plan.procs or plan.act:
		plan.BasicLap()
		plan.act.sort(key=getcpu)
	return plan.terminados
		
def RRobbin(procs,  q=5):
	plan = Plan(procs,  q)
	while plan.procs or plan.act:
		plan.RRLap()
	return plan.terminados
	
def Prio(procs,  q=5,  mcolas = False):
	plan = Plan(procs,  q,  mcolas)
	def getprio(p): return p.prio
	while plan.procs or plan.act:
		plan.RRLap()
		plan.act.sort(key=getprio)
	return plan.terminados

def MColas(procs,  q=5):
	return Prio(procs,  q,  mcolas=True)
	
"""for i in Fifo(procs):	print i.num,  i.fin
print ""

for i in RRobbin(procs):	print i.num,  i.fin
print ""

for i in MasCorto(procs): print i.num,  i.fin
print ""

for i in Prio(procs): print i.num ,  i.fin
print ""

for i in MColas(procs):    print i.num,  i.fin"""
