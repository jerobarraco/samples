# mizodb_remoto.py
#Esta clase permite conectarse a zodb de manera remota a traves de ZEO
from ZEO.ClientStorage import ClientStorage
from ZODB import DB

#Define la clase principal
class ZODBRemoto(object):
	def __init__(self, server, port):
		#El constructor recibe como parametor el servidor y el puerto 
		#al cual conectarse
		server_and_port = (server, port)
		#Crea un almacenamiento a partir de los datos
		self.storage = ClientStorage(server_and_port)
		#crea la base de datos a partir del storage
		#notar como es intercambiable con el FileStorage visto anteriormente
		self.db = DB(self.storage)
		self.connection = self.db.open()
		self.dbroot = self.connection.root()
		
	def close(self):
		self.connection.close()
		self.db.close()
		self.storage.close()
	
#En caso de ejecutar este script individualmente
if __name__ == "__main__":
	#Crea una instancia de la conexion remota
	mydb = ZODBRemoto('localhost', 8090)
	dbroot = mydb.dbroot
	#e imprime los datos en la raiz de la base de datos
	print dbroot
