#include <cstdlib>
#include <iostream>

using namespace std;
typedef struct nodo {
        int dato;
        nodo * sig;
};

int main(int argc, char *argv[])
{
    nodo *uno , *dos ;
    uno = (nodo*) malloc (sizeof(nodo));
    dos = (nodo*) malloc (sizeof(nodo));
    uno->sig= dos;
    
    system("PAUSE");
    return EXIT_SUCCESS;
}
