org 100h 
;imprimir el abecedario
MOV CX, 26; cant de letras
MOV DL, 65; letra inicial 65='A'
MOV AH, 2; operacion de la int 21h (Imprimir caracter)
L:
    INT 21h; Imprime lo que hay en DL
    INC DL; Incrementa, pasa al siguiente caracter de la tabla ascii
LOOP L


MOV CX, 26
MOV DL, 97
L2:
    INT 21h
    INC DL
LOOP L2
ret




