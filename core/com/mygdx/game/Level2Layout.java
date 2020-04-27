package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import gameData.Asteroid;
import gameData.Bullet;
import gameData.CollisionRect;
import gameData.Level1;
import gameData.Level2Explosion;
import gameData.Level2;
import gameData.Virus;
import gameData.VirusLevel2;
import gameData.databaseClass;

public class Level2Layout extends ScreenAdapter{
	public static final int FINAL_SPEED = 800; 
	public static final int GOAL_REACH_ACCELERATION = 55;
	public static final float min_virus_time=0.3f;
	public static final float  max_virus_time=0.6f;
	public static final float  max_asteroid_time=0.6f;
	public static final float  min_asteroid_time=0.3f;
	
	boolean freez;	
	
	int rightWidth, leftWidth;
	
    String IntroText1, IntroText2, IntroText3, IntroText4, IntroText5, IntroText6, IntroText7, IntroText8,
    IntroText9, IntroText10, IntroText11, IntroText12, IntroText13, IntroText14, IntroText15, IntroText16;
    
    BitmapFont introText, smallyellow, orangeFont, gameOver, congratesGreen, gameOverRed, MediumRed, 
    scoreFont,scoreFont2, NumOfviruses, scoreWordFont, MediumGreen, healthbar;

    String introTitle, backToCharachter, backToLevel, backToMenu, restart, gameSummry, asteroidHits,
    VirusHits, gameOverText, healthbartext, scoreText, numberOfKilledViruses, NextLevel, noNextLevel, contgratulationsText;
	
	int score, scoreCounter, asteroidCounter, virusCounter;
	
	//for background
	float y1, y2;
	int countDataBase = 0;
	int speed;//In pixels / second
	int goalSpeed;
	
	boolean speedFixed;
	public static boolean enteredLevel2 = false;
	
	float virus_timer;
	float asteroid_timer;
	
	Random random;
	
	CollisionRect playerRect;
	
	SpriteBatch batch;
	
	Texture bullet, spaceShip, virus, background,asteroid;
	
	Level2 l;
	Virus v;
	
	Vector2 spaceShipLoc = new Vector2();
	
	ArrayList<Asteroid> asteroidManager ;
	ArrayList<Bullet> bulletManager ;
	ArrayList<VirusLevel2> virusManager;
	ArrayList<Level2Explosion> explosions;

	int bulletSpeed;
	
	final VirusFighter game;
	
	Texture image, blank;
    
	float health = 1; //1 ya3ni full health 0 ya3ni dead
	
	Music levelMusic;

