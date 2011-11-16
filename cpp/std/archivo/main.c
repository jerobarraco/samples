#include <stdio.h>
#include <stdlib.h>
#include <string.h>
struct Persona{
       char nombre[255];
       char apellido[255];
       int anio;
       int edad;
       float estatura;
};

void Rellenar(char l[], struct Persona* persona){
     /*int pos;
     //pos = strpos(l, ';');*/
     char *p;
     //cargamos la primer parte de la linea (el nombre)
     p = strtok(l, ";");
     strcpy(persona->nombre, p);
     
     //cargamos la segunda parte de la linea (apellido)
     p = strtok(NULL, ";");
     strcpy(persona->apellido, p);   
     
     //cargamos el año
     p = strtok(NULL, ";");
     persona->anio = atoi(p);
     
     //cargamos la edad
     p = strtok(NULL, ";");
     persona->edad = atoi(p);
     
     //cargamos la estatura
     p = strtok(NULL, ";");
     persona->estatura = atof(p);
     
}

void MostrarPersona(struct Persona persona){
     printf ("Nombre: %s, Apellido: %s, Edad %d, Altura: %f\n", 
            persona.nombre, persona.apellido, persona.edad, persona.estatura);
}

int main(int argc, char* argv[])
{ 
 FILE *f;
 FILE *fsalida;
 FILE *fotro;
  int i;
  float promedio;
  //Guardamos los datos de cada renglon aca
 struct Persona datos[15];
 //Nombre de archivo, para entrada y para salida y una linea
  char entrada[255], salida[255], linea[255];
  
  printf("me pasaron %d parametros\n", argc);
  printf("los parametros son\n");
  
  for (i =0; i<argc; i++){
      printf("%s\n", argv[i] );
  };
  
  // programa
 
  //copiamos el 2º parametro en entrada
  strcpy(entrada, argv[1]);
  
  //Si no nos pasaron el 3º parametro
  if (argc < 3){
     //escribimos salida
     strcpy(salida, "salida.txt");
  }else{
        //sino lo copiamos 
        strcpy(salida, argv[2]);
  }
  
  //abrimos el archivo entrada como LECTURA
  f = fopen(entrada, "r");
  //abrimos el archivo salida como Escritura.
  fsalida = fopen(salida, "w");
  fotro = fopen("otro.txt", "w");
  
  for (i=0; i<15; i++){
      // otra forma      fscanf(f, "%s", &datos[i].nombre);
      //Cargamos en _linea_ un renglon desde _f_
      //con sizeof linea indicamos lo maximo que podemos leer
      //(el tamaño de linea)
      fgets(linea, sizeof linea, f);
      //cargar en uno de los "registros" los datos de la linea
      Rellenar(linea, &datos[i]);
  }
  
  //Calculamos el promedio
  promedio =0;
  for (i=0; i<15; i++){
      promedio = promedio + datos[i].edad;
  }
  promedio = promedio /15;
  
  //Ordenar
  int j;
  struct Persona aux;
  for (i=0; i<15; i++){
      for (j=i; j<14; j++){
          if (strcmp(datos[j].apellido, datos[j+1].apellido)<0){
             aux = datos[j];
             datos[j]=datos[j+1];
             datos[j+1]=aux;
          }
      }
  }
  
  for (i=0; i<15;i++){
      MostrarPersona(datos[i]);
      aux = datos[i];
      fprintf(fsalida, "%s;%s;%d%;%d;%f\n", aux.nombre, aux.apellido, aux.anio, aux.edad,  aux.estatura);
      if (aux.edad > promedio){
            fprintf(fotro, "%s;%s;%d%;%d;%f\n", aux.nombre, aux.apellido, aux.anio, aux.edad,  aux.estatura);                   
            }
  }
  
  //para escribir un texto
//      fputs(linea, fsalida);  
  //Cerramos los dos archivos
  fclose(f);
  fclose(fsalida);
  fclose(fotro);
  system("PAUSE");	
  return 0;
}
