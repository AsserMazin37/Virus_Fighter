package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MainMenu extends ScreenAdapter {
	
	SpriteBatch batch;
	
	Texture img, name, startButton, exitButton, musicButton, info, background, closeButton;
	
	Rectangle rec, rec2, rec3, rec4, rec5;
	
	public static boolean mute, check = false;

	boolean show = false;
	
	ShapeRenderer sr;
	
	String music_status;
	
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
		info = new Texture("Info_BTN.png");
		music_status = "Music_BTN_Active.png";
		musicButton = new Texture(music_status);

		name = new Texture("vfImage.png");
		rec = new Rectangle();
		rec2 = new Rectangle();
		rec3 = new Rectangle();
		rec4 = new Rectangle();
		rec5 = new Rectangle();

		background = new Texture("Table_01.png");
		closeButton = new Texture("Close_BTN.png");

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
		
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		
		rec.set(210, 200, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		rec2.set(210, 300, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		rec3.set(10, 10, Gdx.graphics.getWidth()/11, Gdx.graphics.getHeight()/9);
		rec4.set(50,10,Gdx.graphics.getWidth()/11, Gdx.graphics.getHeight()/9);
		rec5.set(450,50,closeButton.getWidth()/8,closeButton.getHeight()/8);
		
		game.batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        
		if(rec3.contains(mouseX,mouseY)) {
			game.batch.draw(musicButton, 10, 410, Gdx.graphics.getWidth()/14, Gdx.graphics.getHeight()/12);
			if(Gdx.input.justTouched() && mute == false) {
				mute = true;
				music_status = ("Music_BTN.png");
				musicButton = new Texture(music_status);
			}
			else if(Gdx.input.justTouched() && mute == true){
				mute = false;
				music_status = ("Music_BTN_Active.png");
				musicButton = new Texture(music_status);
			}
		}
		else {
			game.batch.draw(musicButton, 10, 410, Gdx.graphics.getWidth()/13, Gdx.graphics.getHeight()/11);
		}
        
		if(rec4.contains(mouseX,mouseY)) {
			game.batch.draw(info, 70, 410, Gdx.graphics.getWidth()/14, Gdx.graphics.getHeight()/12);
			if(Gdx.input.justTouched()) {
				show = true;
			}
		}
		else {
			game.batch.draw(info, 70, 410, Gdx.graphics.getWidth()/13, Gdx.graphics.getHeight()/11);
		}
		
		if(show == true) {
			game.batch.draw(background, 100, 50, Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/1.2f);
			game.batch.draw(closeButton, 450, 400, closeButton.getWidth()/8,closeButton.getHeight()/8);
			if(rec5.contains(mouseX,mouseY)) {
				game.batch.draw(closeButton, 451, 400, closeButton.getWidth()/9,closeButton.getHeight()/9);

				if(Gdx.input.justTouched())
					show = false;
			}
		}		
		else if(show == false){
		game.batch.draw(name, -20, 100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if(rec.contains(mouseX,mouseY)) {
			game.batch.draw(startButton, 235, 200, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);

			if(Gdx.input.justTouched()) {
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
			
			game.setScreen(new CharacterMenu(game));
		}
	}
		
		game.batch.end();
				
		/*sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.RED);
		sr.rect(rec5.x, rec5.y,closeButton.getWidth()/8,closeButton.getHeight()/8);
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