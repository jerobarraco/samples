#!python3
#coding: utf-8
import math

import pyray as p

def draw_point(pos=(0, 0), size=10, col=p.MAROON):
	p.draw_circle_v(pos, size, col)

def draw_a_parabola(abc, off, x0, x1, step = 1):
	offx, offy = off
	while x0<x1:
		px = x0 - offx
		py = par_point(abc, px) + offy
		draw_point((x0, py), 1.5, p.GREEN)
		x0 += step

def find_parabola(ps):
	try:
		p1, p2, p3 = ps
		x1,y1 = p1
		x2,y2 = p2
		x3,y3 = p3

		denom = (x1 - x2) * (x1 - x3) * (x2 - x3)
		a = (x3 * (y2 - y1) + x2 * (y1 - y3) + x1 * (y3 - y2)) / denom
		b = ((x3**2) * (y1 - y2) + (x2 ** 2) * (y3 - y1) + (x1 ** 2) * (y2 - y3)) / denom
		c = (x2 * x3 * (x2 - x3) * y1 + x3 * x1 * (x3 - x1) * y2 + x1 * x2 * (x1 - x2) * y3) / denom
		xv = -b / (2 * a)
		yv = c - (b ** 2) / (4 * a)
		return (a,b,c),(0,0)
	except Exception as e:
		return (0,0,0),(0,0)
# https://www.youtube.com/watch?v=GvaIsQ7tef8

def adjust_vertex(ps):
	dUp = ps[0][1] - ps[1][1]
	dDown = ps[2][1] - ps[1][1]
	dHor = ps[2][0] - ps[0][0]
	dTotal = dUp + dDown
	"""
		y = a(x+h)²+k
		y = a(x+h)(x+h) + k
		y = a(x²+2hx+ h²) + k
		y = ax² + 2ahx + ah² + k
		(y -ax² - ah² -k) / 2ah = x
		
	dy = d(ax2 + bx)
	5 = ax2 + bx
	"""

	ratUp = math.sqrt(dUp**2/dTotal**2)
	mid = dHor*ratUp
	ps[1][0] = ps[0][0] + mid
	pass

def par_point(abc, x):
	a,b,c = abc
	return (a*(x**2.0)) + (b*x) + c


class App:
	points = [
		[150.0, 500.0],
		[300.0, 300.0],
		[460.0, 700.0],
	]
	colors = (p.GRAY, p.SKYBLUE, p.GRAY)
	sel_point = 0
	def draw_points(self):
		for pp, c in zip(self.points, self.colors):
			draw_point(pp, 10, c)

	def draw_parabola(self):
		ps = [pp[:] for pp in self.points]
		abc, off = find_parabola(ps)
		# draw_a_parabola((.03,-1.5, 200), (0,0), 0, 500, 2)
		draw_a_parabola(abc, off, 0, 1000, 1)
		print(abc, off)

	def run(self):
		p.init_window(1600, 1200, "Parabola")
		p.set_target_fps(30)
		while not p.window_should_close():
			self.input()
			self.draw()
		p.close_window()

	def draw(self):
		p.begin_drawing()
		p.clear_background(p.BLACK)
		p.draw_text("My first RayLib code", 190, 200, 20, p.VIOLET)
		self.draw_parabola()
		self.draw_points()
		p.end_drawing()

	def input(self):
		self.in_select()
		self.in_move()
		adjust_vertex(self.points)

	def in_move(self):
		speed = 20.0
		if p.is_key_down(p.KEY_UP):
			self.points[self.sel_point][1] -= speed
		if p.is_key_down(p.KEY_DOWN):
			self.points[self.sel_point][1] += speed
		if p.is_key_down(p.KEY_RIGHT):
			self.points[self.sel_point][0] += speed
		if p.is_key_down(p.KEY_LEFT):
			self.points[self.sel_point][0] -= speed

	def in_select(self):
		k = p.get_key_pressed()
		if k == 49:
			self.sel_point = 0
		elif k == 50:
			self.sel_point = 1
		if k == 51:
			self.sel_point = 2


if __name__=="__main__":
	a = App()
	a.run()


