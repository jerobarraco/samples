#include <iostream>
#include <cstring>
using namespace std;

int leer(char const * msg){
    int x;
    cout << msg << ": ";
    cin >> x;
    //cout << endl;
    return x;
}

void leer(char const *msg, char *out){
    cout << msg << ": ";
    cin >> out;
    //cout << endl;
}

void leer(char const *msg, char *out, size_t len){
    cout << msg << ": " << flush;
    cin.getline(out, len);
    //cout << endl;
}

int main()
{
    char **palabras;
    int n;
    n = leer("Ingrese la cantidad de palabras");


    palabras = new char* [n];//creamos el vector de palabras
    for(int i =0; i<n; i++){
        palabras[i] = new char[12];
        leer("ingrese palabra", palabras[i]);
        //cin >> palabras[i]
    }

    cout << "Elementos pares :"<< endl;
    for (int i = 0; i<n; i+=2){
        cout << "\t" <<palabras[i] << endl;
    }

    cout << "El primero alfabético es: "<< endl;
    int menor=0;
    for (int i =0; i<n; i++){
        int res = strcmp(palabras[menor], palabras[i]);
        // res == 0  -> son iguales
        // res <  0  -> palabra menor esta antes que la palabra i (actual)
        // res >  0  -> palabra menor esta DESPUES que la palabra i (actual)
        /* esto no se puede (debe)
        palabras[menor] < palabras[i];
        string < string;//mentira, es una ilusion
        char[] < char[]
        char * < char *
        puntero < puntero;
        */
        if( res>0 ){
            menor =i;
        }
    }
    cout << "\t" << palabras[menor] << endl;

    cout << "Palabras que comienzan con 'mar': "<< endl;
    for(int i =0; i<n; i++){
        int res = strncmp(palabras[i], "mar", 3);
        if( res ==0){
            cout << "\t" << palabras[i] << endl;
        }
    }

    for (int i=0; i<n; i++){
        delete palabras[i];
    }
    delete palabras;
    return 0;
}

