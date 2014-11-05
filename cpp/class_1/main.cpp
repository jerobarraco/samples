#include <iostream>

using namespace std;

class Cilindro{
private:
	float radio, altura, volumen;
public:
	Cilindro(float r, float a);
	~Cilindro();
};

int main(){
	cout << "Hello World!" << endl;
	Cilindro c1(1,1);
	Cilindro *c2;
	Cilindro c3(3,3);
	c2 = new Cilindro(5.3,10.2);
	//delete c2;
	return 0;
}



Cilindro::Cilindro(float r, float a){
	cout << "soy el constructor del objeto en la posicion "<< this<<endl;

}

Cilindro::~Cilindro(){
	cout << "destruyendo el objeto en la posicion "<< this<<endl;
}
