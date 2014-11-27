org 100h
.data
texto DB "Este es un texto que deberia tener 500 caracteres, pero no me voy a poner a contar."; aca deberian haber 500 elementos
; la direccion de texto en el ejercicio seria 200
; la de palabras 700
palabras DB 0

.code     
;recorrer el texto, hasta que encuentre un espacio. Cuando encuentro un espacio, incremento el contador de palabras (en la direccion 700).
;MOV BX, 200
LEA BX, [TEXTO]

MOV CX, 500
L:  
    ; Si es un espacio
    MOV AL, [BX]
    CMP AL, 32; comparamos con un espacio 
    JNE FINSI ; si no es espacio salto y no hago nada
    ; si es espacio, sumo uno en la POSICION 700 (o sea el contador de palabras)
        ;INC [700]
        INC palabras
        
    FINSI:  

    INC BX ; pasamos al siguiente elemento
LOOP L ;decrementa CX _Y_ salta a el label (L) si CX NO es zero
INC palabras;para contar la ultima palbra que no tiene espacio al final       
;INC [700]

ret