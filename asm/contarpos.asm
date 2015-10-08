
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt
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
        inc dl
        jmp finsi        
    pos:
    ;pos  
        inc dh
    finsi:
    
    inc bx
    loop L

mov [res], dh
mov [res+1], dl
 

ret