#include "secuencia.h"

Secuencia::Secuencia(int m, int n)
{
    panel = new Panel(m, n);
    this->m = m;
    this->n = n;
}

Secuencia::~Secuencia(){
    delete panel;
}

bool Secuencia::encendido(int i, int j){
    return panel->encendido(i, j);
}

void Secuencia::apagarTodo(){
    for (int i= 0;i<m; i++){
        for (int j=0; j<n; j++){
            panel->apagar(i,j);
        }
    }
}
