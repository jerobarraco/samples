org 100h
;estrategia
; establecer modo de pantalla
; ubicar la fila
; loop: recorremos las columnas
;   por cada columna:
;       Borrar la A anterior
;       imprimir la A

; establecer modo de pantalla
MOV AH, 0   ; modo pantalla
MOV AL, 3   ; 80x25 color
INT 10h     ; go

; ubicar la fila

MOV DH, 12  ; fila
MOV DL, -1   ; col
call posicion

; loop: recorremos las columnas
MOV DL, 0   ; col
L:; por cada columna:    
    ;   Borrar la A anterior, escribiendo un espacio
    MOV AL, ' ' ; espacio
    call escribir
    
    ;   imprimir la A
    ;   posicion cursor
    INC DL      ;   ADD DL, 1 es lo mismo
    
    call posicion
    
    MOV AL, 'A' ; espacio   
    call escribir
    
    CMP DL, 80
    JL L        ; Finmientras
ret ; finprograma

 
posicion: 
    MOV AH, 2   ; posicionar cursor
    INT 10h     ; go
ret

escribir:
    MOV AH, 9   ; escribir
    MOV BH, 0   ; pagina
    MOV CX, 1   ; cant
    MOV BL, 4   ; atributo
    INT 10h
ret


ret




