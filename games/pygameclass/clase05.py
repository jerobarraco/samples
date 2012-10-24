"""
Clase 4
Tema Funciones
"""
import pygame
from pygame.locals import *

FPS = 30
FPSCLOCK = pygame.time.Clock()

pygame.init()
size = (800, 480) #pixels in x, pixel in y
#creamos la pantalla y la guardamos en DISPLAYSURF
DISPLAYSURF = pygame.display.set_mode(size, 0, 32)
pygame.display.set_caption('El juego de Tenzin')
negro = pygame.Color(0, 0, 0)

class Cat:
	x = 10
	y = size[1] - 100
	img = pygame.image.load('cat.png')
	
	def movIzq(self):
		#Obliga al gato a moverse a la izquierda
		#el if permite mover SOLO si el gato esta a mas de 10 pixeles del borde izquierdo
		if self.x > 10:
			self.x -= 10 #lo mismo que decir self.x = self.x -10 (o sea, decrementar en 10)
		
	def movDer(self):
		global size
		width = size[0]
		if self.x < width:
			self.x +=10	
		
	def pintar(self):
		global DISPLAYSURF
		pos = (self.x, self.y)
		#le decimos a LA PANTALLA que COPIE la imagen de catImg en la posicion que dijimos
		DISPLAYSURF.blit(self.img, pos)

cat1 = Cat()

class Rat:
	x = size[0] - 20
	y = size[1] - 100
	img = pygame.image.load('rat.png')
	
	def movIzq(self):
		#Obliga al gato a moverse a la izquierda
		#el if permite mover SOLO si el gato esta a mas de 10 pixeles del borde izquierdo
		if self.x > 10:
			self.x -= 10 #lo mismo que decir self.x = self.x -10 (o sea, decrementar en 10)
		
	def movDer(self):
		global size
		width = size[0]
		if self.x < width:
			self.x +=10	
			
	def update(self):
		#Esta funcion sera llamada por CADA cuadro
		#en cada cuadro queremos movernos un poco a la izq
		self.movIzq()
		
	def pintar(self):
		global DISPLAYSURF
		pos = (self.x, self.y)
		#le decimos a LA PANTALLA que COPIE la imagen de catImg en la posicion que dijimos
		DISPLAYSURF.blit(self.img, pos)
		
rat1 = None

def teclaIzquierda():
	#Procesa los eventos para cuando se presiona la tecla izquierda
	#global nos permite acceder a una variable externa a la funcion que no viene por parametro (eso se llama cochinada)
	global cat1
	cat1.movIzq()
	
def teclaDerecha():
	global cat1
	cat1.movDer()
	
def mouseUp():
	global rat1
	#creamos una rata :D
	#si ya habia una rata, 
	if rat1 != None:
		#la eliminamos :D
		#por la forma que funciona python esto es redundante, pero es bueno saberlo
		del rat1
	rat1 = Rat()
	
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
			elif event.key == K_ESCAPE:
				return False
		#Si el evento no es NI QUIT NI KEYDOWN, el if verifica SI es MOUSEUP
		elif event.type == MOUSEBUTTONUP:
			#Si es mouseup
			mouseUp()
	#Si llegamos hasta aqui, no encontro el return del quit, por lo que devolvemos True
	return True
	
def estados():
	"""
	#######################
	## SEGUNDO : ESTADO  ##
	#######################
	"""
	#por ahora no hay nada que hacer
	if rat1 != None:
		rat1.update()
	return True
	
def pintar():
	"""
	#######################
	## TERCERO : DIBUJAR ##
	#######################
	"""
	#usamos global para acceder las variables externas
	global DISPLAYSURF, cat1
	#"re-iniciamos" la pantalla
	DISPLAYSURF.fill(negro)
	#le decimos al gato que se pinte
	cat1.pintar()
	#Si rat1 existe, 
	if rat1 != None: 
		#entonces dile que se pinte
		rat1.pintar()
	#Actualizamos la pantalla
	pygame.display.flip()
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
	FPSCLOCK.tick(FPS)

print ('fuera del while')
pygame.quit()
