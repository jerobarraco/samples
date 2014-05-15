#include <iostream>
#include <cstring>

using namespace std;

void plural(char *&p){
    int len = strlen(p);
    char *na = new char[len+3];
    na[0]='\0';
    strcat(na, p);

    /*
    char vocales[] = {'a','e','i','o','u'};
    bool es_vocal = false;
    for (int i = 0; i< 5; i++){
        if(p[len-2]==vocales[i]){
            es_vocal = true;
        }
    }
    if(es_vocal)
    */

    switch(p[len-1]){
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u':
            //agregar s
            strcat(na, "s");
            break;
        case 'z':
            na[len-1] = 'c';
            strcat(na, "es");
            break;
        default:
            strcat(na, "es");
            break;
    }
    delete p;
    p = na;
}

int main(){
    char *q = new char[50];
    cout << "ingrese sustantivo ";
    cin >> q;
    plural(q);
    cout << endl<< q<<endl;

}

