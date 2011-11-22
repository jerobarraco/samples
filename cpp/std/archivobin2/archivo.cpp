#include "archivo.h"

bool Archivo::Abrir(char nombre[]){
     arc.open(nombre, ios::binary|ios::in|ios::out);
     return arc.good();
}

void Archivo::Cerrar(){
     arc.close();
}

int Archivo::Cantidad(){
    int tam_archivo;
    arc.seekg(0, ios::end);
    tam_archivo = arc.tellg();
    return tam_archivo /sizeof(dato);   
}
void Archivo::Escribir(dato d, int pos){
     arc.seekp(pos*sizeof(dato));
     arc.write(reinterpret_cast<const char*> (&d), sizeof(dato));
}
dato Archivo::Leer(int pos){
     dato aux;
     arc.seekg(pos*sizeof(dato));
     arc.read(reinterpret_cast<char*> (&aux), sizeof(dato));
     return aux;
};

void Archivo::Verificar()
{
     for(int i = 0; i<Cantidad(); i++){
             dato aux;
             aux = Leer(i);
             int res_aux;
             if (aux.op == 1){
                res_aux = aux.a+aux.b;
             } else{//asumo resta
                res_aux = aux.a-aux.b;        
             }
             if (res_aux != aux.res){
                aux.res=res_aux;
                Escribir( aux,i);
                }
     }  
}
