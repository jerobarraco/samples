#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct{
        char tipo;
        float param1;
        float param2;
        float param3;
        float param4;
} tiposGeometricos;

float Atriangulo(float a, float b);
float Acirculo(float a);
float Acuadrado(float a);
void menu(void);

int main(int argc, char *argv[])
{
    tiposGeometricos tipo[100];
    int cont;
    char op;
    float area;
    char archivo[100];
    int totalfiguras;
    int cont1,cont2,cont3;
    float area1=0,area2=0,area3=0;
    float areatotal;
    int primercuadrante;
    
    FILE *fp;
    FILE *fs;
    if(argc>1)
    {
              strcpy(archivo,argv[1]);
              fp=fopen(archivo,"rb");
    }
    else
    {
              printf("ERROR:El archivo no pudo abrirse\n\a");
              system("PAUSE");
              return 0;
    }
    
    fs=fopen("salida.txt","w");
    if(fs==NULL)
    {
                printf("ERROR: El archivo no pudo abrirse\n\a");
                system("PAUSE");
                return 0;
    }
    cont=0;
    while(!feof(fp))
                    {
                    fread(&tipo[cont], sizeof(tiposGeometricos), 1, fp);
                    cont++;
                    totalfiguras=cont;
    };//Aca pusiste la llave para abrir en vez de la de cerrar o sea, "{"
   fclose(fp);      
   menu();
   scanf("%s", &op);
   cont=0;
   cont1=0;
   cont2=0;
   cont3=0;
   switch(op) {
             case 'A': case 'a':
                  area=Acirculo(tipo[cont].param1);
                  fprintf(fs,"El area del circulo es %.2f\n", area);
                  /* cuando necesites comparar dos parametros compara cada uno 
                  por separado, nunca dos juntos, el (a &&b)>c no cumple la logica
                  que pensas*/
                  if((tipo[cont].param2>0 && tipo[cont].param3>0))
                  {
                          cont1++;
                  }
                          cont++;
                          area1=area1+area;              
                          break;//te faltaron los breaks, no te los olvides
             case 'R':                      
                  area=Acuadrado(tipo[cont].param1);
                  fprintf(fs,"El area del cuadrado es %.2f\n", area);
                  if((tipo[cont].param2>0 && tipo[cont].param3>0))
                  {
                           cont2++;
                  }
                           cont++;
                           area2=area2+area;
                           break;
             case 'Q':
                  area=Atriangulo(tipo[cont].param1,tipo[cont].param2);
                  fprintf(fs,"El area del triangulo es %.2f\n", area);
                  if((tipo[cont].param3>0 && tipo[cont].param4>0))             
                  {
                           cont3++;
                  }
                           cont++;
                           area3=area3+area;
                  break;
             case 'S':
                  break;
             default:
                  printf("Opcion ingresada incorrecta\n");
                  scanf("%s", &op); 
   }
   areatotal=area1+area2+area3;
   primercuadrante=cont1+cont2+cont3;
   printf("La cantidad de figuras totales son %d\n",totalfiguras);            
   printf("La cantidad de figuras que estan en el primer cuadrante son %d\n", primercuadrante);
   fprintf(fs,"La suma de todas las areas es %.2f\n", areatotal);
   fprintf(fs,"La cantidad de figuras totales son %d\n",totalfiguras);            
   fprintf(fs,"La cantidad de figuras que estan en el primer cuadrante son %d\n", primercuadrante);   

   fclose(fs);
   
   
  system("PAUSE");	/**/
  return 0;
};

//*********************************************************************
void menu(void)
{
     printf("Ingrese la opcion de la figura que desea calcular el area\n");
     printf("A o a: area del circulo\n");
     printf("R: area del cuadrado\n");
     printf("Q: area del triangulo\n");
     printf("S: salir\n");
};
//**********************************************************************

float Acirculo(float a)
{
      //en estas tres funciones te falto poner el tipo de la variable area (float)
      float area=M_PI*a*a;
      return area;
};

float Acuadrado(float a)
{
      float area=a*a;
      return area;
};

float Atriangulo(float a,float b)
{
      float area=(a*b)/2;
      return area;
};

