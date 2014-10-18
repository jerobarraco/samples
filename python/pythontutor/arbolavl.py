#!/bin/python
#coding:utf-8


#Implementacion de arboles AVL en python para visualizar con pythontutor.com
#BASADO en el libro 
#Árboles AVL
#Sebastián Gurin (Cancerbero)
#Copyright © 2004 by Sebastián Gurin
#Copyright (c) 2004 Sebastián Gurin. Permission is granted to copy,
#distribute and/or modify this document under the terms of the GNU Free
#Documentation License, Version 1.2 or any later version published by the
#Free Software Foundation; with no Invariant Sections, no Front-Cover Texts,
#and no Back-Cover Texts. A copy of the license is included in the section
#entitled "GNU Free Documentation License".
#----------------------------------------------
#(c) 2014 Jeronimo Barraco Marmol (GPLv3 o v2 o la que sea compatible con la de sebastian)
#no lo planteo en forma orientada a objeto porque agregaría una complejidad extra completamente innecesaria
#ademas que por respeto al autor es mejor que quede lo mas similar posible


class Nodo :
	altura = 0
	izq = None
	der = None
	def __init__(self, dato, izq=None, der=None):
		self.dato = dato
		#self.altura = 0#*1
		#self.izq = izq
		#self.der = der

#la altura se declara como propiedad del nodo y no se calcula recursivamente segun explicaciones del libro para evitar problemas de velocidad
#"Important: Debemos tener mucho cuidado en actualizar el campo altura de cada nodo siempre que modifiquemos de alguna manera el árbol AVL."
#*1 PythonTutor has a limit of 300 execution frames, for that some code will be "optimized" to not stole frames with uninportant actions 
#	still i'll leave the original form so it can be easy to understand (and port)

#*1
#def altura(nodo): 
	#if(nodo):
		#return nodo.altura
	#else:
		#return -1

altura = lambda nodo=None: nodo.altura if nodo else -1

def actualizarAltura(nodo):
	if (not nodo): return
	nodo.altura = max(altura(nodo.izq), altura(nodo.der)) +1

#rotaciones
def rotarS(nodo, a_izq):
	#Rotar simple
	#/* realiza una rotación simple del árbol t el cual se
	#pasa por referencia. La rotación será izquierda
	#sii. (izq==true) o será derecha
	#sii. (izq==false).
	#Nota: las alturas de t y sus subárboles serán actualizadas
	#dentro de esta función!
	#Precondición:
	#si (izq==true) ==> !es_vacio(izquierdo(t))
	#si (izq==false) ==> !es_vacio(derecho(t))
	#*/
	
	#n2 = None #*1
	if (a_izq):#rotacion izquierda
		n2 = nodo.izq
		nodo.izq = n2.der
		n2.der = nodo
	else:#rotacion derecha
		n2 = nodo.der
		nodo.der = n2.izq
		n2.izq = nodo
	actualizarAltura(nodo)
	actualizarAltura(n2)
	return n2 #no hay pasaje por referencia en python (de manera limpia)

def rotarD(nodo, a_izq):
	if(a_izq):
		nodo.izq = rotarS(nodo.izq, False)
		nodo = rotarS(nodo, True)
	else:
		nodo.der = rotarS(nodo.der, True)
		nodo = rotarS(nodo, False)
	#/* la actualización de las alturas se realiza en las rotaciones simples */
	return nodo
	
def balancear(nodo):
	if (not nodo): return
	al_dif = altura(nodo.izq) - altura(nodo.der) #*1
	#*1 #if altura(nodo.izq) - altura(nodo.der) == 2:#podria almacenar el resultado de la diferencia pero asi se entiende mas
	if al_dif == 2:
		#desequilibrio hacia la izquierda
		if altura(nodo.izq.izq) >= altura(nodo.izq.der):
			#desequilibrio simple a la izq
			nodo = rotarS(nodo, True)
		else:
			#desequilibrio doble a la izquierda
			nodo = rotarD(nodo, True)
	#*1 elif altura(nodo.der) - altura(nodo.izq) == 2:
	elif al_dif == -2:
		#desequilibrio hacia la derecha
		if altura(nodo.der.der) >= altura(nodo.der.izq): 
			#desequilibrio simple hacia la derecha
			nodo = rotarS(nodo, False)
		else:
			#desequilibrio doble hacia la derecha
			nodo = rotarD(nodo, False)
	return nodo

