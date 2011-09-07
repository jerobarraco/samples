#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      Administrador
#
# Created:     31/08/2011
# Copyright:   (c) Administrador 2011
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python

from ZODB import FileStorage, DB
from BTrees.OOBTree import OOBTree as Obt
import transaction

from persistent import Persistent

class IndexedItem(Persistent):
	sequence = 'global_seq'
	container = 'globals'
	id = 0
	def __init__(self, root):
		Persistent.__init__(self)
		root[self.sequence] +=1
		self.id = root[self.sequence]
		root[self.container][self.id] = self
		
class Articulo(Persistent):
	def __init__(self, root):

		root['last_art'] +=1

		self.id = root['last_art']
		self.codigo = ""
		self.descripcion = ""
		self.precioCosto= 0
		self.precioVenta= 0
		self.obs = ""
		self.imagen = list()
		self.activo = False
		root['articulos'][self.id]= self

class MyZODB(object):
	def __init__(self, path):
		self.storage = FileStorage.FileStorage(path)
		self.db = DB(self.storage)
		self.connection = self.db.open()
		self.dbroot = self.connection.root()
		self.active = True

	def close(self):
 		transaction.commit()
		self.connection.close()
		self.db.close()
		self.storage.close()
		self.active = False

	def getRoot(self): return self.dbroot
	def __del__(self):
		if self.active:
			self.close()

def main():
	mzdb = MyZODB('data.fs')
	try:
		root = mzdb.getRoot()

		if not root.has_key('articulos'):
			root['articulos'] = Obt()
		if not root.has_key('last_art'):
			root['last_art'] = 0

		articulos = root['articulos']
		na = Articulo(root)

		for k,v in articulos.items():
			print k, v
		transaction.commit()
	finally:
		mzdb.close()

if __name__ == '__main__':
    main()
