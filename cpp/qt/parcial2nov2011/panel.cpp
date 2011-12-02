#include "panel.h"

Panel::Panel(int m, int n)
{
    matriz = vector< vector<bool> > (m, vector<bool>(n, false ) );
}
void Panel::encender(int i, int j){
    matriz.at(i).at(j) = true;
}

void Panel::apagar(int i, int j){
    matriz.at(i).at(j) = false;
}

bool Panel::encendido(int i, int j){
    return matriz.at(i).at(j);
}

/*
matriz = vector<vector<bool>> (m);
vector< vector<bool> >::iterator i;
vector<bool>::iterator j;
for (i=matriz.begin(); i!= matriz.end(); i++ ){
    *i = vector<bool>(n);
    for (j = i->begin(); j!= i->end(); j++){
        *j = false;
    }
}*/
