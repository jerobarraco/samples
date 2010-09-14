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
		ini = 0
		fin = 0
		prio = 0
		cpu = 0
		num = 0
	tp = p()
	tp.cpu, tp.prio, tp.ini = procarr
	tp.num = num
	return tp

def aa2aproc(arr):
	#por un array de array devuelve un array de procesos
	return [a2proc(j, i+1) for i, j in enumerate(arr)]

def activos(procs, t):
	#devuelve los procesos activos,
	#procs ha de ser un array de procesos (como en aa2aproc)
	act = []
	for i in procs:
	  if i.ini <=t :
		act.append(i)
	for i in act:
	  procs.remove(i)
	return act
	
def Fifo(procs, q = 5):
  #First in first out
  procs = aa2aproc(procs)
  time = 0
  act = []
  terminados = []
  while procs or act:
	act.extend(activos(procs, time))
	time+=q
	if act:
		p = act[0]
		p.cpu -= q
		if p.cpu <=0:
			p.fin = time
			terminados.append(p)
			act.remove(p)
  return terminados
  