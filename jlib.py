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