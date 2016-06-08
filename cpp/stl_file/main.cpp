#include <fstream>
#include <iostream>
#include <list>


using namespace std;

class medida {
public:
	float altura;
	char grupo;
	bool operator<(const medida &b){
		return this->altura < b.altura;
	}
	bool operator==(const medida &b){
		return (this->altura == b.altura);
	}

};

	/*sort(){

		res = a>b
		res = a.mayor(b)
	}

	unique(){grupo

		res = (a==b)
		res = a.igual(b)
	}
	*/



int main(int argc, char *argv[]){
	fstream archivo;
	archivo.open("alturas.txt", ios::in | ios::out );
	if (archivo.is_open()){
		cout << "Bien" << endl;
	}

//1º leer archivo
	string grupo;
	float altura;
	std::list<medida> grupoA, grupoB;
	/*while( archivo >> altura >> grupo){
		cout << "La altura es " << altura << " grupo "<< grupo << endl;
	}*/
	bool continuar= true;
	medida med;
	while (continuar){

		if( archivo >> altura >> grupo){
			med.altura = altura;
			med.grupo = grupo.c_str()[0];
			cout << "La altura es " << altura << " grupo "<< grupo << endl;
			// si es a o b cargar en la lista que corresponda
			if (med.grupo == 'A'){
				grupoA.push_back(med);
			}else{
				grupoB.push_back(med);
			}

		}else{
			continuar = false;
		}
	}

	//ordenar
	grupoA.sort();
	grupoA.unique();


	//mostrar
	list<medida>::iterator reco;
	for (reco = grupoA.begin(); reco != grupoA.end(); reco++){
		//o esto
		//medida med = *reco;
		cout << "La nueva altura es " << (reco->altura) << " grupo " << (reco->grupo)<< endl;

	}





	/*
	while (!archivo.eof()){
		archivo >> altura;
		archivo >> grupo;
		archivo.peek();

		cout << "La altura es " << altura << " grupo "<< grupo << endl;
	}*/








}
