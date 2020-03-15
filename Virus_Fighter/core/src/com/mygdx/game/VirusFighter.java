package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class VirusFighter extends ApplicationAdapter{
	
	SpriteBatch batch;
	Texture backGround, startButton, exitButton, settingsButton, name;
	Rectangle startRec, exitRec, settingsRec;
	ShapeRenderer sr;
	boolean send;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		backGround = new Texture("BG.png");
		startButton = new Texture("Start_BTN.png");
		exitButton = new Texture("Exit_BTN.png");
		settingsButton = new Texture("Settings_BTN.png");
		//name = new Texture("vfImage.png");
		startRec = new Rectangle();
		exitRec = new Rectangle();
		settingsRec = new Rectangle();
		send = false;
        sr = new ShapeRenderer();   
	}
	
    public void start(boolean check) {
		if(check == true) {
			myGame obj = new myGame();
			obj.create();
			obj.render();
		}
	}
	
    public void settings(boolean check) {
		
		if(check == true) {
			
		}		
	}
    
	@Override
	public void render () {
		batch.begin();
		batch.draw(backGround, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		//batch.draw(name, -20, 100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		startRec.set(210, 200, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		exitRec.set(210, 300, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		settingsRec.set(10, 10, Gdx.graphics.getWidth()/11, Gdx.graphics.getHeight()/9);

		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		if(settingsRec.contains(mouseX,mouseY)) {
			if(Gdx.input.justTouched())
				send= true;
			batch.draw(settingsButton, 10, 410, Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/10);
		}
		else {
			batch.draw(settingsButton, 10, 410, Gdx.graphics.getWidth()/11, Gdx.graphics.getHeight()/9);
		}
		if(startRec.contains(mouseX,mouseY)) {
			batch.draw(startButton, 235, 200, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);
			if(Gdx.input.justTouched())
				send= true;
		}
		else
			batch.draw(startButton, 210, 200, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		if(exitRec.contains(mouseX,mouseY)) {
			batch.draw(exitButton, 235, 100, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);
		}
		else {
			batch.draw(exitButton, 210, 100, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		}
		
		start(send);
		batch.end();
		/*sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.RED);
		sr.rect(exitRec.x, exitRec.y,Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		sr.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}