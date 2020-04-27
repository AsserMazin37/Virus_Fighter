package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import gameData.databaseClass;


public class LevelsMenu extends ScreenAdapter{
	SpriteBatch batch;
	BitmapFont font;
    static int levelChoice;	
    Texture img;
	Texture widndoWimg3;
	Texture lungsImg;
	Texture eyeImg;
	Texture stomachImg;
	Texture heartImg;
	Texture brainImg;
	Texture forward,backward;
	public static int tag;

	Texture mouthImg;
	Texture lvlimg5;

	ShapeRenderer SR;
	
	Rectangle recForLvl1;
	Rectangle recForLvl2;
	Rectangle recForLvl3;
	Rectangle recForLvl4;
	Rectangle recForLvl5;
	Rectangle recForLvl6;
	boolean bool;
	
	String myText1;
	String myText2;
	String myText3;
	String myText4;
	String myText5;
	String myText6;
	
	boolean l1,l2,l3,l4,l5;
	
	final VirusFighter game;

	LevelsMenu(	final VirusFighter game){
		this.game = game;
		batch = new SpriteBatch();
		img=new Texture ("MainUi/BG.png");
		eyeImg =new Texture ("Levels/level1.png");
		widndoWimg3=new Texture ("MainUi/Window.png");
		mouthImg =new Texture ("Levels/level2.png");
		stomachImg =new Texture ("Levels/level4.png");
		lvlimg5 =new Texture ("MainUi/Header.png");
		lungsImg=new Texture ("Levels/level3.png");
		heartImg=new Texture ("Levels/level5.png");
		brainImg=new Texture ("Levels/level6.png");
		
		SR = new ShapeRenderer();
        bool =false; 
        font =new BitmapFont (Gdx.files.internal("fonts/myfont.fnt"));
		recForLvl1=new Rectangle();
		recForLvl2=new Rectangle();
		recForLvl3=new Rectangle();
		recForLvl4=new Rectangle();
		recForLvl5=new Rectangle();
		recForLvl6=new Rectangle();
		myText1="Level1:Eye";
		myText2="Level2:Pharynx";
		myText3="Level3:Lungs";
		myText4="Leve14:Stomach";
		myText5="Level5:Heart";
		myText6="Level6:Brain";
		levelChoice = 0;

	}
	
	public void finalChoice (int number, boolean choice) {
		if (choice = true) {
		switch (number) {
		case 1:
				tag = 0;
				game.setScreen(new Map(game));
				break;
		 case 2:
			 	tag = 1;
				game.setScreen(new Map(game));
				break;
		 case 3:
			 	tag = 2;
				game.setScreen(new Map(game));
	 			break;
	     case 4:
	    	 	tag = 3;
				game.setScreen(new Map(game));
	 			break;
	     case 5:
	    	 	tag = 4;
				game.setScreen(new Map(game));
	 			break;
	     case 6:
	    	 	tag = 5;
				game.setScreen(new Map(game));
	 			break;    
	     default:        
	    	 	break;		
		}		
	}
	}	
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		game.batch.begin();
		game.batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());  //background
		game.batch.draw(widndoWimg3, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); // window
		
		recForLvl1.set(85, 130, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);  //in all of 6 the same x
		recForLvl2.set(265, 130, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);// first 3 when increasing y moves down
		recForLvl3.set(450, 130, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
		recForLvl4.set(85, 300, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
		recForLvl5.set(265, 300, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
		recForLvl6.set(450, 300, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
		
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		try {
			if(databaseClass.update() == 1) {
				l1 = true;
			}
			else if(databaseClass.update()  == 2) {
				l1 = true;
				l2 = true;
			}
			else if(databaseClass.update()  == 3) {
				l1 = true;
				l2 = true;
				l3 = true;
					}
			else if(databaseClass.update()  == 4) {
				l1 = true;
				l2 = true;
				l3 = true;
				l4 = true;
			}
			else if(databaseClass.update()  == 5) {
				l1 = true;
				l2 = true;
				l3 = true;
				l4 = true;
				l5 = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(l1 == true) {
		if(recForLvl1.contains(mouseX, mouseY))
		{
			
				game.batch.draw(eyeImg, 85, 260, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
				if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))	{	
					bool=true;
					levelChoice= 1;
					}	  
			}
		else {
			game.batch.draw(eyeImg, 85, 260, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);}
		}
		else {
			game.batch.draw(eyeImg, 85, 260, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);
		}
		if(l2 == true) {
		if(recForLvl2.contains(mouseX,mouseY)) {
			
				game.batch.draw(mouthImg, 265, 260, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);	
				if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
					bool=true;
			        levelChoice= 2;
				}
			}
		else {
			game.batch.draw(mouthImg, 265, 260, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);		
			}
		}
		else {
			game.batch.draw(mouthImg, 265, 260, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);		
			}
		if(l3 == true) {
		if(recForLvl3.contains(mouseX,mouseY)) {
			
				if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
					bool=true;
					levelChoice= 3;
					}	    
				game.batch.draw(lungsImg, 450, 260, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
			}
		else {
			game.batch.draw(lungsImg, 450, 260, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);		
			}	
		}
		else {
			game.batch.draw(lungsImg, 450, 260, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);		
			}	
		if(l4 == true) {
		if(recForLvl4.contains(mouseX,mouseY)) {
			
				if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
					bool=true;
					levelChoice= 4;
					}		    
				game.batch.draw(stomachImg, 85, 100, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
			}
		else {
			game.batch.draw(stomachImg, 85, 100, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);		
			}
		}
		else {
			game.batch.draw(stomachImg, 85, 100, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);		
			}	
		if(l5 == true) {
		if(recForLvl5.contains(mouseX,mouseY)) {
			
				if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
					bool=true;
					levelChoice= 5;
				}			    
				game.batch.draw(heartImg, 265, 100, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);		
			}
		else {
			game.batch.draw(heartImg, 265, 100, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);		
			}	
			}
		else {
			game.batch.draw(heartImg, 265, 100, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);		
			}		
		if(recForLvl6.contains(mouseX,mouseY)) {
			/*
			game.batch.draw(brainImg, 450, 100, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				bool=true;
				levelChoice= 6;
			}*/
			game.batch.draw(brainImg, 450, 100, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);

					}
		else {
			game.batch.draw(brainImg, 450, 100, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);
			}
		
		game.batch.draw(lvlimg5, 175, 420, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/10);
		font.draw(game.batch,myText1,Gdx.graphics.getWidth()/10f,Gdx.graphics.getHeight()/1.8f);
		font.draw(game.batch,myText2,Gdx.graphics.getWidth()/2.6f,Gdx.graphics.getHeight()/1.8f);
		font.draw(game.batch,myText3,Gdx.graphics.getWidth()/1.4f,Gdx.graphics.getHeight()/1.8f);
		font.draw(game.batch,myText4,Gdx.graphics.getWidth()/10f,Gdx.graphics.getHeight()/4.3f);
		font.draw(game.batch,myText5,Gdx.graphics.getWidth()/2.4f,Gdx.graphics.getHeight()/4.3f);
		font.draw(game.batch,myText6,Gdx.graphics.getWidth()/1.4f,Gdx.graphics.getHeight()/4.3f);
	    finalChoice (levelChoice,bool);
	    bool = false;
		game.batch.end();
	}
	public void dispose () {
		game.batch.dispose();
	}
}



