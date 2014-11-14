#include "motor.h"

Motor::Motor(float pCil){
	rpm = 0;
	cilindrada = pCil;
}

void Motor::darCil(float pCil){
	cilindrada = pCil;
}

void Motor::acelerar(){
	rpm+= cilindrada*0.5;
}

void Motor::frenar()
{
	if(rpm>0)
		rpm -= cilindrada*0.5;
	else
		rpm = 0;
}

int Motor::velocidad(){
	return rpm;
}
