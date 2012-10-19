import pygame
from pygame.locals import *

pygame.init()
size = (320, 240) #pixels in x, pixel in y
#creamos la pantalla y la guardamos en DISPLAYSURF
DISPLAYSURF = pygame.display.set_mode(size, 0, 32)

pygame.display.set_caption('El juego de Tenzin')

catImg = pygame.image.load('cat.png')
#definimos la posicion INICIAL en el eje de las X y las Y
catX = 10
catY = 10

continuar = True
while continuar:
	#le pedimos a pygame una lista de "lo que paso mientras dibujamos"
	eventos = pygame.event.get()
	#recorremos CADA evento en la lista
	for event in eventos:
		#si UNO de esos eventos es IGUAL a "QUIT"
		if event == QUIT:
			#Ponemos el valor False en continuar, de manera que el while de arriba termine
			continuar = False
	#termina el for y continuamos normalmente
	#creamos la posicion, como una lista de X e Y
	pos = (catX, catY)
	#le decimos a LA PANTALLA que COPIE la imagen de catImg en la posicion que dijimos
	DISPLAYSURF.blit(catImg, pos)
	#Actualizamos la pantalla
	pygame.display.update()
	
	
print ('fuera del while')
pygame.quit()
