
package com.moongate.casanova;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Music {
	Sound [] music;
	String []n_music = {"looby", "disco1", "corazon", "presentacion" };
	Sound []sounds;
	String []n_sounds = {"corazon", "paso"};
	public Music(){
		music = new Sound[n_music.length];
		for (int i=0; i< n_music.length; i++){
			music[i] = Gdx.audio.newSound(Gdx.files.internal("data/musica/".concat(n_music[i]).concat(".mp3")));
		}
	/*	sounds = new Sound[n_sounds.length];
		for (int i=0; i< n_sounds.length; i++){
			sounds[i] = Gdx.audio.newSound(Gdx.files.internal("data/fx/".concat(n_sounds[i]).concat(".mp3")));
		}*/
	}
	public void play(){
		//music[0].play();
	}
	public void play_music(int i ){
		stop_music();
		music[i].loop();
	}
	public void stop_music(int i){
		music[i].stop();
	}
	public void stop_music(){
		for (Sound s : music){
			s.stop();
		}
	}

}
