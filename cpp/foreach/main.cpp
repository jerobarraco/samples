#include <iostream>

#define FORITCH(__type, __name, __size, __vec, __code)                                                                         \
    {int __i=0;\
    __type __name, *__arr = __vec;\
    while((__i<__size)){\
        __name = *(__arr++);\
        __code;\
        __i++;\
   }}\

using namespace std;

int main()
{
    char coisarada[] = "hello people\n";
    int len= 13;
    FORITCH (char, c, len, coisarada,
             cout << c;
    );
    return 0;
}

