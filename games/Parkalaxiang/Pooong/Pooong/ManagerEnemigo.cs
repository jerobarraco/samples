using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;          // Espacio de nombre necesario para usar Vector2
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;


namespace Pooong
{
    public class ManagerEnemigo : Objeto
    {
        private List<Enemigo> enemigos = new List<Enemigo>();
        private Game1 padre;

        public ManagerEnemigo(Game1 parent, int columnas, int filas)
        {
            padre = parent;
            Texture2D imagen = parent.Content.Load<Texture2D>("Texturas/EnemigoA");
            System.Console.Out.Write("ladsk");
            int x = 50;
            int y = 50;
            int width = imagen.Width + 20;
            int height = imagen.Height + 20;
            for (int i = 0; i < filas; i++){
                for (int j = 0; j < columnas; j++)
                {
                    Enemigo e = new Enemigo(imagen, x, y);
                    enemigos.Add(e);
                    x += width;
                }
                x = 50;
                y += height;
            }
            
        }

        override
        public void Dibujar(SpriteBatch spriteBatch)
        {
            foreach (Enemigo e in enemigos)
            {
                e.Dibujar(spriteBatch);
            }
        
        }
        override
        public void update(){
            Pelota pelota = padre.pelota;
            Enemigo enemigo;
            for (int i = 0; i < enemigos.Count;i++ ){
                    enemigo = enemigos[i];
                    enemigo.update();
                    if (pelota.rect.Intersects(enemigo.rect))
                    {
                        enemigos.RemoveAt(i);
                        if ((pelota.velocidad.X>0 && pelota.rect.Center.X < enemigo.rect.Center.X ) //si se esta moviendo a la dercha y esta a la izq
                           || (pelota.velocidad.X<0 && pelota.rect.Center.X> enemigo.rect.Center.X))
                        {
                            pelota.velocidad.X = - pelota.velocidad.X;
                        }
                        if ((pelota.velocidad.Y > 0 && pelota.rect.Center.Y< enemigo.rect.Center.Y) //si se esta moviendo a la dercha y esta a la izq
                           || (pelota.velocidad.Y < 0 && pelota.rect.Center.Y> enemigo.rect.Center.Y))
                        {
                            pelota.velocidad.Y = -pelota.velocidad.Y;
                        }
                        //pelota.velocidad = -pelota.velocidad;
                        break;
                    }

                }
        }

        public bool llegoAlFinal()
        {
            bool llego = false;
            foreach (Enemigo unEnemigo in enemigos)
            {
                if (unEnemigo.rect.Y + unEnemigo.rect.Height >= padre.GraphicsDevice.Viewport.Height)
                {
                    llego = true;
                };
            }
            return llego;
        }
    }
}
