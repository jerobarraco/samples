#!/bin/python
#coding:utf-8
# Stack implementation on python so it can be visualized using pythontutor.com
# It represents the behavior on c++ but more understandable
#Copyright (c) 2014 Jerónimo Barraco Mármol GPLv3  (moongate.com.ar)

# To visualize it , go to this link
# http://pythontutor.com/visualize.html#code=%23!/bin/python%0A%23coding%3Autf-8%0A%23+Stack+implementation+on+python+so+it+can+be+visualized+using+pythontutor.com%0A%23+It+represents+the+behavior+on+c%2B%2B+but+more+understandable%0A%23Copyright+(c)+2014+Jer%C3%B3nimo+Barraco+M%C3%A1rmol+GPLv3++(moongate.com.ar)%0A%0A%0Aclass+Node%3A%0A%09%23A+node,+when+it+is+created+the+values+are+initialized+as+data+%3D+0+and+link+%3D+None%0A%09data+%3D+0%0A%09link+%3D+None%0A%0Adef+push(top,+data)%3A%0A%09%23This+is+the+%22insert%22+function+on+a+stack.+In+the+stack+the+new+data+goes+always+first,+pushing+old+data+below+it.%0A%09%23Also+known+as+LIFO+(Last+In+First+Out)%0A%09%23Pushes+the+new+data+to+the+top+of+the+stack%0A%09%0A%09newnode+%3D+Node()+%0A%09%0A%09%23assign+the+info%0A%09newnode.data+%3D+data%0A%09%23the+new+node+will+link+to+the+%22old%22+top%0A%09newnode.link+%3D+top%0A%09%23and+we+update+the+old+top+with+the+%22new%22+one+(the+new+node)%0A%09top+%3D+newnode%0A%09%0A%09return+top+%23we+use+return+because+there+is+no+byreference+variable+passing+(and+its+also+cleaner)%0A%09%0Adef+pop(top)%3A%0A%09%23Pop+is+the+same+as+remove+AND+read+from+a+stack%0A%09%23by+definition+a+stack+can+only+be+read+by+taking+the+elements+of+the+stack,+that+is,+you+can+traverse+it+without+removing+the+elements.+In+other+words,+when+reading+an+element+you+need+to+remove+it+too.%0A%09%23It+is+technically+possible+to+traverse+the+stack+without+removing+the+elements,+but+the+definition+is+not+that.%0A%09%0A%09%23read+(or+store)+the+data+before+elimination%0A%09link+%3D+top.link%0A%09data+%3D+top.data%0A%09%0A%09%23eliminate+the+node+by+releasing+its+memory%0A%09%23this+is+not+necesary+on+python+(nor+would+work)+but+it's+symbolic+to+the+elimitation+in+c%2B%2B%0A%09del+top%0A%09%0A%09%23update+the+pointer+to+the+top%0A%09top+%3D+link%0A%09%0A%09return+top,+data%0A%0A%23insertion+example%0Atop+%3D+None%0Afor+i+in+(10,+2,+5,+20,+30)%3A%0A%09top+%3D+push(top,+i)%0A%0A%09%0A%23reading+example%0Asumm+%3D+0%0Acount+%3D+0%0Awhile+top!%3D+None%3A%0A%09top,+data+%3D+pop(top)%0A%09count+%2B%3D+1%0A%09summ+%2B%3D+data%0A%09print+(data)%0Aprint+(%22summ+is+%22%2B+str(summ))&mode=display&origin=opt-frontend.js&cumulative=false&heapPrimitives=false&drawParentPointers=false&textReferences=false&showOnlyOutputs=false&py=3&rawInputLstJSON=%5B%5D&curInstr=114

class Node:
	#A node, when it is created the values are initialized as data = 0 and link = None
	data = 0
	link = None

def push(top, data):
	#This is the "insert" function on a stack. In the stack the new data goes always first, pushing old data below it.
	#Also known as LIFO (Last In First Out)
	#Pushes the new data to the top of the stack
	
	newnode = Node() 
	
	#assign the info
	newnode.data = data
	#the new node will link to the "old" top
	newnode.link = top
	#and we update the old top with the "new" one (the new node)
	top = newnode
	
	return top #we use return because there is no byreference variable passing (and its also cleaner)
	
def pop(top):
	#Pop is the same as remove AND read from a stack
	#by definition a stack can only be read by taking the elements of the stack, that is, you can traverse it without removing the elements. In other words, when reading an element you need to remove it too.
	#It is technically possible to traverse the stack without removing the elements, but the definition is not that.
	
	#read (or store) the data before elimination
	link = top.link
	data = top.data
	
	#eliminate the node by releasing its memory
	#this is not necesary on python (nor would work) but it's symbolic to the elimitation in c++
	del top
	
	#update the pointer to the top
	top = link
	
	return top, data

#insertion example
top = None
for i in (10, 2, 5, 20, 30):
	top = push(top, i)

	
#reading example
summ = 0
count = 0
while top!= None:
	top, data = pop(top)
	count += 1
	summ += data
	print (data)
print ("summ is "+ str(summ))