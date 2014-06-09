#copyright moongate GPLv3 (moongate.com.ar)
class Celda:
    def __init__(self):
        self.dato = 0
        self.enlace = None

def agregaCola(ini, dato):
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

def agregaPila(tope, dato):
	nueva = Celda()
	nueva.dato = dato
	nueva.enlace = tope
	tope = nueva
	return tope
	
	
pila = None
pila = agregaPila(pila, 10)
pila = agregaPila(pila, 20)
pila = agregaPila(pila, 30)
pila = agregaPila(pila, 40)

inicio = None

#paso de la pila a la cola
while (pila != None):
	inicio = agregaCola(inicio, pila.dato)
	pila = pila.enlace

imprimir(inicio)

pos = 2
inicio = intercambiar(inicio, pos)
imprimir(inicio)
