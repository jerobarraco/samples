#include <cstdlib>
#include <iostream>
#include "archivo.h"

using namespace std;

int main(int argc, char *argv[])
{
    Archivo arch ;
    arch.Abrir("datos.bin");
    /*
    for (int i = 0; i<5; i++){
        dato aux;
        cout << "ingrese a, b, op y resultado" << endl;
        cin >> aux.a >> aux.b >> aux.op >> aux.res;
        arch.Escribir(aux, i);
    };*/
    cout << "El archivo contiene " << arch.Cantidad() << " registros: " << endl;
    for(int i = 0; i<arch.Cantidad(); i++){
            dato aux = arch.Leer(i);
            cout << aux.a << aux.b << aux.op << aux.res << endl;
    }

    arch.Verificar();
    cout << "verificando\n\n";
    for(int i = 0; i<arch.Cantidad(); i++){
            dato aux = arch.Leer(i);
            cout << aux.a << aux.b << aux.op << aux.res << endl;
    }
    
    arch.Cerrar();
    
    system("PAUSE");
    return EXIT_SUCCESS;
}
