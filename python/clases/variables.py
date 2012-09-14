# -*- coding: utf-8 -*-
consigna= """Deducir la edad a partir del año de nacimiento y 
el año actual"""

"""
1) (Datos variables)
	año nacimiento
	año actual
	
3) (Datos a informar)
	edad
	
2) Transformaciones. Relaciones o condiciones.
  año actual menos el año de nacimiento , nos da, la edad.
		
"""

#1º Obtener los datos
a_nacimiento = input("Ingrese su año de nacimiento: ")
a_actual = input("Ingrese el año actual: ")

#2 Calcular la edad
edad = a_actual - a_nacimiento
print( "Su edad es de", edad, "años")