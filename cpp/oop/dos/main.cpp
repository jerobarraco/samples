#include <iostream>

using namespace std;

class Circulo{
private:
	float r, ar;
public:
	void asignar(float rad);
	void calcular();
	float mostrar_area();
};

void Circulo::asignar(float rad){
	r = rad;
}
void Circulo::calcular(){
	ar = 3.14 *r*r;
}

float Circulo::mostrar_area(){
	return ar;
}

class Cilindro{
private:
	float a, v;
	Circulo c;
public:
	void asignar(float altura, float radio);
	void calcular();
	float mostrar();
};

void Cilindro::asignar(float altura, float radio){
	a = altura;
	c.asignar(radio);
}

void Cilindro::calcular(){
	c.calcular();
	v = a * c.mostrar_area();
}

float Cilindro::mostrar(){
	return v;
}

int main()
{
	cout << "Ingrese la altura y radio: ";
	float alt, rad;
	cin >> alt >> rad;

	Cilindro c1, c2;
	c1.asignar(alt, rad);
	c2.asignar(10.2, 5.3);

	c1.calcular();
	c2.calcular();

	cout << "El vol de c1 es " << c1.mostrar() << endl;
	cout << "El vol de c2 es " << c2.mostrar() << endl;

	return 0;
}

