#ifndef MOTOR_H
#define MOTOR_H

class Motor{
	float cilindrada, rpm;
public:
	Motor(float pCil=0.80);
	void darCil(float pCil);
	void acelerar();
	void frenar();
	float velocidad();
};

#endif // MOTOR_H
