
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h
.data
datos DB "asffdrujkñsalfmxcvpwoeirkdjfKMQWN ROISDMVKLAÑDURQIW$%&/()=?¿-_<>#,;.:*º\{}[]"; aca deberian haber 100 elementos

.code

MOV DX, 0 ; la variable contador I 
LA: ; para I = 0 hasta 100
    LEA SI, [datos]
    MOV CX, 100 ; inicializamos el contador en 100 elementos (J)
    SUB CX, DX ; para J = 0 hasta 100-i 
    LB:     
        MOV AH, [SI] ; cargamos dos valores consecutivos
        MOV AL, [SI+1]
        CMP AH, AL ;y los comparamos
        JLE FINSI; si el primero es MENOR O IGUAL saltamos
            MOV [SI], AL; sino (si es mayor al otro) los intercambiamos
            MOV [SI+1], AH
        FINSI:
        INC SI ; siguiente dato
    LOOP LB; este funciona con el CX
    INC DX ; el loop trabaja con CX, emulamos un loop con DX, primero lo incrementamos (este va de 0 a 100)
    CMP DX, 100 ; despues lo comparamos con el máximo (100)
JL LA ;finpara

ret




