#coding:utf-8
frase = input("ingrese la frase:")
palabras = frase.split(" ")

resultado = ""
cont = 0
for pal in palabras:
	if(cont %2)==0:
		pal = pal[::-1]
	resultado += pal + " "
	cont += 1
print(resultado)	
