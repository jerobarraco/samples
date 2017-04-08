#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

class Persona {
public:
    float altura;
    char tipo;
    Persona(float a =0 , char t='x'){
        altura = a;
        tipo = t;
    }
    bool operator<(const Persona &otro){
        if (this->tipo < otro.tipo){
            return true;
        }else{
            if (this->tipo == otro.tipo){
                if (this->altura < otro.altura){
                    return true;
                }
            }
        }
        return false;
    }
};

int main(int argc, char *argv[]){
    fstream alturas("alturas.txt", ios::in);
    vector<Persona> v;

    while(alturas.is_open() && !alturas.eof()){
        Persona p;
        alturas >> p.altura >> p.tipo;
        alturas.ignore();
        v.push_back(p);
    }
    sort(v.begin(), v.end());
    reverse(v.begin(), v.end()-1);

    // sort(v.rbegin(), v.rend()); // esto hace lo mismo que las dos lineas anteriores

    cout << "Hello World!" << endl;
    return 0;
}
