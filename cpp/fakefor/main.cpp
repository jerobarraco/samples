
#include <iostream>
#define likely(x)       __builtin_expect(!!(x), 1)
#define unlikely(x)     __builtin_expect(!!(x), 0)
 //#include "linux/include/linux/compiler.h"
using namespace std;
#define FAKEFOR(__init, __cond, __inc, __code)                                                                         \
    __init;\
    while((__cond)){  \
        __code;\
        __inc; \
   }\

int main()
{

    clock_t t1=clock();
    long a = 0;
    FAKEFOR(int i=0, i<1000000000, i++,
        a +=1;
    );
    clock_t t2=clock();
    cout << ((t2-t1)/(double)CLOCKS_PER_SEC)*1000 << " milliseconds of processing fakefor\n";


    t1=clock();
     a = 0;
    for(int i=0; i<1000000000; i++){
        a +=1;
    };
    t2=clock();
    cout << ((t2-t1)/(double)CLOCKS_PER_SEC)*1000 << " milliseconds of processing for\n";
    return 0;
}

