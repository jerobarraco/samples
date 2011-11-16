#include <iostream>
using namespace std;

int busca_secu(int v[], int k, int t, int &p);

int main() {
	int vec[10], i, band=0, dat, pos, n;
	
	cout<<"Cantidad de datos a cargar: "; cin>>n;
	for (i=0; i<n; i++)
	{
		cout<<"Dato: "; cin>>vec[i];
	}
	cout<<"*****************************************"<<endl;
	cout<<"Ingrese el dato a buscar: ";
	cin>>dat;
	band= busca_secu(vec,dat,n,pos);
	cout<<"*****************************************"<<endl;
	
	if (band == 0)
		cout<<"El dato no fue hallado";
	else cout<<"El dato fue hallado en la posición "<<pos;
	
	return 0; 
}

int busca_secu(int v[], int k, int t, int &p)
{
	int c=0; 	
	while (c < t)
	{
		if (v[c] == k)
		{	p=c+1;
			c=t;
			return 1;
			}
	c++;		
	}
}
