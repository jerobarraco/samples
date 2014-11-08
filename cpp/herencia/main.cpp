#include <iostream>
#include "audia3.h"
#include "auto.h"

using namespace std;

int main(){
	Auto generico;
	AudiA3 aa3;
	generico.acelerar();
	aa3.acelerar();
	Auto consecionaria[4];
	Auto *prestado;
	prestado = new Auto();
	cout << "Hello World!" << aa3.velocidad() << endl;

	delete prestado;
	return 0;
}

