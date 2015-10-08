org 100h
.data
  res db 0,0

.code
mov cx, 10h
mov dx, 0h; dh pos, dl neg
LEA bx, L

L:
    mov al, [bx]
    cmp al, 0  
    jge pos
    ;neg   
        ;mov ah, 0
        ;sub ah, al
        neg al
        jmp finsi        
    pos:
    ;pos  
        ;inc dh
    finsi:
    ;guardar el dato
    inc bx
    loop L

mov [res], dh
mov [res+1], dl
 

ret