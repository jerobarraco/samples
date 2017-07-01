#coding: utf-8
__author__ = 'nande'
tablero = None
import sys
"""
Tablero :
    MacroCol[3]
        MacroFila[3]
            Fila[3]
                Col[3]
                    celda [9]
"""
def show():
    sys.stdout.write("+----------------+----------------+-----------------+\n")
    for mc in range(3):
        for c in range(3):
            for mf in range(3):
                sys.stdout.write("| ")
                for f in range(3):
                    celda = tablero[mf][mc][f][c]
                    t = ""
                    l = len(celda)
                    if l == 1:
                        t = '  '+str(celda[0])+'  '
                    else:
                        t = ' ['+str(len(celda))+'] '
                    sys.stdout.write(t)
            sys.stdout.write(" |\n")
        sys.stdout.write("+----------------+----------------+-----------------+\n")

def isSet(cell):
    """devuelve true cuando una celda tiene un solo valor, false en otro caso. o excepción si no es iterable"""
    return len(cell)==1

def removeFromCell(mf, mc, f, c, v):
    celda = tablero[mf][mc][f][c]
    if len(celda) == 1:
        return
    if v in celda:
        celda.remove(v)
        if isSet(celda):
            print("\nDEDUJE UNA CELDA!\n")
            setCelda(mf, mc, f, c, celda[0])

def takeFromRow(mc, c, v):
     for mf in range(3):
        for f in range(3):
            removeFromCell(mf, mc, f, c, v)

def takeFromCol(mf, f, v):
    """Quita un valor de toda la fila"""
    for mc in range(3):
        for c in range(3):
           removeFromCell(mf, mc, f, c, v)

def takeFromSquare(mf, mc, v):
    "Quita un valor de todo el cuadradito"
    for c in range(3):
        for f in range(3):
           removeFromCell(mf, mc, f, c, v)

def checkUniqueCell(mf, mc, f, c):
    "Verifica si la celda UN valor UNICO en el cuadrado" #en realidad si hay uno unico es uno
    celda = tablero[mf][mc][f][c]
    if isSet(celda):
        return None

    for v in celda:
        unico = True
        for f2 in range(3):
            for c2 in range(3):
                if f2 == f and c2 == c: continue

                celdab = tablero[mf][mc][f2][c2]

                if isSet(celdab): continue
                if v in celdab:
                    unico = False
                    break
            if not unico: break
        ##### aca sabemos si es unico o no
        if unico:
            return v
    return None#nada es unico acá

def checkSquare(mf, mc):
    hubo = False
    for f in range(3):
        for c in range(3):
            #celda = tablero[mf][mc][f][c]
            v = checkUniqueCell(mf, mc, f, c)
            if v is not None:
                print("Encontré un valor único!")
                setCelda(mf, mc, f, c, v)
                hubo = True

def checkAllSquares():
    hubo = True
    while hubo:
        hubo = False
        for mc in range(3):
            for mf in range(3):
                hubo = hubo or checkSquare(mf, mc)

def setCelda(mf, mc, f, c, v):
    "Asigna un valor a una celda y desencadena el infierno!!"
    global tablero
    celda = tablero[mf][mc][f][c]
    if v not in celda:
        print ("El valor no es posible para dicha celda")
        return False

    if isSet(celda):
        if (celda[0] != v):
            print ("Intenté asignar a una celda asignada, ant=%d actual=%d"%(celda[0], v))
            print ("Y SON DIFERENTES!!!!")
            return False

    tablero[mf][mc][f][c] = [v]
    print("%d:%d %d:%d -> %d"%(mf, mc, f, c, v))
    show()
    takeFromRow(mc, c, v)
    takeFromCol(mf, f, v)
    takeFromSquare(mf, mc, v)
    #checkSquare(mf, mc)
    #for mc2 in range(3):
    #    checkSquare(mf, mc2)
    #for mf2 in range(3):
    #    checkSquare(mf2, mc)



def setLinea(nl, t):
    """
    mc= macrocolumna
    t = linea de texto. ej "-2--3---"
    """
    mc, c = divmod(nl, 3)
    for i, v in enumerate(t):
        if not v.isdigit() : continue
        mf, f = divmod(i, 3)
        setCelda(mf, mc, f, c, int(v))

def init():
    global tablero
    tablero = [
        [ #macroCol
            [#fila
                [# una col
                    list(range(1,10)) #una celda
                    for c in range(3)
                ]
                for f in range(3)
            ]
            for mc in range(3)
        ]
        for mc in range(3)
    ]