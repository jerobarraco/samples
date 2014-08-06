#include <iostream>

using namespace std;
///para empezar vamos a definir la clase en el mismo main

class Cilindro{
private:
	float a, r, v;
public:
	void asignar(float altura, float radio);
	void calcular();
	float mostrar();
	//constructor vacio
	Cilindro();
	Cilindro(float alt, float rad);
	Cilindro(float rad);
	~Cilindro();
};

Cilindro::Cilindro(){
	a = 0;
	r = 0;
	v = 0;
}

Cilindro::Cilindro(float alt, float rad){
	a = alt;
	r = rad;
}

Cilindro::Cilindro(float rad){
	r = rad;
	a = 1.0;
}

Cilindro::~Cilindro(){
	//aca liberaria los punteros etc
	cout << "chau";
}
void Cilindro::asignar(float altura, float radio){
	a = altura;
	r = radio;
}

void Cilindro::calcular(){
	float area = 3.14*r*r;
	v = area * a;
}

float Cilindro::mostrar(){
	return v;
}


int main(){
	cout << "Hello World!" << endl;
	cout << "Ingrese la altura y radio: ";
	float alt, rad;
	cin >> alt >> rad;

	Cilindro c1, c2, c3(2, 7), c4(8);
	c1.asignar(alt, rad);
	c2.asignar(10.2, 5.3);

	c1.calcular();
	c2.calcular();

	cout << "El vol de c1 es " << c1.mostrar() << endl;
	cout << "El vol de c2 es " << c2.mostrar() << endl;

	return 0;
}

