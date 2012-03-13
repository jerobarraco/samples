# leer_hosts.py - show hosts in the database
from mizodb import MiZODB
from modelo import Host

#Se crea la conexion a la base de datos
db = MiZODB('./Data.fs')
dbroot = db.raiz

#Se listan las claves en la raiz de la base de datos
for key in dbroot.keys():
	#por cada clave se obtiene el objeto asociado
	obj = dbroot[key]
	#se verifica si el objeto es una instancia de la clase Host
	if isinstance(obj, Host):
		#se imprimen los datos
		print "Host:", obj.hostname
		print " IP address:", obj.ip, " Interfaces:", obj.interfaces

#al terminar se cierra la conexion
db.close()