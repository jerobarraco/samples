#!/usr/bin/env python

import ctypes
  
LIBRARY_PATH = './milib.so'
  
def main():
    '''Main entry point.'''
    
    # Load the library
    my_library = ctypes.CDLL(LIBRARY_PATH)
    
    # You need to specify the parameter and return types needed by the
    # functions in your library that you intend to call.
    # 
    # First, let's set the parameter types...
    #my_library.add.argtypes = [ctypes.c_float, ctypes.c_float]
    my_library.avg_array.argtypes = [ctypes.POINTER(ctypes.c_float), ctypes.c_int]
    
    # Next, set the return types...
    #my_library.add.restype = ctypes.c_float
    my_library.avg_array.restype = ctypes.c_float
    
    # Before calling the functions, we need to convert our Python data into C
    # data. For the 'add' function of our library, we will add 2.0 and 9.0, and
    # can convert those directly. For the 'avg_array' function, we will take a
    # list of floats and convert it into an array of C floats, which is only
    # mildly more complex.
    #add_float1 = ctypes.c_float(2.0)
    #add_float2 = ctypes.c_float(9.0)
    
    # You declare a float array of a particular size by using:
    # (ctypes.c_float * size_of_the_array)()
    numbers_to_average = [1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0]
    array_to_average = (ctypes.c_float * len(numbers_to_average))()
    # Once we have the C array, we can just fill it in
    for index, value in enumerate(numbers_to_average):
        array_to_average[index] = value
    
    # Call those functions!
    #add_result = my_library.add(add_float1, add_float2)
    avg_result = my_library.avg_array(array_to_average, ctypes.c_int(len(array_to_average)))
    
    # Confirm the results. Note that ctypes.c_float objects need their 'value'
    # field accessed to get the Python float value.
    #print 'The sum of')
    #print add_float1.value, 'and', add_float2.value, 'is', add_result
    print ('The average of ', repr(array_to_average) ,'is', avg_result)
    #print 'otro avg', sum(numbers_to_average)/len(numbers_to_average)
  
if __name__ == '__main__':
    main()