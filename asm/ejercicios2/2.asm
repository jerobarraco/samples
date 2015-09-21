
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h

;MOV AL, [0220h]
MOV AL, 0B6h
MOV BL, 07h
SHR BL, 1
OR AL,BL    
NEG AL
MOV [0250h], AL
INT 20


ret




