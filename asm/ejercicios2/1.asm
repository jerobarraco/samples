
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h

MOV AH, 0F3H
MOV BH, 04h
ADD AH, BH
SHL AH
INT 20

ret




