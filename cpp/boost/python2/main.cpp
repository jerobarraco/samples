#include <Python.h>

int main(int, char **){
  Py_Initialize();

  PyRun_SimpleString("import hello");
  PyRun_SimpleString("hello.hello()");

  Py_Finalize();
  return 0;
}
