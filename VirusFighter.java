package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VirusFighter extends Game{
	SpriteBatch batch;
	Music music;
	
	@Override
	public void create () {		
		
		music = Gdx.audio.newMusic(Gdx.files.internal("gameTheme.mp3"));
		batch = new SpriteBatch();
		
		this.setScreen(new MainMenu(this));
		
	}

	@Override
	public void render () {
		super.render();
		//music.play();
		if(MainMenu.pause == true) {
			music.pause();
		}
		else
			music.play();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}