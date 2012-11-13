using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Pooong
{
    public class Pelota : Objeto
    {
        int turno;
        public int ancho;
        public int alto;
        Texture2D textura1;
        Texture2D textura2;
        Game1 padre;
        public Pelota(Game1 parent)
        {
            padre = parent;
            this.velocidad = new Vector2(4, 4);
            ancho = parent.GraphicsDevice.Viewport.Width;
            alto = parent.GraphicsDevice.Viewport.Height;
            textura1 = parent.Content.Load<Texture2D>("Texturas/pelotita");
            textura2 = parent.Content.Load<Texture2D>("Texturas/pelotita2");
            Inicializar(textura1, new Vector2(240.0f, 320.0f));
            turno = 0;
        }
        void cambiarTurno()
        {
            if (turno == 0)
            {
                turno = 1;
                textura = textura2;
            }
            else
            {
                turno = 0;
                textura = textura1;
            }
        }
        override
        public void update() {
            this.checkColisionVentana();
            this.Desplazar();
        }

        public void checkColisionVentana()
        {
            Rectangle rectVentana = new Rectangle(0, 0, ancho, alto);
            if (this.rect.X + this.puntoAncla.X > ancho)
            {
                this.rect.X = ancho - ((int)this.puntoAncla.X);
                this.velocidad.X = -this.velocidad.X;
            }
            if (this.rect.X - this.puntoAncla.X < 0)
            {
                this.rect.X = (int)puntoAncla.X;
                this.velocidad.X = -this.velocidad.X;
            }
            if (this.rect.Y - this.puntoAncla.Y < 0)
            {
                this.rect.Y = (int)this.puntoAncla.Y;
                this.velocidad.Y = -this.velocidad.Y;
            }
            if (this.rect.Y + this.puntoAncla.Y > alto)
            {
                this.rect.Y = alto-(int)puntoAncla.Y;
                this.velocidad.Y = -this.velocidad.Y;
                if (turno == 0)
                {
                    padre.paleta1.hit();
                }
                else
                {
                    padre.paleta2.hit();
                }
                cambiarTurno();
            }
        }

        public void checkColisionObj(Paleta unObjeto)
        {
            Paleta p;
            if (turno == 0)
            {
                p = padre.paleta1;
            }
            else
            {
                p = padre.paleta2;
            }
            
            if (this.rect.Intersects(p.rect)){
                if (this.velocidad.Y>0){
                    this.velocidad.Y = -this.velocidad.Y;
                    cambiarTurno();
                    rect.Y = p.rect.Y - rect.Height;
                    if (
                        (rect.Center.X < p.rect.Center.X && velocidad.X > 0)
                        ||
                        (rect.Center.X > p.rect.Center.X && velocidad.X < 0)
                        )
                    {
                        velocidad.X = -velocidad.X;
                    }
                }
            }
            /*
         if (this.posicion.X + this.puntoAncla.X > posObj.X)
            {
                this.posicion.X = ancho - this.puntoAncla.X;
                this.velocidad.X = -this.velocidad.X;
            }
            if (this.posicion.X - this.puntoAncla.X < posObj.X)
            {
                this.posicion.X = 0 + puntoAncla.X;
                this.velocidad.X = -this.velocidad.X;
            }
            if (this.posicion.Y - this.puntoAncla.Y < posObj.Y)
            {
                this.posicion.Y = 0 + this.puntoAncla.Y;
                this.velocidad.Y = -this.velocidad.Y;
            }
            if (this.posicion.Y > alto)
            {
                this.posicion.Y = alto - puntoAncla.Y;
                this.velocidad.Y = -this.velocidad.Y;
            }
        }*/
        }
    }
}
