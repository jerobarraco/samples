org 100h
.data
nums DB "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!$%&/()=?¿-_<>#,;.:*º\{}[]"; aca deberian haber 100 elementos
; la direccion de nums en el ejercicio seria 500
; la de pares 600, e impares 601
pares DB 0
impares DB 0  

.code           
; segun el ejercicio deberia ser mov bx, 500
; en bx guardamos la DIRECCION de los datos,
; o sea, la direccion del PRIMER elemento
lea bx, [nums]

;inicializamos el contador; int i = 100
mov cx, 100 
;hacemos todo
L:;por cada elemento 
    ; colocamos el elemento en ax para trabajar
    ; tomamos el elemento en la direccion que APUNTA bx
    ; bx tendria la DIRECCION de cada elemento
    mov al, [bx]
    ; "dividimos" por dos (lo que nos interesa es el resto)
    ; al hacer un shift, como la base es 2, estamos dividiendo por dos
    ; el bit que sobra se mete en CF (carry flag)
    ; si ese bit es 1, el numero era impar
    shr al, 1
    ;AND al, 1
	
    ; si el carry es 1 entonces era impar, y saltamos ahi
    ; if (ax%2==0){}
    JC impar; 
    ;JNZ impar;si uso el AND uso este jump
    ; Si el carry era 0 se ejecuta esto. e sea, par
    PAR:        
    	; en el ejercicio seria inc [601]
       inc pares                  
       ; si era par tenemos que saltearnos la parte impar
       ; lo que seria saltar hasta el "FinSi"
       jmp TERM 
    IMPAR:; else{}
		inc impares    
    TERM:
    ; pasamos al siguiente elemento
    ; como dx tiene la direccion, al incrementarlo nos queda
    ; apuntando al siguiente elemento (siempre que el elemento sean bytes)
    inc bx
    loop L;decrementa CX _Y_ salta a el label (L) si CX NO es zero
;dec cx;decrementar (i--)
;jnz ini; while (i!=0)

;L:      do 
;loop L  while( --i != 0)

ret

