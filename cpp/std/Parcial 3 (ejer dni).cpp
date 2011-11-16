#include <iostream>
using namespace std;
int busca_bin (int n);
struct{
	long dni;
	int edad;
	float altura;
}dato	;

int main() {
    int i=0;
    dato b[999];
    cout << "ingrese el dni:"; cin >> b[i].dni;
    while (b[i].dni != 0){
            cout << "ingrese edad: "; cin >> b[i].edad;
            cout << "ingrese altura: "; cin >> b[i].altura;
            i++;
            cout << "ingrese el dni:"; cin >> b[i].dni;
    }
    a = busca_bin(i);
    return 0;
}
void busca_bin (int n)
{ cout << "ingrese el dni a buscar: "; cin >> dat; 
li = 0; ls = n; medio = (li + ls)/2; 
while ((li <= ls) && (dat != b[medio].dni)) 
{ if (dat < b[medio].dni)
	ls = medio-1; 
else li = medio+1;
medio = (li + ls) / 2; }
if (li < ls)
	cout << "Dni no encontrado estupido!"; 
else cout << " El dni es: " << b[medio+1].dni << endl;
	cout << " La edad es: " << b[medio+1].edad << endl;
	cout << " La altura es: " << b[medio+1].altura << endl;
}

