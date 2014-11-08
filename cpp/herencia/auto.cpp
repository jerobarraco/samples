#include "auto.h"

Auto::Auto(){
}

void Auto::darDatos(float cil, float rodado){
	motor.darCil(cil);
	for (int i = 0; i<4; i++){
		ruedas[i].darRad(rodado);
	}
}

void Auto::acelerar(){
	motor.acelerar();
}

void Auto::frenar(){
	motor.frenar();
}

int Auto::velocidad(){
	//Rev/Seg * Metros/Rev = Metros/Segs
	return motor.velocidad()*ruedas[0].perimetro()/60;//metros sobre segundo
}
