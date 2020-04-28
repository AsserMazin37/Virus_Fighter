

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
public class Level5Layout extends ScreenAdapter{
	BitmapFont gameOverFont, smallGreen;
	String gameOvertext, backToCharacter, backToLevel, restart, congratulationsText;
	SpriteBatch batch;
	ShapeRenderer sr;
	Music levelMusic;
	Texture bullet, spaceShip, virus, background , virusBulletTexture , heart1 , heart2 , heart3 ,  instruction;	
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
	Circle spaceShipCircle = new Circle(); 
	Circle virusBossCircle = new Circle();
	int bulletSpeed , virusBulletSpeed;
	OrthographicCamera camera;
	final VirusFighter game;
	int posY = 300;
	int right , left;
	float virusTime , virusPeriod , attackTime , attackPeriod;
	Random rand;
	int lives , collidBullets; //how many bullets attacked boss virus till death
	float time; 
	boolean drawInstructions ;
	static boolean enteredLevel5;
	int countDataBase = 0;
	BitmapFont font, introText, smallYellow, orangeFont, mediumRed, healthbar, smallRed;
	String introTitle, IntroText1, IntroText2, IntroText3, IntroText4, IntroText5, IntroText6,
	IntroText7, IntroText8, IntroText9;
	Level5Layout(final VirusFighter game){
		gameOverFont = new BitmapFont (Gdx.files.internal("fonts/myfont.fnt"));
		smallGreen = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		congratulationsText = "CONGRATULATIONS!";
		gameOvertext="GAME OVER!!!";
        backToCharacter = "C to choose another character";
		backToLevel = "ESC to choose another level";
		restart = "R to restart your game";
		this.game = game;		
		batch = new SpriteBatch();	
		l = new Level5();
		v = new Virus();
		enteredLevel5 = true;
		levelMusic = Gdx.audio.newMusic(Gdx.files.internal(l.getMusic()));
		if(MainMenu.disable == false) {

		levelMusic.play();
		}
		background = new Texture(l.getBackground());
		spaceShip = new Texture(l.getCharacter());
		virus = new Texture(v.getVirus5());
		bullet = new Texture(l.getCharacterBullet());
		virusBulletTexture = new Texture(v.getVirusBullets5());
		heart1 = new Texture("Levels/healthHeart.png");
		heart2 = new Texture("Levels/healthHeart.png");
		heart3 = new Texture("Levels/healthHeart.png");
		instruction = new Texture("Levels/InstructionsLevel4.jpg");
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
		drawInstructions = true;
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w/2,h/1.5f);
		camera.position.set(camera.viewportWidth , camera.viewportHeight, 0);
		camera.update();
		rand = new Random();
		sr = new ShapeRenderer();
		lives = 3;
		collidBullets =0;
		font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		smallYellow = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		smallYellow.setColor(Color.YELLOW);
		orangeFont = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		orangeFont.setColor(Color.ORANGE);
		mediumRed = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		mediumRed.setColor(Color.RED);
		healthbar = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		smallRed = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		smallRed.setColor(Color.RED);
		smallGreen = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		smallGreen.setColor(Color.GREEN);
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
	    }
	@Override
	public void render (float delta) {
		update();	
		game.batch.begin();	
		game.batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()*20);
		if(drawInstructions==true)
		{
			
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
			 if(Gdx.input.isKeyJustPressed(Keys.ENTER))
			 {
				 drawInstructions = false;
			 }
		}
		else
		{
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
	    		gameOverFont.setColor(Color.GREEN);
	    		gameOverFont.draw(game.batch, congratulationsText, 230 ,spaceShipLoc.y +300);
	    		smallGreen.draw(game.batch, "Press:", 160, spaceShipLoc.y+230);
	    		smallGreen.setColor(Color.MAROON);
	    		smallGreen.getData().scaleX = 0.5f;
	    		smallGreen.draw(game.batch, backToCharacter, 170, spaceShipLoc.y+200); 
	    		smallGreen.draw(game.batch, backToLevel, 170, spaceShipLoc.y+150);
	    		smallGreen.draw(game.batch, restart, 170, spaceShipLoc.y+100);
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
	    		gameOverFont.setColor(Color.RED);
	    		gameOverFont.draw(game.batch, gameOvertext,230 ,spaceShipLoc.y +300);
	    		smallGreen.draw(game.batch, "Press:", 165, spaceShipLoc.y+230);
	    		smallGreen.setColor(Color.ORANGE);
	    		smallGreen.getData().scaleX = 0.5f;
	    		smallGreen.draw(game.batch, backToCharacter, 170, spaceShipLoc.y+200); 
	    		smallGreen.draw(game.batch, backToLevel, 170, spaceShipLoc.y+150);
	    		smallGreen.draw(game.batch, restart, 170, spaceShipLoc.y+100);
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
		game.batch.end();
	}
	@Override
	public void dispose () {
	}
}
