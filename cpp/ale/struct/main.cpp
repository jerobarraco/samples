#include <iostream>
#include <string>
using namespace std;

struct Persona{//esto es un molde para UNA persona
    int nota;
    int edad;
    char sexo;
};

//forma vieja para los c++ viejos.
typedef struct {
}Persona;

unsigned short int x;
typedef unsigned short int usi; //creo un "tipo de datos" llamado "usi" que es un alias de unsigned short int

usi a, b;

Persona crear(int nota, int edad, char sexo){
    Persona ret;
    ret.nota = nota;
    ret.edad = edad;
    ret.sexo = sexo;
    return ret;
}

int main(){
    cout << "Hello World!" << endl;
    //esto representaria un alumno
    int notas[20];
    int edades[20];
    char sexo[20];// 'm' o 'f'

    //se usa como un tipo
    Persona personas[20];
    Persona p1;
    //se accede a los miembros con punto
    p1.edad = 50;
    p1.nota = 6;

    personas[5].sexo = 'm';

    return 0;
}

