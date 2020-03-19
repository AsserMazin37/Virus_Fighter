package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoveTo extends ScreenAdapter{

	public boolean check;
	
	SpriteBatch batch;
	
	Texture img, loadingBar1, loadingBar2, loadingBar3, Table, name;

	int timer = 0;
	
	static String text;
	
	BitmapFont bmf;

	final VirusFighter game;
	
	public MoveTo(final VirusFighter game){
		this.game = game;
		batch = new SpriteBatch();
		img = new Texture("BG.png");
		name = new Texture("vfImage.png");
        bmf = new BitmapFont (Gdx.files.internal("myfont.fnt"));
		loadingBar1 = new Texture("Loading_Bar_1_1.png");
		loadingBar2 = new Texture("Loading_Bar_1_2.png");
		loadingBar3 = new Texture("Loading_Bar_1_3.png");
		Table = new Texture("Table.png");
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
		bmf.draw(game.batch,"  HOLD ON, THE FIGHT IS ABOUT TO BEGIN",Gdx.graphics.getWidth()/10f,Gdx.graphics.getHeight()/1.8f);

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
			game.setScreen(new LevelLayout(game));
		}
		game.batch.end();
	}

	@Override
	public void hide() {
		
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