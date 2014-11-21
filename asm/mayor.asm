	mov dl, 0
	mov cx, 20
	mov bx, 200
ini:
	mov al, [bx]
	cmp al, dl
	jng nomayor
	mov dl, al
nomayor:
	inc bx
	dec cx
	jnz ini
fin:
	int 20