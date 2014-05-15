#include <iostream>
#include <cstring>

using namespace std;

int main(){
    char *dato;
    dato = new char[120];
    int op;
    //igual que char dato[120];
    cin.getline(dato, 120);
    cout << "1. a Mayus \n 2. a minus \n 3. solo inicial Mayus: " ;
    cin >> op;
    switch(op){
        case 3:
            dato[0] = toupper(dato[0]);
            break;
        case 2:
            //for(int i =0; datos[i] != '\0'; i++){
            int n= strlen(dato);
            for (int i =0; i<n ; i++){
                dato[i] = tolower(dato[i]);
            }
            break;
        case 1:
            int n= strlen(dato);
            for (int i =0; i<n ; i++){
                dato[i] = toupper(dato[i]);
            }
            break;
    }
    cout << endl << dato;
    delete dato;

   return 0;
}

