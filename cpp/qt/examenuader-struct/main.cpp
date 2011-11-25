#include "iostream"
using namespace std;

//TypeDef significa "definir un tipo de dato"
typedef struct {
    long dni;
    int edad;
    float altura;
} persona;

int busca_bin( ){//hay que poner los parametros necesarios para poder recorrer el vector
    /*
        necesito los parametros
        dni a buscar
        vector con los datos a buscar
        la cantidad maxima de elementos utiles en el vector

     */
    //el int, significa que voy a devolver la posicion
    //si no encuentro nada devolver -1


}

int main(int argc, char *argv[])
{
    const int max = 200;
    persona vector[max];

    int pos = 0; //una variable que lleva la posicion sobre el vector para ir recorriendolo
    //usamos do-while para evaluar al final
    do{

        //variable auxiliar para cada persona, se reemplaza en cada vuelta del while
        persona aux;

        cout << "\n\ndni (0 para salir): ";
        cin >> aux.dni;

        cout << "\nedad: ";
        cin >> aux.edad;

        cout << "\naltura: ";
        cin >> aux.altura;

        vector[pos] = aux;

        /* es similar a esto;
          cin >> vector[pos].dni;
          cin >> vector[pos].altura;
          cin >> vector[pos].edad;
        */

        //incrementamos pos para ir al siguiente
        pos++;
    }while( vector[pos-1].dni != 0);

    pos--; //elimino el ultimo que es invalido

    /*con esto imprimo la lista entera
    for (int i = 0; i< pos; i++)
        cout << vector[i].altura << "\t" << vector[i].edad << "\t" << vector[i].dni << endl;
    */

    //int posicion_encotrado = busca_bin(dni_a_buscar, vector, pos);
    if (posicion_encontrado == -1){
        cout << "No se ha encontrado";
    }else{
        cout << "La persona es:";
        cout << vector[posicion_encontrado].altura << "\t";
        cout << vector[posicion_encontrado].edad << "\t";
        cout << vector[posicion_encontrado].dni << endl;
    }

}
