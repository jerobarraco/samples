#include <iostream>
using namespace std;

void sort1(float v[], int t);

int main() {
	int n, i;
	float vec[50];
	
	cout<<"Ingrese la cantidad de datos a leer: "; cin>>n;
	
	for (i=0; i<n; i++)
	{
		cout<<"Dato: "; cin>>vec[i];
	}
	
	sort1(vec,n);
	cout<<"***********************************************"<<endl;
	cout<<"ARREGLO ORDENADO"<<endl;
	for (i=0; i<n; i++)
		cout<<vec[i]<<endl;
	
	return 0;
}

void sort1(float v[], int t)
{ float aux;
	int i, j;

for (i=0; i<(t-1); i++)
	for (j=i+1; j<t; j++)
	{
		if (v[i] > v[j])
		{
			aux= v[i];
			v[i]= v[j];
			v[j]= aux;
		}
	}
}
