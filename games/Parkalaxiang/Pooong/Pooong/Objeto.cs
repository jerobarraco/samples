using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;          // Espacio de nombre necesario para usar Vector2
using Microsoft.Xna.Framework.Graphics;

namespace Pooong
{
    public class Objeto
    {
        public int energia = 100;
        protected Texture2D textura;  // Textura que se usará para representar al jugador.
        protected Vector2 posicion;   // Contendrá las coordenadas de su posición.
        protected Vector2 puntoAncla; // Contendrá las coordenadas de su punto de anclaje(u Origen) como referencia para rotación y escala.
        public Vector2 velocidad;
        protected float velocidadMov, // Representa al valor de la velocidad de su desplazamiento.
                      angulo,       // Guardará el valor del ángulo de inclinación del objeto.
                      escala;       // Guardará el valor de la escala de la Textura (o Sprite).
        public Rectangle rect;
        
        public static Texture2D bounds; // Utilizamos esta textura para dibujar los BoundingBox.

        virtual public void update()
        { }

        /// <summary>
        /// Inicializa un objeto con una textura y una posición inicial dada. Valores por defecto: 
        /// 1) Punto de origen = (0,0);  
        /// 2) Velocidad de Movimiento = 5f;   
        /// 3) Escala = 0.5f.
        /// </summary>
        /// <param name="textura">: Establece una textura 2D que representará al objeto.</param>
        /// <param name="posicion">: Establece una posición incicial para el objeto dentro de la pantalla.</param>
        public virtual void Inicializar(Texture2D textura, Vector2 posicion)
        {
            Vector2 ancla = new Vector2( textura.Width / 2, textura.Height / 2);
            this.Inicializar(textura, posicion, ancla);
        }

        /// <summary>
        /// Inicializa un objeto con una textura, una posición inicial dada y un punto de origen. Valores por defecto:   
        /// 1) Velocidad de Movimiento = 5f;   
        /// 2) Escala = 0.5f.
        /// </summary>
        /// <param name="textura">: Establece una textura 2D que representará al objeto.</param>
        /// <param name="posicion">: Establece una posición incicial para el objeto dentro de la pantalla.</param>
        /// <param name="puntoAncla">: Establece una punto de origen (x,y), que servirá de referencia para la rotación y escala del objeto.</param>
        public virtual void Inicializar(Texture2D textura, Vector2 posicion, Vector2 puntoAncla)
        {
            this.textura = textura;
            this.posicion = posicion;
            this.puntoAncla = puntoAncla;
            this.velocidadMov = 5f;
            this.angulo = 0f;
            this.escala = 1f;

            this.rect = this.CrearRectanguloLim();
            this.rect.X = (int)posicion.X;
            this.rect.Y = (int)posicion.Y;

        }

        /// <summary>
        /// Dibuja al objeto según la Posición, Escala y Rotación preestablecida.
        /// </summary>
        /// <param name="spriteBatch"></param>
        public virtual void Dibujar(SpriteBatch spriteBatch)
        {
            Vector2 pos = new Vector2(rect.X, rect.Y);
            // Dibujo al objeto.
            spriteBatch.Draw(
                            textura,
                            pos,
                            null,
                            Color.White,
                            angulo,         //MathHelper.ToRadians(45)
                            puntoAncla,
                            escala,
                            SpriteEffects.None,
                            0f
                            );
        }

        /// <summary>
        /// Dibuja al objeto según la Posición, Escala y Rotación preestablecida. Además
        /// se dibujará detrás del objeto, un BoundingBox (o Cuadro Delimitador) con el
        /// color que se indique y la transparencia.
        /// </summary>
        /// <param name="spriteBatch"></param>
        /// <param name="colorDelCuadro">Establece un color para representar al Cuadro Delimitador. Recomiendo 'Color.DarkTurquoise'</param>
        /// <param name="transparencia">Establece una transparencia al color del Cuadro. Recomiendo '175'</param>
        public virtual void Dibujar(SpriteBatch spriteBatch, Color colorDelCuadro, byte transparencia)
        {
            // Se elije aplica Transparencia al color indicado.
            colorDelCuadro.A = transparencia;

            // Utilizando lo anterior, dibujo un Cuadro Delimitador.
            spriteBatch.Draw(bounds, CrearRectanguloLim(), colorDelCuadro);
            Vector2 pos = new Vector2(rect.X, rect.Y);
            // Dibujo al objeto.
            spriteBatch.Draw(
                            textura,
                            pos,
                            null,
                            Color.White,
                            angulo,         //MathHelper.ToRadians(45)
                            puntoAncla,
                            escala,
                            SpriteEffects.None,
                            0f
                            );
        }
        public void Desplazar()
        {
            this.rect.X += (int)this.velocidad.X;
            this.rect.Y += (int)this.velocidad.Y;
            
        }

        public void Desplazar(int direccionX, int direccionY)
        {
            this.rect.X = (int)(rect.X + velocidadMov) * direccionX;
            this.rect.Y = (int)(rect.Y + velocidadMov) * direccionY;
        }


        /// <summary>
        /// Dirige la posición (x,y) del objeto al centro de la pantalla.  
        /// </summary>
        /// <param name="anchoPantalla"></param>
        /// <param name="altoPantalla"></param>
        public void CentrarEnPantalla(int anchoPantalla, int altoPantalla)
        {
            posicion = new Vector2(anchoPantalla / 2, altoPantalla / 2);

        }

        /// <summary>
        /// Modifica el Origen (o Punto de Ancla) del objeto y lo ubica al centro de la textura.
        /// </summary>
        public void CentrarOrigen()
        {
            puntoAncla = new Vector2((textura.Width / 2), (textura.Height / 2));
        }

