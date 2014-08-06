#include <iostream>

using namespace std;
int *nuevoarreglo(int *p[], int n){
    int *y = new int[n];
    for (int i=0; i<n; i++)
        y[i] = (*(p[i])) *2;
    return y;
}

int main()
{
    const int n = 10;
    int x[n];
    int *p[n];
    int *z = new int[n];
    for (int i=0; i<n; i++)
        cin >> x[i];

    for (int i=0; i<n; i++){
        p[i] = &x[i];
    }

    int *na = nuevoarreglo(p, n);
    int *pent = &x[5]; //aca no guardo un arreglo sino un solo elemento, pero la sintaxis de definicion es la misma
    for (int i=0; i<n; i++)
        cout << na[i];
    delete na;
    return 0;
}

