org 100h
msg: db 'hola_esto_es_un_ejemplo_de_una-linea-de.texto que es muy largo para que llene la mayor parte de la pantallaLorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur sodales ligula in libero. Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. Aenean quam. In scelerisque sem at dolor. Maecenas mattis. Sed convallis tristique sem. Proin ut ligula vel nunc egestas porttitor$'

mov ax, 3
int 10h                    

mov ah, 9
mov dx, msg
int 21h ; imprimir
int 21h ; imprimir
int 21h ; imprimir


mov     bl, 0   ; current attributes.
; set cursor position at (dl,dh):

mov si, 0

mov     dh, 0   ; current row.
**:                           
    mov     dl, 0   ; current column.
    *:
        mov     ah, 02h
        int     10h
        
        mov ah, 8 ; leer caracter y atributo
        mov bh, 0 ; pagina
        int 10h
        
        ;mov bl, ah  ; copia el atributo original
        mov bx, si ; asigno en bl el contador
        
        rol bl, 4 ; esto invierte el atributo, comentarlo para ver el efecto
        
        mov ah, 9
        mov bh, 0
        mov cx, 1
        int 10h ; escribir caracter y atributo
        ;loop *
        inc dl
        inc si
        cmp dl, 80
        jl *
    inc dh
    cmp dh, 25
    jl **
ret