	Level2Layout(final VirusFighter game){
		bulletManager = new ArrayList<Bullet>();
		virusManager = new ArrayList<VirusLevel2>();
		asteroidManager = new ArrayList<Asteroid>();
		explosions = new ArrayList<Level2Explosion>();
		freez = false;
		this.game = game;		
		batch = new SpriteBatch();
		image = new Texture("Backgrounds/Background4.png");
		smallyellow = new BitmapFont (Gdx.files.internal("fonts/myfont9yellowsmall.fnt") );
		orangeFont = new BitmapFont (Gdx.files.internal("fonts/myfont7orangeMedium.fnt") );
		MediumGreen = new BitmapFont (Gdx.files.internal("fonts/myfont6greenMedium.fnt") );
		congratesGreen = new BitmapFont (Gdx.files.internal("fonts/myfont6green.fnt") );
		scoreFont = new BitmapFont (Gdx.files.internal("fonts/myfont.fnt") );
		scoreFont2 = new BitmapFont (Gdx.files.internal("fonts/myfont2.fnt") );
		scoreWordFont = new BitmapFont (Gdx.files.internal("fonts/myfont2.fnt") );
		NumOfviruses = new BitmapFont (Gdx.files.internal("fonts/myfont2.fnt") );
		MediumRed = new BitmapFont (Gdx.files.internal("fonts/myfont5red.fnt") );
		
		introTitle = "LEVEL GUIDE";
		
		IntroText1 = "-Challenge Yourself.";
		IntroText2 = "-Kill the virus before it kills you.";
		IntroText3 = "-Destroy virus to get 100 points.";
		IntroText4 = "-You lose 200 Points if the virus hits you.";
		IntroText5 = "-You lose 500 Points if the asteroids hits you.";
		IntroText6 = "-To pass to the next level your score must exceed 1000 points.";
	    IntroText7 = "*Press UP button to shoot.";
		IntroText8 = "*To avoid virus and asteroids hits:";
		IntroText9 = " *prees W to go up";
		IntroText10 = " *Press S to go down";
		IntroText11 = " *press A to go left";
		IntroText12 = " *press D to go right";
		IntroText13 = "-DON'T FORGET TO WATCH YOUR HEALTH BAR!!";
		IntroText14 = "-GOOD LUCK.";
		IntroText15 = " -Press ENTER to start your fight. ";
		IntroText16 = "-CONTROLS: ";
		
		backToMenu = "Press M to go back to main menu";
		backToCharachter = "Press c to choose another character";
		backToLevel = "Pess ESC to choose another level";
		restart = "Press R to restart your game";
		gameSummry = "YOUR GAME SUMMARY";
		asteroidHits = "Asteroid Hits:  ";
		VirusHits = "Virus Hits: ";
		numberOfKilledViruses = "Viruses Destroyed:  ";
        scoreText = "Your Score: ";
        contgratulationsText = "CONGRATULATIONS!";
        NextLevel = "YOU PASSED TO THE NEXT LEVEL";
        noNextLevel = "YOU DIDN'T REACH THE REQUIRED SCORE";
		score = 0;
		rightWidth = 490;
		leftWidth =- 75;
	    scoreCounter = 0;
	    virusCounter = 0;
	    asteroidCounter = 0;
        healthbar = new BitmapFont (Gdx.files.internal("fonts/myfont.fnt"));
        healthbartext = " Health Bar";
        gameOver = new BitmapFont (Gdx.files.internal("fonts/myfont3.fnt"));
        gameOverRed = new BitmapFont (Gdx.files.internal("fonts/myfont4Red.fnt"));


        gameOverText = "GAME OVER!!!";
        
		l = new Level2();
		v = new Virus();
		
		levelMusic = Gdx.audio.newMusic(Gdx.files.internal(l.getMusic()));
		enteredLevel2 = true;
		if(MainMenu.disable == false) {

		levelMusic.play();
		}
		background = new Texture(l.getBackground());
		spaceShip = new Texture(l.getCharacter());
		
		blank = new Texture("Levels/blank.png");
		asteroid = new Texture(Gdx.files.internal(v.getAsteroid()));
		virus = new Texture(Gdx.files.internal(v.getVirus2()));
		bullet = new Texture(l.getCharacterBullet());
		bulletSpeed = l.getCharacterBulletSpeed();
		random = new Random();
		virus_timer = random.nextFloat()*(max_virus_time-min_virus_time)+min_virus_time;
		spaceShipLoc.x = 130;
		spaceShipLoc.y = -30;
		playerRect = new CollisionRect (spaceShipLoc.x,spaceShipLoc.y,spaceShip.getWidth()/200,spaceShip.getHeight()/100);

		y1 = 0;
		y2 = background.getHeight();
		speed = 0;
		goalSpeed = FINAL_SPEED;
		speedFixed = true;
	}
	
