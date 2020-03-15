package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class MainMenu extends ScreenAdapter {
	SpriteBatch batch;
	Texture img;
	Rectangle rec;
	Rectangle rec2;
	Rectangle rec3;
	
	public static boolean check = false;

	Texture startButton;
	Texture exitButton;
	Texture settingsButton;
	Texture name;
	ShapeRenderer sr;
	
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	final VirusFighter game;
	
	public MainMenu(final VirusFighter game) {
		this.game = game;
		batch = new SpriteBatch();
		img = new Texture("BG.png");
		startButton = new Texture("Start_BTN.png");
		exitButton = new Texture("Exit_BTN.png");
		settingsButton = new Texture("Settings_BTN.png");
		name = new Texture("vfImage.png");
		rec = new Rectangle();
		rec2 = new Rectangle();
		rec3 = new Rectangle();
		sr = new ShapeRenderer();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		game.batch.begin();
		game.batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		game.batch.draw(name, -20, 100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		rec.set(210, 200, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		rec2.set(210, 300, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		rec3.set(10, 10, Gdx.graphics.getWidth()/11, Gdx.graphics.getHeight()/9);



		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		if(rec3.contains(mouseX,mouseY)) {
			game.batch.draw(settingsButton, 10, 410, Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/10);
		}
		else {
			game.batch.draw(settingsButton, 10, 410, Gdx.graphics.getWidth()/11, Gdx.graphics.getHeight()/9);
		}
		if(rec.contains(mouseX,mouseY)) {
			game.batch.draw(startButton, 235, 200, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);

			if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				UserName obj = new UserName();
				obj.create();
				obj.render();
			}
		}
		else
			game.batch.draw(startButton, 210, 200, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);

		if(rec2.contains(mouseX,mouseY)) {
			game.batch.draw(exitButton, 235, 100, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);
		}
		else {
			game.batch.draw(exitButton, 210, 100, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		}
		if(UserName.get() == true) {
			game.setScreen(new MoveTo(game));
			
		}
		game.batch.end();
				

		/*sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.RED);
		sr.rect(rec2.x, rec2.y,Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		sr.end();*/
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
		
}
