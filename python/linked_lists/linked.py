#!/usr/bin/python
#coding:utf-8
"""
Using reST documentation for compatibility and simplicity. (using sphinx)

Challenge:
	Using any programming language you're comfortable with, write the required class(es) to implement
	a simple singly-linked list.

	Write two functions to reverse the order of a singly-linked list using your implementation.
	You must provide:
	 1. An iterative reverse.
	 2. A recursive reverse.
	 3. A full suite of automated tests.
	List reversal must be executed in-place and not on a copy.
"""
__author__ = 'Jeronimo Barraco Marmol'
__license__ = "GPLv3"
__email__ = "jerobarraco at yahoo dot com dot ar"

class Node:
	"""Base class for storing information as a node.
	Is not needed to use it on the client side.
	"""
	data = 0
	_link = None
	#Access could be implemented as properties, but it's not necesary acording to Python's coding guidelines,
	#would add extra complexity and overhead.
	#Also and can be implemented transparently later.
	def __init__(self, data = 0):
		self.data = data

	def __repr__(self):
		return repr(self.data)

class LinkList:
	"""
	Main class for singly-linked lists.
	LinkList() -> new empty linked list.
	"""
	__root = None
	__end = None # small optimization at the cost of some complexity

	#Challenged functions (first)
	def reverse(self, rtype=0):
		"""
		L.reverse(rtype) -- reverse *IN PLACE*

		:param rtype: Reverse type: 0 uses iteration, 1 uses recursive. Optional, default is 0.
		"""
		#implemented like this to retain compatibility with python's normal api
		if rtype == 0:
			self.reverse_iter()
		else:#avoid other values
			self.reverse_rec()

	def reverse_iter(self):
		"""
		L.reverse_iter() -- reverse *IN PLACE* using an iterative method.
		"""
		next = None #not needed, but adds clarification
		prev = None
		node = self.__end = self.__root #preserves end optimization
		while (node is not None):
			next = node._link #preserve next item
			node._link = prev #invert order
			#now advance
			prev = node
			node = next
		self.__root = prev
		"""
		This is actually the same, but some people could argue that we are creating a new list, and hence not in place.
		(And that has been a requirement of the challenge)
		Though the operation is basically the same, the ammount of memory and variables is the same, but far more
		easy to understand; the prior solution is choosed to avoid miss-calification of the solution.

		newl = None
		self.__end = self.__root #preserves the optimization
		while (self.__root is not None):
			node = self.__root #preserve node

			self.__root = node._link # advance the old link, effectively removing "node"
			#now insert in first place of new list
			node._link = newl #link points to the "new list"
			newl = node #new head of list
		self.__root = newl
		"""

	def reverse_rec(self, llist = None):
		"""
		L.reverse_rec() -- reverse *IN PLACE* using a recursive method.

		:param llist: used internally, don't specfy it.
		"""
		"""
		How it works:
			Divides the list in 'first' and the rest, returns the 'end' of the rest of the list.
		Then reataches the 'end' and 'first' in reverse order.
		Care is taken to set __root and __end accordingly, as well as to avoid circular references
		(which could mess with debbugers or tests).
		"""
		#init case, allows to be called with no parameters (remember, it should be in-place)
		if llist is None:
			if self.__root is None: return #empty list: do nothing
			# not empty
			llist = self.__root #start at the beginning
			#set the new beggining in an easy way (at this point the list is inconsistent until this method finishes)
			self.__root = self.__end
			self.__end = llist # preserves optimization

		# ## Tail case:
		#checking against *link* is the best option, 1st *llist* is checked as init case, it allows no param,
		# and is taken care of. So this function should not be called with a None as parameter unless it's the first iteration.
		#so we can always check *._link* safely, and after this check we ensure NOT to call reverse_rec with a None param
		#which could lead to problems as it will "re-start" the operation
		if llist._link is None:
			return llist

		#this_first = llist # llist is the new first
		new_end = self.reverse_rec(llist._link) #notice ._link, also read the comment in the *if* adove
		llist._link = None # detaches the link from the list, avoiding circular references
		new_end._link = llist #reverse
		return llist #but alwas return the "end", which was the first once

		"""The above code could be "optimized" (but readability and maintainability gets reduced) as:
		if llist._link:
			new_end = self.reverse_rec(llist._link)
			llist._link = None
			new_end._link = llist #reverse
		return llist
		"""

	def __checkIndex(self, i):
		"""
		Checks if the index is in the correct range.
		If i is negative, it gets converted and returned.

		:param i: the index to check.
		:raise: TypeError if the index is not an integer.
		:raise: IndexError if the index is out of range.
		:return: the index in positive notation.
		"""

		#check type
		if not isinstance(i, int):
			raise TypeError("list indices must be integers, not %s"% (type(i), ) )


		s = len(self)
		if i < 0:
			i += s  #index is negative, so add it

		if abs(i) >= s: #checks range
			raise IndexError()
		return i

	def append(self, value):
		"""
 		L.append(object) -> new node -- append object to end.

		:param value: object to append.
		:returns: new node for object.
		"""

		new = Node(value)
		if self.__root: #if it's not empty, add to the end
			self.__end._link = new #small optimization for quick add (when used as queue)
			self.__end = new
		else:#if it is empty add to the start
			self.__root = new
			self.__end = new
		return new

	def index(self, value, start=0, stop=-1):
		"""
		L.index(value, [start, [stop]]) -> integer -- return first index of value.

		:param value: Value to find.
		:param start: First index to check. Optional (default 0).
		:param stop: Last index to check. Optional (default -1).
		:raise: ValueError if the value is not found.
		:raise: TypeError if the index is not an integer.
		:raise: IndexError if the index is out of range.
		:return: The index of the first value that matches the value.
		"""

		start = self.__checkIndex(start)
		stop = self.__checkIndex(stop)
		for count, node in enumerate(self):
			#the linked list can only be iterated sequentially, (unless indexed...)
			#  so the if is not much less performant
			if count > stop : break #check until, but not past, stop
			if start <= count: #checks from start, included.
				if node.data == value:
					return count
		raise ValueError() #Exception if it haven't been found

	def insert(self, index, value):
		"""
 		L.insert(index, object) -> new node -- insert object before index.

		:param index: The object gets inserted before this index.
		:param value: object to insert.
		:raise: TypeError if the index is not an integer.
		:raise: IndexError if the index is out of range.
		:return: new node for the inserted object.
		"""

		index = self.__checkIndex(index)
		new = Node(value)
		prev = None
		# i could use a simple count variable instead of enum for a slightly performance improvement, but "readability counts"
		# it would probably introuduce unnedded risks of errors
		for count, node in enumerate(self):
			if count == index: #add it here
				if prev is None:  #check if first
					new._link = self.__root
					self.__root = new
				else:
					new._link = prev._link
					prev._link = new
				#check if its the last one, notice it's outside of the previous ifs, it applies to both cases above (if and else).
				if new._link is None:
					self.__end = new
				return new
			#no else because it returns above
			prev = node
		raise IndexError() #If it was outside raise

	def __delitem__(self, index):
		"""
		Supports the deletion of an item using *del L[i]*
		x.__delitem__(y) <==> del x[y]

		:param index: Index of the item to remove.
		:return: returns the data, so it can be used by pop.
		:raise: TypeError if the index is not an integer.
		:raise: IndexError if the index is out of range.
		"""

		index = self.__checkIndex(index)
		prev = None
		for count, node in  enumerate(self):
			if count == index:
				if prev is None:
					#if its the first, set the first to the next
					self.__root = node._link #handles only one item properly
				else:
					prev._link = node._link

				#check if its last
				#notice its a different if here, it can be first and last at the same time (only 1 item)
				if node._link is None:
					self.__end = None
				return node.data #by breaking this small convention we can reuse the same code for pop
			prev = node
		raise IndexError() #if we reach here, there has been an index problem

	def remove(self, value):
		"""
		L.remove(value) -- remove first occurrence of value.

		:param value: Value to remove.
		:return: Nothing.
		:raise: TypeError if the index is not an integer.
		:raise: IndexError if the index is out of range.
		:raise: ValueError if the value is not found.
		"""

		#tested
		del self[self.index(value)] #not very performant, but useful nevertheless
		#is almost the same, but less useful as pop, created only to comply with python's standard

	def pop(self, index=-1):
		"""
		L.pop([index]) -> item -- remove and return item at index (default last).

		:param index: Optional. Index of the item to remove. Default is the last item.
		:return: Removed value.
		"""

		return self.__delitem__(index)

	def __len__(self):
		"""
		Get item count for the list.
		To check if it's emtpy is faster to use L.empty().
		x.__len__() <==> len(x)

		:return: Count of items on the list.
		"""
		#tested
		s = 0
		for n in self:
			s+=1
		return s

	def __iter__(self):
		"""
		Allows for iteration using "for" and using iterators.
		Note that modifying the list inside an iteration could be problematic.
		x.__iter__() <==> iter(x)

		"""
		#tested
		node = self.__root
		while (node is not None):
			yield node
			node = node._link

	def empty(self):
		"""
		L.empty() -- Returns True if the list is empty, and False if it's not.
		Faster than checking for len(L).

		:return: True if empty, False if contains something
		"""

		return self.__root is None #i could use len, but would be slower

	def __setitem__(self, i, value):
		"""
		Sets the value for an item
		x.__setitem__(i, y) <==> x[i]=y


		:param i: index to modify.
		:param value: new value to store.
		:raise: TypeError if the index is not an integer.
		:raise: IndexError if the index is out of range.
		"""
		i = self.__checkIndex(i)
		for count, node in enumerate(self):
			if count == i:
				node.data = value
				return
		raise IndexError() #if we reach here, there has been an index problem

	def __getitem__(self, index):
		"""
		Gets the value for the given position.
		x.__getitem__(y) <==> x[y]

		:param index: Index of the value to retrieve.
		:return: The value stored at the given index.
		:raise: TypeError if the index is not an integer.
		:raise: IndexError if the index is out of range.
		"""
		item = self.__checkIndex(index)
		for count, node in enumerate(self):
			if count == item:
				return node.data
		raise IndexError() #if we reach here, there has been an index problem

	def __contains__(self, value):
		"""
		Returns true if the value is stored in the list.
		x.__contains__(y) <==> y in x

		:param value: Value to search for.
		:return: True if found, False if not.
		"""
		for node in self:
			if node.data == value: return True
		return False

	def __repr__(self):
		"""
		Representation of the list.
		x.__repr__() <==> repr(x)

		:returns: Representation of the list using a similar format than the builtin *list*.
		"""
		"""a little criptic but understandable,
		1) creates a list of the values,
		2) converts to its repr
		3) concatenates separated by ", "
		4) format with wrapping "[]"
		5) returns

		This is the standard python notation for a list.
		"""
		return "[%s]"%(", ".join(map(repr, [i.data for i in self])))
