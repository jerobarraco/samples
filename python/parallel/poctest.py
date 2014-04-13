#coding:utf-8
import pyopencl as cl
from pyopencl import array
import numpy
#numpy.arange([start], stop[, step], dtype=None)
#vector = numpy.arrange(0, 10000, 1, cl.array.vec.float4)
vector = numpy.zeros(10000000, numpy.float32)

## Step #1. Obtain an OpenCL platform.
platform = cl.get_platforms()[0]
	
## It would be necessary to add some code to check the check the support for
## the necessary platform extensions with platform.extensions
	
## Step #2. Obtain a device id for at least one device (accelerator).
device = [ platform.get_devices()[0] ]
#device = platform.get_devices()
print ("I will use the device: " + str(device))

## It would be necessary to add some code to check the check the support for
## the necessary device extensions with device.extensions
	
## Step #3. Create a context for the selected device.
#context = cl.Context([device])
context = cl.Context(device)
## Step #4. Create the accelerator program from source code.
## Step #5. Build the program.
## Step #6. Create one or more kernels from the program functions.
program = cl.Program(context, """
	__kernel void sinus(__global float *vec)
	{
		int gid = get_global_id(0);
		vec[gid] = sin(gid*0.00000314);
	}
	""").build()
	
## Step #7. Create a command queue for the target device.
queue = cl.CommandQueue(context)
	
## Step #8. Allocate device memory and move input data from the host to the device memory.
mem_flags = cl.mem_flags
#matrix_buf = cl.Buffer(context, mem_flags.READ_ONLY | mem_flags.COPY_HOST_PTR, hostbuf=matrix)
vector_buf = cl.Buffer(context, mem_flags.WRITE_ONLY | mem_flags.COPY_HOST_PTR, hostbuf=vector)
#matrix_dot_vector = numpy.zeros(4, numpy.float32)
#destination_buf = cl.Buffer(context, mem_flags.WRITE_ONLY, matrix_dot_vector.nbytes)
	
## Step #9. Associate the arguments to the kernel with kernel object.
## Step #10. Deploy the kernel for device execution.
program.sinus(queue, vector.shape, None, vector_buf)
	
## Step #11. Move the kernel's output data to host memory.
#cl.enqueue_copy(queue, vector, vector_buf)
cl.enqueue_read_buffer(queue, vector_buf, vector).wait()
	
## Step #12. Release context, program, kernels and memory.
## PyOpenCL performs this step for you, and therefore,
## you don't need to worry about cleanup code
	
print(vector)
"""
import visvis as vv
app = vv.use()
f = vv.clf()
a = vv.cla()
legends = []
vv.plot(numpy.arange(0, 1, 0.00001), vector, lc='c', mc='c', ms='.', ls='', mw=1,lw=0)
vv.title("Lol")
app.Run()"""