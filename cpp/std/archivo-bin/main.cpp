#include <cstdlib>
#include <iostream>
#include <fstream>

using namespace std;
typedef struct {
        int a, b;
        int op;
        int res;
}dato;

int main(int argc, char *argv[])
{
    ofstream escribe;
    const int cant = 10;
    dato valores [cant];
    
    escribe.open("datos.bin", ios::out | ios::binary);

    
    for (int i = 0; i<cant; i++){
        escribe.write(reinterpret_cast< const char *> (&valores[i]), sizeof(dato));         
    }
    //escribe.seekp( 5*sizeof(dato));
    //escribe.tellp(); //-> devuelve la posicion (en bytes) del puntero de escritura, 
    
    escribe.close();
    
    
    /*
    ifstream lectura;
    lectura.open("datos.bin", ios::in | ios::binary);
    for (int i = 0; i<cant; i++){
            lectura.read( reinterpret_cast< const char *> (&valores[i]), sizeof(dato));
    }
    lectura.seekg(6*sizeof(dato));
    lectura.tellg();
    lectura.close();*/
    system("PAUSE");
    return EXIT_SUCCESS;
}
