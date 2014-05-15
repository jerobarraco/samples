#include <iostream>

using namespace std;

void func(int *p){
    int i, sum=0;
    for(i=0; i<6;i++)
        sum+=*(p+i);
    cout << "sum="<< sum<<endl;
}

int main()
{
    int x[6]={12,34,56,78,39,90};
    cout << *(x+3) << endl << *x+3;
    return 0;
    int a=90;
    int *p = &a;
    int b = (*p)++;
    int *q = p+2;
    cout << p << " " << *p << endl;
    cout << q << " " << *q << endl;
    cout << a << " " << b << endl;
    p++;
    b = *(q--)-1;
    a = (*p++)+1;
    cout << a << " " << b << endl;
    return 0;
}

