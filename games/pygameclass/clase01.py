import pygame
pygame.init()
size = (320, 240) #pixels in x, pixel in y
pygame.display.set_mode(size)
pygame.display.set_caption('EL juego de Tenzin')

continuar = True

while continuar:
	pygame.display.update()
	
print ('fuera del while')
pygame.quit()
