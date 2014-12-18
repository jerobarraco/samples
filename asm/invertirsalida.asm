org 100h

.data
cadena db 'BIOS interrupt calls are a facility that operating systems and application programs use to invoke the facilities of the Basic Input/Output System on IBM PC compatible computers. Traditionally, BIOS calls are mainly used by MS-DOS programs and some other software such as boot loaders (including, mostly historically, relatively simple application software that boots directly and runs without an operating system especially game software.) BIOS only runs in the real address mode (Real Mode) of the x86 CPU, so programs that call BIOS either must also run in real mode or must switch from protected mode to real mode before calling BIOS and then switch back again. For this reason, modern operating systems that use the CPU in Protected Mode generally do not use the BIOS to support system functions$'
cadenab db ', although some of them use the BIOS to probe and initialize hardware resources during their early stages of booting. In all computers, software instructions control the physical hardware (screen, disk, keyboard, etc.) from the moment the power is switched on. In a PC, the BIOS, preloaded in ROM on the mainboard, takes control immediately after the processor is reset, including during power-up or when a hardware reset button is pressed. The BIOS initializes the hardware, finds, loads and runs the boot program (usually, but not necessarily, an OS loader), and provides basic hardware control to the operating system running on the machine, which is usually an operating system but may be a directly-booting single software application. Many modern operating systems$'
cadenac db ' (such as newer versions of Windows and Linux) bypass the built-in BIOS interrupt communication system altogether, preferring to use their own software to control the attached hardware directly. The original reason for this was primarily that these operating systems run the processor in protected mode, whereas calling BIOS requires switching to real mode and back again, and switching to real mode is slow. However, there are also serious security reasons not to switch to real mode, and the BIOS code has limitations both in functionality and speed that motivate operating system designers to find a replacement for it. In fact, the speed limitations of the BIOS made it common even in the MS-DOS era for programs to circumvent it in order to avoid its performance limitations, $'
cadenad db 'especially for video graphics display and fast serial communication. The problems with BIOS functionality include limitations in the range of functions defined, inconsistency in the subsets of those functions supported on different computers, and variations in the quality of BIOSes (i.e. some BIOSes are complete and reliable, others are abridged and buggy). By taking matters into their own hands and avoiding reliance on BIOS, operating system developers can eliminate some of the risks and complications they face in writing and supporting system software. On the other hand, by doing so those developers become responsible for providing bare-metal driver software for every different system or peripheral device they intend for their operating system to work with$'
; (or for inducing the hardware producers to provide those drivers). Thus it should be apparent that compact operating systems developed on small budgets would tend to use BIOS heavily, while large operating systems built by huge groups of software engineers with large budgets would more often opt to write their own drivers instead of using BIOS that is, even without considering the compatibility problems of BIOS and protected mode.$'
 ;como estamos cortos de registros vamos a almacenar los contadores y los auxiliares en una memoria auxiliar
 fila db 0; contador de filas
 col db 0; contador de columnas  
 uno db 0; 
 dos db 0;
 
.code   

;configurar el modo de pantalla
mov AX, 0 ; para colocar el modo de pantalla
mov AL, 3 ; modo de 80x25 en color en texto
int 10h ; llamamos a la int 10 para que setee todo

; mostramos el cursor
MOV AH, 1h
MOV CX, 0007h
INT 10h

;imprimir el texto primero
mov AH, 9 ; imprime una cadena terminada en $, 
LEA DX, cadena ; 
; vos harias algo asi MOV DX, 800 etc
INT 21h ; lanzamos la interrupcion para imprimir
lea dx, cadenab
int 21h          
lea dx, cadenac
int 21h
lea dx, cadenad
int 21h
; hasta aca es como viene el ejercicio dado. 


; aca empieza la parte de reemplazar
; estrategia:
; recorrer la pantalla
; ir caracter por caracter cambiando de posicion el cursor.
; con dos bucles, uno para las filas y otro para las columnas.     
;   el bucle de las filas llega solo hasta la mitad (12)
;       Leer el caracter en la columna y fila, 
;       Leer el caracter en la columna y fila = 25-fila
;       Escribimos en la col, fila el 2do caracter leido
;       Escribimos en la col, 25-fila el 1er caracter leido


MOV fila, 0; iniciamos en la fila 0 
;ya esta inicializado en la seccion data, pero es buena costumbre siempre inicializar antes del loop

LFILA: ; recorro las filas

    ; recorrer la columna
    MOV col, 0 ; importante inicializar porque este codigo se repite muchas veces
    ; ya que esta dentro del loop LFILA
    LCOL:
        ; leer caracter en col, fila
        ;   1ro poner el cursor
        MOV AH, 2h; pos cursor
        MOV BH, 0
        MOV DH, fila
        MOV DL, col
        INT 10h
        ;   2do leemos lo que esta ahi
        MOV AH, 8h  ; lectura
        MOV BH, 0   ; pagina
        INT 10h
        MOV uno, AL ; guardamos el caracter leido para despues.
        
        ; leer el otro caracter en col, 24-fila                            
        ;   1ro poner el cursor
        MOV AH, 2h  ; pos cursor
        MOV BH, 0   ; pagina
        MOV DH, 24  ; fila
        SUB DH, fila; fila es 24-fila
        MOV DL, col ; col
        INT 10h
        
        ;   2do leer el caracter ahi
        MOV AH, 8h
        MOV BH, 0
        INT 10h
        MOV dos, AL ; guardamos el otro caracter
        
        
        ; escribir los caracteres invertidos
        ;   1ro escribir el uno, ya que estamos posicionados al final de la pantalla
        MOV AH, 9h  ; escribir 
        MOV BH, 0h  ; pagina
        MOV BL, 9   ; atributo
        MOV AL, uno ; caracter
        MOV CX, 1   ; cant
        INT 10h
        
        ;   2do escribir "dos", 
        ;       1ro cambiar cursor arriba
        MOV AH, 2h
        MOV BH, 0h
        MOV DH, fila
        MOV DL, col
        INT 10h
        ;       2do escrbir el caracter
        MOV AH, 9h
        MOV BH, 0h
        MOV BL, 9
        MOV CX, 1
        MOV AL, dos
        INT 10h
        
        
                            
        MOV DX, 5000
        mov CX, 10000
        int 15h 
        INC col ; pasamos a la siguiente columna
        CMP col, 80 ; contamos solo hasta 79
        JL LCOL
        ;finmientras
    ; incremento la fila, estas 3 lineas son similares a lo que haria el LOOP con CX
    ; pero loop usa siempre CX y las interrupciones nos usan cx tambien
    INC fila; pasamos a la proxima fila
    CMP fila, 13 ; comprobamos si llego al final
    JL LFILA ; siempre que sea menor, vamos a volver a ejecutar
    ;finmientras
;
mov ax,0
int 16h
ret




