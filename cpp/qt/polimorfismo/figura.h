#ifndef FIGURA_H
#define FIGURA_H

class figura {
protected:
 double x, y;

public:
 figura();

 void set_dim(double i, double j=0) {
   x = i;
   y = j;
 } 

 /*virtual int area(){
     return 0;
 }*/
 //Al poner =0 le decimos al compilador que esta funcion no tiene implementacion
 //Por lo tanto no se puede instanciar esta clase
 virtual int area()=0; //metodo virual puro, y con que haya un solo metodo virtual puro en la clase
 //convierte la clase en una clase abstracta
};

#endif // FIGURA_H
