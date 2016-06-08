#include <QCoreApplication>
#include <fstream>
#include <iostream>

struct ficha{
	char * ape;
	char * nom;
	long int dni;
	int edad;
	int c_materias;
};

struct fichab{
	char ape[20];
	char nom[20];
	long int dni;
	int edad;
	int c_materias;
};


using namespace std;

void imprimir(fichab par){
	cout<< "Persona: " << par.ape << ", " << par.nom << " (" <<par.dni<< ").";
}

fichab leer(){
	fichab res;
	cin.getline(res.ape, 20);
	cin.getline(res.nom, 20);
	cin >> res.dni;
	return res;
}

void lib_leer(fichab *par){
	cin.getline(par->ape, 20);

	cin.getline(par->nom, 20);
	//cin.getline((*par).nom, 20);//esto es lo mismo de arriba
	cin >> par->dni;
}


void lib_leer2(fichab &par){
	cin.getline(par.ape, 20);

	cin.getline(par.nom, 20);
	//cin.getline((*par).nom, 20);//esto es lo mismo de arriba
	cin >> par.dni;
}

void suma(const int &a, int &b){b=a+b;}
int main(int argc, char *argv[]){
	fstream archivo;
	archivo.open("fichas.txt",  ios::out | ios::binary);
	if (archivo.is_open()){
		cout << "Bien" << endl;
	}
	cout << sizeof(fichab);
	fichab A = {"Lopez", "Gerardo", 24567890, 21, 11};
	imprimir(A);
	lib_leer(&A);
	lib_leer2(A);

	fichab B;
	B = leer();
	int c;
	suma(1, c);

	ficha vfichas[1] = {{"Lopez", "Gerardo", 24567890, 21, 11}};
	fichab vfichab[1] = {{"Lopez", "Gerardo", 24567890, 21, 11}};

	archivo.write((char*) vfichas, sizeof(ficha));
	archivo.write((char*)vfichab, sizeof(fichab));

	char texto[5] = "hola";
	cin >> texto;

	cout << endl<< texto;
}
