#include <cstdlib>
#include <iostream>

using namespace std;

void cambiar(int *a, int *b){
         int aux;
         aux = *a;
         *a = *b;
         *b = aux;
}

void minsus(int v[], int c){
     int i, h;
     for (i=0; i<c; i++){
         for (h=i; h<c; h++){
             if (v[i]>v[h]){
                cambiar(&v[i], &v[h]);
                }            
         }
     }
}

void elec(int v[], int c){
     int i;
     for (i =0; i<(c-1); i++){
         if (v[i]>v[i+1]){
                          cambiar(&v[i], &v[i+1]);
                          if (i!=0){
                             i =i-2;
                             }
         }
     }
}
int main(int argc, char *argv[])
{
    int c = 20;
    int v[c];
    int i, op;
    void (*ordenar)(int[], int);
    
    for (i = 0; i<c; i++){
        v[i] = (rand()%87)+56;
    }
    printf("1-Minimos\n 2-electronico");
    scanf("%d", &op);
    
    switch(op){
               case 1: ordenar = minsus; break;
               case 2: ordenar = elec; break;
    }
    ordenar(v, c);
    for (i=0; i<c; i++){
        printf("%d ", v[i]);
        }
    system("PAUSE");
    return EXIT_SUCCESS;
}
