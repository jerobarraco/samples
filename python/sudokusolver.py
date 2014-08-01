#coding:utf-8
"""/*
 * minimalistic approach to a sudoku solver
 * just for fun
 * no academic code is to be found here (or if it is, it is an accident)
 * (c) Jeronimo Barraco Marmol 2014 GPL/v3
 * Done in <1 hour, optimized and documented in like 2
 */"""
"""/*The trick in this approach is how the matrix is handled and set.
 * this is what the matrix looks like:
 * matrix is an array of submatrixes, is a 3x3 matrix of 3x3 submatrixes
 * 123 xxx yyy
 * 456 xxx yyy
 * 789 xxx yyy
 * zzz vvv bbb
 * zzz vvv bbb
 * zzz vvv bbb
 * www eee rrr
 * www eee rrr
 * www eee rrr
 * (the numbers represents a submatrix (an example) x,y,z,v,b,w,e,r are the other submatrixes)
 * each submatrix is another matrix of 3x3 elements.
 * Each element on the submatrix represents a cell.
 * the cells are stored in a weird way, indexes are [submatrixY][y][submatrixX][x]
 * So itll actually look like this
 [
  [ #smy0
   [ #row0 for all submatrixesy=0
	[ 1, 2, 3], #smx0 all submatrixes that are 0 in x , inside there are #cell  values (for submatrixX=0, submatrixY=0 and Y=0
	[ x, x, x], #cell values for smx=1, smy=0 y=0
	[ y, y, y], #cell values for smx=2, smy=0 y=0
   ],
   [ #row1 for al submatrixesy=0
	[ 4, 5, 6], #smx0 all submatrixes that are 0 in x , inside there are #cell  values (for submatrixX=0, submatrixY=0 and Y=0
	[ x, x, x], #cell values for smx=1, smy=0 y=0
	[ y, y, y], #cell values for smx=2, smy=0 y=0
   ],
   [ #row2 for all submatrixesy=0
	[ 7, 8, 9], 
	etc
   ], 
  ], 
  [ (z, v and b) ], #smy1
  [ (w, e and r) ], #smy2
 ]
 
 * its probably not cpu/optimized and also hard to read, but its a lot easier to implement, 
 it is "optimized" for printing (maybe "tomorrow" i'll change it ).

 * But the trick is this:
 * I don't store the "value" of the cell, that approach will make me lose a lot of time "searching" other cells for values, 
 * storing stuff and making comparations. Proably "scanning" the whole matrix time and time again.
 * Instead, each cell stores "all the possible values" it could hold (range(1, 10)). When a cell only has ONE possible value remaining, it's automatically
 * said to be of that value (represented by the value and a "." when printed)
 * At first all the cells stores all the values (1,2,3,4,5,6,7,8 and 9).
 * As each cell receives a certain value, that value is taken from the submatrix, column, and row (just as sudoku rules states)
 * At the same time, each time a value is taken from any cell, the cell is checked to see if it ended up with one value,
 * if it only has one value (after actually removing another value) that means the cell can hold only one value, and then, 
 * that value is assigned to the cell (adding it to the queue) which in turn starts the whole mechanism again.
 * Basically it cascades to the solution as soon as all the necesary information is given.
 * I used a queue because it makes the code a little cleaner, and also, by not using recursion, i skip the possible problem
 * of a stack overflow.
 * The queue holds a list of values to set to a cell, and hence, remove elsewhere because they are being used. which triggers
 * the whole process again.
 */"""

import sys
#globals for the win :)
matrix = tuple()
queue = []

#helpers (most of here is purely decorational)
DEBUG = False #use test data
LOG = True #print stuff on console
LF = "\n"
STUB ="<<stub>>"+LF

def p(m): sys.stdout.write(str(m)) #prints with no endl

def log(m): 
	if LOG:	p(m) #logs if log == true

def stub():	log(STUB) #prints stub, unused

def cc(sx, sy, x, y, v): return (sy, y, sx, x, v)
#creates a "c" (a cell and value by global positioning reference) by using the parameters
#this func hides the order of the values in the "c" to other functions (thats because i was experiemnting to see what was the best indices ordering)
#to be used in conjunction with c2comp and c2cell
#the order must match c2cell and the matrix
#v can be, the value to PUT on that cell or the value to REMOVE from it. it basically depends on the function that calls it

def c2comp(c): return c[2], c[0], c[3], c[1], c[4]
#Returns a touple with the components orderer as smx, smy, x, y, val
#the inverse of cc (to avoid touching the c directly) and thus effectively hiding the ordering to other functions (yes this two funcs makes everything else slower)
	
def c2cell(c): return matrix[c[0]][c[1]][c[2]][c[3]]
#Returns a "Cell" from a "c" , a cell is only the list of possible elements it could have. notice that this item is the only mutable (the list generated from a range)
#i know i could have used lambdas but i dont like them :(

def strc(c): #converts a "c" to a string
	global matrix
	comps = c2comp(c)
	d = comps + ( c2cell(c), ) #can only concatenate tuple with tuple
	return"sm(%s, %s) c(%s, %s) v=%s pos=%s" %d
	
#end-helpers

def setMatrix(): #initialices the matrix
	global matrix
	#i will use separated indexes not because it is faster but because it is simpler to read
	#list comprehensions, nice but sharp knife
	#matrix[smy][y][smx][x] gives a cell, which is simply a list of possible values
	#indices are in that order to be able to iterate it normally for print
	#notice the only mutable element is the last one (the list of possible values)
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
	