def insertar(nodo, dato):
	if (not nodo):
		nodo = Nodo(dato, None, None)
	else:
		#la magia de la recursividad hace todo aca
		if (dato < nodo.dato):
			nodo.izq = insertar(nodo.izq, dato)
		else:
			nodo.der = insertar(nodo.der, dato)
		nodo = balancear(nodo)
		actualizarAltura(nodo)
	return nodo

#raiz = None
#for i in (2, 3, 4 , 10, 15 ,22, 16, 7 , 5, 8, 1, 6, 23, 9, 11, 12, 13, 14, 75):#
#	raiz = insertar(raiz, i)
	
#Link is http://pythontutor.com/visualize.html#code=%23Implementacion+de+arboles+AVL+en+python+para+visualizar+con+pythontutor.com%0A%23BASADO+en+el+libro+%0A%23%C3%81rboles+AVL%0A%23Sebasti%C3%A1n+Gurin+(Cancerbero)%0A%23Copyright+%C2%A9+2004+by+Sebasti%C3%A1n+Gurin%0A%23Copyright+(c)+2004+Sebasti%C3%A1n+Gurin.+Permission+is+granted+to+copy,%0A%23distribute+and/or+modify+this+document+under+the+terms+of+the+GNU+Free%0A%23Documentation+License,+Version+1.2+or+any+later+version+published+by+the%0A%23Free+Software+Foundation%3B+with+no+Invariant+Sections,+no+Front-Cover+Texts,%0A%23and+no+Back-Cover+Texts.+A+copy+of+the+license+is+included+in+the+section%0A%23entitled+%22GNU+Free+Documentation+License%22.%0A%23----------------------------------------------%0A%23(c)+2014+Jeronimo+Barraco+Marmol+(GPLv3+o+v2+o+la+que+sea+compatible+con+la+de+sebastian)%0A%23no+lo+planteo+en+forma+orientada+a+objeto+porque+agregar%C3%ADa+una+complejidad+extra+completamente+innecesaria%0A%23ademas+que+por+respeto+al+autor+es+mejor+que+quede+lo+mas+similar+posible%0A%0A%0Aclass+Nodo+%3A%0A%09altura+%3D+0%0A%09def+__init__(self,+dato,+izq%3DNone,+der%3DNone)%3A%0A%09%09self.dato+%3D+dato%0A%09%09%23self.altura+%3D+0%23*1%0A%09%09self.izq+%3D+izq%0A%09%09self.der+%3D+der%0A%0A%23la+altura+se+declara+como+propiedad+del+nodo+y+no+se+calcula+recursivamente+segun+explicaciones+del+libro+para+evitar+problemas+de+velocidad%0A%23%22Important%3A+Debemos+tener+mucho+cuidado+en+actualizar+el+campo+altura+de+cada+nodo+siempre+que+modifiquemos+de+alguna+manera+el+%C3%A1rbol+AVL.%22%0A%23*1+PythonTutor+has+a+limit+of+300+execution+frames,+for+that+some+code+will+be+%22optimized%22+to+not+stole+frames+with+uninportant+actions+%0A%23%09still+i'll+leave+the+original+form+so+it+can+be+easy+to+understand+(and+port)%0A%0A%23*1%0A%23def+altura(nodo)%3A+%0A%09%23if(nodo)%3A%0A%09%09%23return+nodo.altura%0A%09%23else%3A%0A%09%09%23return+-1%0A%0Aaltura+%3D+lambda+nodo%3DNone%3A+nodo.altura+if+nodo+else+-1%0A%0Adef+actualizarAltura(nodo)%3A%0A%09if+(not+nodo)%3A+return%0A%09nodo.altura+%3D+max(altura(nodo.izq),+altura(nodo.der))+%2B1%0A%0A%23rotaciones%0Adef+rotarS(nodo,+a_izq)%3A%0A%09%23Rotar+simple%0A%09%23/*+realiza+una+rotaci%C3%B3n+simple+del+%C3%A1rbol+t+el+cual+se%0A%09%23pasa+por+referencia.+La+rotaci%C3%B3n+ser%C3%A1+izquierda%0A%09%23sii.+(izq%3D%3Dtrue)+o+ser%C3%A1+derecha%0A%09%23sii.+(izq%3D%3Dfalse).%0A%09%23Nota%3A+las+alturas+de+t+y+sus+sub%C3%A1rboles+ser%C3%A1n+actualizadas%0A%09%23dentro+de+esta+funci%C3%B3n!%0A%09%23Precondici%C3%B3n%3A%0A%09%23si+(izq%3D%3Dtrue)+%3D%3D%3E+!es_vacio(izquierdo(t))%0A%09%23si+(izq%3D%3Dfalse)+%3D%3D%3E+!es_vacio(derecho(t))%0A%09%23*/%0A%09%0A%09%23n2+%3D+None+%23*1%0A%09if+(a_izq)%3A%23rotacion+izquierda%0A%09%09n2+%3D+nodo.izq%0A%09%09nodo.izq+%3D+n2.der%0A%09%09n2.der+%3D+nodo%0A%09else%3A%23rotacion+derecha%0A%09%09n2+%3D+nodo.der%0A%09%09nodo.der+%3D+n2.izq%0A%09%09n2.izq+%3D+nodo%0A%09actualizarAltura(nodo)%0A%09actualizarAltura(n2)%0A%09return+n2+%23no+hay+pasaje+por+referencia+en+python+(de+manera+limpia)%0A%0Adef+rotarD(nodo,+a_izq)%3A%0A%09if(a_izq)%3A%0A%09%09nodo.izq+%3D+rotarS(nodo.izq,+False)%0A%09%09nodo+%3D+rotarS(nodo,+True)%0A%09else%3A%0A%09%09nodo.der+%3D+rotarS(nodo.der,+True)%0A%09%09nodo+%3D+rotarS(nodo,+False)%0A%09%23/*+la+actualizaci%C3%B3n+de+las+alturas+se+realiza+en+las+rotaciones+simples+*/%0A%09return+nodo%0A%09%0Adef+balancear(nodo)%3A%0A%09if+(not+nodo)%3A+return%0A%09al_dif+%3D+altura(nodo.izq)+-+altura(nodo.der)+%23*1%0A%09%23*1+%23if+altura(nodo.izq)+-+altura(nodo.der)+%3D%3D+2%3A%23podria+almacenar+el+resultado+de+la+diferencia+pero+asi+se+entiende+mas%0A%09if+al_dif+%3D%3D+2%3A%0A%09%09%23desequilibrio+hacia+la+izquierda%0A%09%09if+altura(nodo.izq.izq)+%3E%3D+altura(nodo.izq.der)%3A%0A%09%09%09%23desequilibrio+simple+a+la+izq%0A%09%09%09nodo+%3D+rotarS(nodo,+True)%0A%09%09else%3A%0A%09%09%09%23desequilibrio+doble+a+la+izquierda%0A%09%09%09nodo+%3D+rotarD(nodo,+True)%0A%09%23*1+elif+altura(nodo.der)+-+altura(nodo.izq)+%3D%3D+2%3A%0A%09elif+al_dif+%3D%3D+-2%3A%0A%09%09%23desequilibrio+hacia+la+derecha%0A%09%09if+altura(nodo.der.der)+%3E%3D+altura(nodo.der.izq)%3A+%0A%09%09%09%23desequilibrio+simple+hacia+la+derecha%0A%09%09%09nodo+%3D+rotarS(nodo,+False)%0A%09%09else%3A%0A%09%09%09%23desequilibrio+doble+hacia+la+derecha%0A%09%09%09nodo+%3D+rotarD(nodo,+False)%0A%09return+nodo%0A%0Adef+insertar(nodo,+dato)%3A%0A%09if+(not+nodo)%3A%0A%09%09nodo+%3D+Nodo(dato,+None,+None)%0A%09else%3A%0A%09%09%23la+magia+de+la+recursividad+hace+todo+aca%0A%09%09if+(dato+%3C+nodo.dato)%3A%0A%09%09%09nodo.izq+%3D+insertar(nodo.izq,+dato)%0A%09%09else%3A%0A%09%09%09nodo.der+%3D+insertar(nodo.der,+dato)%0A%09%09nodo+%3D+balancear(nodo)%0A%09%09actualizarAltura(nodo)%0A%09return+nodo%0A%0Araiz+%3D+None%0Afor+i+in+(2,+3,+4+,+10,+15+,22,+16,+7+,+5,+8,+1,+6,+23,+9,+11,+12,+13,+14,+75)%3A%0A%09raiz+%3D+insertar(raiz,+i)&mode=edit&origin=opt-frontend.js&cumulative=false&heapPrimitives=false&drawParentPointers=false&textReferences=false&showOnlyOutputs=false&py=3&rawInputLstJSON=%5B%5D


