#coding:utf-8
#global for the win :)
import sys

matrix = tuple()
queue = []

#helpers
#most of here is purely decorational
DEBUG = False
LOG = True
LF = "\n"
STUB ="<<stub>>"+LF

def p(m):
	sys.stdout.write(str(m))

def log(m):
	if LOG:
		p(m)

def stub():
	log(STUB)

def cc(sx, sy, x, y, v):
	#the order must match c2cell and the matrix
	#v can be, the value to PUT on that cell or the value to REMOVE from it. it basically depends on the function that calls it
	return (sy, y, sx, x, v)

def c2comp(c):
	#Returns a touple with the components orderer as smx, smy, x, y, val
	#the inverse of cc (to avoid touching the c directly)
	return c[2], c[0], c[3], c[1], c[4]
	
def c2cell(c):
	return matrix[c[0]][c[1]][c[2]][c[3]] #notice that this item is the only mutable (the list generated from a range)

def strc(c):
	global matrix
	comps = c2comp(c)
	d = comps + ( c2cell(c), ) #can only concatenate tuple with tuple
	return"sm(%s, %s) c(%s, %s) v=%s pos=%s" %d

#end-helpers

def setMatrix():
	global matrix
	#i will use separated indexes not because it is faster but because it is simpler to read
	#list comprehensions, nice but sharp knife
	
	#matrix[smx][smy] gives a submatrix
	#matrix[smx][smy][cx][cy] gives a cell, which is simply a list of 
	#matrix[smy][y][smx][x] #to be able to iterate it normally for print
	#notice the only mutable element is the last one (hte list of possible values)
	matrix = tuple(
			tuple( 
				tuple( 
					tuple( list(range(1, 10)) for m in range(3) )
					for k in range(3)
				)
				for i in range(3)
			)
		for j in range(3)
	)
	
def printm():
	global matrix
	hr = "+----------+----------+----------+"+LF
	vr = "| "
	#sad way to traverse the matrix...
	"""
	for sy in range(3):
		for y in range(3):
			for sx in range(3):
				for x in range(3): 
					stub()"""
	for a in matrix: #submatrix row (smy)
		for i, b in enumerate(a): #row (y)
			if i%3==0:
				p(hr)
			for c in b: #submatrix col (smx)
				p(vr)
				for d in c: # cell cell (x)
					le = len(d)
					txt = str(le and d[0] or "E") #format would be nicer
					txt += str(le>1 and str(le) or ".")
					txt += " "
					p(txt)
			p(vr+LF)
	p(hr)

def add2Queue(sx, sy, x, y, v):
	global queue
	c = cc(sx, sy, x, y, v) 
	log("Adding cell " + strc(c) +LF)
	queue.append(c)
	
def checkCell(c):
	log("Checking cell " + strc(c) +LF)
	cell = c2cell(c)
	if len(cell) == 1:
		log("I'm a genius! I've found a cell using logic! val="+str(cell[0])+", c: "+strc(c)+LF)
		smx, smy, x, y, v = c2comp(c)
		add2Queue(smx, smy, x, y, cell[0]) 
	
def remove(c):
	v = c[4]
	cell = c2cell(c)
	try:
		cell.remove(v)#remove raises an exception if the value is not found,
		#so if its not found, it wont check the cell
		#log("value removed" +LF)
		#log("Removing cell " +strc(c) +LF)
		checkCell(c)
	except:
		pass
	
def takePos(c):
	#take from x, y
	#remove it from submatrix
	smx, smy, x, y, v = c2comp(c)
	
	for a in range(3):
		for b in range(3):
			tc = cc(smx, smy, a, b, v)
			remove(tc)
	
	#Remove from global row
	for a in range(3): #submatrix col
		for b in range(3): #col
			#cell
			tc = cc(a, smy, b, y, v)
			remove (tc)
	
	#Remove from global col
	for a in range(3): #submatrix row
		for b in range(3): #row
			#cell
			tc = cc(smx, a, x, b, v)
			remove (tc)

def setCell(c):
	log("Setting cell "+ strc(c) + LF)
	cell = c2cell(c)
	cell.clear()
	takePos(c)
	cell.append(c[4])
	
def doQueue():
	while (queue):
		c = queue.pop(0)
		setCell(c)

def debug():
	add2Queue(0, 0, 1, 0, 5)
	add2Queue(0, 0, 0, 1, 4)
	add2Queue(0, 0, 2, 1, 7)
	add2Queue(0, 0, 1, 2, 1)
	
	add2Queue(1, 0, 2, 0, 2)
	add2Queue(1, 0, 0, 2, 4)
	
	add2Queue(2, 0, 0, 0, 3)
	add2Queue(2, 0, 2, 0, 1)
	add2Queue(2, 0, 0, 1, 9)
	add2Queue(2, 0, 1, 2, 8)
	
	
	add2Queue(0, 1, 0, 0, 5)
	add2Queue(0, 1, 1, 0, 9)
	add2Queue(0, 1, 0, 2, 2)
	add2Queue(0, 1, 1, 2, 6)
	
	add2Queue(1, 1, 1, 0, 1)
	add2Queue(1, 1, 0, 1, 9)
	add2Queue(1, 1, 2, 1, 5)
	add2Queue(1, 1, 1, 2, 4)

	add2Queue(2, 1, 1, 0, 7)
	add2Queue(2, 1, 2, 0, 4)
	add2Queue(2, 1, 2, 2, 9)
	add2Queue(2, 1, 1, 2, 5)

	add2Queue(0, 2, 1, 0, 8)
	add2Queue(0, 2, 2, 1, 6)
	add2Queue(0, 2, 0, 2, 3)
	add2Queue(0, 2, 2, 2, 1)
	
	
	add2Queue(1, 2, 2, 0, 7)
	add2Queue(1, 2, 0, 2, 2)
	
	add2Queue(2, 2, 1, 0, 2)
	add2Queue(2, 2, 0, 1, 5)
	add2Queue(2, 2, 1, 1, 1)
	add2Queue(2, 2, 2, 1, 8)
	add2Queue(2, 2, 1, 2, 9)
	doQueue()
	#print(queue)

def read():
	row = 0
	msg = """Input one line for the row '%s':
	The value of each cell (1 to 9) or ONE space ' ' (or a dash '-' (or 0)) if it is empty.
	(One after the other, without separations)
	Example '12-45--8-'(If you make any mistake, you will need to start over)
	>>> """
	
	for a in range(3): #submatrixes row
		for b in range(3): #row
			p(msg%(row+1))
			line = input()[:9]#take at most 9 chars
			for i, ch in enumerate(line):
				if ch in '123456789': #we have is digit, but i want to deliveratedly exclude the 0
					d, x = divmod(i, 3)
					add2Queue(d, a, x, b, int(ch))
			doQueue()
			printm()
			p(LF)
			row += 1

if __name__ == "__main__":
	setMatrix()
	printm()
	if DEBUG:
		debug()
	else:
		read()
	printm()
"""example
-2-8-
8754----9
-46-----5
563-----4
7845391
------357
----257-6
------2
----8--3
"""
#print(matrix)