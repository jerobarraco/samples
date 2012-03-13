# mizodb.py
from ZODB import FileStorage, DB
import transaction

class MiZODB(object):
	def __init__(self, archivo):
		self.storage = FileStorage.FileStorage(archivo)
		self.db = DB(self.storage)
		self.conexion = self.db.open()
		self.raiz = self.conexion.root()
	def close(self):
		self.conexion.close()
		self.db.close()
		self.storage.close()