space = 5
v_space = 1

def mostrar(nodo, lvl=0):
	if (not nodo):
		print ("")
		return
	
	nlvl = lvl+ 1
	mostrar(nodo.der, nlvl)
	print((" "*space*lvl) + str(nodo.dato))

	mostrar(nodo.izq, nlvl)

def lvlnode(nodo, dlvl, lvl =0):
	if (lvl == dlvl): 
		return [nodo]#if nodo is None will return None (important)
	#return empty only if is not the level im looking 4
	if (lvl < dlvl):
		nodes = []
		izq = nodo and nodo.izq #this forces to add a None when the node doesnt exists and there is another level
		der = nodo and nodo.der
		nodes += lvlnode(izq, dlvl, lvl+1)
		nodes += lvlnode(der, dlvl, lvl+1)
		return nodes 
	
def mostrar2(raiz):
	import sys
	p = sys.stdout.write
	width = (2**(raiz.altura))*space
	for i in range(raiz.altura+1):
		cnodes = 2**i
		spaces = (width/cnodes)
		for n in lvlnode(raiz, i):
			t = str(n and n.dato or "")#returns "" if n is None
			tspaces = int((spaces -len(t))/2)
			p(" " *tspaces)
			p(t)
			p(" " *tspaces)
		p("\n")
		
