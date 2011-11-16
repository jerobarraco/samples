#include <stdio.h>
#include <stdlib.h>
#include <math.h>

float suma(int cant, int *ar){
      float total = 0;
      int i;
      for (i=0; i<cant; i++){
          total += ar[i];
          printf("%d", ar[i]);
          };
      return total;
}

int main(int argc, char *argv[])
{
  int val=0;
  int arr[100];
  int cont = 0;

  while ((val >=0) && (cont<100)){
        printf("Ingrese un valor o -1 para salir");
        scanf("%d", &val);
        arr[cont] = val;
        cont++;
  }

  printf("el valor de la suma es %d", suma(cont-1, arr));
  system("PAUSE");	
  return 0;
}


