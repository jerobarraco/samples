#include <stdio.h>
#include <stdlib.h>
int suma(int a, int b){
    return a+b;
}
int resta(int a , int b){
    return a-b;
}
int mult(int a, int b){
    return a*b;
}

int ordenar(    int (*funcion)(int, int)      ){
 return funcion(1,2);   
}


int main(int argc, char *argv[])
{
 int opcion;
 int uno, dos;
 int (*operacion) (int, int);
 printf("Ingrese la opcion\n 1-Suma\n 2-Resta\n 3-Multiplicacion\n");
 scanf("%d", &opcion); 
 switch (opcion){
       case 1: operacion = suma; break;
       case 2: operacion = resta; break;
       case 3: operacion = mult; break;
 }
 printf("Ingrese los 2 numeros\n");
 scanf("%d%d", &uno, &dos);
 printf("Resultado %d\n", operacion(uno, dos));
 printf("...%d", ordenar(operacion));
 system("PAUSE");	
 return 0;
}
