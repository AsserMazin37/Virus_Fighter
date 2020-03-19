package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public  class Map extends ScreenAdapter {
	
	SpriteBatch batch;
	Texture img, spaceship;
	Texture[] levels;
	static int score = 0;
	
	int spaceshipX = -500;
	
	int posMain = 500;
	int posMain2 = 0;
	int pos2 = 500;
	int pos3 = 500;
	int pos4 = 500;
	int pos5 = 500;
	int pos6 = 500;
	int pos7 = 500;
	int tempPos=0;
	 
	final VirusFighter game;

	 public Map(final VirusFighter game) {
		 this.game = game;
		 
		 batch = new SpriteBatch();
		 img = new Texture("BG.png");
		 levels = new Texture[6];
			
		 levels[0] = new Texture("level1.png");
		 levels[1] = new Texture("level2.png");
		 levels[2] = new Texture("level3.png");
		 levels[3] = new Texture("level4.png");
		 levels[4] = new Texture("level5.png");
		 levels[5] = new Texture("level6.png");
		 
			if(CharacterMenu.getShipChoice() == 1) {
				 spaceship = new Texture("spaceship1inverted.png");
			 }
			 else if(CharacterMenu.getShipChoice() == 2) {
				 spaceship = new Texture("spaceship2inverted.png");
			 }
			 else {
				 spaceship = new Texture("spaceship3inverted.png");
			 }
	 }
	 
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		game.batch.begin();
		
		game.batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		
			if(LevelsMenu.getTag() == 1 ) {
				game.batch.draw(levels[0], posMain2, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				if(posMain2 == -500){
					posMain2 = -500;	
				}
				else
					posMain2--;
				
				
				game.batch.draw(levels[1], pos2, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				
				
				if(pos2 == 0) {
					pos2 = 0;
					game.setScreen(new MoveTo(game));
				}
				else
					pos2--;
			}
			
			
			else if(LevelsMenu.getTag()== 2) {
			    game.batch.draw(levels[1], tempPos, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				if(tempPos == -500) {
					tempPos = -500;
				}
				else
					tempPos--;
				
				game.batch.draw(levels[2], pos3, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				
				
				if(pos3 == 0) {
					pos3 = 0;
					game.setScreen(new MoveTo(game));
				}
				else
					pos3--;
			}
			
			
			else if(LevelsMenu.getTag() == 3) {
				 game.batch.draw(levels[2], tempPos, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				 if(tempPos == -500) {
					 tempPos = -500;
				}
				 else
					 tempPos--;
				 game.batch.draw(levels[3], pos4, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				 if(pos4 == 0) {
					 pos4 = 0;
					 game.setScreen(new MoveTo(game));
				}
				 else
					 pos4--;
			}
			
			
			else if(LevelsMenu.getTag() == 4) {
				 game.batch.draw(levels[3], tempPos, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				 if(tempPos == -500) {
					 tempPos = -500;
				}
				 else
					 tempPos--;
				 game.batch.draw(levels[4], pos5, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				 if(pos5 == 0) {
					 pos5 = 0;
					 game.setScreen(new MoveTo(game));
				}
				 else
					 pos5--;
			}
			
			
			else if(LevelsMenu.getTag() == 5) {
				 game.batch.draw(levels[4], tempPos, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				 if(tempPos == -500) {
					 tempPos = -500;
				}
				 else
					 tempPos--;
				 game.batch.draw(levels[5], pos6, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				 
				 if(pos6 == 0) {
					 pos6 = 0;
					 game.setScreen(new MoveTo(game));
				}
				 else
					 pos6--;
			}
			
			
			else if(LevelsMenu.getTag() == 5) {
				 game.batch.draw(levels[5], tempPos, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				 if(tempPos == -500) {
					 tempPos = -500;
				}
				 else
					 tempPos--;
				 game.batch.draw(levels[6], pos7, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
				 
				 if(pos7 == 0) {
					 pos7 = 0;
					 game.setScreen(new MoveTo(game));
				}
				 else
					 pos7--;
			}
			
			
			else if(LevelsMenu.getTag() == 0) {
				game.batch.draw(levels[0], posMain, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());		
			}
			if(posMain == 0){
				posMain = 0;
				 game.setScreen(new MoveTo(game));
			}
			else
				posMain--;
			
			if(spaceshipX == 0) {
				spaceshipX = 0;
			}
			else
				spaceshipX++;
			
			if(LevelsMenu.getTag() != 0) {
				game.batch.draw(spaceship, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			}
			else
				game.batch.draw(spaceship, spaceshipX, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		game.batch.end();
	}
	
	@Override
	public void dispose () {
		game.batch.dispose();		
		img.dispose();
		spaceship.dispose();
		levels[0].dispose();
		levels[1].dispose();
		levels[2].dispose();
		levels[3].dispose();
		levels[4].dispose();
		levels[5].dispose();
	}

	@Override
	public void show() {
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

	@Override
	public void hide() {
		// TODO Auto-generated method stub		
	}
}