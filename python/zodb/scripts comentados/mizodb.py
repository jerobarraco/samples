# mizodb.py
from ZODB import FileStorage, DB
import transaction

#Define la clase MiZODB que manejara la conexion basica a la base de datos
class MiZODB(object):
	
	def __init__(self, archivo):
		#El constructor recibe por parametro el nombre de archivo de la base de datos
		#Se crea un storage del tipo FileStorage
		self.storage = FileStorage.FileStorage(archivo)
		#crea la base de datos a partir del storage
		self.db = DB(self.storage)
		#abre la conexion
		self.conexion = self.db.open()
		#toma el objeto raiz para acceder mas facilmente
		self.raiz = self.conexion.root()
	
	#Define un metodo close
	def close(self):
		#Esta funcion permite cerrar la conexion a la base de datos
		#cierra la conexion, la base de datos y el storage
		self.conexion.close()
		self.db.close()
		self.storage.close()
