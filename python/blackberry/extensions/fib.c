#include <Python.h>
 
int
_fib(int n)
{
    if (n < 2)
        return n;
    else
        return _fib(n-1) + _fib(n-2);
}
 
static PyObject*
fib(PyObject* self, PyObject* args)
{
    int n;
 
    if (!PyArg_ParseTuple(args, "i", &n))
        return NULL;
 
    return Py_BuildValue("i", _fib(n));
}
 
static PyMethodDef FibMethods[] = {
    {"fib", fib, METH_VARARGS, "Calculate the Fibonacci numbers."},
    {NULL, NULL, 0, NULL}
};
static struct PyModuleDef moduledef = {
	PyModuleDef_HEAD_INIT,
	"fib",
	"does fibonacci",
	-1,
	FibMethods
};
	
PyMODINIT_FUNC
PyInit_fib(void)
{
   return PyModule_Create(&moduledef);
}