	 public void update(float delta) {
		 
		 //////////////////////////////////////////////////////////////
		 
	    	if(Gdx.input.isKeyPressed(Keys.W) && (spaceShipLoc.y < Gdx.graphics.getHeight() - spaceShip.getHeight() / 5)) {
	    		spaceShipLoc.y += Level2.getChSpeed();
	    	}
	    	if(Gdx.input.isKeyPressed(Keys.S) && (spaceShipLoc.y > 0)) {
	    		spaceShipLoc.y -= Level2.getChSpeed();
	    	}
	    	if(Gdx.input.isKeyPressed(Keys.D) ) {
	    		if (spaceShipLoc.x <rightWidth){
	    		spaceShipLoc.x += Level2.getChSpeed(); 
	    		}
	    	}
	    	if(Gdx.input.isKeyPressed(Keys.A) ) {
	    		if(spaceShipLoc.x > leftWidth) {
	    		spaceShipLoc.x -= Level2.getChSpeed(); 
	    		}
	    	}
	    	if(health>0) {
	    	if(Gdx.input.isKeyJustPressed(Keys.UP)) {
	    		Bullet myBullet = new Bullet(spaceShipLoc, new Vector2(0, bulletSpeed),bullet);
	    		bulletManager.add(myBullet);
	    	}
	    	}
	    	if (health<=0) {
		    	if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
		    		enteredLevel2 = false;
		    		levelMusic.stop();
		    		game.setScreen(new LevelsMenu (game));
		    	}
		    	if(Gdx.input.isKeyJustPressed(Keys.R)) {
		    		enteredLevel2 = false;
		    		levelMusic.stop();
		    		game.setScreen(new Map (game));
		    	}
		    	if(Gdx.input.isKeyJustPressed(Keys.C)) {
		    		enteredLevel2 = false;
		    		levelMusic.stop();
		    		game.setScreen(new CharactersMenu (game));
		    	}
	    	}
	    	/*if(Gdx.input.isKeyJustPressed(Keys.M)) {
	    		game.setScreen(new MainMenu (game));
	    	}*/
	    	/*if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
	    		Bullet myBullet = new Bullet(spaceShipLoc, new Vector2(0, -bulletSpeed));
	    		bulletManager.add(myBullet);
	    	}
	    	if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
	    		Bullet myBullet = new Bullet(spaceShipLoc, new Vector2(bulletSpeed, 0));
	    		bulletManager.add(myBullet);
	    	}
	    	if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
	    		Bullet myBullet = new Bullet(spaceShipLoc, new Vector2(-bulletSpeed, 0));
	    		bulletManager.add(myBullet);
	    	}*/
	    	
	    	/////////////////////////////////
	    		    	
	    	
	    	////////////////////////////////
	    	//speed adjustment
	    	if (speed < goalSpeed) {
				speed += GOAL_REACH_ACCELERATION * delta;
				//Asteroid.SPEED+= (GOAL_REACH_ACCELERATION) * delta;
				if (speed > goalSpeed)
					speed = goalSpeed;
			} else if (speed > goalSpeed) {
				speed -= GOAL_REACH_ACCELERATION * delta;
				//Asteroid.SPEED+= (GOAL_REACH_ACCELERATION) * delta;
				if (speed < goalSpeed)
					speed = goalSpeed;
			}
			/////
		//	if (!speedFixed)
			//	speed += ACCELERATION * delta;
			
			y1 -= speed * delta;
			y2 -= speed * delta;
			
			//if image reached the bottom of screen and is not visible put it back on top
			if (y1 + background.getHeight() <= 0)
				y1 = y2 + background.getHeight() ;
			
			if (y2 + background.getHeight() <= 0)
				y2 = y1 + background.getHeight() ;
			//////////////////////////////////////////////////////////////////////////////
			
			
		 	asteroid_timer-=delta;
	    	if(asteroid_timer<=-1f) {		
	    		asteroid_timer=random.nextFloat()*(max_asteroid_time-min_asteroid_time)+min_asteroid_time;  //b3mlo reset
	    		asteroidManager.add(new Asteroid(random.nextInt(background.getWidth())));  // limit el virus mn el gamb w 3ashan kda el constructor bya5od x bas
	    	}
	    	
