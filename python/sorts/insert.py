#!/bin/python
#coding: utf-8
import random


def mysort(a, b):
	return (a, b) if a<b else (b, a)


def main():
	nums = list(range(10))
	random.shuffle(nums)
	for i in range(len(nums)-1):
		j = i+1
		mysort(nums[i], nums[j])
		print(mysort(nums[i], nums[i+1]))

if __name__=="__main__":
	main()