def draw_a_parabola2(abc, off, x0, x1, step = 1):
	offx, offy = off
	while x0<x1:
		px = x0
		py = par_point(abc, px)
		draw_point((px, py), 1.5, p.GREEN)
		x0 += step

def find_parabola3(ps):
	p1, p2, p3 = ps
	x1,y1 = p1
	x2,y2 = p2
	x3,y3 = p3
	x1 -=100
	x2 -=100
	x3 -=100

	y1 -=200
	y2 -=200
	y3 -=200

	denom = (x1 - x2) * (x1 - x3) * (x2 - x3)
	a = (x3 * (y2 - y1) + x2 * (y1 - y3) + x1 * (y3 - y2)) / denom
	b = ((x3**2) * (y1 - y2) + (x2 ** 2) * (y3 - y1) + (x1 ** 2) * (y2 - y3)) / denom
	c = (x2 * x3 * (x2 - x3) * y1 + x3 * x1 * (x3 - x1) * y2 + x1 * x2 * (x1 - x2) * y3) / denom
	xv = -b / (2 * a)
	yv = c - (b ** 2) / (4 * a)

	return (a,b,c),(100,200),0

def find_parabolaold(ps):
	# IF x0 == 0 > f(x0) == c == y0
	px0 = ps[0][0]
	ps[0][0] = 0 #-= px0
	ps[1][0] -= px0
	ps[2][0] -= px0

	# if y2 == 0 > -b =0
	py2 = ps[2][1]
	ps[0][1] -= py2
	ps[1][1] -= py2
	ps[2][1] = 0 #-= py2

	c = y0 = ps[0][1] # cuz c==y0
	k = 0 # cuz y2==k
	b = 0 # cuz y2==0
	x0 = ps[0][0] # THIS IS 0!
	x1 = ps[1][0]
	x2 = 0 # unknown
	y1 = ps[1][1]
	y2 = ps[2][1]

	# cant calculate with y0 since x0 is 0 and you get a division by 0 a = 1/(x0**2) #1/x0². cuz y2==0 > b=0 ^ y0 = ax0²+bx0+y0

	try:
		# a = (y2-c-(b*x2))/x2**2
		# (y1-c-(b*x1))/x1**2 = (y2-c-(b*x2))/x2**2
		a = (y1 - c) / (x1 ** 2)

	except Exception as e:
		print("e", e)
		return (0, 0, 0), (px0, py2), x2

	# y2 = ax2²+b?x2?+c
	return (a, b, c), (px0, py2), x2


"""

https://math.stackexchange.com/questions/3125263/equation-of-parabola-that-passes-through-two-points-and-vertex-has-coordinates


I can't solve the last exercises in a worksheet of Pre-Calculus problems. It says:

Quadratic function f(x)=ax2+bx+c
determines a parabola that passes through points (0,2) and (4,2), and its vertex has coordinates (xv,0)

.

a) Calculate coordinate xv

of parabola's vertex.

b) Calculate a,b
and c

coefficients.

How can I get parabola's equation with this information and find what is requested?

I would appreciate any help. Thanks in advance.





----
https: // www.wyzant.com / resources / answers / 626364 / how - do - i - find - the - equation - of - a - parabola - given - 2 - points - and -the - axis - of - sym

Using the vertex form of a parabola f(x) = a(x - h)2 + k where (h,k) is the vertex of the parabola

The axis of symmetry is x = 0 so h also equals 0
Substitute each point from the parabola into the vertex form:


4 = a(1 - 0)2 + k

4 = a(1) + k

4 = a + k


7 = a(2 - 0)2 + k

7 = a(4) + k

7 = 4a + k


We know have a linear system:


4 = a + k

7 = 4a + k


Subtracting the two equations gives us:


-3 = -3a

a = 1


Substituting the a value into the first equation of the linear system:


4 = 1 + k

k = 3


f(x) = (x - 0)2 + 3


f(1) = 4 = (1 - 0)2 + 3 = 1 + 3


f(2) = 7 = (2 - 0)2 + 3 = 4 + 3


The equation of the parabola through the given points and axis of symmetry is


f(x) = (x - 0)2 + 3 = x2 + 3
"""

