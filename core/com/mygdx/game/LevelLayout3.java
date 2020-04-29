package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import gameData.*;

public class LevelLayout3 extends ScreenAdapter{
	private State state = State.RUN;
	
	ShapeRenderer sr;

	Circle characterCircle, firstVirusCircle, secondVirusCircle;
	
	Music levelMusic, shoot, explosion;

	SpriteBatch batch;
	
	Texture bullet, spaceShip, firstVirus, background, secondVirus, collision,
	firstHealthBarFull, secondHealthBarFull, firstHealthBarSemi, secondHealthBarSemi,
	firstHealthBarPartial, secondHealthBarPartial, firstHealthBarEmpty, secondHealthBarEmpty,
	loser, window, pause;
	
	Level3 l;
	Virus v;
	
	Vector2 characterLoc = new Vector2();
	Vector2 firstVirusLoc = new Vector2();
	Vector2 secondVirusLoc = new Vector2();
	Vector2 timerLoc = new Vector2();
	Vector2 firstHealthBarLoc = new Vector2();
	Vector2 secondHealthBarLoc = new Vector2();
	
	ArrayList<Bullet1> bulletManager = new ArrayList<Bullet1>();
	ArrayList<Circle> bulletCircle = new ArrayList<Circle>();
	
	int bulletSpeed, posY, leftWidth, rightWidth, timerCount, firstVirusHealth, secondVirusHealth,
	totalTime, firstWithCharacter, secondWithCharacter, firstWithBullet, secondWithBullet;
	int countDataBase = 0;
	
	Random virusOneRand, virusTwoRand;
	
	OrthographicCamera camera;
		
	float timerPeriod, timerCounter, virusPeriod, virusCounter, w, h;
	
	public static boolean enteredLevel3 = false;
	
	boolean firstVirusKilled, secondVirusKilled, characterKilled, gameLost, gameWon, levelEnded,
	instructions;
	
	BitmapFont font, introText, smallYellow, orangeFont, mediumRed, healthbar, smallRed, smallGreen;
	
	String introTitle, IntroText1, IntroText2, IntroText3, IntroText4, IntroText5, IntroText6,
	IntroText7, IntroText8, IntroText9, congratulationsText, gameOverText,
	backToCharacter, backToLevel, restart, returnToGamePlay, press;
	
	final VirusFighter game;
	
	LevelLayout3(final VirusFighter game){
		this.game = game;
		
		firstVirusKilled = false;
		secondVirusKilled = false;
		characterKilled = false;
		enteredLevel3 = true;
		gameLost = false;
		gameWon = false;
		levelEnded = false;
		instructions = true;
		
		sr = new ShapeRenderer();
		
		batch = new SpriteBatch();	
		
		l = new Level3();
		v = new Virus();
		
		levelMusic = Gdx.audio.newMusic(Gdx.files.internal(l.getMusic()));
		shoot = Gdx.audio.newMusic(Gdx.files.internal("music/fire.mp3"));
		explosion = Gdx.audio.newMusic(Gdx.files.internal("music/explosion.mp3"));
		
		levelMusic.setVolume(0.5f);
		
		if(MainMenu.disable == false) {
			levelMusic.play();
		}
		
		firstVirus = new Texture(v.getVirus3());
		secondVirus = new Texture(v.getSecondVirus());
		background = new Texture(l.getBackground());
		spaceShip = new Texture(l.getCharacter());
		bullet = new Texture(l.getCharacterBullet());
		collision = new Texture("Levels/collision.png");
		firstHealthBarFull = new Texture("Levels/fullHealthBar.png");
		secondHealthBarFull = new Texture("Levels/fullHealthBarInverted.png");
		firstHealthBarSemi = new Texture("Levels/semiHealthBar.png");
		secondHealthBarSemi = new Texture("Levels/semiHealthBarInverted.png");
		firstHealthBarPartial = new Texture("Levels/partialHealthBar.png");
		secondHealthBarPartial = new Texture("Levels/partialHealthBarInverted.png");
		firstHealthBarEmpty = new Texture("Levels/noHealthBar.png");
		secondHealthBarEmpty = new Texture("Levels/noHealthBarInverted.png");
		loser = new Texture("Levels/loser.png");
		bulletSpeed = l.getCharacterBulletSpeed();
		window = new Texture("MainUi/Window.png");
		pause = new Texture("Levels/pause.png");
		
		characterLoc.x = 230;
		characterLoc.y = 150;
		firstVirusLoc.x = 150;
		firstVirusLoc.y = 390;
		secondVirusLoc.x = 310;
		secondVirusLoc.y = 390;
		timerLoc.x = 305;
		timerLoc.y = 470;
		firstHealthBarLoc.x = -50;
		firstHealthBarLoc.y = 300;
		secondHealthBarLoc.x = 145;
		secondHealthBarLoc.y = 315;
					
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w/2,h/1.5f);
		camera.position.set(camera.viewportWidth , camera.viewportHeight, 0);
		camera.update();
		
