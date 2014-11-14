#include <iostream>
#include <fstream>

using namespace std;
const unsigned short TAM = 256;
struct Alumno{
    char nom[TAM];
    int edad;
    long dni;
};

int main(){
    cout << "Hello World!" << endl;

    const unsigned short CAL = 20;
    unsigned short i=0;
    long pdni;
    Alumno alumnos[CAL];
    cin >> pdni;
    //for (long pdni; pdni!=0; cin>>pdni){
    while (pdni!=0){
        alumnos[i].dni = pdni;
        cin >> alumnos[i].nom;
        cin >> alumnos[i].edad;
        i++;
        cin >> pdni;
    }

    fstream f;
    f.open("alumnos.txt", ios::out | ios::app);
    for (int j =0; j<i; j++){
        f<< alumnos[j].nom << " " << alumnos[j].edad << " " << alumnos[j].dni<< endl;
    }

    f.close();
    return 0;
}

