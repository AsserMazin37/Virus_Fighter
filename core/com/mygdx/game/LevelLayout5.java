package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import gameData.*;

public class LevelLayout5 extends ScreenAdapter{
	private State state = State.RUN;
	
	SpriteBatch batch;
	
	ShapeRenderer sr;
	
	Music levelMusic;
	
	Texture bullet, spaceShip, virus, background, virusBulletTexture,
	heart1, heart2, heart3,  instruction, window, pause;
	
	Level5 l;
	Virus v;
	
	Vector2 spaceShipLoc = new Vector2();
	Vector2 virusLoc = new Vector2();
	Vector2 heartLocation = new Vector2();
	
	ArrayList<Bullet1> bulletManager = new ArrayList<Bullet1>();	
	ArrayList<VirusBullet> virusBulletManager = new ArrayList<VirusBullet>();
	ArrayList<Level2Explosion> explosions;
	ArrayList<Circle> circleVirus = new ArrayList<Circle>(); //for tracking small viruses
	ArrayList<Circle> circleBullet = new ArrayList<Circle>(); //for tracking bullets
	ArrayList<SpaceShipExplosion>virusExplosion = new ArrayList<SpaceShipExplosion>();
	int count = 0;
	Music boom;
	Circle spaceShipCircle = new Circle(); 
	Circle virusBossCircle = new Circle();
	
	OrthographicCamera camera;
	
	int posY = 300;
	int right, left, bulletSpeed , virusBulletSpeed;
	int lives , collidBullets; //how many bullets attacked boss virus till death
	int countDataBase = 0;
	
	float virusTime , virusPeriod , attackTime , attackPeriod, time;
	
	Random rand;
	
	boolean drawInstructions ;
	
	static boolean enteredLevel5;
	
	BitmapFont font, introText, smallYellow, orangeFont, mediumRed, healthbar,
	smallRed, gameOverFont, smallGreen;
	
	String introTitle, IntroText1, IntroText2, IntroText3, IntroText4, IntroText5, IntroText6,
	IntroText7, IntroText8, IntroText9,gameOverText,
	backToCharacter, backToLevel, restart, congratulationsText, returnToGamePlay, press;
	
	final VirusFighter game;
	
	LevelLayout5(final VirusFighter game){
		this.game = game;		
		
		batch = new SpriteBatch();	
		
		l = new Level5();
		v = new Virus();
		boom = Gdx.audio.newMusic(Gdx.files.internal("music/Canoon.mp3"));
		enteredLevel5 = true;
		levelMusic = Gdx.audio.newMusic(Gdx.files.internal(l.getMusic()));
		if(MainMenu.disable == false) {
			levelMusic.play();
		}
		
		background = new Texture(l.getBackground());
		spaceShip = new Texture(l.getCharacter());
		virus = new Texture(v.getVirus5());
		bullet = new Texture(Level5.getBullet());
		virusBulletTexture = new Texture(v.getVirusBullets5());
		heart1 = new Texture("Levels/healthHeart.png");
		heart2 = new Texture("Levels/healthHeart.png");
		heart3 = new Texture("Levels/healthHeart.png");
		instruction = new Texture("Levels/InstructionsLevel4.jpg");
		window = new Texture("MainUi/Window.png");
		pause = new Texture("Levels/pause.png");
		
		heartLocation.x = 120;
		heartLocation.y = 430;
		bulletSpeed = l.getCharacterBulletSpeed();	
		virusBulletSpeed = v.getVirusBulletSpeed5();
		spaceShipLoc.x = 230;
		spaceShipLoc.y = 150;
		virusLoc.x = 40;
		virusLoc.y = 230;
		right = 380;
		left = 100;
		virusTime = 0;
		virusPeriod = 1;
		attackTime = 0;
		attackPeriod = 1.5f;
		lives = 3;
		collidBullets =0;
		
		drawInstructions = true;
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w/2,h/1.5f);
		camera.position.set(camera.viewportWidth , camera.viewportHeight, 0);
		camera.update();
		
		rand = new Random();
		
		sr = new ShapeRenderer();
		
