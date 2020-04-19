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
	BitmapFont bmf;
	final VirusFighter game;
	
	public MoveTo(final VirusFighter game){
		this.game = game;	
		batch = new SpriteBatch();
		img = new Texture("MainUi/BG.png");
		name = new Texture("MainUi/vfImage.png");
        bmf =new BitmapFont (Gdx.files.internal("myfont.fnt"));
		loadingBar1 = new Texture("MainUi/Loading_Bar_1_1.png");
		loadingBar2 = new Texture("MainUi/Loading_Bar_1_2.png");
		loadingBar3 = new Texture("MainUi/Loading_Bar_1_3.png");
		Table = new Texture("MainUi/Table.png");
		

	}
	@Override
	public void render(float delta) {
		
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
			
			if(LevelsMenu.getTag()==0)
			{
				game.setScreen(new LevelLayout1(game));
			}
			else if(LevelsMenu.getTag()==1)
			{
				game.setScreen(new LevelLayout2(game));
			}
			else if(LevelsMenu.getTag()==2)
			{
				game.setScreen(new LevelLayout3(game));
			}
		    else if(LevelsMenu.getTag()==3)
			{
			 game.setScreen(new LevelLayout4(game));
			}
			else if(LevelsMenu.getTag()==4)
			{
				game.setScreen(new LevelLayout5(game));
			}
			/*else if(LevelsMenu.getTag()==5)
			{
				games.setScreen(new LevelLayout6(games));
			}*/
		}
		game.batch.end();
	}
}
