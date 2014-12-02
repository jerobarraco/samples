
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h
.data
  how    db 'how are you',13,10,'$'
.code


lea dx, how
 call print
     
mov ax, 4C00h; terminating 
int 21h      

print: 
    ;printing the line
       mov bl, 1  ;color attribute
       mov ah, 9 
       mov al, 0  ;avoding extra characters
       int 10h   ;setear atributo (color)
       
       int 21h ; imprimir
    ret  

ret




