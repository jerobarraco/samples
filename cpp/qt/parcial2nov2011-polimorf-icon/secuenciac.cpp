#include "secuenciac.h"

secuenciac::secuenciac(int m, int n):Secuencia(m, n)
{
    fil = 1;
}

void secuenciac::actualizarEstado(){
    apagarTodo();
    for (int i=0; i<n; i++){
        panel->encender(fil, i);
    }
    fil += 1;
    fil %= m;
}
