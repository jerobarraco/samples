#copyright moongate GPLv3 (moongate.com.ar)
class Celda:
    def __init__(self):
        self.dato = 0
        self.enlace = None

def agregar(ini, dato):
    nueva = Celda()
    nueva.dato = dato
    
    if ini==None:
        return nueva
    
    reco = ini
    while (reco != None):
        if (reco.enlace == None):
            reco.enlace = nueva
            break
        reco = reco.enlace
    return ini
    #fin
#fin
    
def imprimir(ini):
    reco = ini
    while reco != None:
        print(reco.dato)
        reco = reco.enlace
		
def intercambiar(lista, pos):
	cont = 0
	A = None
	B = None
	Ant = None
	A = lista
	while A != None:
		B = A.enlace
		if (B == None):
			break;
		if(cont == pos):
			#encontramos el elemento a intercambiar
			A.enlace = B.enlace
			B.enlace = A
			if( Ant == None): 
				#A es el primero, hay que modificar el inicio de la lista
				lista = B
			else:
				Ant.enlace = B
			#finsi
			break
		#finsi
		cont += 1
		Ant = A
		A = A.enlace
	return lista
#finintercambiar
	
inicio = None

inicio = agregar(inicio, 10)
inicio = agregar(inicio, 20)
inicio = agregar(inicio, 30)
inicio = agregar(inicio, 40)

imprimir(inicio)


pos = 2
inicio = intercambiar(inicio, pos)
imprimir(inicio)
