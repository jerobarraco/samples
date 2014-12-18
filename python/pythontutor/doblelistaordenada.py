#copyright moongate GPLv3
class DCelda:
	#celda doble
	dato = 0
	sig = None
	ant = None

def agregar(inicio, dato):
	#agrega una celda en una lista doble ordenada de menor a mayor
	#creamos la celda y la inicializamos
	ncelda = DCelda() # RCelda()
	
	ncelda.dato = dato		#Esto es 
	ncelda.sig = None	# GCelda(ncelda, dato, NULL, NULL)
	ncelda.ant = None	# 
	if inicio == None:
		inicio = ncelda
	else:
		reco = inicio
		while(reco!= None):
			dator = reco.dato # LCelda(reco, dator, antr, sigr)
			antr = reco.ant 
			sigr = reco.sig
			if (dato < dator):
				#si reco es el primero, antr es None (NULL)
				ncelda.ant = antr #
				ncelda.sig = reco # GCelda(ncelda, dato, antr, reco)
				reco.ant = ncelda # GCelda(reco, dator, ncelda, sigr)
				if (antr == None):
					inicio = ncelda
				else:
					#LCelda(antr, datoant, antant, sigant)
					antr.sig = ncelda #GCelda(antr, datoant, antant, ncelda)
				break #EL break corta el flujo
			#finsi
			if sigr == None:
				ncelda.ant = reco #GCelda(ncelda, dato, reco, null)
				reco.sig = ncelda #gcelda(reco, dator, antr, ncelda)
				break
			#finsi
			reco = reco.sig
		#finmientras
	return inicio
#fin 

def eliminarMax(inicio):
	#busca el mayor y lo elimina
	#no considera que la lista esta ordenada (para aprender a hacer la busqueda)
	#como la lista esta ordenada de menor a mayor simplemente habria que quitar el ultimo
	if inicio == None : 
		return inicio
	max = inicio
	reco = inicio
	while (reco != None):
		datomax = max.dato #LCelda(max, datomax, antm, sigm)
		datoreco = reco.dato #LCelda(reco, datoreco, antr, sigr)
		if (datoreco > datomax ):
			max = reco
		reco = reco.sig		
	#finmientras
	#eliminamos la celda
	
	ant = max.ant #
	sig = max.sig #LCelda(max, datom, ant, sig)
	if( ant == None):
		#es la primera
		inicio = sig #si no hay siguiente , pini queda en nulo, o sea, lista vacia.
		#si si hay siguiente, queda con el valor que corresponda
	else:
		#LCelda(ant, datoant, antant, sigant)
		#GCelda(ant, datoant, antant, sig)
		ant.sig = sig #si es el ultimo, sig  == null, y esto queda como corresponde.
	#finsi
	
	if (sig != None):
		#en caso que este primera, ant es null, y se asigna automaticamente null, que esta bien
		#en caso que no este primera, queda enlazada con el anterior.
		#LCelda(sig, datos, ants, sigs)
		#GCelda(sig, datos, ant, sigs)
		sig.ant = ant

	del max #LibCelda(max)
	
	return inicio
#fin

def eliminarPos(inicio, pos):
	#elimina por posicion
	if inicio == None : 
		return inicio
	cont = 0
	reco = inicio
	while (reco != None):
		if cont == pos:
			#eliminamos la celda
			ant = reco.ant #
			sig = reco.sig #LCelda(reco, datom, ant, sig)
			if( ant == None):
				#es la primera
				inicio = sig #si no hay siguiente , pini queda en nulo, o sea, lista vacia.
				#si si hay siguiente, queda con el valor que corresponda
			else:
				#LCelda(ant, datoant, antant, sigant)
				#GCelda(ant, datoant, antant, sig)
				ant.sig = sig #si es el ultimo, sig  == null, y esto queda como corresponde.
			#finsi
			
			if (sig != None):
				#en caso que este primera, ant es null, y se asigna automaticamente null, que esta bien
				#en caso que no este primera, queda enlazada con el anterior.
				#LCelda(sig, datos, ants, sigs)
				#GCelda(sig, datos, ant, sigs)
				sig.ant = ant

			del reco #LibCelda(reco)
			break
		#finsi
		
		cont = cont +1
		reco = reco.sig		
	#finmientras	
	return inicio
#fin

pini = None	
for dato in [1,2,3,4, -3, -2, -1]:
	if (dato>0):
		#agregamos
		pini = agregar(pini, dato)
	else:
		pos = -(dato+1)
		pini = eliminarPos(pini, pos)