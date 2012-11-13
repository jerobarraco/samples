using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;          // Espacio de nombre necesario para usar Vector2
using Microsoft.Xna.Framework.Graphics;

namespace Pooong
{
    class Enemigo : Objeto
    {
        private int contador=0;
        private int maxContador = 230;
        
        public Enemigo(Texture2D textura, int x, int y )
        {
            this.velocidad = new Vector2(1, 0);
            Inicializar(textura, new Vector2(x,  y));
        }

        override
        public void update()
        {
            contador++;
            if (contador == maxContador)
            {
                this.velocidad.X = -this.velocidad.X;
                this.rect.Y += 20;
                contador = 0;
            }
            base.Desplazar();
        }
    }
}
