org 100h
.data
datos DB "0123401234"; aca deberian haber 10 elementos

.code
;inicializar

;MOV BX, 016Fh ; direccion donde comienza el vector (los datos)
LEA SI, [datos]
MOV AX, 0
MOV DX, 10; cant
; recorrer el vector
MOV CX, DX; iniciamos el contador con la cantidad de datos
L:; do{
    ; por cada elemento lo acumulamos  
    MOV BX, 0; borro lo que quedo del bucle anterior
    MOV BL, [SI]; uso BL para que lea solo un byte de [SI]
    ADD AX, BX; sumo a AX con BX porque si sumo en AL seguro me quedo corto (overflow)
    INC SI; paso al siguiente elemento
LOOP L; while(--cx); dec cx, JZ L

; al final, dividmos por la cant
DIV DL; ah = AX %10(resto), AL = AX/10(cociente)
;Divido por DL para que me haga una division por Byte
; AL = AX / DL 
; AH = AX % DL

; si divido por DX hace
; AX = (DXAX)/OPERANDO  (DX)
; DX = DXAX % OPERANDO

; almacenar resultado
MOV [200], AL

; asi funcionaria un loop
; int cx = 10;
; do{
; }while(--cx);