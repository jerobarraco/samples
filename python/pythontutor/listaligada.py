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

def agregarAlInicio(ini, dato):
    nuevoInicio = Celda()
    nuevoInicio.dato = dato
    nuevoInicio.enlace = ini
    return nuevoInicio

def invertir(ini):
    reco = ini
    iniinv = None
    
    while reco!=None:
        iniinv = agregarAlInicio(iniinv, reco.dato)
        reco = reco.enlace
    return iniinv
    
def imprimir(ini):
    reco = ini
    while reco != None:
        print(reco.dato)
        reco = reco.enlace
        
        
inicio = None

inicio = agregar(inicio, 10)
inicio = agregar(inicio, 20)
inicio = agregar(inicio, 30)
inicio = agregar(inicio, 40)

invertida = invertir(inicio)

print("normal")
imprimir(inicio)

print ("invertia")
imprimir(invertida)