	    	if(asteroid_timer<=-0.8 && score > 2000) {		
	    		
	    		asteroid_timer=random.nextFloat()*(max_asteroid_time-min_asteroid_time)+min_asteroid_time;  //b3mlo reset
	    		asteroidManager.add(new Asteroid(random.nextInt(background.getWidth())));  // limit el virus mn el gamb w 3ashan kda el constructor bya5od x bas
	    	}
	    	
			
			
			/////////////////////////////////////////////////////// da ely byzl el virus 
			// virus spawn
	    	virus_timer-=delta;
	    	if(virus_timer<=0.1) {		
	    		virus_timer=random.nextFloat()*(max_virus_time-min_virus_time)+min_virus_time;  //b3mlo reset
	    		virusManager.add(new VirusLevel2(random.nextInt(background.getWidth())));  // limit el virus mn el gamb w 3ashan kda el constructor bya5od x bas
	    	}
	    	
	    	if(virus_timer<=0.3 && score > 2000) {		
	    		
	    		virus_timer=random.nextFloat()*(max_virus_time-min_virus_time)+min_virus_time;  //b3mlo reset
	    		virusManager.add(new VirusLevel2(random.nextInt(background.getWidth())));  // limit el virus mn el gamb w 3ashan kda el constructor bya5od x bas
	    	}
	    	/////////////////////////////////////////////////////////////////////////////////////////
	    	
	    	
	    	
	    
	    	
	    }
	 
	@Override
	public void render (float delta) {
		if(Gdx.input.isKeyPressed(Keys.ENTER) ) {
    		freez=true;
    		}
		
		game.batch.begin();
		

		if(freez == true) {
		update(delta);
		
		
		GlyphLayout scoreLayout =new GlyphLayout (scoreFont,""+score);


		game.batch.draw(background, 0, y1,  Gdx.graphics.getWidth(),background.getHeight());
		
		game.batch.draw(background, 0, y2,  Gdx.graphics.getWidth(), background.getHeight());
		

        	//game.batch.draw(virus, 130, 300, virus.getWidth() / 6, virus.getHeight() / 6);
    	   /////////////////////////////////////////////////////////
    		
		//healthbar.draw(game.batch, IntroText, 2, 300, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);

    	///////////////////////////////////////////////////////////
		ArrayList<Bullet> bulletRemove= new ArrayList<Bullet>();
		boolean Remove=false;
		
		////
    	int counter = 0;
    	while(counter < bulletManager.size()) {
    		Bullet currentBullet = bulletManager.get(counter);
    		currentBullet.update();
    		if(  currentBullet.bulletLocation.y > 0 || currentBullet.bulletLocation.y < Gdx.graphics.getHeight()) {
    			game.batch.draw(bullet, currentBullet.bulletLocation.x+25, currentBullet.bulletLocation.y, bullet.getWidth() / 7, bullet.getHeight() / 7);
    			//game.batch.draw(bullet, currentBullet.bulletLocation.x+40, currentBullet.bulletLocation.y, bullet.getWidth() / 7, bullet.getHeight() / 7);

    		}
    		else {
    			Remove= true;

    	    	if(Remove= true)
    	    	bulletRemove.add(currentBullet);  ///magdy
    	    	
    			bulletManager.remove(counter);
    			
    			if(bulletManager.size() > 0) {
        			counter--;
    			}
    		}
    		counter++;
    	}
    	// update explosion
    	ArrayList<Level2Explosion>explosionRemove =new ArrayList<Level2Explosion>();
    	for(Level2Explosion explosion : explosions) {
    		
    		explosion.update(delta);
    		if (explosion.remove) {
    			
    			explosionRemove.add(explosion);
    		}
    	}
    	
    	explosions.removeAll(explosionRemove);
    	
    	//
    
    	
    	
      	//virus update
    	ArrayList<VirusLevel2> virusRemove= new ArrayList<VirusLevel2>();
    	
    	for(VirusLevel2 virus : virusManager) {
    		
    		virus.render(game.batch);
    	    		
    	    	}
    	
    	
    	ArrayList<Asteroid> asteroidRemove= new ArrayList<Asteroid>();

    	
    	for(Asteroid asteroid : asteroidManager) {
    		asteroid.render(game.batch);
    	}
        	
    	for(Level2Explosion explosion : explosions) {
    		explosion.render(game.batch);
    	}
    	
    	for(Asteroid asteroid : asteroidManager) {
    		asteroid.update(delta);	
        	if(asteroid.remove)
        		asteroidRemove.add(asteroid);
    	}
    	
    	for(VirusLevel2 virus : virusManager) {
    		virus.update(delta);	
        	if(virus.remove)
        		virusRemove.add(virus);
    	}
    	
    	//virusManager.removeAll(virusRemove);

		////////////////////////////////////////////////  // checking for collisions 
    	
    	for(Bullet bullet : bulletManager) {  // for each bullet in bullets list
    		bullet.update();
    		
    		for(Asteroid asteroid :asteroidManager) {  //for each virus in the array list
    			if (bullet.getCollisionRect().collidesWith(asteroid.getCollisionRect())) {  //collision happend
    				//asteroidRemove.add(asteroid);
    			
    				bulletRemove.add(bullet);
    				//explosions.add(new Explosion(asteroid.getx(),asteroid.gety()));
    				//score +=100;
    			}
    		}
  		}
    	
		////////////////////////////////////////////////////////////////////////////////////////
    	for(Bullet bullet : bulletManager) {  // for each bullet in bullets list
    		bullet.update();
    		
    		for(VirusLevel2 virus :virusManager) {  //for each virus in the array list
    			if (bullet.getCollisionRect().collidesWith(virus.getCollisionRect())) {  //collision happend
    				virusRemove.add(virus);
    				bulletRemove.add(bullet);
    				explosions.add(new Level2Explosion(virus.getx(),virus.gety(),30));
    				scoreCounter+=1;
    				score +=100;
    			}
    		}
  		}
    	//virusManager.removeAll(virusRemove);

    	//bulletManager.removeAll(bulletRemove);
    	

    	//for health bar
    	playerRect.move(spaceShipLoc.x+103, spaceShipLoc.y+60);

    	if (health>0.0f) {
			game.batch.draw(spaceShip, spaceShipLoc.x, spaceShipLoc.y, spaceShip.getWidth() / 5, spaceShip.getHeight() / 5);

			scoreFont.draw(game.batch, scoreLayout,Gdx.graphics.getWidth()/2-scoreLayout.width/2,Gdx.graphics.getHeight()-scoreLayout.height-1);

			 if (health>0.6f) {
				 game.batch.setColor(Color.FOREST);
			 }

			 else if(health>0.4f) {
				 game.batch.setColor(Color.ORANGE);
			 }
			 else {
				 game.batch.setColor(Color.RED);
			 }
			 
			 game.batch.draw(blank,0,473.5f,Gdx.graphics.getWidth() *health,70);
		     game.batch.setColor(Color.WHITE);
		     healthbar.draw(game.batch, healthbartext,0,468);
    	
		for(VirusLevel2 virus : virusManager) {
			if(virus.getCollisionRect().collidesWith(playerRect)) {
				virusRemove.add(virus);
				virusCounter+=1;
				score-=200;
				health-=0.1;
				if(health<=0) {
					explosions.add(new Level2Explosion(virus.getx(),virus.gety(),70));
				}
			}
		}
	
		for(Asteroid asteroid : asteroidManager) {
			if(asteroid.getCollisionRect().collidesWith(playerRect)) {
				asteroidRemove.add(asteroid);
				asteroidCounter+=1;
				score-=500;
				health-=0.3;
				if(health<=0) {
					explosions.add(new Level2Explosion(spaceShipLoc.x,spaceShipLoc.y,70));
				}
			}
		}
		
    	}
    	else {  //game over part
    		if(score>=1000)
    		{
    			MediumGreen.draw(game.batch, NextLevel,80, 365);//you passed to the next level
            	congratesGreen.draw(game.batch, contgratulationsText,120,440); // klemet congratulations
            	countDataBase++;
				VirusFighter.played = 2;
            	if(countDataBase == 1) {
            		try {
						databaseClass.database();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
					}
            	}
            	if(Gdx.input.isKeyPressed(Keys.ALT_LEFT))
            	{
			    	levelMusic.stop();
		    		enteredLevel2 = false;
	                LevelsMenu.tag = LevelsMenu.tag+1;
	                game.setScreen(new Map(game));
            	}
    		}
    		else {
    			MediumRed.draw(game.batch, noNextLevel,7, 365); //you didnt pass to the next level
            	gameOverRed.draw(game.batch, gameOverText,135,440); // klemet game over
    		}
    		freez=true;
    		GlyphLayout scoreLayout2 =new GlyphLayout (scoreFont2,""+score); //da le rakm el score zat nafso
    		GlyphLayout numberOFVirusesLayout =new GlyphLayout (NumOfviruses,""+scoreCounter); //3dd el viruses
    		GlyphLayout numberOFasteroidHits =new GlyphLayout (NumOfviruses,""+asteroidCounter); //3dd el asteroid 
    		GlyphLayout numberOfVirusHits =new GlyphLayout (NumOfviruses,""+virusCounter); //3dd el asteroid 

    		gameOver.draw(game.batch, gameSummry,110,280); //3ashan nafs el font bta3 gameOver
        	scoreWordFont.draw(game.batch, scoreText,150, 225); //kelmt your score
			scoreFont2.draw(game.batch, scoreLayout2,355,225); //el score nafso
			NumOfviruses.draw(game.batch,numberOfKilledViruses,150,195);	//kelmt no.of virus
        	NumOfviruses.draw(game.batch, numberOFVirusesLayout, 473,195);// el number nafso
			NumOfviruses.draw(game.batch,asteroidHits,150,165);	//kelmt asteroid hits
        	NumOfviruses.draw(game.batch, numberOFasteroidHits, 403,165);// el number nafso
			NumOfviruses.draw(game.batch,VirusHits,150,135);	//kelmt virus hits
        	NumOfviruses.draw(game.batch, numberOfVirusHits, 350,135);// el number nafso
        	scoreFont.draw(game.batch, backToCharachter,127, 80); 
        	scoreFont.draw(game.batch, backToLevel,127, 55);
         	scoreFont.draw(game.batch, restart,127, 30); 
         //	scoreFont.draw(game.batch, backToMenu,130, 5);
    	}
    	bulletManager.removeAll(bulletRemove);

    	virusManager.removeAll(virusRemove);
    	asteroidManager.removeAll(asteroidRemove);

//////////////////////////////////////////////////////////////////////////////////////
	
   ////////////////////////////////////////////////////////////////////////////////////////
		}
		else
		{
			game.batch.draw(background, 0, y1,  Gdx.graphics.getWidth(),background.getHeight());		
			game.batch.draw(background, 0, y2,  Gdx.graphics.getWidth(), background.getHeight());
			orangeFont.draw(game.batch,introTitle ,180, 450); 
			healthbar.draw(game.batch, IntroText1, 2, 400, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText2, 2, 380, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText3, 2, 360, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText4, 2, 340, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText5, 2, 320, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText6, 2, 300, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText16, 2, 260, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);

			healthbar.draw(game.batch, IntroText7, 2, 240, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText8, 2, 220, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText9, 2, 200, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText10, 2, 180, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText11, 2, 160, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText12, 2, 140, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			smallyellow.draw(game.batch, IntroText13, 2, 120, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText14, 2, 100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
			healthbar.draw(game.batch, IntroText15, 2, 80, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-200, true);
		}
		game.batch.end();
		}
	
	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void show () {
	}

	@Override
	public void hide () {
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}