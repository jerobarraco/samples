#include <iostream>

using namespace std;
void esBisiesto(){
//1ro leer
	int anyo;
	cout << "Ingrese el año: ";
	cin >> anyo;

	//2do calcular
	bool bisiesto = false;
	if(anyo%400 == 0){ //es divisible por 400
		bisiesto = true;
	}else{
		if((anyo%4==0) && (anyo%100!=0)){
			bisiesto = true;
		}
	}

	//3ro devolver
	if(bisiesto){
		cout << endl << "Es bisiesto"<< endl;
	}else{
		cout << endl << "No es bisiesto"<< endl;
	}

}

int main(int argc, char *argv[]){
	esBisiesto();
}
