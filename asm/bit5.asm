
org 100h
.data
datos DB "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!$%&/()=?¿-_<>#,;.:*º\{}[]"; aca deberian haber 100 elementos
cant DB 0

.code
;MOV SI, 500
LEA SI, [datos]
MOV CX, 100
; MOV [20Ah], 0
MOV cant, 0

L:
    MOV BH, [SI]
    AND BH, 16 ; dejo solo el 5to bit
    JZ Fin
        ; si no es 0 (tiene la pata 5 a 1)
        ;INC [20Ah]
        INC cant
    Fin:
    INC SI
LOOP L

ret




