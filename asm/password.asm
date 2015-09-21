
org 100h
.data
    pwd db "uno"
    titulo db "Computacion I$"
.code

; estrategia 
; almacenar la contraseña en memoria
; modo en 40x25

; loop- por cada letra (3)
;   leer una letra
;   comparar con la letra en memoria
;   (adelantar la letra) (hasta 3)
;   poner un flag si esta bien

; verificar el flag y escribir en pantalla o terminar

; almacenar la contraseña en memoria
;MOV AL, [300]




; modo en 40x25
MOV AH, 0
MOV AL, 1
INT 10h

LEA BX, [pwd]
MOV DL, 1
MOV CX, 3   ; (hasta 3)
L:; loop- por cada letra (3) 
    ;   leer una letra
    MOV AH, 8 ; leer caracter sin eco
    INT 21h
    ;en AL  queda el caracter
    
    ;   comparar con la letra en memoria
    MOV AH, [BX]
    CMP AL, AH
    ;   poner un flag a cero si no esta bien
    JE finsi
        MOV DL, 0
    finsi:
     
    ;   (adelantar la letra)
    INC BX
    
    
    LOOP L
; verificar el flag y escribir en pantalla o terminar

CMP DL, 1 ; ver si esta bien
JNE mal ; si al == 1
    MOV AH, 2
    MOV DH, 20
    MOV DL, 12
    INT 10h
    
        
    MOV AH, 9
    LEA DX, titulo
    INT 21h


mal:    
ret




