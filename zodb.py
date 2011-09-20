#-------------------------------------------------------------------------------
# Name:        zodb
# Purpose:      Probar zodb
#
# Author:      Barraco M?rmol Jer?nimo
#
# Created:     31/08/2011
# Copyright:   (c) Barraco M?rmol Jer?nimo
# Licence:     GPL
#-------------------------------------------------------------------------------
#!/usr/bin/env python

from ZODB import FileStorage, DB
from BTrees.OOBTree import OOBTree as Obt
import transaction

from persistent import Persistent

class Indexed(Persistent):
	container = 'globals'
	id = 0
	def __init__(self, root):
		Persistent.__init__(self)
		seqs = root['sequences']
		seqs[self.container] +=1
		self.id = seqs[self.container]
		root[self.container][self.id] = self

class Articulo(Indexed):
	id = 0
	container = 'articulos'
	def __init__(self, root):
		#self.sequence = 'seq_articulos'
		#self.container = 'articulos'
		super(Articulo, self).__init__(root)
		self.codigo = ""
		self.descripcion = ""
		self.precioCosto= 0
		self.precioVenta= 0
		self.obs = ""
		self.imagen = list()
		self.activo = False

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

def initDb(root):
	if not root.has_key('articulos'):
			root['articulos'] = Obt()
	if not root.has_key('sequences'):
		root['sequences'] = Obt()
	if not root['sequences'].has_key('articulos'):
			root['sequences']['articulos'] = 0

def main():
	mzdb = MyZODB('data.fs')
	try:
		root = mzdb.getRoot()
		initDb(root)
		articulos = root['articulos']
		na = Articulo(root)

		for k,v in articulos.items():
			print k, v, v.id
		transaction.commit()
	finally:
		mzdb.close()


if __name__ == '__main__':
    main()
