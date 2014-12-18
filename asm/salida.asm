
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h
        
mov Cx, 5
L: 
    mov DL, 7h
    mov AH, 2
    int 21h
LOOP L

mov ah,0
mov al, 1
int 10h
mov ah, 1
INT 21h


mov ah,4Ch
mov al, 0
int 21h
ret




	