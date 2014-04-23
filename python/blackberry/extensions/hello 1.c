#include <Python.h>
#ifdef __cplusplus
extern "C" {
#endif

	static PyObject*
	say_hello(PyObject* self, PyObject* args)
	{
		const char* name;
		//char name[200];
		//if (!PyArg_ParseTuple(args, "s", &name))
		//	return NULL;
		//printf("Hello %s!\n", name);
		char msg[200];
		//strcat(
		return Py_BuildValue("s", "Hello, Python extensions!!");
		//Py_RETURN_NONE;
	}
	
	static PyMethodDef HelloMethods[] =
	{
		{"say_hello",(PyCFunction) say_hello, METH_VARARGS, "Greet somebody."},
		{NULL, NULL, 0, NULL}
	};
	
	PyMODINIT_FUNC
	PyInit_hello(void)
	{
		(void) Py_InitModule("hello", HelloMethods);
		//Py_InitModule3("hello", HelloMethods, "hello module");
	}
	
	/*PyMODINIT_FUNC //pyton2
	inithello(void)
	{
		(void) Py_InitModule("hello", HelloMethods);
		//Py_InitModule3("hello", HelloMethods, "hello module");
	}*/
	
#ifdef __cplusplus
}
#endif