        /// <summary>
        /// Reestablece el Origen (o Punto de Ancla) del objeto a la posición por defecto (0,0).
        /// </summary>
        public void ReestablecerOrigen()
        {
            puntoAncla = Vector2.Zero;
        }

        /// <summary>
        /// Crea y retorna un Cuadro Delimitador, adaptada según las características del objeto 
        /// (Posición, Rotación y Escala). 
        /// </summary>
        /// <returns></returns>
        public Rectangle CrearRectanguloLim()
        {
            // Creamos un Rectangle teniendo en cuenta el ancho y alto de la textura.
            Rectangle rectangulo = new Rectangle(0, 0, textura.Width, textura.Height);

            // Necesitamos adaptar ese Rectangulo según la rotación, escala y posición del objeto.
            Matrix transformada = GetMatrizTextura();

            // Se toma el valor de cada esquina del Rectangulo creado.
            Vector2 esquinaSupIzq = new Vector2(rectangulo.Left, rectangulo.Top);
            Vector2 esquinaSupDer = new Vector2(rectangulo.Right, rectangulo.Top);
            Vector2 esquinaInfIzq = new Vector2(rectangulo.Left, rectangulo.Bottom);
            Vector2 esquinaInfDer = new Vector2(rectangulo.Right, rectangulo.Bottom);

            // Se transforman esas 4 esquinas según la figura.
            Vector2.Transform(ref esquinaSupIzq, ref transformada, out esquinaSupIzq);
            Vector2.Transform(ref esquinaSupDer, ref transformada, out esquinaSupDer);
            Vector2.Transform(ref esquinaInfIzq, ref transformada, out esquinaInfIzq);
            Vector2.Transform(ref esquinaInfDer, ref transformada, out esquinaInfDer);

            // Se busca el mínimo y máximo punto del rectangulo.
            Vector2 min = Vector2.Min(Vector2.Min(esquinaSupIzq, esquinaSupDer),
                                      Vector2.Min(esquinaInfIzq, esquinaInfDer));
            Vector2 max = Vector2.Max(Vector2.Max(esquinaSupIzq, esquinaSupDer),
                                                  Vector2.Max(esquinaInfIzq, esquinaInfDer));

            // Retorna un rectangulo transformado
            return new Rectangle((int)min.X, (int)min.Y, (int)(max.X - min.X), (int)(max.Y - min.Y));
        }

        /// <summary>
        /// Retorna una matriz que contiene los datos sobre la rotación, escala y posición
        /// de la textura del objeto.
        ///  Es útil cuando se necesita detectar una colisión con otra textura a nivel pixel.
        /// </summary>
        /// <returns></returns>
        public Matrix GetMatrizTextura()
        {
            Matrix matFigura = Matrix.CreateTranslation(-puntoAncla.X, -puntoAncla.Y, 0) *
                                Matrix.CreateScale(escala) *
                                Matrix.CreateRotationZ(angulo) *
                                Matrix.CreateTranslation(posicion.X, posicion.Y, 0);
            return matFigura;
        }

        /// <summary>
        /// Retorna un vector bidimensional que contiene los datos de todos los pixeles
        /// compuestos por la textura del objeto.
        ///  Es útil cuando se necesita detectar una colisión con otra textura a nivel pixel.
        /// </summary>
        /// <returns></returns>
        public Color[,] GetMatrizPixeles()
        {
            Color[] colors1D = new Color[(textura.Width) * (textura.Height)];
            textura.GetData(colors1D);
            Color[,] colors2D = new Color[(textura.Width), (textura.Height)];

            for (int x = 0; x < textura.Width; x++)
                for (int y = 0; y < textura.Height; y++)
                    colors2D[x, y] = colors1D[x + y * textura.Width];

            return colors2D;
        }

        #region Gets & Sets
        // ============ TEXTURA ================
        public Texture2D GetTextura()
        {
            return textura;
        }

        public void SetTextura(Texture2D nuevaTextura)
        {
            this.textura = nuevaTextura;
        }
        //============= PUNTO DE ANCLA ========
        public Vector2 GetPuntoAncla()
        {
            return puntoAncla;
        }

        public void SetPuntoAncla(Vector2 puntoAncla)
        {
            this.puntoAncla = puntoAncla;
        }

        // ============ ESCALA ================
        public float GetEscala()
        {
            return escala;
        }

        public void SetEscala(float escala)
        {
            this.escala = escala;
        }

        public void IncreaseEscala(float valor)
        {
            if (this.escala + valor > 0.02f && this.escala + valor < 4.5f)
                this.escala += valor;
        }


        // ============ ANGULO ================
        public float GetAngulo()
        {
            return angulo;
        }

        public void SetAngulo(float angulo)
        {
            this.angulo = angulo;
        }

        public void InreaseAngulo(float valor)
        {
            float val = MathHelper.ToDegrees(valor);
            if (MathHelper.ToDegrees(this.angulo + valor) > 360)
            {
                this.angulo = valor;
            }
            else if (MathHelper.ToDegrees(this.angulo + valor) < 0)
            {
                this.angulo = MathHelper.ToRadians(360) - valor;
            }
            else
                this.angulo += valor;
        }

        // ============ VELOCIDAD ================
        public float GetVelocidad()
        {
            return velocidadMov; // Se retorna el valor de la velocidad establecida.
        }

        public void SetVelocidad(float velocidad)
        {
            this.velocidadMov = velocidad;
        }


        // ============ POSICION =================
        public virtual Vector2 GetPosicion()
        {
            return this.posicion;
        }

        public virtual void SetPosicion(Vector2 posicion)
        {
            this.posicion = posicion;
        }

        public virtual void IncreasePos(Vector2 nuevaPos)
        {
            posicion += nuevaPos;
        }

        #endregion
    }
}
