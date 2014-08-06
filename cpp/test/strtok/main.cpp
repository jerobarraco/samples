#include <iostream>
#include <cstring>
#include <cstdlib>

using namespace std;

int main(){

    //inicializacion
    ppal = strtok(frase, " ");

    //condicion de mientras
    while(ppal != NULL){

        ////operacion
        cout << ppal << endl;

        //incremento o cambio
        ppal = strtok(NULL, " ");
    }

    /*
    // for( inicializacion; condicion; incremento o cambio){
    //    operacion
    //    }
    for (ppal =strtok(frase, " "); ppal != NULL; ppal = strtok(NULL, " ")){
        cout << ppal << endl;
    }
    */


    cout << "Hello World!" << endl;
    delete frase;
    return 0;
}

