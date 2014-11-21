
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt


org 100h
.data
nums DB "ojsdlfkjd{SAFÑWER_:;ZXCVW$%&][*¨1234567890'¿"
pares DB 0
impares DB 0  

.code           
; datos falsos
; segun el ejercicio deberia ser mov ax, 500
; en dx guardamos la DIRECCION de los datos,
; o sea, la direccion del PRIMER elemento
mov dx, @data
add dx, 2

;inicializamos el contador; int i = 100
mov cx, 100 
;hacemos todo
L:;por cada elemento 
    ; colocamos el elemento en ax para trabajar
    ; tomamos el elemento en la direccion que apunta dx
    mov ax, [dl]   
    ; "dividimos" por dos (lo que nos interesa es el resto)
    ; al hacer un shift, como la base es 2, estamos dividiendo por dos
    shr ax, 1
    
    ; pasamos al siguiente elemento
    ; como dx tiene la direccion, al incrementarlo nos queda
    ; apuntando al siguiente elemento (siempre que el elemento sean bytes)
    inc dx
loop L;decrementa CX _Y_ salta a el label (ini) si CX NO es zero
;dec cx;decrementar (i--)
;jnz ini; while (i!=0)

;L:      do 
;loop L  while( --i != 0)


ret