"""

if i know a,k and h
y = a(x+h)²+k
y = a(x+h)(x+h) + k
y = a(x²+2hx+ h²) + k
y = ax² + 2ahx + ah² + k
(y -ax² - ah² -k) / 2ah = x


# y1 = ax1²+bx1+c
# y2 = ax2²+b?x2?+c


# x0 == 0 > f(x0) == c > c == y0

#general
	# y = f(x) = a(x - h)² + k
	# y = f(x) = ax²+bx+c
	# vertex = (−b/2a, f(−b/2a) )
	# a = (y0−k)/(x0−h)²

# unknowns
# a, b, c, x2 | a, h, k, x2
# knows
# x0,y0,x1,y1,y2

# one of the point is the vertex
# but we only know the Y of vertex not x
# IF y2 == 0 > k == 0
	# y2 = 0 = a(x-h)²+k = a(x-x2)²
	# with this i only have 3 unknowns. a,b, and x2
	# a = (y0−k)/(x0−h)²
	# a = (y1−k)/(x1−h)²
	# a = (y2−k)/(x2??−h)²

	y == 0 > k == 0
	a = y0/(x0-h)²

	a = y1/(x1-h)²
	a * (X1-h)² = y1
	(x1-h)² = y1/a
	x1-h = sqrt(y1/a)
	h = -(sqrt(y1/a)-x1)

	y0 = a(x0-h)²
	y0 = a(x0 -x1 + sqrt(y1/a))²
	y0 = a(x0 -x1 + sqrt(y1/a))(x0 -x1 + sqrt(y1/a))
	y0 = (ax0 -ax1 + a*sqrt(y1/a) )(x0 -x1 + sqrt(y1/a))
	y0 = (ax0(x0 -x1 + sqrt(y1/a)) -ax1(x0 -x1 + sqrt(y1/a)) + a*sqrt(y1/a)(x0 -x1 + sqrt(y1/a)) )
	y0 = ax0x0 -ax0x1 + ax0sqrt(y1/a) -ax1x0 +ax1x1 -ax1sqrt(y1/a) + a*sqrt(y1/a)x0 -a*sqrt(y1/a)x1 + a*sqrt(y1/a)sqrt(y1/a))


	# vertex = (−b/2a, f(−b/2a) ) && y2 =0 = f(-b/2a) = -b ==0?
	y2 = a(-b/2a)² + b(-b/2a) + c
	a(b/2a)² = b²/2a +c
	a = ((b²/2a) + c)/(b/2a)²


	y2 = a(-b/2a)(-b/2a) + -b²/2a + c
	y2 = a(-b²/(2a)²) + b²/2a + c
	y2 = -ab2 / 2a² + c
	-c = -ab² / 2a² + b²/2a



	a = (y0−k)/(x0 + (sqrt(y1/a)-x1) )²
	a = (y0−k)/(x0 -x1 + sqrt(y1/a) )²

	a = 0/XXX NaN
	a = y2/(x2?-h)²


# IF x0 == 0 > f(x0) == c == y0
# x0 == 0 > f(x0) == c > c == y0
# if y2 == 0 > -b =0
	# y0 = ax0²+bx0+c = c
	# y1 = ax1²+bx1+c
	# y2 = ax2²+b?x2?+c

	x0 = 0
	c = y0
	y2 = 0
	b = +-0

	y0 ???
	y1 = ax1²
	y2 = ax2²



	k = ps[2][1] # y of the vertex
	a = ps[0][1] / ((ps[0][0] - h) + k)
	h = -((ps[1][1]-k)/a)+ps[1][0]

	a = ps[0][1] / ((ps[0][0] - -((ps[1][1]-k)/a)+ps[1][0]) + k)

	ps[1][1] - k == a*(ps[1][0]-h)
	(ps[1][1] - k)/a == (ps[1][0]-h)
	((ps[1][1] - k)/a)-ps[1][0] == -h

	-((ps[1][1]-k)/a)+ps[1][0] == h

	h = 0# ??
	a = 0 # ??
	ps[2][0] == h # ???
	ps[0][1] == a * (ps[0][0] - h) + k
	ps[1][1] == a * (ps[1][0] - h) + k
	ps[2][1] == a * (ps[2][0] - h) + k


	ps[2][1] == a * (ps[2][0] - h) + k

	pass
"""
