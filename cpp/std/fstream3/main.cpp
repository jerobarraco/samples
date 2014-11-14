#include <iostream>
#include <iomanip>
#include <fstream>

using namespace std;

int main(){
    fstream f;
    f.open("alumnos.txt", ios::in);
    /*char nom[255];
    f.getline(nom, 255);*/
    string nom;
    int nota1, nota2;
    float prom;
    while (getline(f, nom )){
        f >> nota1 >> nota2;
        prom = (nota1+nota2)/2.0;
        f.ignore();//por el enter que queda al final, para que pase a la siguiente linea
        cout << nom << " "<< nota1 << " " << nota2 << " " << setprecision(2) << prom <<endl;
    }
    //getline(cin, nom);
    //cin >> nom;

    //cout << "Hello " <<nom<< endl;
    return 0;
}

