#include <iostream>
#include <cstdlib>
#include <cstring>

using namespace std;

int main()
{
    char s[20];
    int anio;

    cout << "Ingrese el año: " ;
    cin.getline(s, 20);
    anio = atoi(s);

    while(anio == 0){
        cout << endl <<"Debe ingresar un año valido, intente nuevamente: ";
        cin.getline(s, 20);
        anio = atoi(s);
    }
    return 0;
}

