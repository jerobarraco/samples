#!/usr/bin/python
#coding:utf-8
"""
This module is used to test the module 'linked.py'.
It uses the standard module 'unittest'. I could have used py.test but for the sake of this challenge i've choosen the
prior to ensure compatibility.
Also i could have used DocTests, but in a shared coding environment the core development and the test development
are usually parallelized. Doctests could hinder that.

"""
__author__ = 'Jeronimo Barraco Marmol'
__license__ = "GPLv3"
__email__ = "jerobarraco at yahoo dot com dot ar"

import unittest
import linked

class TestLinkedList(unittest.TestCase):
	def setUp(self):
		"""
		Sets up the shared data for the tests.
		"""
		self.sample_data = ('a', 'b', 'c', 'd')

	def test_node(self):
		"""
		Tests the node class
		"""
		node = linked.Node(3)
		self.assertEqual(node.data, 3)
		self.assertIsNone(node._link)

	#list tests
	def __sample_list(self):
		"""
		Creates an instance of a linked list using the shared sample data.
		:return: Pre-filled LinkedList
		"""
		ll = linked.LinkList()
		for i in self.sample_data:
			ll.append(i)
		return ll

	def test_initial_state(self):
		"""
		Tests the initial state, wether it handles begin empty correctly.
		:return:
		"""
		ll = linked.LinkList()
		self.assertTrue(ll.empty())
		self.assertEqual(repr(ll), "[]")

	def test_basic(self):
		"""
		This tests are better tested in one place because their functionality is all related and intertwined.
		Tests:
			append
			repr
			empty
			len (implicitly __iter__)
		"""
		ll = linked.LinkList()
		data = ( ("a", "['a']"),
				 ("b", "['a', 'b']"),
				 ("c", "['a', 'b', 'c']"),
				)
		for i, cr in enumerate(data):
			char, result = cr
			ll.append(char)
			self.assertFalse(ll.empty())
			self.assertEqual(i+1, len(ll))
			self.assertEqual(result, repr(ll))

	def test_iter(self):
		"""
		Tests iteration over a linked list
		"""
		ll = self.__sample_list()
		for i, j in zip(self.sample_data, ll):
			self.assertEqual(i, j.data)

	def test_in(self):
		"""
		Tests pertenency checks
		"""
		ll = self.__sample_list()
		#contains
		for i in self.sample_data:
			self.assertTrue(i in ll)
		self.assertFalse('z' in ll)

	def test_index(self):
		"""
		Tests the use of index function. With possitive and negative indexes.
		"""
		#index
		ll = self.__sample_list()
		first = self.sample_data[0]
		ll.append(first)
		#tests that the indexes are reported correctly
		for i, c in enumerate(self.sample_data):
			self.assertEqual(i, ll.index(c)) #Compare data
		#tests the start parameter
		self.assertEqual(len(ll)-1, ll.index(first, 2))
		#tests the negative start parameter
		self.assertEqual(len(ll)-1, ll.index(first, -2)) #paranoid #"first" is also at the end
		#tests when a value is not found
		self.assertRaises(ValueError, ll.index, (first, 2, -2))

	def test_get_set_item(self):
		"""
		Tests the use of getItem and setitem using array notation
		"""

		ll = self.__sample_list()
		#test the use of []
		for i, c in enumerate(self.sample_data):
			self.assertEqual(c, ll[i])

		#test invalid access positive
		with self.assertRaises(IndexError):
			ll[len(ll)]

		#test invalid access negative
		with self.assertRaises(IndexError):
			ll[-(len(ll)+1)]

		#test writing an item using []
		new = 'e'
		ll[1] = new
		self.assertEqual(new, ll[1])

		#test invalid access
		with self.assertRaises(IndexError):
			ll[len(ll)] = new

		#test invalid access
		with self.assertRaises(IndexError):
			ll[-(len(ll)+1)] = new

	def test_insert(self):
		"""
		Test the use of insert
		"""
		ll = self.__sample_list()
		news = ('e', 'f', 'g')
		#inserts at different positions, begin, negative, middle
		indexes = (0, -1, 3)
		new_data = list(self.sample_data)
		for i, c in zip(indexes, news):
			ll.insert(i, c)
			new_data.insert(i, c)
		self.assertEqual(repr(ll), repr(new_data))

	def test_rem(self):
		"""
		Tests all the removing code
		"""
		new_data = list(self.sample_data)
		ll = self.__sample_list()
		l = len(ll)

		#test del - Also tests removal from mid
		#tests index error
		with self.assertRaises(IndexError):
			del ll[7]
		#deletes
		del ll[1]
		del new_data[1]
		l -= 1 # to test size
		self.assertFalse(ll.empty()) #test empty
		self.assertFalse(self.sample_data[1] in ll) #test removal
		self.assertEqual(l, len(ll)) #test len
		self.assertEqual(repr(ll), repr(new_data)) #test result

		#remove - Also tests removal from beginning
		l -= 1
		#test erroneous value
		self.assertRaises(ValueError, ll.remove, 'z')
		#removal
		ll.remove(self.sample_data[0]) #implicitely use del, but lets test it anyway
		new_data.remove(self.sample_data[0])
		self.assertEqual(l, len(ll))
		self.assertFalse(ll.empty())
		self.assertFalse(self.sample_data[0] in ll)
		self.assertEqual(repr(ll), repr(new_data)) #test result

		#pop - Also tests removal from end
		l -= 1
		self.assertRaises(IndexError, ll.pop, -3) #test special case
		self.assertRaises(IndexError, ll.pop, 3) #test special case
		#removal
		d = ll.pop() #defaults to -1
		d2 = new_data.pop()

		self.assertEqual(d, d2) #test return value
		self.assertEqual(l, len(ll))
		self.assertFalse(ll.empty())
		self.assertFalse(d in ll)
		self.assertEqual(repr(ll), repr(new_data)) #test result

		#pop - Also tests removal of the last item
		l -= 1
		self.assertEqual(l, 0) #must be the last item (in case sample_data changed)
		d = ll.pop(0)
		d2 = new_data.pop(0)
		self.assertEqual(d, d2) #test return value
		self.assertEqual(l, len(ll))
		self.assertTrue(ll.empty())
		self.assertFalse(d in ll)
		self.assertEqual(repr(ll), repr(new_data)) #test result

	def test_reverse_iter(self):
		"""
		Tests the in-place list reversal using iterative method
		"""

		#Test with empty list
		ll = linked.LinkList()
		ll.reverse(1) #tests if there is an internal problem (raise, or lock)
		self.assertEqual(repr([]), repr(ll)) #tests if the result is consistent

		#Test with one item
		new_data = [self.sample_data[0]]
		new_data.reverse() #no really needed but avoids confusion
		ll.append(self.sample_data[0])
		ll.reverse()
		self.assertEqual(repr(ll), repr(new_data)) #test result

		#test normal case
		ll = self.__sample_list()
		ll.reverse()
		new_data = list(self.sample_data)
		new_data.reverse()
		self.assertEqual(repr(ll), repr(new_data))

	def test_reverse_rec(self):
		"""
		Tests the in-place list reversal using recursive method
		"""
		#Test the empty list
		ll = linked.LinkList()
		ll.reverse(1) #tests if there is an internal problem (raise, or lock)
		self.assertEqual(repr([]), repr(ll)) #tests if the result is consistent


		#Test one item
		new_data = [self.sample_data[0]]
		new_data.reverse() #no really needed but added for clarity
		ll.append(self.sample_data[0])
		ll.reverse(1)
		self.assertEqual(repr(ll), repr(new_data)) #test result

		#test normal case
		ll = self.__sample_list()
		ll.reverse(1)
		new_data = list(self.sample_data)
		new_data.reverse()
		self.assertEqual(repr(ll), repr(new_data))

if __name__ == '__main__':
	unittest.main()