#include <Python.h>

static PyObject* hello(PyObject* self)
{
    return Py_BuildValue("s", "Hello, Python extensions!!");
}

static char helloworld_docs[] =
    "helloworld( ): Any message you want to put here!!\n";

static PyMethodDef helloworld_funcs[] = {
    {"hello", (PyCFunction)hello, METH_NOARGS, helloworld_docs},
    {NULL}
};

void PyInit_hello(void)
{
    Py_InitModule3("hello", helloworld_funcs,
                   "Extension module example!");
}