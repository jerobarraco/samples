mov [200], 62
mov ah, [200]
and ah, 0Fh
cmp ah, 9
jbe salto
add ah, 6
salto: 
mov [210], ah
int 20