#include <QtCore/QCoreApplication>
#include <iostream>

#include "figura.h"
#include "triangulo.h"
#include "rectangulo.h"
#include "t_rectangulo.h"

using namespace std;

void mostrarArea(figura *fig){
    cout << "El area es " << fig->area() << endl;
}

int main(int argc, char *argv[])
{
    /* ya no puedo porque la hice abstracta igualando a 0 el metodo virtual
    figura f;
    f.set_dim(10, 10);*/

    triangulo t;
    t.set_dim(20, 10);

    t_rectangulo tr;
    tr.set_dim(20, 10);

    rectangulo r;
    r.set_dim(20, 10);

    figura *pfig_temp;

    pfig_temp= &t;
    mostrarArea(pfig_temp);

    pfig_temp= &tr;
    mostrarArea(pfig_temp);

    pfig_temp= &r;
    mostrarArea(pfig_temp);

    system("PAUSE");
}