		virusOneRand = new Random();
		virusTwoRand = new Random();
		
		leftWidth = 100;
		rightWidth = 380;
		timerCount = 30;
		timerPeriod = 1f;
		timerCounter = 0f;
		virusPeriod = 0.5f;
		virusCounter = 0f;
		posY = 300;
		firstVirusHealth = 200;
		secondVirusHealth = 200;
		totalTime = 0;
		firstWithCharacter = 0;
		secondWithCharacter = 0;
		firstWithBullet = 0;
		secondWithBullet = 0;
		
		characterCircle = new Circle();
		firstVirusCircle = new Circle(); 
		secondVirusCircle = new Circle();
		
		font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		smallYellow = new BitmapFont(Gdx.files.internal("fonts/myfont9yellowsmall.fnt"));
		orangeFont = new BitmapFont(Gdx.files.internal("fonts/myfont7orangeMedium.fnt"));
		mediumRed = new BitmapFont(Gdx.files.internal("fonts/myfont5red.fnt"));
		healthbar = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		smallRed = new BitmapFont(Gdx.files.internal("fonts/myfont10redsmall.fnt"));
		smallGreen = new BitmapFont(Gdx.files.internal("fonts/myfont11green.fnt"));
		
		introTitle = "LEVEL GUIDE";
		IntroText1 = "-Challenge Yourself.";
		IntroText2 = "-Kill the virus before it kills you.";
		IntroText3 = "-CONTROLS: ";
		IntroText4 = "*Press SPACE button to shoot.";
		IntroText5 = "*Press Left arrow to go left";
		IntroText6 = "*Press Right arrow to go right";
		IntroText7 = "-DON'T FORGET TO WATCH THE TIMER!!";
		IntroText8 = "-GOOD LUCK.";
		IntroText9 = "-Press ENTER to start your fight.";
		congratulationsText = "CONGRATULATIONS!";
		gameOverText = "GAME OVER!!!";
		backToCharacter = "C to choose a character";
		backToLevel = "ESC to choose another level";
		restart = "R to restart your game";
		returnToGamePlay = "A to return to your Game";
		press = "Press:";
	}
	
	public void update() {
    	if(Gdx.input.isKeyPressed(Keys.RIGHT) ) {
    		if(!characterKilled) {
	    		if(characterLoc.x < rightWidth)
	    			characterLoc.x += Level3.getChSpeed(); 		    			
    		}
    	}
    	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
    		if(!characterKilled) {
	    		if(characterLoc.x > leftWidth)
	    			characterLoc.x -= Level3.getChSpeed(); 		    			
    		}
    	}
    	
    	if(!characterKilled && !gameLost && Gdx.input.isKeyJustPressed(Keys.SPACE)) {
    		shoot.setVolume(1);
    		shoot.play();
    		Bullet1 myBullet = new Bullet1(characterLoc, new Vector2(0, bulletSpeed));
    		bulletManager.add(myBullet);
    	}
    	if(Gdx.input.isKeyJustPressed(Keys.R)) {
    		camera.setToOrtho(false);
	    	game.batch.setProjectionMatrix(camera.combined);
	    	levelMusic.stop();
    		enteredLevel3 = false;
    		game.setScreen(new Map (game));
    	}
    	if(Gdx.input.isKeyJustPressed(Keys.C)) {
    		camera.setToOrtho(false);
	    	game.batch.setProjectionMatrix(camera.combined);
	    	levelMusic.stop();
    		enteredLevel3 = false;
    		game.setScreen(new CharactersMenu (game));
    	}
    	if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
    		camera.setToOrtho(false);
	    	game.batch.setProjectionMatrix(camera.combined);
	    	levelMusic.stop();
    		enteredLevel3 = false;
    		game.setScreen(new LevelsMenu (game));
    	}
    	if(Gdx.input.isKeyJustPressed(Keys.P)) {
    		pause();
    	}
    	if(Gdx.input.isKeyJustPressed(Keys.A)) {
    		resume();
    	}
 }
	
	@Override
	public void render (float delta) {
		
		game.batch.begin();
		update();
		
		switch (state){
	    case RUN:{
	    	game.batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()*20);
			
			if(instructions) {
				orangeFont.draw(game.batch, introTitle, 180, 450); 
				healthbar.draw(game.batch, IntroText1, 2, 400, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
				healthbar.draw(game.batch, IntroText2, 2, 360, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
				healthbar.draw(game.batch, IntroText3, 2, 320, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
				healthbar.draw(game.batch, IntroText4, 2, 280, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
				healthbar.draw(game.batch, IntroText5, 2, 240, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
				healthbar.draw(game.batch, IntroText6, 2, 200, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
				smallYellow.draw(game.batch, IntroText7, 2, 160, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
				healthbar.draw(game.batch, IntroText8, 2, 120, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
				mediumRed.draw(game.batch, IntroText9, 2, 80, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
				
				if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
					instructions = false;
		    	}
			}
			else {
				if(!levelEnded) { //levelEnded becomes true when both viruses are killed or character is killed or timer=0
					timerCounter += Gdx.graphics.getRawDeltaTime();
					if(timerCounter > timerPeriod) {
						if(timerCount != 0) {
							timerCount--;
						}
						if(timerCount == 0) {
							levelEnded = true;
							gameLost = true;
						}
						timerCounter = 0;	
					}
				}
				
				virusCounter += Gdx.graphics.getRawDeltaTime();
				if(virusCounter > virusPeriod) {
				    virusCounter = 0;
				    v.generateRandom(characterLoc, firstVirusLoc, secondVirusLoc, rightWidth, leftWidth, virusOneRand, virusTwoRand, firstVirusKilled, secondVirusKilled);
				}
				
				camera.translate(0, 2, 0);
				camera.update();
				characterLoc.y += 2;
		
				game.batch.setProjectionMatrix(camera.combined);
				
				if(!characterKilled) {
			    	game.batch.draw(spaceShip, characterLoc.x, characterLoc.y, spaceShip.getWidth()/7, spaceShip.getHeight()/7);
			    	characterCircle.set(characterLoc.x+30, characterLoc.y, 20);
				}
				
				if(!firstVirusKilled) {
			    	game.batch.draw(firstVirus, firstVirusLoc.x, firstVirusLoc.y, firstVirus.getWidth()/7, firstVirus.getHeight()/7);		    	
			    	firstVirusCircle.set(firstVirusLoc.x+30, firstVirusLoc.y, 30);
				}
				
		    	if(!secondVirusKilled) {
			    	game.batch.draw(secondVirus, secondVirusLoc.x, secondVirusLoc.y, secondVirus.getWidth()/7, secondVirus.getHeight()/7);
			    	secondVirusCircle.set(secondVirusLoc.x+30, secondVirusLoc.y, 30);
		    	}
		    	
		    	posY += 2;
		    	firstVirusLoc.y += 2;
		    	secondVirusLoc.y += 2;
		    	  
		    	int counter = 0;
		    	bulletCircle.clear();
		    	while(counter < bulletManager.size()) {
		    		Bullet1 currentBullet = bulletManager.get(counter);
		    		currentBullet.update();
		    		
		    		if(currentBullet.bulletLocation.y > 0 || currentBullet.bulletLocation.y < Gdx.graphics.getHeight()) {
		    			game.batch.draw(bullet, currentBullet.bulletLocation.x+10, currentBullet.bulletLocation.y+20, bullet.getWidth()/8, bullet.getHeight()/8);
		    			bulletCircle.add(new Circle(currentBullet.bulletLocation.x+36.7f,currentBullet.bulletLocation.y,1));
		    		}
		    		else {
		    			bulletManager.remove(counter);
		    			if(bulletManager.size() > 0) {
		        			counter--;
		    			}
		    		}
		    		counter++;
		    	}
		    	
		    	
		    	// If any of the bullets collide with the first virus
		    	for(int i = 0; i < bulletCircle.size();i++) {
	    			if (Intersector.overlaps(bulletCircle.get(i), firstVirusCircle)) {
	    				if(firstVirusHealth > 0) {
	        				firstVirusHealth -=5;
	    				}
	    				bulletCircle.remove(i);
	    				bulletManager.remove(i);
	    			}
		    	}
		    	
		    	// If any of the bullets collide with the second virus
		    	for(int i = 0; i < bulletCircle.size();i++) {
	    			if (Intersector.overlaps(bulletCircle.get(i), secondVirusCircle)) {
	    				if(secondVirusHealth > 0) {
	        				secondVirusHealth -=5;
	    				}
	    				bulletCircle.remove(i);
	    				bulletManager.remove(i);
	    			}
		    	}
		    	
		    	// If the first virus collides with our character
		    	if(Intersector.overlaps(characterCircle, firstVirusCircle) && !gameLost) {
		    		if(firstWithCharacter == 0) {
		    			firstWithCharacter++;
			    		explosion.play();
			    		game.batch.draw(collision, characterLoc.x+30, characterLoc.y,80,80);
		    		}
		    		characterKilled = true;
		    		gameLost = true;
		    		levelEnded = true;
		    	}
		    	
		    	// If the second virus collides with our character
		    	if(Intersector.overlaps(characterCircle, secondVirusCircle) && !gameLost) {
		    		if(secondWithCharacter == 0) {
		    			secondWithCharacter++;
		    			explosion.play();
			    		game.batch.draw(collision, characterLoc.x+30, characterLoc.y,80,80);
		    		}
		    		characterKilled = true;
		    		gameLost = true;
		    		levelEnded = true;
		    	}
		    	
		    	// Draw the health bar for the first virus
		    	if(firstVirusHealth <= 200 && firstVirusHealth > 150) {
		    		game.batch.draw(firstHealthBarFull, firstHealthBarLoc.x, firstHealthBarLoc.y, firstHealthBarFull.getWidth()/2, firstHealthBarFull.getHeight()/2);
		    	}
		    	else if(firstVirusHealth <= 150 && firstVirusHealth > 75) {
		    		game.batch.draw(firstHealthBarSemi, firstHealthBarLoc.x-8, firstHealthBarLoc.y-3, firstHealthBarFull.getWidth()/2, firstHealthBarFull.getHeight()/2);
		    	}
		    	else if(firstVirusHealth <= 75 && firstVirusHealth > 0) {
		    		game.batch.draw(firstHealthBarPartial, firstHealthBarLoc.x-8, firstHealthBarLoc.y-3, firstHealthBarFull.getWidth()/2, firstHealthBarFull.getHeight()/2);
		    	}
		    	else {
		    		firstVirusCircle.set(0, 0, 0);
		    		firstVirusKilled = true;
		    		if(firstWithBullet == 0) {
		    			firstWithBullet++;
		    			explosion.play();
			    		game.batch.draw(collision, firstVirusLoc.x+25, firstVirusLoc.y,80,80);	    			
		    		}
		    		game.batch.draw(firstHealthBarEmpty, firstHealthBarLoc.x+5, firstHealthBarLoc.y+15, firstHealthBarFull.getWidth()/2, firstHealthBarFull.getHeight()/2);
		    	}
		    	
		    	// Draw the health bar for the second virus
		    	if(secondVirusHealth <= 200 && secondVirusHealth > 150) {
		    		game.batch.draw(secondHealthBarFull, secondHealthBarLoc.x, secondHealthBarLoc.y, secondHealthBarFull.getWidth()/2, secondHealthBarFull.getHeight()/2);
		    	}
		    	else if(secondVirusHealth <= 150 && secondVirusHealth > 75) {
		    		game.batch.draw(secondHealthBarSemi, secondHealthBarLoc.x, secondHealthBarLoc.y-30, secondHealthBarFull.getWidth()/2, secondHealthBarFull.getHeight()/2);
		    	}
		    	else if(secondVirusHealth <= 75 && secondVirusHealth > 0) {
		    		game.batch.draw(secondHealthBarPartial, secondHealthBarLoc.x, secondHealthBarLoc.y-8, secondHealthBarFull.getWidth()/2, secondHealthBarFull.getHeight()/2);
		    	}
		    	else {
		    		secondVirusCircle.set(0, 0, 0);
		    		secondVirusKilled = true;
		    		if(secondWithBullet == 0) {
		    			secondWithBullet++;
		    			explosion.play();
			    		game.batch.draw(collision, secondVirusLoc.x+25, secondVirusLoc.y,80,80);	    			
		    		}

		    		game.batch.draw(secondHealthBarEmpty, secondHealthBarLoc.x, secondHealthBarLoc.y+20, secondHealthBarFull.getWidth()/2, secondHealthBarFull.getHeight()/2);
		    	}
		    	
		    	if(firstVirusKilled && secondVirusKilled) {
		    		totalTime = 30 - timerCount;      // calculate time in which player finished the level
		    		gameWon = true;
		    		levelEnded = true;
		    		characterLoc.y += 5;
		    		
		    		smallRed.draw(game.batch, congratulationsText, 250, timerLoc.y-40);
	            	
		    		smallYellow.draw(game.batch, "You finished this level", 170, timerLoc.y-100);
		    		smallYellow.draw(game.batch, "in " + totalTime + " seconds", 230, timerLoc.y-120);
		    		
		    		smallGreen.getData().scaleX = 0.7f;
		    		smallGreen.draw(game.batch, backToCharacter, 163, timerLoc.y-150); 
		    		smallGreen.draw(game.batch, backToLevel, 163, timerLoc.y-190);
		    		smallGreen.draw(game.batch, restart, 163, timerLoc.y-220);
		    		smallGreen.getData().scaleX = 1;
		    		
		    		smallRed.getData().scaleX = 0.7f;
		    		smallRed.draw(game.batch, "Alt Left to go to next Level", 170, timerLoc.y-260);
		    		smallRed.getData().scaleX = 1;
		    		
		    		countDataBase++;
					VirusFighter.played = 3;
	            	if(countDataBase == 1) {
	            		try {
							databaseClass.database();
						} catch (ClassNotFoundException e) {
							e.getStackTrace();
						}
	            	}
	            	if(Gdx.input.isKeyPressed(Keys.ALT_LEFT))
	            	{
	            		camera.setToOrtho(false);
				    	game.batch.setProjectionMatrix(camera.combined);
				    	levelMusic.stop();
			    		enteredLevel3 = false;
		                LevelsMenu.tag = LevelsMenu.tag+1;
		                game.setScreen(new Map(game));
	            	}
				}
		    	
		    	if(gameLost) {
		    		if(!firstVirusKilled && secondVirusKilled) {
		    			game.batch.draw(loser, firstVirusLoc.x-15, firstVirusLoc.y-2, loser.getWidth()/6, loser.getHeight()/6);
		    		}
		    		if(firstVirusKilled && !secondVirusKilled) {
		    			game.batch.draw(loser, secondVirusLoc.x-15, secondVirusLoc.y-2, loser.getWidth()/6, loser.getHeight()/6);
		    		}
		    		if(!firstVirusKilled && !secondVirusKilled) {
		    			game.batch.draw(loser, firstVirusLoc.x-15, firstVirusLoc.y-2, loser.getWidth()/6, loser.getHeight()/6);
		    			game.batch.draw(loser, secondVirusLoc.x-15, secondVirusLoc.y-2, loser.getWidth()/6, loser.getHeight()/6);
		    		}
		    		
		    		smallRed.draw(game.batch, gameOverText, 250, timerLoc.y-40);
		    		
		    		smallGreen.getData().scaleX = 0.7f;
		    		smallGreen.draw(game.batch, backToCharacter, 163, timerLoc.y-150); 
		    		smallGreen.draw(game.batch, backToLevel, 163, timerLoc.y-190);
		    		smallGreen.draw(game.batch, restart, 163, timerLoc.y-220);
		    		smallGreen.getData().scaleX = 1;
		    	}
		    	
		    	firstHealthBarLoc.y += 2;
		    	secondHealthBarLoc.y += 2;
		    	font.draw(game.batch,Integer.toString(timerCount),timerLoc.x, timerLoc.y); //draw the timer in upper left corner
		    	timerLoc.y += 2;		    	
		    	/*sr.begin(ShapeRenderer.ShapeType.Filled);
				sr.setColor(Color.RED);
				sr.circle(firstVirusCircle.x, firstVirusCircle.y,firstVirus.getWidth() / 7, firstVirus.getHeight() / 7);
				sr.end();*/
			}
	        break;
		}
	    case PAUSE:{
	    	game.batch.draw(window, 240, characterLoc.y+30, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/1.8f);
	    	
	    	game.batch.draw(pause, 270, characterLoc.y+270, pause.getWidth()/3,pause.getHeight()/5);
	    	
	    	font.getData().scaleX = 0.4f;
	    	font.draw(game.batch, press, 255, timerLoc.y-90);
	    	font.draw(game.batch, returnToGamePlay, 255, timerLoc.y-130);
	    	font.draw(game.batch, backToCharacter, 255, timerLoc.y-170); 
	    	font.draw(game.batch, backToLevel, 255, timerLoc.y-210);
	    	font.draw(game.batch, restart, 255, timerLoc.y-250);
	    	font.getData().scaleX = 1;
	    	
	    	game.batch.setProjectionMatrix(camera.combined);
	    	camera.translate(0, 0, 0);
			camera.update();
	        break;
	    }
	    default:
	        break;
	    }
		game.batch.end();
	}
	
	@Override
	public void pause(){
	    this.state = State.PAUSE;
	}

	@Override
	public void resume(){
	    this.state = State.RUN;
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}