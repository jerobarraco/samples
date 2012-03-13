# abort_transaction.py
#Se utilizara la clase MiZODB que se encarga de las cosas basicas
from mizodb import MiZODB, transaction
#Importa el modelo que usaremos
from modelo import Host

#Crea una instancia de la conexion a la base de datos
db = MiZODB('./Data.fs')
#toma la raiz de la base de datos
dbroot = db.raiz
#se lee el host bajo la clave 'www.example.com'
host1 = dbroot['www.example.com']
print "Ip actual:" , host1.ip

#Se modifica la ip del mismo objeto anterior
host1.ip = "192.168.7.3"
print "Ip modificada:", host1.ip

#Se aborta la transaccion
transaction.abort()

print "Valor luego de abortar la trasaccion:", host1.ip
#Cierra la conexion
db.close()