#include <iostream>
using namespace std;
struct Persona{
	char nombre[10];
	char apellido[10];
	char ciudad [10];
	char domicilio [20];
	char trabajo [10];
	int numero_de_cliente;
	int cantidad_de_compras_realizadas;
};

int main() {
    int i,edad,peso;
    char nombre;
    Persona x;
    for (i=0; i<100; i++);{
        cout<<"Ingrese el Nombre de la Persona: ";
        cin.getline(x.nombre,10);
        cout<<"Ingrese el Apellido de la Persona: ";
        cin.getline (x.apellido,10);
        cout<<"Ingrese la Ciudad del Cliente: ";
        cin.getline (x.ciudad,10);
        cout<<"Ingrese el Nombre del Local: ";
        cin.getline (x.trabajo,10);
        cout<<"Ingrese el El Domicilio del Local: ";
        cin.getline (x.domicilio,20);
        cout<<"Ingrese el Nro. del Cliente: ";
        cin>> x.numero_de_cliente;
        cout<<"Ingrese la catidad de Comprras que realizo en Cliente: ",
        cin>> x.cantidad_de_compras_realizadas;
        i=100;
        }

    cout<<"Nombre: " <<x.nombre <<endl;
    cout<<"Apellido: " <<x.apellido <<endl;
    cout<<"Localidad: " <<x.ciudad <<endl;
    cout<<"Auteservicio: " <<x.trabajo<<endl;
    cout<<"El Autoservicio se encuentra en calle: " <<x.domicilio<<endl;
    cout<<"Cliente Nro: " << x.numero_de_cliente <<endl;
    cout<<"El cliente realizo " << x.cantidad_de_compras_realizadas <<" " << "Compras." <<endl;
    system("PAUSE");
    return 0;
}

