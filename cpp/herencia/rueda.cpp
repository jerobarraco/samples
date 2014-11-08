#include "rueda.h"

Rueda::Rueda(float pRad){
	rad = pRad;
}

void Rueda::darRad(float pRad){
	rad = pRad;
}

int Rueda::perimetro(){
	return 6.2830*rad;

}
