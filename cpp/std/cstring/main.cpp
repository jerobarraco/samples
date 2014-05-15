#include <iostream>
#include <cstring>

using namespace std;
void func(const char*a, const char* b){
    cout << a << b;
}

int main()
{
    char h[20] = "Hello";
    char *p = " world";
    char *r;
    int len ;
    len = strlen(h) + strlen(p);
    r = new char[len+2];
    r[0] = '\0';
    strcat(r, h);
    strcat(r, p);
    strcat(r, "!");
    cout << r << endl;
    cout << h << p<< endl;

    func(r, p);


    float *f = new float[2];
    f[0] =20; f[1]=40;
    cout << f <<endl;
    float g[2] = {4, 5};
    cout << g<< endl;

    strcpy(h, "internet es la red");
    cout << h;
    return 0;
}

