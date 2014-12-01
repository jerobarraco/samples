
org 100h
.data
datos DB "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!$%&/()=?¿-_<>#,;.:*º\{}[]"; aca deberian haber 100 elementos
cpos DB 0
cneg DB 0

.code

;inicializar
; contadores
MOV cpos, 0
MOV cneg, 0 

; pos de los datos
;MOV SI, 300
LEA SI, [datos]


; recorrer los datos
MOV CX, 100
L: ; do{
    ; por cada uno comparar para ver si es positivo
    MOV AL, [SI];
    SUB AL, 54; Simular numero negativos
    
    CMP AL, 0; comparo para saber si es neg o pos, esto afecta los flags
    JS ESNEG; Si el resultado dio negativo, saltamos a la parte del negativo, 
    ; sino, continuamos normal
    ESPOS:; si pos
        ; incremento la cuenta pos        
        INC cpos ; INC [200]
        JMP FINSI ;    
    ESNEG: ; sino
        ; inc neg
        INC cneg ; INC [201]
    FINSI:
    INC SI
LOOP L;}while(--cx)


ret




