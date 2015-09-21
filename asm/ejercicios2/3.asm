
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h

MOV AH, 045h ; [210]
MOV BH, 073h ; [211]
SUB AH, BH
MOV AL, 02h
MUL AH
SHR AL, 1
MOV [215], AH

ret




