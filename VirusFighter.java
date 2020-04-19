package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VirusFighter extends Game{
	SpriteBatch batch;
	static Music music;
	@Override
	public void create () {		
		
		music = Gdx.audio.newMusic(Gdx.files.internal("gameTheme.mp3"));
		batch = new SpriteBatch();	
		this.setScreen(new MainMenu(this));	
	}
	@Override
	public void render () {
		super.render();
		if(MainMenu.pause == true || LevelLayout2.enteredLevel2 == true ||LevelLayout3.enteredLevel3 == true || LevelLayout5.enteredLevel5 == true) {
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
