package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import gameData.Level;
import gameData.Level1;

public class LevelLayout extends ScreenAdapter{

	SpriteBatch batch;
	
	Texture bullet, spaceShip, virus, background;
	
	Level l;
	
	Vector2 spaceShipLoc = new Vector2();
	
	ArrayList<Bullet> bulletManager = new ArrayList<Bullet>();
	
	int bulletSpeed;
	
	final VirusFighter game;

	LevelLayout(final VirusFighter game){
		this.game = game;		
		batch = new SpriteBatch();
		
		l = new Level1();
		background = new Texture(l.getBackground());
		spaceShip = new Texture(l.getCharacter());
		virus = new Texture(l.getVirus());
		bullet = new Texture(l.getBullet());
		bulletSpeed = l.getBulletSpeed();
		
		spaceShipLoc.x = 130;
		spaceShipLoc.y = -30;
		
		/*if(l instanceof Level1){
		 * 
		 * 
		 * }
		 * else if(l instanceof Level2){
		 * 
		 * 
		 * 
		 * }
		 * .
		 * .
		 * .
		 * .
		 * */		
	}
	
	 public void update() {
	    	if(Gdx.input.isKeyPressed(Keys.W) && (spaceShipLoc.y < Gdx.graphics.getHeight() - spaceShip.getHeight() / 5)) {
	    		spaceShipLoc.y += 5;
	    	}
	    	if(Gdx.input.isKeyPressed(Keys.S) && (spaceShipLoc.y > 0)) {
	    		spaceShipLoc.y -= 5;
	    	}
	    	if(Gdx.input.isKeyPressed(Keys.D) && (spaceShipLoc.x < Gdx.graphics.getWidth() - spaceShip.getWidth() / 5)) {
	    		spaceShipLoc.x += 5; 
	    	}
	    	if(Gdx.input.isKeyPressed(Keys.A) && (spaceShipLoc.x > 0)) {
	    		spaceShipLoc.x -= 5; 
	    	}
	    	
	    	if(Gdx.input.isKeyJustPressed(Keys.UP)) {
	    		Bullet myBullet = new Bullet(spaceShipLoc, new Vector2(0, bulletSpeed));
	    		bulletManager.add(myBullet);
	    	}
	    	if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
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
	    	}
	    }
	
	@Override
	public void render (float delta) {
		update();
		
		game.batch.begin();
		
		game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    	game.batch.draw(spaceShip, spaceShipLoc.x, spaceShipLoc.y, spaceShip.getWidth() / 5, spaceShip.getHeight() / 5);
    	game.batch.draw(virus, 130, 300, virus.getWidth() / 3, virus.getHeight() / 3);
    	
    	int counter = 0;
		
    	while(counter < bulletManager.size()) {
    		Bullet currentBullet = bulletManager.get(counter);
    		currentBullet.update();
    		if(currentBullet.bulletLocation.x > 0 && currentBullet.bulletLocation.x < Gdx.graphics.getWidth() && currentBullet.bulletLocation.y > 0 && currentBullet.bulletLocation.y < Gdx.graphics.getHeight()) {
    			game.batch.draw(bullet, currentBullet.bulletLocation.x, currentBullet.bulletLocation.y, bullet.getWidth() / 5, bullet.getHeight() / 5);
    		}
    		else {
    			bulletManager.remove(counter);
    			if(bulletManager.size() > 0) {
        			counter--;
    			}
    		}
    		counter++;
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