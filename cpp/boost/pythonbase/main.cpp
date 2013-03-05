#include <Python.h>

int main(int, char **) {
  Py_Initialize();

  Py_Finalize();
  return 0;
}
