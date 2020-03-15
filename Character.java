package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Scaling;

public class Character extends ScreenAdapter{
	SpriteBatch batch;
	Texture spaceship1 , spaceship2 , spaceship3 ,background;
	ShapeRenderer sr;
	Rectangle r1 , r2 , r3 , r4 , r5;
	String Text1, Text2, Text3 , Text4 , Text5;
	BitmapFont font;
	static int shipChoice;

	
	@Override
	public void dispose () {
		batch.dispose();
	}

	final VirusFighter game;
	
	public Character(final VirusFighter game) {
		this.game = game;
		batch = new SpriteBatch();
		background = new Texture("Window.png");
		spaceship1 = new Texture("spaceShip1.png");
		spaceship2 = new Texture("spaceShip2.png");
		spaceship3 = new Texture("spaceShip3.png");
		font = new BitmapFont (Gdx.files.internal("myfont.fnt"));
		sr = new ShapeRenderer();
		Text1 = "SpaceShip01";
		Text2 = "SpaceShip02";
		Text3 = "SpaceShip03";
		Text4 = "WELCOME " + UserName.text;
		Text5 = "Choose your Fighter";
		r1 = new Rectangle();
		r2 = new Rectangle();
		r3 = new Rectangle();

		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		 int mouseX = Gdx.input.getX();
	     int mouseY = Gdx.input.getY();
		game.batch.begin();
		game.batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	    r1.set(80, 220, Gdx.graphics.getWidth()/7, Gdx.graphics.getHeight()/5);   //in all of 6 the same x
	    r2.set(270, 200, Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/5);  // first 3 when increasing y moves down
	    r3.set(475, 220, Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/5);
	    
	    
	      
	    
	       
	       if(r1.contains(mouseX, mouseY)){
	    	   game.batch.draw(spaceship1, -40, 100 ,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	    	   if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
	               shipChoice= 1;
	               game.setScreen(new Map(game));
	           }
	           }
	       
	       else
	    	   game.batch.draw(spaceship1, -100, 80 ,Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/1.5f);
	       

	       if(r2.contains(mouseX,mouseY)) {
	    	   game.batch.draw(spaceship2, 150 , 120 ,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	    	   if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
	               shipChoice= 2;
	               game.setScreen(new Map(game));
	           }
	       }
	       else
	    	   game.batch.draw(spaceship2, 90 , 80 ,Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/1.5f);
	       
	       if(r3.contains(mouseX,mouseY)) {
	    	   game.batch.draw(spaceship3, 350 , 150 ,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	    	   if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
	               shipChoice= 3;
	               game.setScreen(new Map(game));
	           }
	       } 
	       else
	    	   game.batch.draw(spaceship3, 300 , 100 ,Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/1.5f);
	       	      

	       font.draw(game.batch,Text4,Gdx.graphics.getWidth()/2.9f,Gdx.graphics.getHeight()/1.25f);
	       font.draw(game.batch,Text1,Gdx.graphics.getWidth()/10f,Gdx.graphics.getHeight()/3);
	       font.draw(game.batch,Text2,Gdx.graphics.getWidth()/2.6f,Gdx.graphics.getHeight()/3);
	       font.draw(game.batch,Text3,Gdx.graphics.getWidth()/1.4f,Gdx.graphics.getHeight()/3);
	       font.draw(game.batch,Text5,Gdx.graphics.getWidth()/3.5f,Gdx.graphics.getHeight()/5);

	       game.batch.end();
	       /*sr.begin(ShapeRenderer.ShapeType.Filled);
		      sr.setColor(Color.RED);
		       sr.rect(r1.x, r1.y,Gdx.graphics.getWidth()/7, Gdx.graphics.getHeight()/5);
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
