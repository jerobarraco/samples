#coding=utf-8
"""you can handle json the way you want, that's not the important part here"""
import json
f = open('j.json' ,'r')
o = json.load(f)
def flatten(comments, level = 0):
	#comments is a list
	
	if not comments:
		return []#exit case
	
	res = []
	#add the level key so you can keep track of the original level
	for c in comments:
		c['level'] = level
		#removes the childs from the item (importan (i think))
		childs = c.pop('children', [])
		#adds the item to the result
		res.append(c)
		#and the flattened childs later
		res += flatten(childs, level+1)
		#in the next loop the next sibling will be added
	return res

fin = flatten(o['items'])

f2= open('f.json', 'w')
json.dump(fin, f2)

