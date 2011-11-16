#include <iostream>
using namespace std;
struct alumno{
	long dni;
	int nota1;
	int nota2;
	int nota3;
	int promedio;
};

int main(int argc, char *argv[]) {
	int n,c;
	struct alumno vec[500], aux;
	cout << "ingrese los alumnos";
	cin >> n;
	c=0;
	while (c!=n){
		cin >> vec[c].dni;
		cin>> vec[c].nota1;
		cin>> vec[c].nota2;
		cin>> vec[c].nota3;
		c++;
	}
	for (c= 0 ; c< n; c++){
		vec[c].promedio = (vec[c].nota1+vec[c].nota2+vec[c].nota3)/3.0;
	}
	for (int i = 0; i<n-1; i++){
		for (int j=0; j<n; j++){
			if (vec[i].promedio < vec[j].promedio){
				aux = vec[i];
				vec[i]  = vec[j];
				vec[j] = aux;
			}
				
		}
	}
	cout << "documento" <<"nota1" <<"nota2"<< "nota3"<< "prom\n";
	for (c=0; c<n; c++){
		cout << vec[c].dni;
		cout << vec[c].nota1;
			cout << vec[c].nota2;
		cout << vec[c].nota3;
		cout << vec[c].promedio;
		cout << "\n";
	}
	return 0;
}