		font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		smallYellow = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		orangeFont = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		mediumRed = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		healthbar = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		smallRed = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		smallGreen = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		
		introTitle = "LEVEL GUIDE";
		IntroText1 = "-Challenge Yourself.";
		IntroText2 = "-Kill the mother virus before it kills you.";
		IntroText3 = "-CONTROLS: ";
		IntroText4 = "*Press SPACE button to shoot.";
		IntroText5 = "*Press Left arrow to go left";
		IntroText6 = "*Press Right arrow to go right";
		IntroText7 = "-The small viruses can't be killed!!";
		IntroText8 = "-GOOD LUCK.";
		IntroText9 = "-Press ENTER to start your fight.";
		congratulationsText = "CONGRATULATIONS!";
		gameOverText="GAME OVER!!!";
        backToCharacter = "C to choose a character";
		backToLevel = "ESC to choose another level";
		restart = "R to restart your game";
		returnToGamePlay = "A to return to your Game";
		press = "Press:";
	}
	
	public void generateRandom( int spaceShipHeight , int collidBullets){
		int r = rand.nextInt(8);
		if(collidBullets>=0 && collidBullets<=70) {
		if(( r==0 ||r==4) && virusLoc.x >= -25) //moving Left
		{
			virusLoc.x -=15;
		}
		else if((r==1 || r==5) && virusLoc.x<right-25) //moving right
		{
			virusLoc.x +=15;
		}
		else if((r==2|| r==6) && (virusLoc.y>spaceShipLoc.y+50))//moving down
		{
			virusLoc.y -=15;
		}
		else if((r==3 || r==7) && (virusLoc.y < spaceShipHeight-15)) //moving up
		{
			virusLoc.y +=15;
		}
	  }
		else if(collidBullets>70 && collidBullets<=90)
    	{
			if(( r==0 ||r==4) && virusLoc.x >= 55) //moving Left
			{
				virusLoc.x -=30;
			}
			else if((r==1 || r==5) && virusLoc.x<right-40) //moving right
			{
				virusLoc.x +=30;
			}
			else if((r==2|| r==6) && (virusLoc.y>spaceShipLoc.y+120))//moving down
			{
				virusLoc.y -=15;
			}
			else if((r==3 || r==7) && (virusLoc.y < spaceShipHeight-30)) //moving up
			{
				virusLoc.y +=30;
			}
    	}
    	else if(collidBullets>90 && collidBullets<100)
    	{
    		if(( r==0 ||r==4) && virusLoc.x >= 80) //moving Left
    		{
    			virusLoc.x -=40;
    		}
    		else if((r==1 || r==5) && virusLoc.x<right-25) //moving right
    		{
    			virusLoc.x +=15;
    		}
    		else if((r==2|| r==6) && (virusLoc.y>spaceShipLoc.y+50))//moving down
    		{
    			virusLoc.y -=15;
    		}
    		else if((r==3 || r==7) && (virusLoc.y < spaceShipHeight-15)) //moving up
    		{
    			virusLoc.y +=15;
    		}
    	}
	}
	 public void update() {
	    	if(Gdx.input.isKeyPressed(Keys.RIGHT) ) {
	    		if(spaceShipLoc.x < right)
	    			spaceShipLoc.x += Level5.getChSpeed(); 
	    	}
	    	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
	    		if(spaceShipLoc.x > left)
	    			spaceShipLoc.x -= Level5.getChSpeed(); 
	    	}
	    	if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
	    		Bullet1 myBullet = new Bullet1(spaceShipLoc, new Vector2(0, bulletSpeed));
	    		bulletManager.add(myBullet);
	    	}
	    	if(Gdx.input.isKeyJustPressed(Keys.R)) {
	    		camera.setToOrtho(false);
		    	game.batch.setProjectionMatrix(camera.combined);
		    	levelMusic.stop();
	    		enteredLevel5 = false;
	    		game.setScreen(new Map (game));
	    	}
	    	if(Gdx.input.isKeyJustPressed(Keys.C)) {
	    		camera.setToOrtho(false);
		    	game.batch.setProjectionMatrix(camera.combined);
		    	levelMusic.stop();
	    		enteredLevel5 = false;
	    		game.setScreen(new CharactersMenu (game));
	    	}
	    	if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	    		camera.setToOrtho(false);
		    	game.batch.setProjectionMatrix(camera.combined);
		    	levelMusic.stop();
	    		enteredLevel5 = false;
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
		update();	
		game.batch.begin();
		
		switch (state){
	    case RUN:{
		game.batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()*20);
		if(drawInstructions==true){
			orangeFont.setColor(Color.ORANGE);
			orangeFont.draw(game.batch, introTitle, 180, 450); 
			healthbar.draw(game.batch, IntroText1, 2, 400, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText2, 2, 360, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText3, 2, 320, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText4, 2, 280, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText5, 2, 240, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText6, 2, 200, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			smallYellow.setColor(Color.YELLOW);
			smallYellow.draw(game.batch, IntroText7, 2, 160, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText8, 2, 120, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			mediumRed.setColor(Color.RED);
			mediumRed.draw(game.batch, IntroText9, 2, 80, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			 if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				 drawInstructions = false;
			 }
		}
		else{
			time += delta;  
			int spaceShipHeight = Gdx.graphics.getHeight();
			virusTime += Gdx.graphics.getRawDeltaTime();
			attackTime += Gdx.graphics.getRawDeltaTime();
			camera.translate(0, 1, 0);
			spaceShipLoc.y++;
			heartLocation.y++;
			virusLoc.y++;
			spaceShipHeight++;
			spaceShipCircle.set(spaceShipLoc.x , spaceShipLoc.y , 20);
			camera.update();
			if(virusTime > virusPeriod) {
			generateRandom(spaceShipHeight , collidBullets);
			virusTime = 0;
			}
			if(attackTime > attackPeriod){
				VirusBullet vb = new VirusBullet(virusLoc , new Vector2(0 , virusBulletSpeed));
				virusBulletManager.add(vb);
			     attackTime =0;
			}
			game.batch.setProjectionMatrix(camera.combined);
	    	if(collidBullets>=0 && collidBullets<=70) {
	    	  game.batch.draw(virus, virusLoc.x, virusLoc.y , virus.getWidth() / 2 , virus.getHeight() / 2);
	    	   virusBossCircle.set( virusLoc.x+220, virusLoc.y+180 , 50);
	    	}
	    	else if(collidBullets>70 && collidBullets<=90)
	    	{
	    		  game.batch.draw(virus, virusLoc.x, virusLoc.y , virus.getWidth() / 2.2f , virus.getHeight() / 2.2f);
	    	    	virusBossCircle.set(virusLoc.x+200, virusLoc.y+150 ,40);
	    	}
	    	else if(collidBullets>90 && collidBullets<100)
	    	{
	    		game.batch.draw(virus, virusLoc.x, virusLoc.y , virus.getWidth() / 2.5f, virus.getHeight() / 2.5f);
		    	virusBossCircle.set( virusLoc.x+170, virusLoc.y+100 , 30);
	    	}
	    	else if(collidBullets>=100) 
			{
	    		font.setColor(Color.BLUE);
	    		/*gameOverFont.draw(game.batch, congratulationsText, 230 ,spaceShipLoc.y +300);
	    		smallGreen.draw(game.batch, "Press:", 160, spaceShipLoc.y+230);
	    		smallGreen.setColor(Color.MAROON);
	    		smallGreen.getData().scaleX = 0.5f;
	    		smallGreen.draw(game.batch, backToCharacter, 170, spaceShipLoc.y+200); 
	    		smallGreen.draw(game.batch, backToLevel, 170, spaceShipLoc.y+150);
	    		smallGreen.draw(game.batch, restart, 170, spaceShipLoc.y+100);*/
	    		count++;
				if(count == 1) {
					virusExplosion.add(new SpaceShipExplosion(virusLoc.x+170, virusLoc.y+100));
					boom.play();
				}
	    		font.draw(game.batch, congratulationsText, 210, spaceShipLoc.y+300);
	    		smallGreen.setColor(Color.WHITE);
	    		smallGreen.getData().scaleX = 0.6f;
	    		smallGreen.draw(game.batch, "Galaxy is now free off viruses!", 162, spaceShipLoc.y+200); 

	    		smallGreen.setColor(Color.RED);
	    		smallGreen.getData().scaleX = 0.5f;
	    		
	    		smallGreen.draw(game.batch, "Press:", 162, spaceShipLoc.y+170); 
	    		smallGreen.draw(game.batch, backToCharacter, 163, spaceShipLoc.y+150); 
	    		smallGreen.draw(game.batch, backToLevel, 163, spaceShipLoc.y+130);
	    		smallGreen.draw(game.batch, restart, 163, spaceShipLoc.y+110);
	    		smallGreen.getData().scaleX = 1;
	    		
	    		countDataBase++;
	    		VirusFighter.played = 5;
            	/*if(countDataBase == 1) {
            		try {
						databaseClass.database();
					} catch (ClassNotFoundException e) {
						e.getStackTrace();
					}
            	}*/
		    }
	    	if(lives==3)
	    	{
	    	game.batch.draw(spaceShip, spaceShipLoc.x, spaceShipLoc.y, spaceShip.getWidth() / 7, spaceShip.getHeight() / 7);
	    	game.batch.draw(heart1 , heartLocation.x, heartLocation.y, heart1.getWidth()/9  , heart1.getHeight()/9);
	    	game.batch.draw(heart2 , heartLocation.x+30, heartLocation.y, heart1.getWidth()/9  , heart1.getHeight()/9);
	    	game.batch.draw(heart3 , heartLocation.x+60, heartLocation.y, heart1.getWidth()/9  , heart1.getHeight()/9);
	    	}
	    	else if(lives==2)
	    	{
	    	game.batch.draw(spaceShip, spaceShipLoc.x, spaceShipLoc.y, spaceShip.getWidth() / 7, spaceShip.getHeight() / 7);
	    	game.batch.draw(heart1 , heartLocation.x, heartLocation.y, heart1.getWidth()/9  , heart1.getHeight()/9);
	    	game.batch.draw(heart2 , heartLocation.x+30, heartLocation.y, heart1.getWidth()/9  , heart1.getHeight()/9);
	    	}
	    	else if(lives==1)
	    	{
	    	game.batch.draw(spaceShip, spaceShipLoc.x, spaceShipLoc.y, spaceShip.getWidth() / 7, spaceShip.getHeight() / 7);
	    	game.batch.draw(heart1 , heartLocation.x, heartLocation.y, heart1.getWidth()/9  , heart1.getHeight()/9);
	    	}
	    	else 
	    	{
	    		smallRed.setColor(Color.RED);
	    		smallRed.draw(game.batch, gameOverText, 250, spaceShipLoc.y+300);
	    		smallGreen.setColor(Color.WHITE);
	    		smallGreen.getData().scaleX = 0.6f;
	    		smallGreen.draw(game.batch, "Heart is still infected!", 162, spaceShipLoc.y+200); 
	    		smallGreen.setColor(Color.GREEN);
	    		smallGreen.getData().scaleX = 0.7f;
	    		smallGreen.draw(game.batch, "Press:", 162, spaceShipLoc.y+170); 
	    		smallGreen.draw(game.batch, backToCharacter, 163, spaceShipLoc.y+150); 
	    		smallGreen.draw(game.batch, backToLevel, 163, spaceShipLoc.y+130);
	    		smallGreen.draw(game.batch, restart, 163, spaceShipLoc.y+110);
	    		smallGreen.getData().scaleX = 1;
	    		count++;
				if(count == 1) {
					virusExplosion.add(new SpaceShipExplosion(spaceShipLoc.x, spaceShipLoc.y));
					boom.play();
				}
	    	}
	    	try {
	    		int counter = 0;
				circleBullet.clear();
				while(counter < bulletManager.size()) {
		    		Bullet1 currentBullet = bulletManager.get(counter);
		    		currentBullet.update();
		    		if(currentBullet.bulletLocation.x > 0 || currentBullet.bulletLocation.x < Gdx.graphics.getWidth() || currentBullet.bulletLocation.y > 0 || currentBullet.bulletLocation.y < Gdx.graphics.getHeight()) {
		    			game.batch.draw(bullet, currentBullet.bulletLocation.x+10, currentBullet.bulletLocation.y+20, bullet.getWidth() / 8, bullet.getHeight() / 8);
		    			circleBullet.add(new Circle(currentBullet.bulletLocation.x+36.7f ,currentBullet.bulletLocation.y , 1));
		    			if(Intersector.overlaps(virusBossCircle, circleBullet.get(counter))) //why circleVirus is an array list
		   		        {
		    				circleBullet.remove(0);
		    				bulletManager.remove(counter);
		    				collidBullets++;
		   		        }	
		    		}
		    		else {
		    			bulletManager.remove(counter);
		    			if(bulletManager.size() > 0) {
		        			counter--;
		    			}
		    		}
		    		counter++;
		    	}
	    	}
	    	catch(Exception e) {
	    		e.getStackTrace();
	    	}
	    	
	    	int virusbulletCounter = 0;
	    	circleVirus.clear();
	    	while(virusbulletCounter < virusBulletManager.size()) {
	    		VirusBullet currentBullet = virusBulletManager.get(virusbulletCounter);
	    		currentBullet.update();
	    		if(currentBullet.virusLocation.x > 0 || currentBullet.virusLocation.x < Gdx.graphics.getWidth() || currentBullet.virusLocation.y > 0 || currentBullet.virusLocation.y < Gdx.graphics.getHeight()) {
	    			if(collidBullets>=0 && collidBullets<=70) {
	    			game.batch.draw(virusBulletTexture, currentBullet.virusLocation.x+200, currentBullet.virusLocation.y+70, virusBulletTexture.getWidth() / 7 , virusBulletTexture.getHeight() / 7);
	    			circleVirus.add(new Circle(currentBullet.virusLocation.x+200 ,currentBullet.virusLocation.y+70 , 1));
	    			if(Intersector.overlaps(spaceShipCircle, circleVirus.get(virusbulletCounter))&& lives>=1) //why circleVirus is an array list
	   		        {
	   			      circleVirus.remove(0);
	   			      virusBulletManager.remove(virusbulletCounter);
	   			      lives--;
	   			      break;
	   		         }	
	    			}
	    			else if(collidBullets>70 && collidBullets<=90) {
	    				game.batch.draw(virusBulletTexture, currentBullet.virusLocation.x+150, currentBullet.virusLocation.y+60, virusBulletTexture.getWidth() / 7 , virusBulletTexture.getHeight() / 7);
	    				circleVirus.add(new Circle(currentBullet.virusLocation.x+150 ,currentBullet.virusLocation.y+60 , 1));
	    				if(Intersector.overlaps(spaceShipCircle, circleVirus.get(virusbulletCounter))&& lives>=1) //why circleVirus is an array list
	       		        {
	       			      circleVirus.remove(0);
	       			      virusBulletManager.remove(virusbulletCounter);
	       			      lives--;
	       			      break;
	       		     }	
	        		}
	    			else if(collidBullets>90 && collidBullets<100){
	    				game.batch.draw(virusBulletTexture, currentBullet.virusLocation.x+130, currentBullet.virusLocation.y+50, virusBulletTexture.getWidth() / 7 , virusBulletTexture.getHeight() / 7);
	    				circleVirus.add(new Circle(currentBullet.virusLocation.x+130 ,currentBullet.virusLocation.y+50 , 1));
	    				if(Intersector.overlaps(spaceShipCircle, circleVirus.get(virusbulletCounter))&& lives>=1) //why circleVirus is an array list
	       		        {
	       			      circleVirus.remove(0);
	       			      virusBulletManager.remove(virusbulletCounter);
	       			      lives--;
	       			      break;
	       		     }	
	        		}
	    		}
	    		else {
	    			virusBulletManager.remove(virusbulletCounter);
	    			if(virusBulletManager.size() > 0) {
	    				virusbulletCounter--;
	    			}
	    		}
	    		virusbulletCounter++;
	    	}
	    	posY++;
		}
        break;
	}
    case PAUSE:{
    	game.batch.draw(window, 240, spaceShipLoc.y+30, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/1.8f);
    	
    	game.batch.draw(pause, 270, spaceShipLoc.y+270, pause.getWidth()/3,pause.getHeight()/5);
    	
    	font.getData().scaleX = 0.4f;
    	font.draw(game.batch, press, 255, spaceShipLoc.y+240);
    	font.draw(game.batch, returnToGamePlay, 255, spaceShipLoc.y+200);
    	font.draw(game.batch, backToCharacter, 255, spaceShipLoc.y+160); 
    	font.draw(game.batch, backToLevel, 255, spaceShipLoc.y+120);
    	font.draw(game.batch, restart, 255, spaceShipLoc.y+80);
    	font.getData().scaleX = 1;
    	
    	game.batch.setProjectionMatrix(camera.combined);
    	camera.translate(0, 0, 0);
		camera.update();
        break;
    }
    default:
        break;
    }
		for(SpaceShipExplosion firework: virusExplosion) {
			firework.render(game.batch);
		}
		ArrayList<SpaceShipExplosion>fireworkRemove = new ArrayList<SpaceShipExplosion>();
		for(SpaceShipExplosion firework : virusExplosion) {
			firework.update(delta);
			if(firework.remove)
				fireworkRemove.add(firework);
		}
		virusExplosion.removeAll(fireworkRemove);
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
