
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h 
MOV [210h], 045h
MOV [211h], 073h
MOV [212h], 0F2h
MOV [220h], 0B6h


MOV BP, 01h
MOV DH, 04h
MOV SI, 0300h
MOV BX, 0210h
MOV CX, 03h

**: MOV AH, 00h
MOV AL, [BX]
DIV DH
MOV [SI], AL
MOV [SI+BP], AH
INC BX
INC SI
INC SI
LOOP **


ret




