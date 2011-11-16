#include <iostream>
using namespace std;
//Se utiliza un array de struct y se realiza una búsqueda sec. por medio de una función.

struct ficha_alumno{
	char nombre[20];
	char apellido[20];
	char domicilio[50];
	long dni;
	int edad;
};
void busca_secu(ficha_alumno v[], long k, int t, int &p);

int main() {
	ficha_alumno vec[10];
	long dat;
	int i, pos=0, n;
	
	cout<<"Cantidad de datos a cargar: "; cin>>n;
	for (i=0; i<n; i++)
	{
		cout<<"Nombre/s: "; cin>>vec[i].nombre; 
		cout<<"Apellido/s: ";cin>>vec[i].apellido; 
		cout<<"Domicilio: "; cin>>vec[i].domicilio; 
		cout<<"DNI: "; cin>>vec[i].dni;
		cout<<"Edad: "; cin>>vec[i].edad;	
		cout<<"............."<<endl;;
	}
	cout<<"*****************************************"<<endl;
	cout<<"Ingrese el DNI a buscar: ";
	cin>>dat;
	busca_secu(vec,dat,n,pos);
	cout<<"*****************************************"<<endl;
	
	if (pos == 0)
		cout<<"El dato no fue hallado";
	else cout<<"El dato fue hallado en la posición "<<pos;
	
	return 0; 
}

void busca_secu(ficha_alumno v[], long k, int t, int &p)
{
	int c=0; 	
	while (c < t)
	{
		if (v[c].dni == k)
		{	p=c+1;
			c=t;
			}
	c++;		
	}
}
