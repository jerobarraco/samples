
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h
.data
datos DB 045h, 073h, 0F2h, 00h, 00h, 00h, 00h, 00h, 00h, 00h, 0B6h ; aca deberian haber 100 elementos

.code
       
LEA SI, [datos]     ;102h ;256 +10E (270)

MOV SI, 105h ; 0213
MOV BX, 102h; 0210
MOV AL, [BX]     
INC BX
MOV AH, [BX]
AND AL, 08
OR AH, 08
MOV [SI], AH
INC SI
MOV [SI], AL

ret




