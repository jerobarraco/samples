#include <fstream>
typedef struct {
        int a, b, op, res;
        }dato;
        
using namespace std;
class Archivo{
      private:
              fstream arc;
      public:
      bool Abrir(char nombre[]);
      void Escribir(dato d, int pos);
      dato Leer(int pos);
      void Cerrar();
      int Cantidad();
      void Verificar();
};
