org 100h
.data
datos DB "0123401234acb~eabcde"; aca deberian haber 10 elementos

.code

;MOV SI , 200 ; posicion donde comienzan los datos
LEA SI, [datos]
MOV CX, 20; inicializamos el contador
MOV BH, 0 ; inicializamos el mayor
L:;1 encontrar el mayor 
    ; recorrer    
    ;  comparar
    CMP BH, [SI]
    JGE FinSi ; si no es mayor salteamos la parte de asignacion
        MOV BH, [SI]; si es mayor lo almacenamos
    FinSi:
    INC SI
LOOP L
;Aca ya tenemos el mayor en BX

;2 convertir a bcd
AND BH, 0Fh ; dejamos la parte baja del numero, que sirve para numeros del 0 al 15
CMP BH, 9
JLE Fin
;si es mayor a 9 le sumamos 6, sino, que salte                                                    
    ;sumamos
    ADD BH, 6
Fin:

;3 almacenar en la pos 300
MOV [300], BH