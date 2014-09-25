global main

main:
    int 3
    mov cl, 0xfa
    mov al, 0x1f
    mov bl, 7
    shr bl,1
    or AL, BL
    mov cl, al
    mov eax, 0
    ret
