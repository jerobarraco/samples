# leer_simple.py Muestra lo que hay en la base de datos
from mizodb import MiZODB
db = MiZODB('./Data.fs')
dbroot = db.raiz
#Enumera todas las claves dentro de la raiz de la base de datos
for key in dbroot.keys():
	#Imprime la clave y el valor almacenado en dicha clave
	print key + ':', dbroot[key]
db.close()