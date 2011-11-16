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
void ordena(ficha_alumno v[], int t);

int main() {
	ficha_alumno x[10];
	long dat;
	int i, n;
	
	cout<<"Ingrese la cantidad de datos a leer: "; cin>>n;
	
	for (i=0; i<n; i++)
	{
		cout<<"Nombre/s: "; cin>>x[i].nombre; 
		cout<<"Apellido/s: ";cin>>x[i].apellido; 
		cout<<"Domicilio: "; cin>>x[i].domicilio; 
		cout<<"DNI: "; cin>>x[i].dni;
		cout<<"Edad: "; cin>>x[i].edad;	
		cout<<"............."<<endl;;
	}
	cout<<"*****************************************"<<endl;
	cout<<"ARREGLO SIN ORDENAR"<<endl;
	for(i=0; i<n ; i++)
	{
		cout<<"Alumno: "<<x[i].nombre<<" "<<x[i].apellido<<endl;
		cout<<"DNI: "<<x[i].dni<<"          "<<"Edad: "<<x[i].edad<<endl;
		cout<<"Domicilio: "<<x[i].domicilio<<endl;
		cout<<"-----------------------------------------------------------"<<endl;
	}
	cout<<endl;
	cout<<"ARREGLO ORDENADO por DNI"<<endl;
	ordena(x,n);
	for(i=0; i<n ; i++)
	{
		cout<<"Alumno: "<<x[i].nombre<<" "<<x[i].apellido<<endl;
		cout<<"DNI: "<<x[i].dni<<"          "<<"Edad: "<<x[i].edad<<endl;
		cout<<"Domicilio: "<<x[i].domicilio<<endl;
		cout<<"-----------------------------------------------------------"<<endl;
	}
	
	return 0;
}



void ordena(ficha_alumno v[], int t)
{ ficha_alumno aux;
int i, j;

	for (i=0; i<(t-1); i++)
		for (j=i+1; j<t; j++)
		{
			if (v[i].dni > v[j].dni)
			{
				aux= v[i];
				v[i]= v[j];
				v[j]= aux;
			}
		}
}
