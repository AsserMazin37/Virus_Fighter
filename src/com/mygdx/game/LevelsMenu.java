package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


public class LevelsMenu extends ScreenAdapter{
	
	SpriteBatch batch;
	
	BitmapFont font;
    
	private static int levelChoice, tag;	
	
	public static int getLevelChoice() {
		return levelChoice;
	}

	private static void setLevelChoice(int levelChoice) {
		LevelsMenu.levelChoice = levelChoice;
	}

	public static int getTag() {
		return tag;
	}

	private static void setTag(int tag) {
		LevelsMenu.tag = tag;
	}

	Texture img, widndoWimg3, lungsImg, eyeImg, stomachImg, heartImg, brainImg, mouthImg, lvlimg5;
	
	ShapeRenderer SR;
	
	Rectangle recForLvl1, recForLvl2, recForLvl3, recForLvl4, recForLvl5, recForLvl6;
	
	boolean bool;
	
	String myText1, myText2, myText3, myText4, myText5, myText6;
	
	final VirusFighter game;

	LevelsMenu(final VirusFighter game){
		this.game = game;
		batch = new SpriteBatch();
		img=new Texture ("BG.png");
		eyeImg =new Texture ("level1.png");
		widndoWimg3=new Texture ("Window.png");
		mouthImg =new Texture ("level2.png");
		stomachImg =new Texture ("level4.png");
		lvlimg5 =new Texture ("Header.png");
		lungsImg=new Texture ("level3.png");
		heartImg=new Texture ("level5.png");
		brainImg=new Texture ("level6.png");
		SR = new ShapeRenderer();
        bool =false; 
        font =new BitmapFont (Gdx.files.internal("myfont.fnt"));
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
		myText6="Level1:Brain";
	}
	
	public void finalChoice (int number, boolean choice) {
		if (choice = true) {
			switch (number) {
			case 1:{
				game.setScreen(new Map(game));
				LevelsMenu.setTag(0);
				break;
			}
			 case 2:{
				 game.setScreen(new Map(game));
				 LevelsMenu.setTag(1);
			     break;
			 }
		     case 3:{
		 		game.setScreen(new Map(game));
		 		LevelsMenu.setTag(2);
	           break;
		     }
		     case 4:{
		 		game.setScreen(new Map(game));
		 		LevelsMenu.setTag(3);
	 			break;
		     }
		     case 5:{
		 		game.setScreen(new Map(game));
		 		LevelsMenu.setTag(4);
	           break;
		     }
		     case 6:{
		 		game.setScreen(new Map(game));
		 		LevelsMenu.setTag(5);
	           break; 
		     }
		         default:{   
			         break; 
		         }		
		     }
		
	    }
	}	
	
	public void dispose () {
		batch.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub		
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
		
		/*SR.begin(ShapeRenderer.ShapeType.Filled);
		SR.setColor(Color.RED);
		SR.rect(recForLvl1.x, recForLvl1.y,Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
		SR.end();*/
		
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		if(recForLvl1.contains(mouseX, mouseY)){
			game.batch.draw(eyeImg, 85, 260, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
			if(Gdx.input.justTouched())	{	
				bool=true;
				LevelsMenu.setLevelChoice(1);
			}
		}
		else {
			game.batch.draw(eyeImg, 85, 260, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);
		}
		
		
		if(recForLvl2.contains(mouseX,mouseY)) {
			game.batch.draw(mouthImg, 265, 260, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);	
			if(Gdx.input.justTouched()) {
				bool=true;
				LevelsMenu.setLevelChoice(2);
			}
		}
		else {
			game.batch.draw(mouthImg, 265, 260, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);
		}
		
		
		if(recForLvl3.contains(mouseX,mouseY)) {
			if(Gdx.input.justTouched()) {
				bool=true;
				LevelsMenu.setLevelChoice(3);
			}
			game.batch.draw(lungsImg, 450, 260, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
		}
		else {
			game.batch.draw(lungsImg, 450, 260, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);
		}
		
		
		if(recForLvl4.contains(mouseX,mouseY)) {
			if(Gdx.input.justTouched()) {
				bool=true;
				LevelsMenu.setLevelChoice(4);
			}    
			game.batch.draw(stomachImg, 85, 100, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
		}
		else {
			game.batch.draw(stomachImg, 85, 100, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);
		}
		
		
		if(recForLvl5.contains(mouseX,mouseY)) {
			if(Gdx.input.justTouched()) {
				bool=true;
				LevelsMenu.setLevelChoice(5);
			}			    
			game.batch.draw(heartImg, 265, 100, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);	
		}
		else {
			game.batch.draw(heartImg, 265, 100, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/4);
		}
		
		
		if(recForLvl6.contains(mouseX,mouseY)) {
			game.batch.draw(brainImg, 450, 100, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/5);
			if(Gdx.input.justTouched()) {
				bool=true;
				LevelsMenu.setLevelChoice(6);
			}
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
}