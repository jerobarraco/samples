"""
Ejercicios de properties y exceptions version 2:

1) Implemente una clase User con los atributos : name, pwd, email, gender y age.
	A) Cree properties para cada atributo. Y coloque cada atributo como oculto ( "__" )
	B) El el caso del password, el property debe aceptar una tupla al asignarse con dos elementos: el password y la confirmación. Pero almacenará el password solo. 
	En el getter debera devolver un solo valor. Pej

	>>> user.pwd = ('password', 'password) #ok
	>>> print(user.pwd)
	'password'
	
	Extra: si desea puede almacenar el password "encriptado" (el termino correcto es hasheado) utilizando algo como:
	>>> import hashlib
	>>> hasheado = hashlib.sha512(password.encode('utf-8')).hexdigest() 
	En ese caso, implemente un método "login" que reciba el password por parámetro, lo hashee y compare con el password almacenado, 
	y devuelva True en caso de que coincidan.
	
	C) Lance excepciones en caso de que:
		- name = Contenga alguno de los caracteres " ,.?¿)(/&%$'-_\""
		- name = Longitud menor a 6
		- pwd = longitud menor a 6
		- pwd = password y verificacion no son iguales
		- pwd = no contenga numeros, o mayusculas o minúsculas (O sea, debe contener al menos un numero, una mayuscula y una minuscula)
		- email = no contenga arroba
		- gender = sea alguno diferente de 'M', 'F' u 'O' (por las dudas)
		- age = No sea convertible a numero
		- age = Sea menor a 16
		Extra: Utilice excepciones personalizadas. Que hereden de una excepcion llamada "InvalidUserData" (que herede de Exception)
		
2) Implemente una clase Employee que herede de User, e implemente el atributo "area" y otra "salary".
	El area solo puede tener un valor posible entre "Management", "Human Resources", "Production" o "Marketing". Caso contrario debe lanzar una excepcion.
	El "salary" debe ser opcional pero en caso de intentar leer dicho atributo, debe lanzar una excepcion si no se le ha asignado ningún valor.

3) Implemente una clase Client que implemente el atributo "phone".
	El cual debe solo contener numeros o alguno de los simbolos "+- "
	
4) Implemente un menu para cargar los datos que permita cargar o Empleados o Clientes, con sus respectivos datos. O un código de ejemplo. 
	Atrape y maneje las excepciones en este código.

"""
import hashlib
class InvalidUser(Exception): pass
class InvalidUserNameCharacter(InvalidUser): pass
class InvalidUserNameLength(InvalidUser): pass
class UserPasswordDoesntMatch(InvalidUser): pass
class UserPasswordTooSmall(InvalidUser): pass
class UserEmailInvalid(InvalidUser): pass
class UserGenderInvalid(InvalidUser): pass
class UserAgeTooYoung(InvalidUser): pass
class UserPasswordNeedsDigit(InvalidUser): pass
class UserPasswordNeedsLower(InvalidUser): pass
class UserPasswordNeedsUpper(InvalidUser): pass

class EmployeeInvalidArea(InvalidUser): pass
class EmployeeSalaryNotSet(InvalidUser): pass

class ClientInvalidPhone(InvalidUser): pass


class User:
	__name  = ""
	__pwd = ""
	__email = ""
	__gender = ""
	__age = 0
	def __sName(self, n):
		if(sum(map(n.count, " ,.?¿)(/&%$'-_\""))>0): raise InvalidUserNameCharacter()
		if(len(n)<6): raise InvalidUserNameLength()
		self.__name = n
		
	def __gName(self): return self.n
	
	name = property (__gName, __sName)
	
	def __sPwd(self, ps):
		pa, pb = ps
		if (pa != pb): raise UserPasswordDoesntMatch()
		if (len(pa)<6): raise UserPasswordTooSmall()
		has_digit = has_upper = has_lower = False
		for c in pa:
			if (c.isdigit()): has_digit = True
			elif (c.islower()): has_lower = True
			elif (c.isupper()): has_upper  = True
		if not has_digit: raise UserPasswordNeedsDigit()
		if not has_lower: raise UserPasswordNeedsLower()
		if not has_upper: raise UserPasswordNeedsUpper()
		self.__pwd = hashlib.sha512(pa.encode("utf-8")).hexdigest()
		
	def __gPwd(self, p): return self.pwd
	
	pwd = property(__gPwd, __sPwd)
	
	def login(self, p):
		return self.__pwd == hashlib.sha512(p.encode("utf-8")).hexdigest()
	
	def __sEmail(self, e):
		if not "@" in e: raise UserEmailInvalid()
		self.__email = e
		
	def __gEmail(self): return self.__email
	email = property(__gEmail, __sEmail)
	
	def __sGender(self, g):
		g = g.upper()
		if g not in ("M", "F", "O"): raise UserGenderInvalid()
		self.__gender = g
		
	def __gGender(self): return self.__gender
	gender = property(__gGender, __sGender)
	
	def __sAge(self, a):
		a = int(a)
		if a < 16: raise UserAgeTooYoung()
		self.__gender = a
		
	def __gAge(self): return self.__gender
	age = property(__gAge, __sAge)
		
	def __init__(self, name, pwd, pwdconf, email, gender, age):
		self.name = name
		self.pwd = (pwd, pwdconf)
		self.email = email
		self.gender = gender
		self.age = age

class Employee(User):
	__area = ""
	__salary = None
	
	def __gArea(self): return self.__area
	def __sArea(self, a):
		a = a.capitalize()
		if a not in ("Management", "Human Resources", "Production", "Marketing"):
			raise EmployeeInvalidArea()
		self.__area = a
	
	def __gSalary(self):
		if self.__salary is None: raise EmployeeSalaryNotSet()
		return self.__salary
		
	def __sSalary(self, s):
		s = int(s)
		self.__salary = s
	salary = property(__gSalary, __sSalary)
	
class Client(User):
	__phone = ""
	def __sPhone(self, p):
		for i in p:
			if not i.isDigit() or i not in  "+- ": raise ClientInvalidPhone()
		self.__phone = p
		
	def __gPhone(self): return self.__phone
	phone = property(__gPhone, __sPhone)
	
e = Employee("asdfasdf", "clavE123", "clavE123", "askdfls@lks.com", "M", 33)
e.salary = 2500.0
e2 = Employee("asdf123", "cLave123", "cLave123", "askdfls@lks.com", "M", 33)
print (e.salary)
c = Client("lklklk", "PPpP123", "PPpP123", "laksl@qqq", "F", 30)
print (c.phone)
#la implementación se las debo