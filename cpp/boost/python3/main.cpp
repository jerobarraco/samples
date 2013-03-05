#include <Python.h>
#include <iostream>

int main(int, char **) {
  Py_Initialize();

  PyRun_SimpleString("result = 5 ** 2");

  PyObject * module = PyImport_AddModule("__main__"); // borrowed reference

  assert(module);                                     // __main__ should always exist
  PyObject * dictionary = PyModule_GetDict(module);   // borrowed reference
  assert(dictionary);                                 // __main__ should have a dictionary
  PyObject * result
    = PyDict_GetItemString(dictionary, "result");     // borrowed reference

  assert(result);                                     // just added result
  assert(PyInt_Check(result));                        // result should be an integer
  long result_value = PyInt_AS_LONG(result);          // already checked that it is an int

  std::cout << result_value << std::endl;

    PyObject * twenty = PyInt_FromLong(20);             // new reference
    PyDict_SetItemString(dictionary, "result", twenty);
    Py_DECREF(twenty);                                  // release reference count
    PyRun_SimpleString("print result");
    /*This creates a new Python integer object with the value of 20 and assigns it to the dictionary. 7) In this case instead of getting a borrowed reference from Python, we get a new reference that we need to track, so we call Py_DECREF() after finishing with the object.*/

  Py_Finalize();
  return 0;
}
