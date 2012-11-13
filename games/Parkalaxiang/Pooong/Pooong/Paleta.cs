using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Pooong
{
    public  class Paleta:Objeto
    {
        KeyboardState estadoTeclado;
        public int jugador;
        public Paleta(Game1 parent, int jugador)
        {
            this.jugador = jugador;
            velocidad = new Vector2(10,10);
            textura = parent.Content.Load<Texture2D>("Texturas/PaletaRed");
            Inicializar(textura, new Vector2(240.0f, 600.0f));
        }
        
        override
        public void update() {
            estadoTeclado = Keyboard.GetState();
            if (jugador == 0)
            {
                if (estadoTeclado.IsKeyDown(Keys.Left))
                {
                   
                    {
                        rect.X -= (int)velocidad.X;
                    }
                }



                if (estadoTeclado.IsKeyDown(Keys.Right))
                {

                    rect.X += (int)velocidad.X;
                }
            }
            if (jugador == 1)
            {
                if (estadoTeclado.IsKeyDown(Keys.A))
                { rect.X -= (int)velocidad.X; }
                if (estadoTeclado.IsKeyDown(Keys.D))
                { rect.X += (int)velocidad.X; }
            }
            if (rect.X + puntoAncla.X >= 480)
            {
                rect.X = 480 - (int)puntoAncla.X;
            }
            if (rect.X - puntoAncla.X <= 0)
            {
                rect.X = (int)puntoAncla.X;
            }

                /*
            jugador.posicion.X = MathHelper.Clamp(jugador.posicion.X, 0,
                                                GraphicsDevice.Viewport.Width - jugador.GetAncho() / 2);
            jugador.posicion.Y = MathHelper.Clamp(jugador.posicion.Y, 0,
                                                    GraphicsDevice.Viewport.Height - jugador.GetAlto() / 2);
            */
        }

        public void hit()
        {
            float dec = 0.1f;
            this.energia -= (int)(100*dec);
            float e = this.GetEscala() - dec;
            this.SetEscala(e);
            int cx, cy,ox,oy;
            cx  = this.rect.Center.X;
            cy = this.rect.Center.Y;
            ox = this.rect.X;
            this.rect.Width = (int)(this.textura.Width * e);
            this.rect.Height= (int)(this.textura.Height * e);
            this.rect.X = (ox + cx) - this.rect.Center.X;


        }

    }
}
