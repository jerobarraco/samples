#include <stdio.h>
#include <stdlib.h>

int suma (int *asdf){
     return asdf[0];
     };
int main(int argc, char *argv[])
{
  srand(time(0));
  int nums[20];
  int i;
  for (i=0; i<20; i++){
      nums[i] = rand()%100;
      printf("%d\n", nums[i]);
}
 suma(nums);
  system("PAUSE");	
  return 0;
}