#mostrar(raiz)
#mostrar2(raiz)

""">>> r = None
>>> r = insertar(r, 31)
>>> r = insertar(r, 30)
>>> r = insertar(r, 28)
>>> r = insertar(r, 21)
>>> r = insertar(r, 18)
>>> r = insertar(r, 4)
>>> r = insertar(r, 2)
>>> r = insertar(r, 13)
>>> r = insertar(r, 16)
>>> r = insertar(r, 22)
>>> mostrar2(r)
                   21
         4                  30
    2        16        28        31
         13  18  22
>>> r = insertar(r, 20)
>>> r = insertar(r, 9)
"""
raiz = None
for i in (3, 21, 30, 11, 13, 22,15, 8, 24, 16, 32, 25):
	raiz = insertar(raiz, i)
	print ("-----------------------------------")
	mostrar2(raiz)
#mostrar2(raiz)

space = 4
rp = None
#for palabra in ("arbol", "barco", "casa", "perro"):
for palabra in ("t", "r", "a", "x", "v", "z", "y", "b", "s"):
	rp = insertar(rp, palabra)#duck typing ftw!
mostrar2(rp)

"""
s = []
def ird(raiz, i=1):
        s.insert(0, (raiz, 1))
        while s:
                p, i = s.pop(0)
                if i==1:
                        s.insert(0, (p, 2))
                        if p.izq:
                                s.insert(0, (p.izq, 1))
                else:
                        print(p.dato)
                        if p.der:
                                s.insert(0, (p.der, 1))

def preorden(raiz):
        s = [raiz,]
        while s:
                p = s.pop(0)
                print(p.dato)
                if p.izq:
                        s.insert(0, p.izq)
                if p.der:
                        s.insert(0, p.der)
def hojas(raiz):
        s = [raiz,]
        while s:
                p = s.pop(0)
                if (not p.izq ) and (not p.der):
                        print(p.dato)
                if p.izq:
                        s.insert(0, p.izq)
                if p.der:
                        s.insert(0, p.der)

def borrarSiHoja(nodo, dato):
        if (nodo == None): return
        print("visitando nodo"+str(nodo.dato))
        if (nodo.izq):
                print ("tengo izq")
                nodo.izq = borrarSiHoja(nodo.izq, dato)
        if (nodo.der):
                print("tengo der")
                nodo.der = borrarSiHoja(nodo.der, dato)
                
        if (nodo.dato == dato):
                print("soy el nodo que buscas")
                if (nodo.izq == None and nodo.der == None):
                        print("no tengo hijos asi que muero")
                        return None
                else:
                        print ("tengo algun hijo asi que no muero")
        return nodo

def copiar(nodo):
	#copia de un arbol a otro usando pilas (no recursividad)
	#no importa el tipo de arbol
	s = [(nodo, None, None), ]
	otro = None
	while s:
			p, padre, izq = s.pop(0)
			nn = Nodo(p.dato)
			if not padre:
					otro = nn
			else:
					if  izq :
							padre.izq = nn
					else:
							padre.der = nn
							
			if (p.izq) : s.insert(0, (p.izq, nn, True))
			if (p.der) : s.insert(0, (p.der, nn, False))
			
	return otro
"""

