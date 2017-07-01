#include <iostream>

using namespace std;
class Complejo {
protected:
    int real;
    int imag;
public :
    Complejo(){
        real = 0;
        imag = 0;
    }
    Complejo (int r , int i){
        real = r;
        imag = i;
    }
    Complejo &suma(Complejo &b){
        Complejo res(*this);
        res.real = this->real+b.real;
        res.imag = this->imag+b.imag;
        return res;
    }
};


class ComplejoPlus: public Complejo{
    int decimales;
public:
    void imprimir(){
        cout << this->real;
    }

};

int main(int argc, char *argv[]){
    ComplejoPlus p;
    cout << "Hello World!" << endl;
    return 0;
}
