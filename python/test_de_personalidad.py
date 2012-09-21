# -*- coding: utf-8 -*-

# Copyright : MoonGate & Jerónimo Barraco Mármol 2012
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
# 
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
# 
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.

opts=( 'optimista', 'cerebral', 'dominante', 'sociable','creativo', 'idealista', 'pragmatico', 'responsable', 'conciliador')

pregs=[
  ( (2, "los problemas nunca me asustan"),
    (8, 'siempre trato de evitar las peleas') ),
  ( (5, 'todos dicen que parezco simpatico, desenvuelto y ambicioso'),
    (4, 'soy muy idealista y digo las cosas de frente') ),
  ( (1, 'me cencentro y pongo todas mis energias cuando estoy trabajando'),
    (0, 'me enganta divertirme y organizar fiestas con amigos') ), 
  ( (3, 'me resulta muy facil conocer gente y hacer nuevos amigos'),
    (4, 'soy muy timido y a veces me aislo')  ),
  ( (7, 'no se porque pero me pongo nervioso por pavadas'),
    (8, 'suelo enfrentar los probemas con tranquilidad')),
  ( (2, 'acepto las cosas como son, aunque me resulten dificiles'),
    (5, 'me enganta pelear hasta el final para lograr mis propositos')),
  ( (2, 'soy demostrativo y necesito del afecto de mis amigos'),
    (1, 'la gente me considera distante y qlgo frio')),
  ( (6, 'antes de tomar una desicion evaluo muy bien sus pro y sus contras'),
    (0, 'la condicion para aceptar un proyecto es que me resulte divertido')),
  ( (4, 'vivo pensando solo en mi'),
    (8, 'vivo pensando demasiado en los demas')),
  ( (1, 'todo lo que consegui fue por mi intuicion y mis conocimientos'),
  (2, 'todo lo que consegui fue por mi energia y fuerza de voluntad')),
  ( (7, 'siempre sufro cuando no puudo complir algo'),
    (5, 'siempre estuve muy seguro de mi mismo')),
  ( (3, 'el centro de mi vida esta en los afectos'),
    (6, 'el centro de mi vida es alcanzar los objetivos que me propuse' )),
  ( (4, 'muchas veces me siento debil y no puedo defenderme'),
    (0, 'soy capaz de decir las cosas que nadie se anima')),
  ( (7, 'me gusta tener todas las variables bajo control'),
    (2, 'soy audaz y no le tengo miedo a nada nuevo')),
  ( (8, 'me cuesta decir quien tiene la razon porque creo que cada uno siempre tiene parte de la verdad'), 
    (3, 'Tengo problemas porque soy muy posesivo')),
  ( (7, 'Casi siempre soy prudente y organizado'),
    (0, 'Me atraen mucho las aventuras y experiencias desconocidas')),
  ( (3, 'Me considero generoso y me encanta estar acompaniado'),
    (5, 'Soy un poco reservado y me gusta discutir los problemas')),
  ( (2, 'Muchas veces siento la necesidad de ser duro como una roca'),
    (6, "Soy muy exigente conmigo mismo y no me perdono los errores")),
  ( (1, 'Defiendo a muerte mi independencia y mi derecho a decir lo que pienso'),
    (8, 'Defiendo a muerte mi tranquilidad y estabilidad')),
  ( (4, 'Mi costumbre de creerme superior irrita a los demas'),
    (5, 'Tengo la mala costumbre de decirles a los demas lo que tiene que hacer')),
  ( (8, 'Cuando los problemas me superan, trato de aislarme y resolverlos'),
    (0, 'Cuando los problemas me superan, trato de distraerme con otra cosa')),
  ( (7, 'Mis amigos saben que siempre pueden contar conmigo'),
    (6, 'No dependo de nadie y siempre me arreglo solo')),
  ( (1, 'Tengo tendencia a parecer una persona distante y preocupada'),
    (4, 'Tengo tendencia a distraerme y caer en la melancolia')),
  ( (4, 'Siento que puedo agradar, pero no me animo a acercarme'),
    (7, 'Generalmente soy serio y disciplinado')),
  ( (8, 'Me inhibo cuando tengo que mostrarle mis habilidades a alguien'),
    (6, 'Me gusta que la gente sepa que puedo hacer bien las cosas')),
  ( (7, 'Generalmente soy esceptico y sospecho de todo'),
    (3, 'Creo que soy demasiado inocente y sentimental')),
  ( (4, 'Cuando tengo un problema con alguien trato de mantenerme lejos'),
    (2, 'Cuando tengo problemas con alguien lo enfrento directamente')),
  ( (8, 'Siento que siempre termino cediendo y los demas me dominan'),
    (5, 'Soy demasiado exigente con los demas')),
  ( (0, 'Mis amigos valoran mucho mi sentido del humor'),
    (3, 'Mis amigos valoran mi generosidad y fuerza interior')),
  ( (6, 'Gran parte del exito se debe a mi capacidad para caer bien'),
    (1, 'Tengo exito a pesar de mi forma de ser')),
]
res =[0]*9
for p in pregs:
  a, b = p
  print 'A)', a[1]
  print 'B)', b[1]
  r = raw_input('Choose a/b: ')
  if r.lower().startswith('a'):
    res[a[0]] +=1
  else:
    res[b[0]] +=1
  print ''


for o, r in zip(opts, res):
  print o, '\t\t', '#'*r 

points = list(reversed(sorted(set(res))))[:2]

print 'Caracteristicas principales'
p=points[0]
for o, r in zip(opts, res):
    if r == p: print '\t',o

print "\nCaracteristicas secundarias"
p=points[1]
for o, r in zip(opts, res):
    if r==p: print '\t',o
raw_input('Presione enter para terminar...')