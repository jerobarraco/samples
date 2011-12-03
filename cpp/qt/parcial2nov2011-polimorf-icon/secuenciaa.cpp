#include "secuenciaa.h"
#include "ctime"
SecuenciaA::SecuenciaA(int m, int n):Secuencia(m, n)
{
    srand(time(NULL));
}

void SecuenciaA::actualizarEstado(){
    int c, i, j;

    apagarTodo();

    c = (m*n/3);
    for (int x = 0; x<c; x++){
        i = rand()%m;
        j = rand()%n;
        panel->encender(i, j);
    }

}
