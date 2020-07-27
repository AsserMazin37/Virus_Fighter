package com.mygdx.game;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class VirusFighter extends Game {
	SpriteBatch batch;
	static Music music;
	public static int played = 0;

	int timer = 0;

	Image img;

	@Override
	public void create() {
		music = Gdx.audio.newMusic(Gdx.files.internal("music/gameTheme.mp3"));
		batch = new SpriteBatch();
		setScreen(new Splash_Screen(this));

	}

	@Override
	public void render() {
		super.render();
		if (MainMenu.pause == true || LevelLayout1.enteredLevel1 == true || LevelLayout2.enteredLevel2 == true
				|| LevelLayout3.enteredLevel3 == true || LevelLayout4.enteredLevel4 == true
				|| LevelLayout5.enteredLevel5 == true) {
			music.pause();
		} else
			music.play();

	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
