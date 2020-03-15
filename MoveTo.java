package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoveTo extends ScreenAdapter{

	public boolean check;
	SpriteBatch batch;
	Texture img;
	Texture loadingBar1;
	Texture loadingBar2;
	Texture loadingBar3;
	Texture Table;
	int timer = 0;
	Texture name;
	static String text;
	String loadingMessage;
	BitmapFont font;

	final VirusFighter game;
	
	public MoveTo(final VirusFighter game){
		
		this.game = game;
		batch = new SpriteBatch();
		img = new Texture("BG.png");
		name = new Texture("vfImage.png");
		font = new BitmapFont (Gdx.files.internal("myfont.fnt"));
		loadingBar1 = new Texture("Loading_Bar_1_1.png");
		loadingBar2 = new Texture("Loading_Bar_1_2.png");
		loadingBar3 = new Texture("Loading_Bar_1_3.png");
		Table = new Texture("Table.png");
		loadingMessage = "Fetching data, Please wait...";

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		game.batch.begin();
		game.batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.draw(Table, 0, 150, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/10);
		game.batch.draw(name, -20, 100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		timer++;
		if(timer >= 150) {
			game.batch.draw(loadingBar1, 5, 154, loadingBar1.getWidth(), loadingBar1.getHeight());

		}
		 if(timer >= 250) {
			game.batch.draw(loadingBar2, 10, 154, loadingBar2.getWidth()/3, loadingBar2.getHeight());
		}
		 if(timer >= 400) {
			game.batch.draw(loadingBar2, 15, 154, loadingBar2.getWidth(), loadingBar2.getHeight());
			}
		if(timer == 500) {
			game.setScreen(new CharacterMenu(game));
		}
		
		font.draw(game.batch,loadingMessage,Gdx.graphics.getWidth()/4.1f,Gdx.graphics.getHeight()/2);
		
		game.batch.end();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}