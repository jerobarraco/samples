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
	
	static struct PyModuleDef moduledef = {
        PyModuleDef_HEAD_INIT,
        "hello",
        "says hello",
        -1,
        HelloMethods
	};
//PyObject *
	PyMODINIT_FUNC 
	PyInit_hello(void)
	{
		PyObject *module = PyModule_Create(&moduledef);
		return module;
		//(void) Py_InitModule("hello", HelloMethods);
		//Py_InitModule3("hello", HelloMethods, "hello module");
	}
	
	/* Add a built-in module, before Py_Initialize 
    PyImport_AppendInittab("spam", PyInit_spam);
    /* Pass argv[0] to the Python interpreter 
    Py_SetProgramName(argv[0]);

     Initialize the Python interpreter.  Required. 
    Py_Initialize();

     Optionally import the module; alternatively,
       import can be deferred until the embedded script
       imports it. 
    PyImport_ImportModule("spam");
    */
	
	/*PyMODINIT_FUNC //pyton2
	inithello(void)
	{
		(void) Py_InitModule("hello", HelloMethods);
		//Py_InitModule3("hello", HelloMethods, "hello module");
	}*/
	
#ifdef __cplusplus
}
#endif