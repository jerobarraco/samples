#include <iostream>
#include <fstream>

using namespace std;

struct Alumno{
    char nombre[255];
    int edad;
};

int main(){
    cout << "Hello World!" << endl;
    fstream f;
    f.open("archivo.txt", ios::in | ios::out |ios::app );
    cout << f.is_open()?"Ok":"Error";
    Alumno a {"Juan Pablo", 23};
    cout << setprecision(2);
    //f.write(&a.nombre, strlen(a.nombre) );
    f << a.nombre << " " << a.edad;
    f.close();
    return 0;
}

