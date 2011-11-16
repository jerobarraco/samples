#include <stdio.h>
#include <stdlib.h>
struct exp {
       float vel;
       float tiempo;
};

int main(int argc, char *argv[])
{
struct exp ensayos[12];
int i;
for (i = 0; i<12 ; i++){
    printf("exp %d \n", i);
    scanf("%f%f", &ensayos[i].vel, &ensayos[i].tiempo);
}  
  system("PAUSE");	
  return 0;
}
