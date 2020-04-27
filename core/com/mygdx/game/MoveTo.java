package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
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
	final VirusFighter games;
	
	public MoveTo(final VirusFighter games){
		this.games = games;	
		batch = new SpriteBatch();
		img = new Texture("MainUi/BG.png");
		name = new Texture("MainUi/vfImage.png");
        bmf =new BitmapFont (Gdx.files.internal("fonts/myfont.fnt"));
		loadingBar1 = new Texture("MainUi/Loading_Bar_1_1.png");
		loadingBar2 = new Texture("MainUi/Loading_Bar_1_2.png");
		loadingBar3 = new Texture("MainUi/Loading_Bar_1_3.png");
		Table = new Texture("MainUi/Table.png");
	}
	@Override
	public void render(float delta) {
		
		games.batch.begin();
		games.batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		games.batch.draw(Table, 0, 150, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/10);
		games.batch.draw(name, -20, 100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		bmf.draw(games.batch,"  HOLD ON, THE FIGHT IS ABOUT TO BEGIN",Gdx.graphics.getWidth()/10f,Gdx.graphics.getHeight()/1.8f);
		timer++;
		if(timer >= 200) {
			games.batch.draw(loadingBar1, 5, 154, loadingBar1.getWidth(), loadingBar1.getHeight());
		}
		 if(timer >= 500) {
			games.batch.draw(loadingBar2, 10, 154, loadingBar2.getWidth()/3, loadingBar2.getHeight());
		}
		 if(timer >= 700) {
				games.batch.draw(loadingBar2, 15, 154, loadingBar2.getWidth(), loadingBar2.getHeight());
		 }
		if(timer == 900) {			
			
			if(LevelsMenu.tag==0)
			{
				games.setScreen(new Level1Layout(games));
			}
			if(LevelsMenu.tag==1)
			{
				games.setScreen(new Level2Layout(games));
			}
			else if(LevelsMenu.tag==2)
			{
				games.setScreen(new Level3Layout(games));
			}
		    else if(LevelsMenu.tag==3)
			{
			 games.setScreen(new Level4Layout(games));
			}
			else if(LevelsMenu.tag==4)
			{
				games.setScreen(new Level5Layout(games));
			}
			/*else if(LevelsMenu.getTag()==5)
			{
				games.setScreen(new LevelLayout6(games));
			}*/
		}
		games.batch.end();
	}
}
