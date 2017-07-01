__author__ = 'nande'
"""
Tablero :
    MacroFila[3]
        MacroCol[3]
            Fila[3]
                Col[3]
                    celda [9]

Input
    Set Linea
        Set Celda
            Asigno en la celda
            YYYYY quito el numero en la fila, col y cuadrado
                - Si queda un solo valor en alguna celda!
                    - Set Celda


            - El numero aparece en un solo lugar
                - check cuadrado
                    - Si si, SetCelda
                - fila
                    - Si si, SetCelda
                - col
                    - Si si, SetCelda

TODO:
    Crear tablero
        - Inicializar
        - Ingresar datos
    Ver tablero
    Resolver tablero
        Casos:
            - Que quede un solo numero
            - Que el número solo aparezca dentro del cuadrado en un solo lugar
"""

import sudoku
sudoku.init()

print ("Ingrese una por una cada linea, con los numeros pre establecidos, donde no hay nada ponga '-'.")

test1 = """-2-3
4
8-65---9
--7-169
-3-945
-6-73-58
-7-6-91
--52----9
----8135"""

test = """---65-7
58--429-6
-7----2-4
-----7--2
-2-----8
3--4-65
-3-2---4
--7--389
--587--2
"""
test3 = """--5--6
-----7-3
67-34---9
-6----85
1-4829-7
8--6
---45
-28763-94
---9--7
"""

test4 = """--78
-6-2-48
5----76
--395---7
8---4392
729-----3
----2-74
--4-783
17---9--2
"""
test = """---5879
35-92---4
-8-6----5
-------7
--63542
-248-9
---4-86
7-32-6-5
---7-53
"""

test = """--8--2--5
---58-243
-4-7
-----85-1
---9-76
45--3--29
56--7
---1964
--4---36
"""
debug = True
if debug:
    for i, l in enumerate(test.splitlines()):
        sudoku.setLinea(i, l)

else:
    for i in range(9):
        l = input("> ")
        sudoku.setLinea(i, l)
print("\n\n--- result ---")
sudoku.show()

sudoku.checkAllSquares()

"""
for mc in range(3):
    for mf in range(3):
        sudoku.checkSquare(mf, mc)

print("\n\n--- result ---")
sudoku.show()"""