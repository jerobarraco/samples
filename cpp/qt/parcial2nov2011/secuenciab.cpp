#include "secuenciab.h"

SecuenciaB::SecuenciaB(int m, int n):Secuencia(m, n)
{
    col = 1;
}

void SecuenciaB::actualizarEstado(){
    apagarTodo();
    for (int i=0; i<m; i++){
        panel->encender(i, col);
    }
    col += 2;
    if (col > n) {col =1;}
    //col = col%n;

}
