package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Scaling;

public class CharactersMenu extends ScreenAdapter implements ActionListener{
	SpriteBatch batch;
	Texture spaceship1 , spaceship2 , spaceship3 ,background;
	ShapeRenderer sr;
	Rectangle r1 , r2 , r3 , r4 , r5;
	String Text1, Text2, Text3;
	String text1,text2,text3,text4,text5;
	BitmapFont font;
	static int shipChoice;
	Texture forward,backward;
	int tag = 0;
	
	final VirusFighter game;
	
	public CharactersMenu(final VirusFighter game) {
		this.game = game;
		batch = new SpriteBatch();
		background = new Texture("MainUi/Window.png");
		spaceship1 = new Texture("SpaceShips/spaceShip1.png");
		spaceship2 = new Texture("SpaceShips/spaceShip2.png");
		spaceship3 = new Texture("SpaceShips/spaceShip3.png");
		forward = new Texture("Mainui/Forward_BTN.png");
		backward = new Texture("Mainui/Backward_BTN.png");
		font = new BitmapFont (Gdx.files.internal("fonts/myfont.fnt"));
		sr = new ShapeRenderer();
		r1 = new Rectangle();
		r2 = new Rectangle();
		r3 = new Rectangle();
		text1 = "Newbie";
		text2 = "Commando";
		text3 = "Falcon";
		text5 = "Choose your Fighter";
		
	}
	public static int getShipChoice() {
		return shipChoice;
	}

	private static void setShipChoice(int shipChoice) {
		CharactersMenu.shipChoice = shipChoice;
	}
	@Override
	public void render(float delta) {
		
		 	int mouseX = Gdx.input.getX();
	     	int mouseY = Gdx.input.getY();
	     	game.batch.begin();
			game.batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	   		r1.set(100, 200, Gdx.graphics.getWidth()/7, Gdx.graphics.getHeight()/5);   //in all of 6 the same x
	    	
	    	if(tag == 0){	
	       if(r1.contains(mouseX, mouseY)){
	    	   game.batch.draw(spaceship1, -40, 50 ,Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/1.6f);
	    	   if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
	    		   	shipChoice = 1;
	   				game.setScreen(new LevelsMenu(game));
	    	   	}
	        }     
	       else
	    	   game.batch.draw(spaceship1, -150, -50 ,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	       font.draw(game.batch,text1,Gdx.graphics.getWidth()/1.99f,Gdx.graphics.getHeight()/1.6f);
	       font.draw(game.batch,"Speed: 100 km/h",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/1.8f);
	       font.draw(game.batch,"Health: 3/10",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/1.95f);
	       font.draw(game.batch,"Bullets: Magnum",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/2.15f);
	       font.draw(game.batch,"Unlocked",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/2.4f);
	       game.batch.draw(forward, 550, 180,forward.getWidth()/3.5f,forward.getHeight()/3.5f);   
	       r2.set(550, 180, Gdx.graphics.getWidth()/3.5f, Gdx.graphics.getHeight()/3.5f); 
	       if(r2.contains(mouseX,mouseY)) {
	    	   if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
	    		   tag+=1;
	    	   }
	       }

	    	}
	    	else if(tag == 1) {
	    		 if(r1.contains(mouseX, mouseY)){
	  	    	   game.batch.draw(spaceship2, -40, 50 ,Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/1.6f);
	  	    	   if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
	  	    		   	shipChoice = 2;
	  	   				game.setScreen(new LevelsMenu(game));
	  	    	   	}
	  	        }     
	  	       else
	  	    	   game.batch.draw(spaceship2, -150, -50 ,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	  	       font.draw(game.batch,text2,Gdx.graphics.getWidth()/1.99f,Gdx.graphics.getHeight()/1.6f);
	  	       font.draw(game.batch,"Speed: 350 km/h",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/1.8f);
	  	       font.draw(game.batch,"Health: 6/10",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/1.95f);
	  	       font.draw(game.batch,"Bullets: ACP",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/2.15f);
	  	       font.draw(game.batch,"Unlocked",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/2.4f);
		       game.batch.draw(forward, 550, 180,forward.getWidth()/3.5f,forward.getHeight()/3.5f);   
		       game.batch.draw(backward, 20, 180,backward.getWidth()/3.5f,backward.getHeight()/3.5f);   
		       r2.set(550, 180, Gdx.graphics.getWidth()/3.5f, Gdx.graphics.getHeight()/3.5f); 
		       r3.set(3, 180, Gdx.graphics.getWidth()/8f, Gdx.graphics.getHeight()/3.5f);   
		       if(r2.contains(mouseX,mouseY)) {
		    	   if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
		    		   tag+=1;
		    	   }
		       }
		       if(r3.contains(mouseX,mouseY)) {
		    	   if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
		    		   tag-=1;
		    	   }
		       } 
	    	}
	    	else {
   			 if(UserName.unlockSpaceShip == true) {
	    		 if(r1.contains(mouseX, mouseY)){
	    				 game.batch.draw(spaceship3, -40, 50 ,Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/1.6f);
	  	  	    	   if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
	  	  	    		   	shipChoice = 3;
	  	  	   				game.setScreen(new LevelsMenu(game));
	  	  	    	   	}
	    			 }
	    		 else
		  	    	 game.batch.draw(spaceship3, -150, -30 ,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	  	        }     
	  	       else
	  	    	   game.batch.draw(spaceship3, -150, -30 ,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	  	       font.draw(game.batch,text3,Gdx.graphics.getWidth()/1.99f,Gdx.graphics.getHeight()/1.6f);
	  	       font.draw(game.batch,"Speed: 500 km/h",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/1.8f);
	  	       font.draw(game.batch,"Health: 8/10",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/1.95f);
	  	       font.draw(game.batch,"Bullets: Colt",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/2.15f);
	  	       font.draw(game.batch,"Pass level 4 to unlock!",Gdx.graphics.getWidth()/2.3f,Gdx.graphics.getHeight()/2.4f);
		       game.batch.draw(backward, 20, 180,backward.getWidth()/3.5f,backward.getHeight()/3.5f);   
		       r3.set(3, 180, Gdx.graphics.getWidth()/8f, Gdx.graphics.getHeight()/3.5f);   
		       if(r3.contains(mouseX,mouseY)) {
		    	   if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
		    		   tag-=1;
		    	   }
		       } 

	    	}
	       font.draw(game.batch,text5,Gdx.graphics.getWidth()/3.5f,Gdx.graphics.getHeight()/1.05f);

	       game.batch.end();

	}
	@Override
	public void dispose () {
		batch.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
