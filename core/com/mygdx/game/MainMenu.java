package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import gameData.SpaceShipExplosion;

public class MainMenu extends ScreenAdapter {
	SpriteBatch batch;
	static boolean disable=false;
	Rectangle rec;
	Rectangle rec2;
	Rectangle rec3;
	Rectangle rec4;
	Rectangle rec5;
	ShapeRenderer sr;

	BitmapFont font;

	public static boolean check = false;
	static boolean show = false;
	public static boolean pause;
	String developer = "Seif Moheb";

	Texture img;
	Texture startButton;
	Texture exitButton;
	Texture musicButton;
	Texture info,seifModel;
	Texture background;
	Texture closeButton;
	Texture name;
	Texture fire;
	Texture credits;
	String status;

	final VirusFighter game;
	
	public MainMenu(final VirusFighter game) {
		this.game = game;
		batch = new SpriteBatch();
	    font =new BitmapFont (Gdx.files.internal("fonts/myfont.fnt"));
	    font.getData().scaleX = 0.7f;
	    font.getData().scaleY = 1.5f;
		img = new Texture("MainUi/BG.png");
		startButton = new Texture("MainUi/Start_BTN.png");
		exitButton = new Texture("MainUi/Exit_BTN.png");
		info = new Texture("MainUi/Info_BTN.png");
		status = "MainUi/Active_Music_BTN.png";
		musicButton = new Texture(status);
		name = new Texture("MainUi/vfImage.png");
		rec = new Rectangle();
		rec2 = new Rectangle();
		rec3 = new Rectangle();
		rec4 = new Rectangle();
		rec5 = new Rectangle();
		background = new Texture("MainUi/Table_01.png");
		seifModel = new Texture("models/team.png");
		closeButton = new Texture("MainUi/Close_BTN.png");
		credits = new Texture("MainUi/credits.png");
		sr = new ShapeRenderer();
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
		game.batch.draw(musicButton, 10, 410, Gdx.graphics.getWidth()/14, Gdx.graphics.getHeight()/12);

		if(rec3.contains(mouseX,mouseY)) {

			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)&& pause == true) {
				pause = false;
				status = "MainUi/Active_Music_BTN.png";
				musicButton = new Texture(status);		
				disable = false;
			}

			else if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)&& pause == false) {
				pause = true;
				status = "MainUi/Music_BTN.png";
				musicButton = new Texture(status);	
				disable = true;
			}
			
		}
		if(rec4.contains(mouseX,mouseY)) {
			game.batch.draw(info, 70, 410, Gdx.graphics.getWidth()/14, Gdx.graphics.getHeight()/12);
			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				show = true;
			}
			
		}
		else {
			game.batch.draw(info, 70, 410, Gdx.graphics.getWidth()/13, Gdx.graphics.getHeight()/11);
		}
		if(show == true) {
			game.batch.draw(background, 100, 50, Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/1.2f);
			game.batch.draw(credits, 230, 370, credits.getWidth()/2.5f,credits.getHeight()/2.5f);
			game.batch.draw(seifModel, -300, -150, seifModel.getWidth(),seifModel.getHeight());
			//font.draw(game.batch, "Developer: "+developer, 160, 250);
			game.batch.draw(closeButton, 450, 400, closeButton.getWidth()/8,closeButton.getHeight()/8);
			if(rec5.contains(mouseX,mouseY)) {
				game.batch.draw(closeButton, 451, 400, closeButton.getWidth()/9,closeButton.getHeight()/9);
				if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
					show = false;
			}


		}	

		else if(show == false){
		game.batch.draw(name, -20, 100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if(rec.contains(mouseX,mouseY)) {
			game.batch.draw(startButton, 235, 200, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);
			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				game.setScreen(new UserName(game));				
			}
		}
		else
			game.batch.draw(startButton, 210, 200, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);

		if(rec2.contains(mouseX,mouseY)) {
			game.batch.draw(exitButton, 235, 100, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);
			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				Gdx.app.exit();
			}
		}
		else {
			game.batch.draw(exitButton, 210, 100, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		}
		
		}	
		game.batch.end();
		
	}
	@Override
	public void dispose () {
		game.batch.dispose();
	}
}
