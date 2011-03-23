#!/usr/bin/python
# -*- coding: utf-8 -*-
import os, sys

if len(sys.argv) >1:
	mydir = sys.argv[1]
else:
	mydir = os.getcwdu()
print mydir

borrar = raw_input('Borrar directorios? [y/N]').lower()=='y'

for path, dirs, files in os.walk(mydir):
	if not (files or dirs): 
		if borrar : 
			print "Borrado: " +path
			os.rmdir (path)
		else:
			print 'No borrado '+path
			
	for f in files:
		ext = os.path.splitext(f)[1].lower()
		if ext not in [ '.mp3' ]: print path+'/'+f

