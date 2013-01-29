#include <iostream>
#include <cmath>
#include <iomanip>
#include <windows.h>

using namespace std;

void IngresoInfo(int data [100][7], int &cantidad_filas);
void MostrarInfo(int data[100][7], int cant);
int HorasAlMes(int A[100][7], int cantidad_filas, int id,int mes,int &horas,int &minutos);
int Cantidad_empleados(int datos_empleados[100][7], int cantidad_fila, int& cantidad_empleados);

int main()
{
 cout<<"///////////////////////////////////////////"<<endl
     <<"//"<<endl
     <<"// EXAMEN: Examen de Promoción de Especificación de Algorítmos, Nov. 2012"<<endl
     <<"// APELLIDO: BOUCHER "<<endl
     <<"// NOMBRES: FIORELLA"<<endl
     <<"// MATRICULA: 38501282"<<endl
     <<"// DNI: 38501282"<<endl
     <<"// COMISION: 1.5 C++ (Corral-Briones)"<<endl
     <<"//"<<endl
     <<"//////////////////////////////////////////"<<endl;

    int empleados[100][7];
    int identificacion=0;
    int cant_filas  =0;
 IngresoInfo(empleados, cant_filas);
 MostrarInfo(empleados, cant_filas);

 //declaramos las variables AFUERA para que las modifique la funcion,
    int horas, minutos;
    HorasAlMes(empleados, cant_filas, 1, 1, horas, minutos);
    cout<<"Las horas trabajadas al mes son: "<<horas<<":"<<minutos<<endl<<endl;
    int cant_empleados;
    Cantidad_empleados(empleados, cant_filas, cant_empleados);
    cout << "La cantidad de empleados es "<< cant_empleados;
 system("pause");
 return 0;
}

void IngresoInfo(int A[100][7], int& cant)
{
 cout<<"\t\t1)Identificacion de empleado."<<endl
     <<"\t\t2)Dia."<<endl
     <<"\t\t3)Mes."<<endl
     <<"\t\t4)Hora de Ingreso."<<endl
     <<"\t\t5)Minuto de Ingreso."<<endl
     <<"\t\t6)Hora de Egreso."<<endl
     <<"\t\t7)Minuto de Egreso."<<endl;

int i = 0;
 while(i<100)
 {
    cout<<"Fila: "<<i+1<<endl;
    ini1:
       cout<<"Ingrese Id. del empleado."<<endl;
       cin>>A[i][0];
       if(A[i][0]>0){
        cout<<"Estupendo!"<<endl<<endl;
       }
       else {
        break;
       }
    ini2:
       cout<<"Ingrese Dia."<<endl;
       cin>>A[i][1];
        if(A[i][1]<30 && A[i][1]>0)
        {
         cout<<"Estupendo!"<<endl<<endl;
        }
        else
        {
         cout<<"No corresponde. Ingrese caracter correcto."<<endl;
         goto ini2;
        }
    ini3:
       cout<<"Ingrese Mes."<<endl;
       cin>>A[i][2];
           if(A[i][2]<12 && A[i][2]>0)
           {
            cout<<"Estupendo!"<<endl<<endl;
           }
           else
           {
            cout<<"No corresponde. Ingrese caracter correcto."<<endl;
            goto ini3;
        }
    ini4:
           cout<<"Ingrese Horas de Ingreso."<<endl;
           cin>>A[i][3];
           if(A[i][3]<23&&A[i][3]>=0)
           {
            cout<<"Estupendo!"<<endl<<endl;
           }
           else
           {
            cout<<"No corresponde. Ingrese caracter correcto."<<endl;
            goto ini4;
           }
   ini5:
           cout<<"Ingrese Minutos de Ingreso."<<endl;
           cin>>A[i][4];
           if(A[i][4]<59&&A[i][4]>=0)
           {
            cout<<"Estupendo!"<<endl<<endl;
           }
           else
           {
            cout<<"No corresponde. Ingrese caracter correcto."<<endl;
            goto ini5;
           }
    ini6:
           cout<<"Ingrese Horas de Egreso."<<endl;
           cin>>A[i][5];
           if(A[i][5]<23&&A[i][5]>=0)
           {
            cout<<"Estupendo!"<<endl<<endl;
           }
           else
           {
            cout<<"No corresponde. Ingrese caracter correcto."<<endl;
            goto ini6;
           }
    ini7:
           cout<<"Ingrese Minutos de Egreso."<<endl;
           cin>>A[i][6];
           if(A[i][6]<59&&A[i][6]>=0)
           {
            cout<<"Estupendo!"<<endl<<endl;
           }
           else
           {
            cout<<"No corresponde. Ingrese caracter correcto."<<endl;
            goto ini7;
           }
    i++;
 }
 cant = i;
 cout<<"Ingreso de datos concluido."<<endl<<endl;
 return ;
}



void MostrarInfo(int instru[100][7], int cant)
{
 for(int i=0;i<cant;i++)
 {
  for(int j=0;j<7;j++)
  {
   cout<<setw(7)<<instru[i][j];
  }
  cout<<endl;
 }
}

int HorasAlMes(int A[100][7], int cantidad_filas, int id,int mes,int &horas,int &minutos)
{
    //las variables son externas y hay que inicializarlas.
    horas=0;
    minutos = 0;
    //estas son internas

    int auxminutos=0;
    int auxhoras=0;
    int acumminutos= 0;
    cout<<"Ingrese identificacion del empleado que desea conocer."<<endl;
    cin>>id;
    cout<<"Ingrese el mes que deseaconocer."<<endl;
    cin>>mes;
    int hentrada, hsalida, mentrada, msalida;
    for(int i=0; i<cantidad_filas; i++)
    {

        if(id==A[i][0] && mes==A[i][2])
        {
            hentrada = A[i][3];
            mentrada = A[i][4];
            hsalida = A[i][5];
            msalida = A[i][6];
            if (hentrada==0 && mentrada==0){
                //significa que el trabajador se quedo la noche anterior
                auxhoras = hsalida;
                auxminutos = msalida;
            }
            //Poniendo el else asumimos q el trabajador n se queda mas de un dia seguido
            else if (hsalida == 0 && msalida == 0){
                auxhoras = 23-hentrada;
                auxminutos = 60-mentrada;
            }
            else{
                auxhoras = hsalida - hentrada;
                auxminutos = msalida - mentrada;
            }

            acumminutos += auxminutos;
            acumminutos += auxhoras*60;
        }

    }
    minutos=(acumminutos%60);
    //acumminutos -= minutos;//esto no es necesario con la division entera.
    horas=acumminutos/60;
    return 0;
}
int Cantidad_empleados(int datos_empleados[100][7], int cantidad_fila, int& cantidad_empleados){
    int vistos[100];
    //int cant_vistos = 0;
    //necesitamos una variable como cant _ vistos pero directamente podemos usar cantidad_empleados,
    //como viene de afuera HACIA afuera hay que inicializarlaa
    cantidad_empleados = 0;

    bool visto;

    for (int i = 0; i< cantidad_fila; i++){
        //inicializamos el visto para cada iteracion.
        visto = false;//usamos visto = false porqeu es mas facil asumir que no lo vimos
        for (int j = 0; j<cantidad_empleados; j++){
            if (datos_empleados[i][0] == vistos[j]){
                //si esta en la lista.. ponemos la bandera a true y salimos
                visto = true;
                break;
            }
        }
        if (!visto){
            //si no estaba en la lista entonces...
            //guardamos el id
            vistos[cantidad_empleados] = datos_empleados[i][0];
            //incrementamos la cantidad de empleados
            cantidad_empleados ++;
        }
    }
    return 0;
}