def tomarPre(nodo):
	#toma un nodo de un arbol en preorden,
	#intenta devolver el nodo mas positivo (mas a la derecha) incluyendo este nodo,
	#lo quita del padre y lo devuelve
	obj = False
	#podria evitar usar obj y usar directamente nodo, pero tendria que usar un truco que haga el codigo aun menos entendible
	if nodo:
		if nodo.der:
			nodo.der, obj = tomarPre(nodo.der)
		else:
			#*1
			#obj = nodo #el objetivo es el nodo
			#izq = nodo.izq#el nodo que tomara su lugar es su rama izq (subarbol izq) (que es menor que este)
			#nodo.izq = None #que hay que quitar del nodo objetivo
			#nodo = izq
			obj, nodo = nodo, nodo.izq #el nodo pasa a ser el obj, la izq pasa a ser el nodo
			obj.izq = None#y quitamos la izq del nodo original
		nodo = balancear(nodo)
		actualizarAltura(nodo)
	return nodo, obj#borrado indica si se borro algo en esta rama, obj es el nodo que va a subir,
	#nodo es el nodo que debe ir
	#necesario en caso de que el nodo tenga que quitarse del nodo padre

def borrar(nodo, dato):
	"""
	We first do the normal BST deletion:
	– 0 children: just delete it
	– 1 child: delete it, connect child to parent
	– 2 children: put successor in your place,
	
	Which nodes’ heights may have changed:
	– 0 children: path from deleted node to root
	– 1 child: path from deleted node to root
	– 2 children: path from deleted successor leaf to root
	Will rebalance as we return along the “path in question” to the root
	"""
	if nodo:
		if nodo.dato == dato : #si es el nodo a borrar lo borramos
			if nodo.izq and nodo.der: #si tiene dos hijos, estamos al horno.. empieza el proceso raro
				izq, nuevo = tomarPre(nodo.izq) #tomarpre nos va a devolver:
				nuevo.izq = izq
				nuevo.der = nodo.der
				nodo = nuevo
				#el nodo que reemplazara el nodo original (nodo.izq) (que puede cambiar o volverse None si el nodo.izq es hoja)
				#el nodo objetivo, que es el que tomara el lugar de este nodo y la bandera de borrado
			elif nodo.der:#si tiene solo el nodo derecho o izq
				nodo = nodo.der#true indica que se encontró y borro el nodo
				#al devolver el nodo.der lo que hacemos es que el padre (que llamo a borrar(....) reemplace este nodo con el nodo.der
				#con eso nos basta para borrar este nodo
			elif nodo.izq:
				nodo = nodo.izq
			else: #en caso que no tenga hijos, lo borramos y ya
				nodo = None
		elif dato < nodo.dato:
			nodo.izq = borrar (nodo.izq, dato)#buscamos en preorden
		else:#nodo.dato > dato
			nodo.der = borrar (nodo.der, dato)
		#el borrado pasa antes que esto, llegando acá estamos de vuelta
		actualizarAltura(nodo)#si se produjo la eliminacion por esta rama, la actualiza
		nodo = balancear(nodo)
	return nodo
#devuelve el nodo (que puede ser none, o puede ser el que vino por parametro, u otro si fue borrado
#si no hicimos nada devuelve el mismo nodo (que puede ser none) y el estado

#rp = borrar(rp, "perro")
#mostrar2(rp)
#rp = borrar(rp, "asd")
#mostrar2(rp)
#rp = borrar(rp, "arbol")
#mostrar2(rp)
#print("\n\n")
#rp = borrar(rp, "t")
#mostrar2(rp)
#print("\n\n")
#rp = borrar(rp, "a")
#mostrar2(rp)

#print("\n\n")
#rp = borrar(rp, "v")
#mostrar2(rp)

aborrar = ["y", "x", 'r']
for i in aborrar:
	print("\n\n")
	rp = borrar(rp, i)
	mostrar2(rp)
	
