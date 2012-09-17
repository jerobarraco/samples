import random

class Algo:
	uno=0
	dos = 0
	tres = 0
	def __init__(self):
		self.uno=random.random()
		self.dos=random.random()
		self.tres=random.random()
algo = Algo()
algo2 = Algo()
algo3 = Algo()
def sumar():
	global algo, algo2, algo3
	return algo.uno + algo2.uno + algo3.uno

class Sumador:
	algo = Algo()
	algo2 = Algo()
	algo3 = Algo()
	def sumar(self):
		return self.algo.uno + self.algo2.uno + self.algo3.uno

sumador = Sumador()

"""
notice: i've renamed this module from 'speed' to 'global_speed' so its clearer
>>> t1 = timeit.timeit('sumar()', 'from speed import *')
<timeit-src>:2: SyntaxWarning: import * only allowed at module level
>>> t2 = timeit.timeit('sumador.sumar()', 'from speed import *')
<timeit-src>:2: SyntaxWarning: import * only allowed at module level
>>> t1
2.0102348327636719
>>> t2
3.1076669692993164
>>> t1
2.0102348327636719
>>> t2
3.1076669692993164
>>> t1 = timeit.timeit('sumar()', 'from speed import *')
<timeit-src>:2: SyntaxWarning: import * only allowed at module level
>>> t2 = timeit.timeit('sumador.sumar()', 'from speed import *')
<timeit-src>:2: SyntaxWarning: import * only allowed at module level
>>> t1,t2
(1.993718147277832, 3.1175990104675293)
>>> t1 = timeit.timeit('sumar()', 'from speed import *')
<timeit-src>:2: SyntaxWarning: import * only allowed at module level
>>> t2 = timeit.timeit('sumador.sumar()', 'from speed import *')
<timeit-src>:2: SyntaxWarning: import * only allowed at module level
>>> t1,t2
(1.9849979877471924, 3.113055944442749)
>>> 


bound method creation
>>> from speed import *
>>> from timeit import timeit
>>> t1 = timeit.timeit('sumar()', 'from speed import *')
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
AttributeError: 'function' object has no attribute 'timeit'
>>> t1 = timeit('sumar()', 'from speed import *')
<timeit-src>:2: SyntaxWarning: import * only allowed at module level
>>> t2 = timeit('sumar2()', 'from speed import *;sumar2=sumador.sumar')
<timeit-src>:2: SyntaxWarning: import * only allowed at module level
>>> t1,t2
(0.47723425178707307, 0.6035872188106168)
>>>
now go on and read http://glyf.livejournal.com/70684.html
"""
