#include <iostream>

struct S {
    int i;
    char c;
};

using namespace std;
int cinco(){
    return 5;
}
int siete(){
    return 7;
}
int veinte(int c){
    return 20;
}
float div (int* pi, char c){
    return 20.0;
}

char f(int a, int b){
    if(a+b>20){
       return 'm';
    }else{
        return 'n';
    }
}

int * entero(        char (*func)(int, int)      ){
    char x = func(10, 20);
    return NULL;
}

int main()
{
    S a;
    a.c = 'h';
    S *ps ;
    ps = new S;
    char * c[30];
    char (*d)[30];
    cout << "Hello World!" << c << d<< endl;

    int (*pfunc)(void);
    pfunc = &cinco;
    cout << "pfunc me da "<< pfunc();

    pfunc= &siete;
    cout << "pfunc me da "<< pfunc();

    float (*otraf)(int*, char);
    otraf = &div;
    int x;
    int *px =&x;
    div(&x, 'c');
    div(px, 'c');

    char *z[3] = {"rojo", "gris", "azul"};
    cout << z[0]<< " - " << z[2];


    int *pi = entero(f);
    cout << pi;
    return 0;
}

