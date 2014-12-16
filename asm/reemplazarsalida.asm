org 100h

.data
 cadena db 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.$'
 fila db 0; contador de filas
 col db 0; contador de columnas
 
.code
;configurar el modo de pantalla
mov AX, 0 ; para colocar el modo de pantalla
mov AL, 3 ; modo de 80x25 en color en texto
int 10h ; llamamos a la int 10 para que setee todo


;imprimir el texto primero
mov AH, 9 ; imprime una cadena terminada en $, 
LEA DX, cadena ; 
; vos harias algo asi MOV DX, 800 etc
INT 21h ; lanzamos la interrupcion para imprimir
; hasta aca es como viene el ejercicio dado. 


; aca empieza la parte de reemplazar
; estrategia:
; recorrer la pantalla
; ir caracter por caracter cambiando de posicion el cursor.
; con dos bucles, uno para las filas y otro para las columnas.
;       comparar si el caracter actual es una A
;           si es escribimos una B

; 1ro el bucle
MOV AH, 1h
MOV CX,0007h
INT 10h

;recorrer la pantalla (80x25)

MOV fila, 0; iniciamos en la fila 0 
;ya esta inicializado en la seccion data, pero es buena costumbre siempre inicializar antes del loop

LFILA: ; recorro las filas

    ; recorrer la columna
    MOV col, 0 ; importante inicializar porque este codigo se repite muchas veces
    ; ya que esta dentro del loop LFILA
    LCOL:
        ; colocar el cursor donde queremos leer
        MOV AH, 2       ; establecer posicion del cursor
        MOV DH, fila    ; las coordenadas en pantalla son
        MOV DL, col     ; (DH, DL)
        INT 10h         ; llamar        
        
        ; leer el caracter
        MOV AH, 8   ; lee atributo y caracter debajo de cursor
        MOV BX, 0   ; pagina
        INT 10h     ; llamamos a la interrupcion
        
        ; comparar 
        CMP AL, 'a' ;   si es A escribir B
        JNE FINSI   ; sino, no
            MOV AL, 'b' ;
        FINSI:
        
        ; Escribir el cambio en pantalla
        MOV AH, 9h  ; escribir caracter en donde este el cursor
        ;AL ya esta listo de antes
        MOV BH, 0   ; pagina
        MOV BL, AH  ; el atributo me lo daba en AH el int 10 ah8
        MOV CX, 1   ; escribir una sola vez
        INT 10h
                
        INC col ; pasamos a la siguiente columna
        CMP col, 80 ; contamos solo hasta 79
        JL LCOL; finmientras
    
        
    ; incremento la fila, estas 3 lineas son similares a lo que haria el LOOP con CX
    ; pero loop usa siempre CX y las interrupciones nos usan cx tambien
    INC fila; pasamos a la proxima fila
    CMP fila, 25 ; comprobamos si llego al final
    JL LFILA ; siempre que sea menor, vamos a volver a ejecutar
    ;finmientras
;

ret




