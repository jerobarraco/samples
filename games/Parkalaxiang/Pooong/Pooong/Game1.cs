using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;


namespace Pooong
{


    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        SpriteFont unaFuente;
        
        public Pelota pelota;
        public Paleta paleta1;
        public Paleta paleta2;
        public ManagerEnemigo enemigos;

        public Objeto fondo;
        public Objeto ganador1;
        public Objeto ganador2;

        bool gameOver =false;

        int w = 480;
        int y = 640;

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);

            graphics.PreferredBackBufferWidth = w;
            graphics.PreferredBackBufferHeight = y;
            Content.RootDirectory = "Content";
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here
            fondo = new Objeto();


            pelota = new Pelota(this);
            paleta1 = new Paleta(this, 0);
            paleta2 = new Paleta(this, 1);
            enemigos = new ManagerEnemigo(this, 5, 5);

            ganador1 = new Objeto(); 
            ganador2 = new Objeto(); 

            base.Initialize();
        }

           
        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);

            unaFuente = Content.Load<SpriteFont>("SpriteFont1");

            Texture2D texturaPaleta = Content.Load<Texture2D>("Texturas/PaletaRed");
            Texture2D texturaPaleta2 = Content.Load<Texture2D>("Texturas/PaletaBlue");
            Texture2D texturaPelo = Content.Load<Texture2D>("Texturas/pelotita");

            Texture2D texturaFondo = Content.Load<Texture2D>("Texturas/background");
            Texture2D texturaGanador1 = Content.Load<Texture2D>("Texturas/player1win");
            Texture2D texturaGanador2 = Content.Load<Texture2D>("Texturas/player2win");

            //INICIALIZAR OBJ
            pelota.Inicializar(texturaPelo, new Vector2(240.0f,320.0f), new Vector2(texturaPelo.Width/2, texturaPelo.Height/2));
            paleta1.Inicializar(texturaPaleta, new Vector2(300.0f, 600.0f));
            paleta2.Inicializar(texturaPaleta2, new Vector2(30.0f, 600.0f));
            fondo.Inicializar(texturaFondo, new Vector2(w/2.0f, y/2.0f));
            ganador1.Inicializar(texturaGanador1, new Vector2(w / 2.0f, y / 2.0f));
            ganador2.Inicializar(texturaGanador2, new Vector2(w / 2.0f, y / 2.0f));

            // TODO: use this.Content to load your game content here
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            // Allows the game to exit
            //if (GamePad.GetState(Player<Index.One).Buttons.Back == ButtonState.Pressed)
              //  this.Exit();
            
            if (!gameOver)
            {
                if ((paleta1.energia > 0.0f) && (paleta2.energia > 0.0f))
                {
                    // LOOOP
                    pelota.update();
                    paleta1.update();
                    paleta2.update();
                    enemigos.update();
                    pelota.checkColisionObj(paleta1);
                    pelota.checkColisionObj(paleta2);
                    // TODO: Add your update logic here
                }
                else
                {
                    gameOver = true;
                }

                if (enemigos.llegoAlFinal())
                {
                    gameOver = true;
                }
                
            }

            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

            spriteBatch.Begin();
            fondo.Dibujar(spriteBatch);
            pelota.Dibujar(spriteBatch);
            paleta1.Dibujar(spriteBatch);
            paleta2.Dibujar(spriteBatch);
            enemigos.Dibujar(spriteBatch);

            if (gameOver)
            {
                spriteBatch.DrawString(unaFuente, "Game Over", new Vector2(240, 320),Color.White,0f,new Vector2(0,0),0.8f,SpriteEffects.None,0f);
            }

            if (paleta1.energia <= 0f)
            {
                ganador2.Dibujar(spriteBatch);
            } 
            if (paleta2.energia <= 0f)
            {
                ganador1.Dibujar(spriteBatch);
            }
            spriteBatch.End();

            // TODO: Add your drawing code here

            base.Draw(gameTime);
        }
    }

}
