org 100h
.data
      cadena db 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor',13,10,'incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.$' 
      color db 0
.code
                          
;Importante primero, antes de empezar a codificar, establecer la estrategia:
; establecer modo pantalla
; ir al principio
;   escribir un caracter
;   avanzar fila y columna
; repetirlo varias veces, hasta el maximo (25)
;---------------------------------------------

; establecer modo pantalla
MOV AH, 0   ; modo pantalla
MOV AL, 3   ; 80x25 color                   
INT 10h     ; 

; ir al principio
MOV DH, 0   ; fila
MOV DL, 0   ; columna
L:
    MOV AH, 2   ; para establecer la pos del cursor  
    MOV BH, 0   ; pagina
    INT 10h
    
    ;   escribir un caracter
    MOV AH, 9   ; para escribir un caracter
    MOV CX, 1   ; cant
    MOV AL, '*' ; caracter
    MOV BL, 4   ; rojo  
    INT 10h            
    
    ; o usando el int 21 
    ;MOV BL, DL ; como usa dl, primero guardo el valor en bl, 
    ;MOV AH, 2  ; funcion salida caracter
    ;MOV DL, '*'; caracter
    ;INT 21h   
    ;MOV DL, BL ; al final restauro el valor original
    ; lo bueno es que la int21 no usa CX, que puedo usar para un contador
    ; lo malo es que no puedo asignar color
        
    ;   avanzar fila y columna
    INC DL
    INC DH
    
    ; repetirlo varias veces, hasta el maximo (25)
    CMP DL, 25
    JL L        

ret