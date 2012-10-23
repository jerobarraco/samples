"""
Clase 4
Tema Funciones
"""
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

def teclaIzquierda():
	#Procesa los eventos para cuando se presiona la tecla izquierda
	#global nos permite acceder a una variable externa a la funcion que no viene por parametro
	global catX
	catX -= 10
	
def teclaDerecha():
	global catX
	catX += 10
	
def eventos():
	"""
	#######################
	## PRIMERO : EVENTOS ##
	#######################
	"""
	#le pedimos a pygame una lista de "lo que paso mientras dibujamos"
	eventos = pygame.event.get()
	#recorremos CADA evento en la lista
	for event in eventos:
		#si UNO de esos eventos es IGUAL a "QUIT"
		if event.type == QUIT:
			#SALIMOS de la funcion, devolviendo un valor falso
			return False
		#SINO, SI evento es una tecla apretada.
		elif event.type == KEYDOWN:
			#Si hay una tecla apretada Y es la flecha izquierda
			if event.key == K_LEFT:
				#si se presiono la tecla izquierda llamamos directamente a la funcion que lo maneja
				teclaIzquierda()
			elif event.key == K_RIGHT:
				#lo mismo con la tecla derecha
				teclaDerecha()
	#Si llegamos hasta aqui, no encontro el return del quit, por lo que devolvemos True
	return True
	
def estados():
	"""
	#######################
	## SEGUNDO : ESTADO  ##
	#######################
	"""
	#por ahora no hay nada que hacer
	return False
	
def pintar():
	"""
	#######################
	## TERCERO : DIBUJAR ##
	#######################
	"""
	#usamos global para acceder las variables externas
	global DISPLAYSURF, catX, catY, catImg
	#"re-iniciamos" la pantalla
	DISPLAYSURF.fill(negro)
	# tomamos las posiciones calculadas 
	#creamos la posicion, como una lista de X e Y
	pos = (catX, catY)
	#le decimos a LA PANTALLA que COPIE la imagen de catImg en la posicion que dijimos
	DISPLAYSURF.blit(catImg, pos)
	#Actualizamos la pantalla
	pygame.display.update()
	return False
		
continuar = True
while continuar:
	#simplificamos los 3 pasos
	#1) procesar eventos 
	#Devuelve False en caso de encontrar un QUIT, lo guardamos en continuar,
	#Eso hara que salga del while si encuentra un QUIT
	continuar = eventos()
	#procesamos los estados
	estados()
	#pintamos TODO
	pintar()

print ('fuera del while')
pygame.quit()
