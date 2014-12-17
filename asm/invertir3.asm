org 100h
.data
      cadena db 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor',13,10,'incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.$' 
      color db 0
.code

;MOV CX, 19; contar 20
 
MOV AH, 00
MOV AL, 02
INT 10h; modo video 80x25 b/n   

MOV AH, 02
MOV DL, 00
MOV DH, 00
INT 10h; coloca el cursor en 0,0
;imprimir el texto primero

mov AH, 9 ; imprime una cadena terminada en $, 
LEA DX, cadena ; 
; vos harias algo asi MOV DX, 800 etc
INT 21h ; lanzamos la interrupcion para imprimir
; hasta aca es como viene el ejercicio dado. 

;mov [300], 128

mov cx, 1
mov dH, 0
**:
    MOV DL, 0
    *:
    
        MOV AH, 02  ; posicion del cursor
        INT 10h     ;

        ; leer caracter
        MOV AH, 8h   ; lee atributo y caracter debajo de cursor
        MOV Bh, 0   ; pagina
        INT 10h     ; llamamos a la interrupcion
        ;queda en AL  
                           
        MOV AH, 09h ; setear atributo y caracter bajo el cursor
        MOV BL, 71h ; atributo 71 (invertir)
        ;mov bl, color ; para jugar con colores
        ;inc color
        INT 10h
        
        INC DL      ; incrementa las columnas ??
        CMP DL,80  ;
        JL *
    INC DH  ; incrementa las filas
    CMP DH, 25  
    JL **
;LOOP **
mov ah, 1
int 21h

INT 20