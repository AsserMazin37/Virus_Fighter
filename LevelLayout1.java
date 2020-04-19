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
	
import gameData.Bullet1;
import gameData.Explosion1;
import gameData.SpaceShipExplosion;
import gameData.Virus;
import gameData.Level1;
	
	public class LevelLayout1 extends ScreenAdapter{
	
		SpriteBatch batch;
		Texture bullet, spaceShip, virus, background,health;	
		Texture star1,star2;
		Level1 l;	
		Virus v;
		Vector2 spaceShipLoc = new Vector2();
		int spaceShipX = 130;
		int spaceShipY = 150;
		int timer = 0;
		BitmapFont font,font2,font3,font4,font5,font6,font7;
		int bmfY = 450;
		int lives = 3;
		int score = 0;
		int virusCount = 0;
		ArrayList<Circle> virusCircle = new ArrayList<Circle>();
		ArrayList<Integer> virusXs = new ArrayList<>();
		ArrayList<Integer> virusYs = new ArrayList<>();
		ArrayList<Bullet1> bulletManager = new ArrayList<Bullet1>();	
		ArrayList<Circle> bulletCircle = new ArrayList<Circle>();
		int bulletSpeed;
		Circle spaceShipCircle;
		ShapeRenderer sr;
		OrthographicCamera camera;
		Random rand;
		public boolean win= false;
		static Music music,musicBoom,musicWin,musicLose;
		public static boolean pause;
		boolean freeze = false;
		boolean dead = false;
		boolean disappear = false;
		ArrayList<Explosion1>explosions = new ArrayList<Explosion1>();
		ArrayList<SpaceShipExplosion>spaceShipExplosion = new ArrayList<SpaceShipExplosion>();
		int count = 0;
		boolean instructions = false;

		final VirusFighter game;
		
		public LevelLayout1(final VirusFighter game){
			this.game = game;		
			batch = new SpriteBatch();	
			l = new Level1();
			v = new Virus();
			background = new Texture(l.getBackground());
			spaceShip = new Texture(l.getCharacter());
			virus = new Texture(v.getVirus1());	
			bullet = new Texture(l.getCharacterBullet());	
			bulletSpeed = l.getCharacterBulletSpeed();		
			health = new Texture("Levels/Cristal_Icon.png");
			float w = Gdx.graphics.getWidth();
			float h = Gdx.graphics.getHeight();
			camera = new OrthographicCamera(w/2,h/1.5f);
			camera.position.set(camera.viewportWidth , camera.viewportHeight, 0);
			camera.update();
			rand = new Random();
			sr = new ShapeRenderer();
			spaceShipCircle = new Circle();
	        font =new BitmapFont (Gdx.files.internal("myfont.fnt"));
	        font2 =new BitmapFont (Gdx.files.internal("myfont.fnt"));
	        font3 =new BitmapFont (Gdx.files.internal("myfont.fnt"));
	        font4 =new BitmapFont (Gdx.files.internal("myfont.fnt"));
	        font.getData().scaleY = 0.3f;
	        font.getData().scaleX = 0.5f;
	        font2.getData().scaleY = 3f;
	        font2.getData().scaleX = 1f;
	        font3.getData().scaleY = 0.3f;
	        font3.getData().scaleX = 0.5f;
	        font4.getData().scaleY = 0.3f;
	        font4.getData().scaleX = 0.5f;
	        font5 = new BitmapFont (Gdx.files.internal("myfont.fnt"));
	        font5.setColor(Color.GREEN);
	        font5.getData().scaleX = 2f;
	        font5.getData().scaleY = 2f;
	        font6 = new BitmapFont (Gdx.files.internal("myfont.fnt"));
	        font7 = new BitmapFont (Gdx.files.internal("myfont.fnt"));
	        font7.getData().scaleX = 0.8f;
	        font7.getData().scaleY = 0.8f;
			music = Gdx.audio.newMusic(Gdx.files.internal(l.getMusic()));
			musicBoom = Gdx.audio.newMusic(Gdx.files.internal("Canoon.mp3"));
			musicWin = Gdx.audio.newMusic(Gdx.files.internal("Victory.mp3"));
			musicLose = Gdx.audio.newMusic(Gdx.files.internal("GameOver.mp3"));
			star1 = new Texture("StarsSmall_1.png");
			star2 = new Texture("Stars-Big_1_1_PC.png");
		}
		public void makeVirus() {
			float y = camera.position.y;
			float x = camera.position.x;
			float height = rand.nextFloat() * x;
			virusYs.add((int) height+80);
			virusXs.add((int) y+300);
		}
		@Override
		public void render (float delta) {
			game.batch.begin();	

	    	game.batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()*20);
	    	
			game.batch.draw(star1, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()*10);
			game.batch.draw(star2, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()*10);
			if(instructions == false) {
				font5.draw(game.batch, "INSTRUCTIONS\n", 150, 430);
				font6.draw(game.batch, "Viruses are increasingly reproducing\n in the Cornea, save the galaxy and hit more\n to get a higher score which is not less\n than 50 hits in a limited time" , 50, 350);
				font6.draw(game.batch, "Your controls:\n right and left buttons for moving on\n the sides which is your only movement", 50, 270);
				font6.draw(game.batch, " Press space to fire", 50, 200);
				font6.draw(game.batch, "You have got health according to the crystals \ndetermined upon the health of the \nspaceship you are using", 50, 180);
				font6.draw(game.batch, "Good luck, I hope you win!", 130, 100);
				font7.draw(game.batch, "Press fire to start game", 180, 50);
				if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
					instructions = true;
				}

			}else {
				if(disappear == false) {
					game.batch.draw(spaceShip, spaceShipX, spaceShipY, 150, 100);

				}
	    	
				if(dead == false) {
					if(virusCount < 20) {
						virusCount++;
					}else {
						virusCount = 0;
						makeVirus();
					}	
					//for creating a circle around every virus goes down from the top
			    	virusCircle.clear();
			    	for (int i=0;i < virusYs.size();i++) {
			    		game.batch.draw(virus, virusYs.get(i), virusXs.get(i),80,80);
			    		virusXs.set(i, virusXs.get(i)-2);
			    		virusCircle.add(new Circle(virusYs.get(i), virusXs.get(i), 30));
			    	}

					}
				
				if(freeze == false) {
				bmfY++;
				pause = true;
				VirusFighter.music.stop();
				if(MainMenu.pause == true) {
					music.stop();
				}
				else
					music.play();
				timer++;
				MainMenu.pause = false;
				spaceShipLoc.x = spaceShipX;
				spaceShipLoc.y = spaceShipY;
				if(timer > 3000) {
					if(lives >= 0 && score >= 40) {				
							win = true;
							dead = true;
							pause = false;
							freeze = true;    
					}
					else {
						
						win = false;
						pause = false;
						freeze = true;
					}

				}
				else {
					spaceShipY++;			
					camera.update();
					camera.translate(0, 1, 0);
			    	game.batch.setProjectionMatrix(camera.combined);
			    		    	
			    	for (int i=0; i < virusCircle.size();i++) {
			    		if (Intersector.overlaps(spaceShipCircle, virusCircle.get(i))) {
			    			if(lives > 0) {
			    				lives--;
			    			}
			    			else {
			    				musicBoom.play();
			    				win = false;
			    				freeze = true;
								pause = false;
								disappear = true;
			    				spaceShipExplosion.add(new SpaceShipExplosion(spaceShipX,spaceShipY));
			    			}
			    				
			    			
			    			virusCircle.remove(i);
			    			virusXs.remove(i);
			    			virusYs.remove(i);
			    			break;
			    		}
			    	}
			    	
				}//the end of the else statement related to the game play and release of the small viruses	
		    	if(lives == 3) {
		    		game.batch.draw(health, 170, bmfY, health.getWidth()/2f, health.getHeight()/2f);
		    		game.batch.draw(health, 190, bmfY, health.getWidth()/2f, health.getHeight()/2f);
		    		game.batch.draw(health, 210, bmfY, health.getWidth()/2f, health.getHeight()/2f);
		    	}
		    	else if(lives == 2) {
		    		game.batch.draw(health, 170, bmfY, health.getWidth()/2f, health.getHeight()/2f);
		    		game.batch.draw(health, 190, bmfY, health.getWidth()/2f, health.getHeight()/2f);
		    	}
		    	else if(lives == 1) {
		    		game.batch.draw(health, 170, bmfY, health.getWidth()/2f, health.getHeight()/2f);
		    	}
				//The following part of code is related to each bullet release
				int counter = 0;
				bulletCircle.clear();
		    	while(counter < bulletManager.size()) {
		    		Bullet1 currentBullet = bulletManager.get(counter);
		    		currentBullet.update();
		    		if(currentBullet.bulletLocation.x > 0 || currentBullet.bulletLocation.x < Gdx.graphics.getWidth() || currentBullet.bulletLocation.y > 0 || currentBullet.bulletLocation.y < Gdx.graphics.getHeight()) {
		    			game.batch.draw(bullet, currentBullet.bulletLocation.x+5, currentBullet.bulletLocation.y, bullet.getWidth() / 8, bullet.getHeight() / 8);
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
		    	boolean removeCheck = false; //only for the following loop of collision detection between bullets and viruses	
		    	for (int i=0; i < virusCircle.size();i++) {
		    		for(int j=0; j < bulletCircle.size();j++) {
		    			if (Intersector.overlaps(bulletCircle.get(j), virusCircle.get(i))) {
			    			
			    			explosions.add(new Explosion1(virusYs.get(i), virusXs.get(i)));
		    				score++;
		    				bulletCircle.remove(j);   
		    				bulletManager.remove(j);
		    				virusCircle.remove(i);
		    				virusXs.remove(i);
		    				virusYs.remove(i);
		    				removeCheck = true;

		    				break;

		    			}
	    				
		    		}
		    		if(removeCheck == true) {
		    			removeCheck =  false;
		    			break;
		    		}
		    	}
		    	
		    	//the following code is related to the movement of the spaceship on the x axis
				if(Gdx.input.isKeyPressed(Keys.RIGHT) ) {
		
		    		if(spaceShipX < 380) {
		    			spaceShipX += 5; 
		    		}
		    			

		    	}
		    	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
		
		    		if(spaceShipX > 90) {
		    			spaceShipX -= 5; 
		    		}
		    	}	
		    	//This if statement is for the release of the bullets on space button press
		    	if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
		    		Bullet1 myBullet = new Bullet1(spaceShipLoc, new Vector2(0, bulletSpeed));
		    		bulletManager.add(myBullet);
		    	}    
				font.draw(game.batch,"Score:"+ String.valueOf(score), 420, bmfY);			
				//This line is for the circle around the spaceship itself
				spaceShipCircle.set(spaceShipX+33,spaceShipY,1);	
				}
				else if(freeze == true) {
					if(win == false) {
						count++;
						if(count == 1)
							musicLose.play();
						int textY = bmfY-80;
						int textY1 = bmfY-150;
						int textY2 = bmfY-170;
						font2.draw(game.batch, "Game Over!", 250, textY);
						font3.draw(game.batch, "score: "+score , 280, textY1);
						font4.draw(game.batch, "Reamining lives: "+ lives, 250, textY2);
						font4.draw(game.batch, "Press enter for level menu", 230, textY2-90);
						music.stop();
						if(Gdx.input.isKeyPressed(Keys.ENTER)) {
							camera.setToOrtho(false);
					    	game.batch.setProjectionMatrix(camera.combined);
							game.setScreen(new LevelsMenu(game));
						}
					}
					if(win == true)
					{
						count++;
						if(count == 1)
							musicWin.play();
						
						spaceShipY++;
						int textY = bmfY-80;
						int textY1 = bmfY-150;
						int textY2 = bmfY-170;
						font2.draw(game.batch, "Well done!", 250, textY);
						font3.draw(game.batch, "score: "+score , 280, textY1);
						font4.draw(game.batch, "Reamining lives: "+ lives, 250, textY2);
						font4.draw(game.batch, "Press Enter to return!", 230, textY2-90);
						music.stop();
						if(Gdx.input.isKeyPressed(Keys.ENTER)) {
							camera.setToOrtho(false);
					    	game.batch.setProjectionMatrix(camera.combined);
							game.setScreen(new CharacterMenu(game));
						}
					}
					
				}
				for(Bullet1 bullet : bulletManager)
					bullet.update();
				for(Explosion1 explosion: explosions) {
					explosion.render(game.batch);
				}
				ArrayList<Explosion1>explosionRemove = new ArrayList<Explosion1>();
				for(Explosion1 explosion : explosions) {
					explosion.update(delta);
					if(explosion.remove)
						explosionRemove.add(explosion);
				}
				explosions.removeAll(explosionRemove);
				
				for(SpaceShipExplosion firework: spaceShipExplosion) {
					firework.render(game.batch);
				}
				ArrayList<SpaceShipExplosion>fireworkRemove = new ArrayList<SpaceShipExplosion>();
				for(SpaceShipExplosion firework : spaceShipExplosion) {
					firework.update(delta);
					if(firework.remove)
						fireworkRemove.add(firework);
				}
				spaceShipExplosion.removeAll(fireworkRemove);
			}
			
			
			game.batch.end();

		}
	}