import pygame
from pygame.locals import *

pygame.init()
size = (320, 240) #pixels in x, pixel in y
#creamos la pantalla y la guardamos en DISPLAYSURF
DISPLAYSURF = pygame.display.set_mode(size, 0, 32)
pygame.display.set_caption('El juego de Tenzin')
negro = pygame.Color(0, 0, 0)

catImg = pygame.image.load('cat.png')
#definimos la posicion INICIAL en el eje de las X y las Y
catX = 10
catY = 10

continuar = True
while continuar:
	#######################
	## PRIMERO : EVENTOS ##
	#######################
	#"Reiniciamos" las variables que nos dicen que esta pasando
	mover_derecha = False
	mover_izquierda = False
	
	#le pedimos a pygame una lista de "lo que paso mientras dibujamos"
	eventos = pygame.event.get()
	#recorremos CADA evento en la lista
	for event in eventos:
		#si UNO de esos eventos es IGUAL a "QUIT"
		if event.type == QUIT:
			#Ponemos el valor False en continuar, de manera que el while de arriba termine
			continuar = False
		#SINO, SI evento es una tecla apretada.
		elif event.type == KEYDOWN:
			#Si hay una tecla apretada Y es la flecha izquierda
			if event.key == K_LEFT:
				mover_izquierda = True
			elif event.key == K_RIGHT:
				mover_derecha = True
				
			
	#######################
	## SEGUNDO : ESTADO  ##
	#######################
	if mover_izquierda : 
		catX -= 10
	elif mover_derecha : 
		catX  += 10
	#termina el for y continuamos normalmente
	#creamos la posicion, como una lista de X e Y
	
	
	#######################
	## TERCERO : DIBUJAR ##
	#######################
	#"re-iniciamos" la pantalla
	DISPLAYSURF.fill(negro)
	# tomamos las posiciones calculadas 
	pos = (catX, catY)
	#le decimos a LA PANTALLA que COPIE la imagen de catImg en la posicion que dijimos
	DISPLAYSURF.blit(catImg, pos)
	#Actualizamos la pantalla
	pygame.display.update()
	
	
print ('fuera del while')
pygame.quit()
