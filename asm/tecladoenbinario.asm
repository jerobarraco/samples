MOV CX, 0008
;pones en 0000 el registro ES, para ir a la página 0000
MOV AX, 0000
MOV ES, AX
;ES:
;traes aBH el contenido de la direccion donde se encuentra la informacion de estado de teclado:
MOV BH, [4017]
;vas a mostrar en pantalla lo que haya en el registro BH
MOV AH, 02
;Desplazas 1 bit a BH y el bit que sale va al bit de acarreo C
SHL BH
;Pones en 00 a DL para luego sumar con acarreo y en DL queda 30 ó 31 seg{un el contenido del bit C. Esto en ASCII es 0 ó 1 que va a aparecer en la pantalla 8 veces que es el contenido del registro BH

MOV DL, 00

ADC DL, 30

INT 10h

LOOP*
INT 20h
 
 
 
 
 
L1
 
MOV CX,19
MOV AH,00
MOV AL,02
INT10
MOV AH,02
MOV DL,00
MOV DH,00
INT10
*MOV AH,09
MOV BL, 71
INT 10
 
MOV AH,02
INT 10
INC DL
CMP DL, 50
JNE*
INC DH
LOOP**
INT 20
 