org 100h 
MOV AX, 0
MOV AL, 3
INT 10h


MOV CX, 008; contador para cada bit del registro de estado
;poner en 0 el segmento porque ahi esta el buffer de teclado
MOV AX, 0; como solo se puede cambiar ES mediante un registro usamos ax
MOV ES, AX 

MOV Bx, [4017h];lee la posicion de memoria que contiene el FLAG de estado del teclado
;MOV AH, 2h
;int 16h
;MOV BH, 234  ;por ejemplo
MOV AH, 02h ; salida de caracter por pantalla para el int 21

*:
    SHL BH, 1 ; pasamos un bit al carry
    MOV DL, 00
    ADC DL, 30h; se lo sumamos a 30 y almacenamos en dl, es lo mismo que pasarlo a Ascii (30h en ascii es '0')
    int 21h; imprimir
LOOP *

ret




