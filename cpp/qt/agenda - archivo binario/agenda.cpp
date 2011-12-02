#include "agenda.h"
Agenda::Agenda(QString narchivo)
{
    archivo.setFileName(narchivo);
    archivo.open( QIODevice::ReadWrite );
}

Agenda::~Agenda(){
    archivo.close();
}

int Agenda::cantEntrada(){
    int tam = archivo.size();
    return tam / sizeof(registro);
}

bool Agenda::actEntrada(unsigned int nentrada, registro v){
    //si el numero de entrada pasa la cantidad, entonces devuelvo false
    if (nentrada>cantEntrada()){
        return false;
    }
    archivo.seek( nentrada*sizeof(registro) );
    //reinterpret_cast<const char*> (&v) //este es el mas recomendado
    archivo.write( (const char*) (&v), sizeof(registro) );
    return true;
}

void Agenda::agregarFinal(registro v){
    /* un truco para reusar la otra funcion
      //tomo la cantidad y la uso como posicion
    int ultimo = cantEntrada();
    //llamo a la funcion para actualizar. esto depende de que se me permita escribir en la posicion
    actEntrada(ultimo, v);
    */
    //nos desplazamos al final
    archivo.seek(archivo.size());
    //escribimos
    archivo.write((const char*) (&v), sizeof(registro));
}

bool Agenda::valorEntrada(unsigned int nentrada, registro &v){
    if (nentrada>=cantEntrada()){
        return false;
    }
    //no necesita un else porque antes hay un return, es algo sucio pero funciona
    archivo.seek(nentrada*sizeof(registro));
    archivo.read((char *) (&v), sizeof(registro) );
    return true;
}
