#copyright moongate GPLv3 (moongate.com.ar)
class Celda:
    dato = 0
    enlace = None
    

def agregar(inicio, dato):
	#creamos la celda y la inicializamos
	ncelda = Celda() # new Celda
	
	ncelda.dato = dato		#Esto es 
	ncelda.enlace = None	# GCelda(ncelda, dato, NULL)
	#vemos si la lista esta vacia
	if (inicio == None):
		#esta vacia
		inicio = ncelda
		#como modificamos inicio, pero inicio es un parametro, para que esto impacte la funcion main, vamos a tener
		#que devolverlo como resultado (return) en c++ podemos abusar del pasaje por referencia (celda *&inicio)
	else:
		#No esta vacia, tiene al menos uno
		#leemos la celda inicial
		datoini = inicio.dato	#Esto es
		#sig = inicio.enlace		# LCelda(inicio, datoini, sig)# a sig no lo necesitamos
		#Primero verificamos el caso en que tenga que ir antes que el primero
		if( dato < datoini):
			#GCelda (ncelda, dato, inicio)
			#ncelda.dato = dato# esto no es necesario porque lo hicimos al principio, si es necesario con el GCelda porque asi esta definido.
			ncelda.enlace = inicio
			inicio = ncelda #En este caso tambien me es importante retornar inicio, porque lo modificamos
		else:
			reco = inicio
			while (reco != None):
				posterior = reco.enlace
				if (posterior == None):
					reco.enlace = ncelda
					break
				else:
					#Si SI hay un posterior, vamos a ver si ncelda queda en el medio 
					#LCelda(posterior, datopos, enlacepos)
					datopos = posterior.dato
					#enlacepos = posterior.enlace#no lo necesitamos
					#vemos si va en el medio (o sea, es mayor a reco, pero menor a posterior)
					if (dato < datopos):
						#GCelda(ncelda, dato, posterior)
						#ncelda.dato = dato #no es necesario porque no lo vamos a cambiar
						ncelda.enlace = posterior

						#GCelda(reco, datoreco, ncelda)
						#reco.dato = datoreco #no es necesario aca
						reco.enlace = ncelda
						
						break #muy importante
					#fin si
				#fin si
				reco = posterior
			#fin mientras
		#finsi
	#finsi
	return inicio

pini = None	
pini = agregar (pini, 10)
pini = agregar (pini, 5)
pini = agregar (pini, 4)
pini = agregar (pini, 15)
pini = agregar (pini, 12)
pini = agregar (pini, 11)