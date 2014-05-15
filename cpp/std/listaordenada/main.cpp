#include <iostream>

using namespace std;

struct nodo{
    int idProc;
    int prio;
    nodo *sig;
};

void agregar(nodo* &pini, int idProc, int prio){
    nodo *ant=NULL, *act=NULL, *nuevo;

    //1º creamos el nodo
    nuevo = new nodo;
    nuevo->idProc = idProc;
    nuevo->prio = prio;
    nuevo->sig = NULL;
    //(*nuevo).idProc // no hay que olvidarse que la flechita indica que estoy accediendo al valor

    //2º lo insertamos en la lista
    // puede pasar que no haya nada
    if(pini == NULL){
        pini = nuevo;
    }else{//o que tenga algo
        //en el caso que quede primero
        if(pini->prio<prio){
            nuevo->sig = pini;
            pini = nuevo;
        }else{//sino
            //vamos a tener que recorrerlo
            ant = pini;
            act = ant->sig;
            while(ant != NULL){
                if(act==NULL){//lo asigno al final
                    ant->sig = nuevo;
                    break; //salimos del bucle porque ya terminamos
                }else if (act->prio < nuevo->prio){
                    nuevo->sig = act;
                    ant->sig = nuevo;
                    break;
                }
                //adelantamos los punteros
                ant = act;
                act = act->sig;
            }
        }
    }

}

int main(){
    nodo *base=NULL;
    int idProc, prio;
    cout << "ingrese id proc , 0 para salir ";
    cin >>idProc;
    while(idProc != 0){
        cout << endl<< "ingese la prioridad ";
        cin >> prio;

        agregar(base, idProc, prio);

        cout << endl<<"ingrese id proc , 0 para salir ";
        cin >>idProc;
    }

    nodo *q =base;
    while(q!=NULL){
        cout << endl<< "Prio " << q->prio << ", idProc "<< q->idProc ;

        q = q->sig;
    }
    cout << endl << "FIN";


    return 0;
}

