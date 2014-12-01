org 100h                      
.data
datos DB "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!$%&/()=?¿-_<>#,;.:*º\{}[]";
.code
;pasar a positivo los valores
; inicializamos el puntero de inicio
;MOV SI, 300
LEA SI, [datos]

MOV CX, 50
L:
    MOV AL , [SI]
    SUB AL, 54 ; simulo un negativo
           
    CMP AL, 0; pone el FS a 1 si es neg
    JNS FINSI ; si es positivo no hacemos nada.
        NEG AL;sino lo negamos 
    FINSI:                             
    MOV [SI], AL; y lo reemplazamos
    INC SI
LOOP L

ret




