
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt
org 100h
.data
ARR db 23h, 45h, 28h, 32h, 55h, 54h, 13h, 11h, 7h, 2h 


.code

; add your code here    
LEA BP, ARR
;MOV BP, OFFSET ARR;900  SAME AS THIS
MOV SI, 0

**:

    MOV AL, [BP+SI]
    ADD AL, 5h
    MOV [BP+SI], AL
    
    INC SI
    CMP SI, 10
    
    JNZ **


;USING CX


LEA BP, ARR
MOV CX, 10
*:
    MOV AL, [BP]
    ADD AL, 5h
    MOV [BP], AL  
    
    INC BP
LOOP *

ret



