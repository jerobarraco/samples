#!/bin/python
#coding:utf-8
class Nodo :
    def __init__(self, dato):
	    self.dato = dato
	    self.izq = None
	    self.der = None

def insertar(raiz, dato):
	ncelda = Nodo(dato)
	#ncelda.dato = dato
	if(raiz is None):
		raiz = ncelda
	else:
		reco = raiz
		while (reco): #simil a mientras reco != Null
			# lo principal de los arboles de busqueda es que los nodos estan ordenados
			#aca deberiamos leer los datos de reco con LCelda(reco, dator, linkir, linkdr)
			if dato < reco.dato :#si es menor al nodo actual lo insertamos a su izquierda
				#pero si ya tiene algo en la izquierda volvemos a empezar el proceso con este hijo izquierdo como nodo actual de "reco"rrido
				if reco.izq:# si linkizquierdo != Null entonces
					reco = reco.izq
				else:
					#GCelda(reco, dato, ncelda, linkderecho)
					reco.izq = ncelda
					reco = None
					break#break para que salga del while, tambien se puede usar reco = null
					
			else:
				#si es mayor hacemos lo mismo pero a la derecha
				if reco.der:
					reco = reco.der
				else:
					reco.der = ncelda
					reco = None
					break
				#finsi
			#finsi
		#finmientras
	#finsi
	return raiz
	
	
r = None
#a_insertar = (1, 2, 3, 4, 5, 6)
#a_insertar = (7, 4, 12, 2, 6, 9, 11, 8)
a_insertar = (7, 0, 0)
for i in a_insertar:
	r = insertar(r, i)
	
def borrar(nodo):
	if not(nodo.der and nodo.izq):
		if (nodo.dato == 0):
			nodo = None
			return None
		#endif
	else:
		if (nodo.izq):
			nodo.izq = borrar (nodo.izq)
		if (nodo.der):
			nodo.der = borrar (nodo.der)
	#al final devolvemos el nodo
	return nodo

def borrarhojas(raiz):
	pila = []
	pila.insert(0, (raiz, None, False))
	
	while pila:# pila != Null
		nodo, padre, izq = pila.pop(0)
		if(nodo.izq == None and nodo.der == None):
			if (nodo.dato == 0):
				if (padre == None):
					raiz = None
				else:
					#LCelda (padre, dato, linkder, linkizq
					linkder = padre.der
					linkizq = padre.izq
					if(izq):
						linkizq = None
					else:
						linkder = None
					#GCelda
					padre.der = linkder
					padre.izq = linkizq
				#finsi
				
				nodo = None #libcelda
			#finsi
		else:
			if(nodo.izq != None):
				pila.insert(0, (nodo.izq, nodo, True))
			#finsi
			if(nodo.der != None):
				pila.insert(0, (nodo.der, nodo, False))
			#finsi
		#finsi
	#finmientras
	return raiz

r = borrarhojas(r)