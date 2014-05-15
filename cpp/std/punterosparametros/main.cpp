#include <iostream>

using namespace std;
struct nodo{
    nodo* sig;
};
void agregar(nodo **ppini){
    nodo *p, *q;
    p= new nodo;
    p->sig = NULL;
    if((*ppini)==NULL){
        (*ppini) = p;
    }else{
        q = (*ppini);
        while(q->sig!=NULL){
            q = q->sig;
        }
        q->sig = p;
    }
}

int main()
{
    nodo *base=NULL;
    agregar(&base);
    cout << "Hello World!" << endl;
    return 0;
}

