#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstdlib>
#include <cmath>
#include <ctime>
#include <cstring>
#include <list>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

class Complejo{
private:
	float ent;
	float imag;

public:
	Complejo (){
		ent=0;
		imag=0;
	}

	Complejo (float x, float y){
		ent=x;
		imag=y;
	}

	void AsignarReal(float f){
	ent=f;}

	void AsignarImag(float f){
	imag=f;}


	/*float DevolverReal(){return ent;};
	float DevolverImag(){return imag;};*/
	Complejo operator+(Complejo C);
	Complejo operator*(Complejo C);
	Complejo operator!();
	friend bool operator<(Complejo const & C1,Complejo const & C2);
	bool operator>(Complejo const & C);

	Complejo sumar(Complejo C2){
		Complejo Res;
		Res.ent=this->ent+C2.ent;
		Res.imag=this->imag+C2.imag;
		return Res;
	}

	Complejo conjugado(){
		Complejo Res;
		Res.ent=this->ent;
		Res.imag=this->imag*(-1);
		return Res;
	}

	Complejo multiplicar(Complejo C){
		Complejo Res;
		Res.ent=(this->ent*C.ent)-(this->imag*C.imag);
		Res.imag=(this->ent*C.imag)+(this->imag*C.ent);
		return Res;
	}

	bool menor(Complejo C){
		float n1, n2;
		n1=ent*ent+imag*imag;
		n2=C.ent*C.ent+C.imag*C.imag;
		return (n1<n2);
	}

	bool mayor(Complejo C){
		float m1, m2;
		m1=ent*ent+imag*imag;
		m2=C.ent*C.ent+C.imag*C.imag;
		return (m1>m2);
	}

	friend ostream & operator<<(ostream &O, Complejo C){
		O<<C.ent;
		if (C.imag>0)
			O<<"+"<<C.imag<<"i"<<endl;
		else O<<C.imag<<"i"<<endl;
		return O;

	}
};

int main() {

	Complejo Comp1(2,3);
	Complejo Comp2(3,4);
	Complejo Comp3;

	Comp3=Comp1+Comp2;
	cout<<"La suma es: "<<Comp3<<endl;

	Comp3=Comp1*Comp2;
	cout<<"El producto es: "<<Comp3<<endl;

	Comp3=!Comp3;
	cout<<"El conjugado es: "<<Comp3<<endl;

	if (Comp3<Comp2)
		cout<<"El resultado es menor al numero dado. "<<endl;
	else cout<<"El resultado no es menor al numero dado. "<<endl;
	if (Comp3>Comp2)
		cout<<"El resultado es mayor al numero dado"<<endl;
	else cout<<"El resultado no es mayor al numero dado. "<<endl;

	vector<Complejo> vc;
	Complejo C;
	int n;
	cout<<"Ingrese cantidad de valores del vector: "<<endl;
	cin>>n;
	for (int i=0; i<n; i++){
		C.AsignarReal(rand()%1000);
		C.AsignarImag(rand()%1000);
		cout << C << endl;
		vc.push_back(C);
	}
	sort(vc.begin(), vc.end());

	for (vector<Complejo>::iterator i=vc.begin(); i!=vc.end(); ++i)
	  cout << *i;
	return 0;
}

Complejo Complejo::operator+(Complejo C){
	Complejo Res;
	Res.ent=this->ent+C.ent;
	Res.imag=this->imag+C.imag;
	return Res;
}

Complejo Complejo::operator*(Complejo C){
Complejo Res;
Res.ent=(this->ent*C.ent)-(this->imag*C.imag);
Res.imag=(this->ent*C.imag)+(this->imag*C.ent);
return Res;}

Complejo Complejo::operator!(){
Complejo Res;
Res.ent=this->ent;
Res.imag=this->imag*(-1);
return Res;}

bool operator<(Complejo const & C1,Complejo const & C2) {
float n1, n2;
n1=C1.ent*C1.ent+C1.imag*C1.imag;
n2=C2.ent*C2.ent+C2.imag*C2.imag;
return (n1<n2);}

bool Complejo::operator>(Complejo const & C) {
	float m1, m2;
	m1=ent*ent+imag*imag;
	m2=C.ent*C.ent+C.imag*C.imag;
	return (m1>m2);
}
