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
MOV AH, 2   ; posicionar cursor
MOV DH, 12  ; fila
MOV DL, 0   ; col
INT 10h     ; go

; loop: recorremos las columnas
MOV DL, 0   ; col
L:; por cada columna:    
    ;   Borrar la A anterior, escribiendo un espacio
    MOV AH, 9   ; escribir
    MOV BH, 0   ; pagina
    MOV CX, 1   ; cant
    MOV AL, ' ' ; espacio   
    MOV BL, 1   ; atributo
    INT 10h
    
    ;   imprimir la A
    ;   posicion cursor
    INC DL      ;   ADD DL, 1 es lo mismo
    
    MOV AH, 2   ;   posicion
    ; dh y dl ya estan
    INT 10h
    
    MOV AH, 9   ; escribir
    MOV BH, 0   ; pagina
    MOV CX, 1   ; cant
    MOV AL, 'A' ; espacio   
    MOV BL, 1   ; atributo
    INT 10h 
     

    ;MOV CX, 00Fh 
    ;MOV DX, 8480h
    ;MOV AH, 86h
    ;INT 15h
    
    CMP DL, 80
    JL L        ; Finmientras
; el loop lo uso unicamente con CX
; si quiero contar con cualquier otra cosa necesito un CMP y un Jump                        
; en realidad eso es lo que hace el loop
; Loop:
;   DEC CX
;   CMP CX, 0 
;   JNE L
ret




