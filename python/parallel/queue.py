#!/usr/bin/python
#coding:Utf-8
import os
import threading
import time
import random
try:
	from Queue import Queue #python2
except:
	from queue import Queue #python3
	
	
class Escritor(threading.Thread):
	def __init__(self, cola):
		threading.Thread.__init__(self)
		self.cola = cola
		
	def run(self):
		while True:
			res = self.cola.get()
			print "tome", res
			time.sleep(2)
	
class Lector(threading.Thread):
	def __init__(self, cola):
		threading.Thread.__init__(self)
		self.cola = cola
		
	def run(self):
		while True:
			a = random.random()*100
			print "agregando", a
			self.cola.put(a)
	
	
c = Queue(10)
leer = Lector(c)
escr = Escritor(c)

leer.start()
escr.start()

leer.join()
escr.join()