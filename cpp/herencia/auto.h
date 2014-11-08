#ifndef AUTO_H
#define AUTO_H
#include "rueda.h"
#include "motor.h"

class Auto{
private:
	Motor motor;//Rel de composicion 1-1
	Rueda ruedas[4];//relacion de composicion 1-4
public:
	Auto();
	void darDatos(float cil, float rodado);
	void acelerar();
	void frenar();
	float velocidad();
};

#endif // AUTO_H