def printm(): #prints the matrix
	global matrix
	hr = "+----------+----------+----------+"+LF
	vr = "| "
	"""	#sad way to traverse the matrix...
	for sy in range(3):
		for y in range(3):
			for sx in range(3):
				for x in range(3): 
					cell = matrix[sx][sy][x][y]"""#not even in your wildest dreams cowboy

	for a in matrix: #submatrix row (smy)
		for i, b in enumerate(a): #row (y)
			if i%3==0: p(hr) #prints the horizontal separator of submatrixes
			for c in b: #submatrix col (smx)
				p(vr)
				for d in c: # cell cell (x)
					le = len(d)
					txt = str(le and d[0] or "E")#prints the first possibility if any, or "E" (error)
					txt += str(le==1 and "." or str(le) ) #prints the count of possibilities or . if there's only one
					txt += " "#format would be nicer?, nah
					p(txt)
			p(vr+LF)
	p(hr)

def add2Queue(sx, sy, x, y, v): #does what it says
	#values indicate the point to be added to the queue, that will then be "Setted" to the cell when doQueue is called
	#this allows to NOT use recursion, or simply not get a stackoverflow (.com)
	global queue
	c = cc(sx, sy, x, y, v)#hides the ordering in c
	log("Adding cell " + strc(c) +LF)
	queue.append(c)#behold , the complexity
	
def checkCell(c): #checks if a cell has only one possibility, if so, it adds it to the queue (of cells to set later)
	#be careful not to add the same cell two times. it won't remove more elements, but could create a infinite loop
	#(actually it won't but be careful anyway) (see Remove to know why)
	log("Checking cell " + strc(c) +LF)
	cell = c2cell(c)
	if len(cell) == 1:
		log("I'm a genius! I've found a cell using logic! val="+str(cell[0])+", c: "+strc(c)+LF)
		smx, smy, x, y, v = c2comp(c)
		add2Queue(smx, smy, x, y, cell[0]) #pretty self explanatory, right? it should

def remove(c): #removes a value from the list of possibles values of a cell
	#be careful not to remove the last possible value 
	#See note on SetCell
	v = c[4] #vale to be removed
	cell = c2cell(c) #real cell to be modified
	try:
		cell.remove(v) #remove raises an exception if the value is not found, so if its not found, it wont check the cell (important!)
		#log("value removed" +LF)
		#log("Removing cell " +strc(c) +LF)
		#check the cell ONLY if removed a value
		#this magical part ensures that no infinite loops are triggered by re-adding a cell to the queue infinitely
		checkCell(c)
		#stop removing because in theory there should not be the same value twice in the array
	except:
		pass
	
def takePos(c):	#Take a "c" (value at a point) from the equation
	#removes all occurrences of a point from the submatrix, the row and the column (as the sudoku told us to do)
	#if in the meantime a cell ends up with only one possibility it will add it to the queue to be taken later
	
	smx, smy, x, y, v = c2comp(c)#cache values
	
	#remove it from submatrix
	for a in range(3):
		for b in range(3):
			tc = cc(smx, smy, a, b, v)#temporary c with the coordenates of the cell to modify and the value to remove
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

def setCell(c):	#Sets a cell to a certain value, that in time will remove it from all the places it should, and trigger possible new "setCells"
	"""Note:
	 * See CheckCell
	 * Bad part of this is that SetCell (me) can't differentiate if it's called as a result of CheckCell
	 * (in which case needs to execute the TakePos AND the cell will only have one value)
	 * or directly by user input, in which case it SHOULD have more than one possible value other way it could mean
	 * that we could take some value more than one time causing errors, and its also redundant.  
	 * i could add information for that (ie, using a class instead of a simple List.) 
	 * but that would make things much much complicated (taking in account that this is only a hack) 
	 * and given the fact that you CANT take what you already took, there SHOULD be no problem (in theory)
	 * from taking the same c (takePos) several times (except wasting cpu and ram).
	 * "remove" actually makes the difference, when trying to see if the cell should be added or not to the queue.
	 * failing on that will create an infinite loop.
	 */"""
	#If the current possibility is the only one logically, TakePos will already set this cell to the only possibility it could be
	#but heck, better to be sure than sorry
	
	log("Setting cell "+ strc(c) + LF)
	cell = c2cell(c)
	#the order in this 3 calls avoids that a) CheckCell gets called for this cell,  b) The value gets removed from this cell instead of holding it. 
	#A.K.A. magic
	cell.clear()
	takePos(c)
	cell.append(c[4])
	
def doQueue(): #each time its called it'll try to consume the whole queue setting the cells that it should
	#setcell can in turn add more stuff to the queue. so basically cascading cells. 
	#this will end when it can no longer logically assume values from the info it has.
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

def read(): #reads the board from the user
	row = 0
	msg = """Input one line for the row '%s':
	The value of each cell (1 to 9) or ONE space ' ' (or a dash '-' (or 0)) if it is empty.
	(One after the other, without separations)
	Example '12-45--8-'(If you make any mistake, you will need to start over)
	>>> """
	
	#goes over each global row, reads a line from the user, iterates over the line assigning the value (if any) to the corresponding cell ( by adding it to the queue)
	for a in range(3): #submatrixes row
		for b in range(3): #row
			p(msg%(row+1)) #(row+1)-> 1 based because 0 based is too complex for the client side
			line = input()[:9]#take at most 9 chars, avoid index overflow, this part is the "client side" so, we must take precautions
			for i, ch in enumerate(line):# dont check for empty line, that is a valid input ( a line with no numbers, weird, but valid)
				if ch in '123456789': # we have ".isDigit()", but i want to deliveratedly exclude the 0
					d, x = divmod(i, 3) # <3
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