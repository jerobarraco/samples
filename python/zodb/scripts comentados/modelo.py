# modelo.py - Simple modelo
#Importa la clase Persistent de ZODB
from persistent import Persistent

#Define una clase Host que hereda de Persistent
class Host(Persistent):
	#El constructor de la clase, toma por parametro 3 variables
	#el nombre del host, la ip y las interfaces asociadas
	def __init__(self, hostname, ip, interfaces):
		#
		#se asignan los valores a las variables de instancia
		self.hostname = hostname
		self.ip = ip
		self.interfaces = interfaces