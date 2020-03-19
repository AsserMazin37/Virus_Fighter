package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class CharacterMenu extends ScreenAdapter{
	SpriteBatch batch;
	Texture spaceship1 , spaceship2 , spaceship3 ,background;
	ShapeRenderer sr;
	Rectangle r1 , r2 , r3 , r4 , r5;
	String Text1, Text2, Text3;
	String text1,text2,text3,text4,text5;
	BitmapFont font;
	private static int shipChoice;
	
	public static int getShipChoice() {
		return shipChoice;
	}

	private static void setShipChoice(int shipChoice) {
		CharacterMenu.shipChoice = shipChoice;
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	final VirusFighter game;
	
	public CharacterMenu(final VirusFighter game) {
		this.game = game;
		batch = new SpriteBatch();
		background = new Texture("Window.png");
		spaceship1 = new Texture("spaceShip1.png");
		spaceship2 = new Texture("spaceShip2.png");
		spaceship3 = new Texture("spaceShip3.png");
		font = new BitmapFont (Gdx.files.internal("myfont.fnt"));
		sr = new ShapeRenderer();
		r1 = new Rectangle();
		r2 = new Rectangle();
		r3 = new Rectangle();
		text1 = "Newbie";
		text2 = "Commando";
		text3 = "Falcon";
		text4 = "WELCOME " + UserName.text;
		text5 = "Choose your Fighter";
		
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
	    	   if(Gdx.input.justTouched()) {
	    		   CharacterMenu.setShipChoice(1);
	   			   game.setScreen(new LevelsMenu(game));
	    	   }
	       }
	       
	       else
	    	   game.batch.draw(spaceship1, -110, 80 ,Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/1.6f);
	       

	       if(r2.contains(mouseX,mouseY)) {
	    	   game.batch.draw(spaceship2, 150 , 120 ,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	    	   if(Gdx.input.justTouched()) {
	    		   CharacterMenu.setShipChoice(2);
		   		   game.setScreen(new LevelsMenu(game));
	    	   }
	       }
	       else
	    	   game.batch.draw(spaceship2, 90 , 80 ,Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/1.5f);
	       
	       if(r3.contains(mouseX,mouseY)) {
	    	   game.batch.draw(spaceship3, 350 , 150 ,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	    	   if(Gdx.input.justTouched()) {
	    		    CharacterMenu.setShipChoice(3);
		   			game.setScreen(new LevelsMenu(game));
	    	   }
	       } 
	       else
	    	   game.batch.draw(spaceship3, 300 , 100 ,Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/1.5f);
	       	      

	      
	       font.draw(game.batch,text1,Gdx.graphics.getWidth()/10f,Gdx.graphics.getHeight()/3);
	       font.draw(game.batch,text2,Gdx.graphics.getWidth()/2.53f,Gdx.graphics.getHeight()/3);
	       font.draw(game.batch,text3,Gdx.graphics.getWidth()/1.35f,Gdx.graphics.getHeight()/3);
	       
	       font.draw(game.batch,text5,Gdx.graphics.getWidth()/3.5f,Gdx.graphics.getHeight()/1.05f);


	       //font.draw(game.batch,text5,Gdx.graphics.getWidth()/3.5f,Gdx.graphics.getHeight()/4f);

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