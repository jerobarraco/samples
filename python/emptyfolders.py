#!/usr/bin/python3
# -*- coding: utf-8 -*-
import os, sys

if len(sys.argv) >1:
	mydir = sys.argv[1]
else:
	mydir = os.getcwd()
print (mydir)

borrar = input('Borrar directorios? [y/N]').lower()=='y'
borrar_file = input('Borrar ARCHIVOS? [y/N]').lower()=='y'

for path, dirs, files in os.walk(mydir):
	
	for f in files[:]:
		ext = os.path.splitext(f)[1].lower()
		if ext not in [ '.mp3', '.ogg', '.aac', '.ac3' ]: 
			if borrar_file:
				print ("Borrado arch: " +path+'/'+f)
				os.remove (path+'/'+f)
				files.remove(f)
			else:
				print ('No borrado arch: '+path+'/'+f)
				#print (path+'/'+f)
	
	if not (files or dirs): 
		if borrar : 
			print ("Borrado: " +path)
			os.rmdir (path)
		else:
			print ('No borrado: '+path)
			

