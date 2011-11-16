import json, urllib2
"""Prueba de uso de requests y json.
Colaboración con Alejandro Caro"""

buscar = raw_input("mete el fucking dato:\n")
url = "http://search.twitter.com/search.json?q=%s" % urllib2.quote(buscar)
server = urllib2.urlopen(url)
djson = server.read()
datos = json.loads(djson)

res = datos['results']
if res:
	undato = res[0]
	print "%(from_user)s dijo: %(text)s" % undato
else:
	print "no encontre nada"
