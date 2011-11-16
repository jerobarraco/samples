#include <iostream>
using namespace std;

int busca_bin(long v1[], long k, int t);

int main() {
	long dni[100], doc, dat;
	float alt[100];
	int edad[100], i, pos;
	
	cout<<"Ingrese los siguientes datos:"<<endl;
	cout<<"DNI: "; cin>>doc;
	i=0;
	while (doc != 0)
	{
		dni[i]= doc;
		cout<<"Edad: "; cin>>edad[i];
		cout<<"Altura: "; cin>>alt[i];
		cout<<"-----------------------------------------"<<endl; //solo para separar cada persona. No es necesario.
		i++;  //para mover el índice del arreglo
		cout<<"DNI: "; cin>>doc;
	}
	
	cout<<"*****************************************"<<endl;
	cout<<"Ingrese el DNI a buscar: ";
	cin>>dat;
	pos= busca_bin(dni,dat,i);
	cout<<"*****************************************"<<endl;
	
	if (pos == i+2)
		cout<<"El dato no fue hallado";
	else {cout<<"El dato fue hallado"<<endl;
		cout<<"DNI: "<<dni[pos]<<endl;
		cout<<"Edad: "<<edad[pos]<<endl;
		cout<<"Altura: "<<alt[pos]<<endl;}
	return 0; 
}

int busca_bin(long v1[], long k, int t)
{ int li, ls, medio, aux;

	li=0; ls=t; medio= (li+ls)/2;
	while ((li <= ls) && (k != v1[medio]))
	{
		if (k < v1[medio])
			ls=medio-1;
		else li=medio+1;
		medio= (li+ls)/2;		
	}
	
	if(li > ls)
		aux= t+2;  //le doy un valor grande y distinto de 0 puesto que 0 es la 1º posición del array.
	else aux= medio;

	
	return aux;
}
