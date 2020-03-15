package com.mygdx.game;

import java.awt.DisplayMode;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

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
		music.play();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
