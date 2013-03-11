#include <boost/python.hpp>
#include <iostream>

int main(int, char **) {
  using namespace boost::python;

  Py_Initialize();

  try {
    PyRun_SimpleString("result = 5 ** 2");

    object module(handle<>(borrowed(PyImport_AddModule("__main__"))));
    object dictionary = module.attr("__dict__");
    object result = dictionary["result"];
    int result_value = extract<int>(result);

    std::cout << result_value << std::endl;

    dictionary["result"] = 20;

    PyRun_SimpleString("print result");
  } catch (error_already_set) {
    PyErr_Print();
  }

  Py_Finalize();
  return 0;
}


/*The call to PyImport_AddModule() becomes slightly more complex, as what was originally a comment about the borrowed reference becomes part of the code itself. However, once we have a reference to the __main__ module, instead of using an API call, we use the attr() member function to get at the __dict__ attribute of the __main__ module, which more closely resembles the equivalent Python code. Similarly, getting the reference to the result variable is done with operator[], and we can get at the value with the extract<>() function, whose usage resembles a cast.

 Also added is a slightly better error handling scheme than before. Instead of assuming that everything works properly, now it traps Python exceptions inside a try/catch block.*/
