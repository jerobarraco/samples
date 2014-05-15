#include <iostream>

using namespace std;
struct nodo{
    nodo* sig;
    char val;
};

void agregar(nodo *&pini){
    nodo *p, *q;
    p= new nodo;
    p->sig = NULL;
    p->val = 'a';
    if(pini==NULL){
       pini = p;
    }else{
        q = pini;
        while(q->sig!=NULL){
            q = q->sig;
        }
        q->sig = p;
    }
}

int main()
{
    nodo *base=NULL;

    agregar(base);
    cout << "Hello World!" << endl;
    return 0;
}

