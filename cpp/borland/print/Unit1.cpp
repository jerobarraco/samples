//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "Unit1.h"

//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma resource "*.dfm"

TForm1 *Form1;
TPrinter *p;
//---------------------------------------------------------------------------
__fastcall TForm1::TForm1(TComponent* Owner)
        : TForm(Owner)
{
        
}
//---------------------------------------------------------------------------


void __fastcall TForm1::Button1Click(TObject *Sender)
{
        if ( PrintDialog1->Execute()){
            p = Printer();
            FontDialog1->Execute();
            p->Canvas->Font =FontDialog1->Font;
            p->BeginDoc();

            for (int i = 0; i<3; i++){

                p->Canvas->TextOut(20,  20, "Page width = "+IntToStr(p->PageWidth));
                p->Canvas->TextOut(20, 150, "Page height = "+IntToStr(p->PageHeight));
                p->Canvas->TextOut(20, 250, "Page number = "+IntToStr(p->PageNumber));
                             p->NewPage();
            }

            p->EndDoc();

        }            
}
//---------------------------------------------------------------------------
