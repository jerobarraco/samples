/*The last topic for this article is creating a module in the application that can be imported from the embedded Python interpreter. To define a Python module with Boost.Python you declare the module with the BOOST_PYTHON_MODULE() macro, and put the various initializers inside the curly braces that follow it. The next article deals more with the initializers and how to export classes. Right now just focus on the one commented line in the following source. */
#include <boost/python.hpp>

using namespace boost::python;

int add_five(int x) {
  return x + 5;
}

BOOST_PYTHON_MODULE(Pointless)
{
    def("add_five", add_five);
}

int main(int, char **) {

  Py_Initialize();
    initPointless(); // initialize Pointless

    PyRun_SimpleString("import Pointless");
    PyRun_SimpleString("print Pointless.add_five(4)");
  Py_Finalize();
  return 0;
}

 /*This program shouldn't require any new measures to build or run properly. The important detail here is that in order for the module to be properly registered with the embedded Python interpreter, the init function for the module needs to be called. The function is called init followed by the module name. In this case it is initPointless(). So if you declare a module with BOOST_PYTHON_MODULE(Foo) you should call initFoo() sometime after Py_Initialize(), but before being potentially imported*/
