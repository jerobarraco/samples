#include <iostream>
#include "math.h"


using namespace std;
class matrix{

public :
    void algo();
    void otra();
};

class amiga{
public :
    void uno();
};

class complex{
    float r, i;
public:
    //estos dos constructores , si yo no defino NINGUN CONSTRUCTOR, el compilador los crea
    //automáticamente, esos constructrores (creados por el compilador) se llaman
    //constructores "por defecto",
    //si yo defino mi propio constructor vacio, no es por defecto, es solo vacio
    // constructor vacio (no recibe parametros)
    //complex(){r=0;i=0;}
    // constructor copia, recibe SI O SI el mismo tipo por REFERENCIA y como CONST
    //complex(const complex &otro){ this->r = otro.r; this->i = otro.i;}

    //constructor personalizado
    complex(float pr=0, float pi=0){
        //puedo evitarme escribir el constructor vacio si pongo los parametros como opcionales
       r = pr;
       i = pi;
   }
   float mag(){
       return sqrt(pow(r,2)+pow(i,2));
   }

   complex& operator+(const complex &otro){
       //const es para que pueda usar operandos que sean const.(asi como operandos normales)
       //complex, porque el otro operando es complex tambien
       //& para evitar que se cree una copia, es por eso que usamos tambien const.
       //si no usara & no es necesario el const
       complex res;
       res.r = this->r + otro.r;
       res.i = this->i + otro.i;
       //otro.r = 255;
       return res;
   }

   complex& operator+(const float &otro){
       complex res;
       res.r = this->r + otro;
       res.i = this->i;
       return res;
   }
   complex& operator*( const complex &o){
       complex res, a, b;

       a.r = this->r*o.r;
       a.i = this->r*o.i;

       b.r = -this->i*o.i;
       b.i = this->i*o.r;

       res = a+b;
       return res;
   }

   complex& conj(){
       complex res;
       res.r = this->r;
       res.i = -this->i;
       return res;
   }
   complex& operator/(const complex & o){
    complex res, conj, num, denc;
    complex tmp = o;
    conj = tmp.conj();
    num = (*this)*conj;
    //Remember that a complex number times its conjugate will give a real number.
    //This process will remove the i from the denominator.)
    denc = conj*o;
    float den = denc.r;

    //distribuyo
    res.r = num.r/den;
    res.i = num.i/den;
    return res;
   }
   friend void imprimir(const complex &c);
   friend void matrix::algo();
   friend amiga;
};


void imprimir(const complex &c){
    cout <<"("<< c.r <<", "<< c.i<<"i)"<<endl;
}

int main(){
    cout << "Hello World!" << endl;
    complex c(4, 2);
    const complex b(3, -1);

    complex d;
    //d = c*b;//d = c.operator*(b);
    d = c/b;
    cout << "Res: ";
    imprimir(d);
    cout << "Mag res"<< d.mag()<< endl;

    float n = 5.4;
    complex e = d+n;// e = d.operator+(n);
    cout << "Mag res n"<< e.mag()<< endl;
    return 0;
}

void matrix::algo(){
    complex c;
    c.r = c.r*2;
}

void matrix::otra(){
    complex c;
    //c.r = c.r*2;//esta no es amiga, no puedo acceder a los miembros privados
}


void amiga::uno(){
    complex c;
    c.i = c.r;

}
