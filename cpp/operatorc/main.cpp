#include <iostream>
#include <stdlib.h>
#include <cmath>
#include <algorithm>
#include <vector>
using namespace std;
class Complejo{
private:
	float ent;
	float imag;

public:
	Complejo(float x=0, float y=0){
		ent=x;
		imag=y;
	}
	Complejo(const Complejo &otro){
		this->ent = otro.ent;
		this->imag = otro.imag;
	}

	void AsignarReal(float f){ ent=f;}
	void AsignarImag(float f){ imag=f;}

	friend ostream & operator<<(ostream &O, const Complejo &C){
		O << C.ent;
		if (C.imag>0)
			O << "+"<<C.imag<<"i"<<endl;
		else
			O << C.imag << "i"<<endl;
		return O;
	}

	Complejo & operator=(const Complejo &otro){
		this->ent = otro.ent;
		this->imag = otro.imag;
		return *this;
	}

	Complejo &operator+(const Complejo &otro){
		Complejo Res;
		Res.ent = this->ent + otro.ent;
		Res.imag = this->imag + otro.imag;
		return Res;
	}

	friend bool operator<(Complejo const & C1, Complejo const & C2) {
		float n1, n2;
		n1=C1.ent*C1.ent+C1.imag*C1.imag;
		n2=C2.ent*C2.ent+C2.imag*C2.imag;
		return (n1<n2);
	}

};

int main(int argc, char *argv[]){
	Complejo Comp1(2,3);
	Complejo Comp2(3,4);
	Complejo Comp3 = Comp1+Comp2;
	cout << "Complejo1 es " << Comp1 << endl;
	cout << "Complejo2 es " << Comp2 << endl;
	cout << "SUma es" << Comp3;

	vector<Complejo> vc;
	Complejo C;
	int n=10;
	//cout<<"Ingrese cantidad de valores del vector: "<<endl;
	//cin>>n;
	for (int i=0; i<n; i++){
		C.AsignarReal(rand()%1000);
		C.AsignarImag(rand()%1000);
		cout << C << endl;
		vc.push_back(C);
	}

	sort(vc.begin(), vc.end());

	for (vector<Complejo>::iterator i=vc.begin(); i!=vc.end(); ++i)
		cout << *i;

	Comp1<Comp2;
	return 0;
}
