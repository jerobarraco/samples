
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h
.data
datos DB "asffdrujkñsalfmxcvpwoeirkdjfKMQWN ROISDMVKLAÑDURQIW$%&/()=?¿-_<>#,;.:*º\{}[]"; aca deberian haber 100 elementos

.code

MOV DX, 0 ; la variable contador I

LA: ; para i = 0 hasta 100
    LEA SI, [datos]
    MOV CX, 50
    SUB CX, DX ; para J = 0 hasta 100-i
    LB:     
        MOV AH, [SI]
        MOV AL, [SI+1]
        CMP AH, AL
        JLE FINSI
            MOV [SI], AL
            MOV [SI+1], AH
        FINSI:
        INC SI
    LOOP LB; este funciona con el CX
    INC DX
    CMP DX, 50
JL LA ;finpara

ret




