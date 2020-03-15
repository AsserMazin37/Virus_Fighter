package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public  class Map implements Screen {
	SpriteBatch batch;
	Texture img;
	Texture spaceship;
	Texture[] levels;
	static int score = 0;
	
	int spaceshipY = -500;
	
	 int posMain = 500;
	 int posMain2 = 0;
	 int pos2 = 500;
	 int pos3 = 500;
	 int pos4 = 500;
	 int pos5 = 500;
	 int pos6 = 500;
	 int pos7 = 500;
	 
	final MoveTo game;

	 public Map(final MoveTo moveTo) {
		 this.game = moveTo;
		 
		 batch = new SpriteBatch();
			img = new Texture("BG.png");
			levels = new Texture[6];
			
			levels[0] = new Texture("level1.png");
			levels[1] = new Texture("level2.png");
			levels[2] = new Texture("level3.png");
			levels[3] = new Texture("level4.png");
			levels[4] = new Texture("level5.png");
			levels[5] = new Texture("level6.png");
			spaceship = new Texture("spaceship.png");
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
	public void render(float delta) {
		// TODO Auto-generated method stub
		game.batch.begin();
		game.batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		try {
			if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				score++;
			}
		
		if(score == 1 ) {
			game.batch.draw(levels[0], posMain2, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

			if(posMain2 == -500)
			{
				posMain2 = -500;
				
			}
			else{
				posMain2--;
			}
			
				game.batch.draw(levels[1], pos2, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			 if(pos2 == 0) {
				 pos2 = 0;
				}
			 else
				 pos2--;
			 
			

		}
		else if(score== 2) {
			
		
		game.batch.draw(levels[1], pos2, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		 if(pos2 == -500) {
			 pos2 = -500;
			}
		 else
			 pos2--;
			
		 game.batch.draw(levels[2], pos3, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		 if(pos3 == 0) {
			 pos3 = 0;
			}
		 else
			 pos3--;
		
	 
		}
		else if(score == 3) {
				
			 game.batch.draw(levels[2], pos3, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			 if(pos3 == -500) {
				 pos3 = -500;
				}
			 else
				 pos3--;
			 game.batch.draw(levels[3], pos4, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			 if(pos4 == 0) {
				 pos4 = 0;
				}
			 else
				 pos4--;
		}
		else if(score == 4) {
			 game.batch.draw(levels[3], pos4, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			 if(pos4 == -500) {
				 pos4 = -500;
				}
			 else
				 pos4--;
			 game.batch.draw(levels[4], pos5, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			 if(pos5 == 0) {
				 pos5 = 0;
				}
			 else
				 pos5--;
		}
		else if(score == 5) {
			 game.batch.draw(levels[4], pos5, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			 if(pos5 == -500) {
				 pos5 = -500;
				}
			 else
				 pos5--;
			 game.batch.draw(levels[5], pos6, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			 if(pos6 == 0) {
				 pos6 = 0;
				}
			 else
				 pos6--;
		}
		else if(score == 6) {

			 game.batch.draw(levels[5], pos6, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			 if(pos6 == -500) {
				 pos6 = -500;
				}
			 else
				 pos6--;
			 game.batch.draw(levels[6], pos7, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			 if(pos7 == 0) {
				 pos7 = 0;
				}
			 else
				 pos7--;
		}
		else if(score== 0) {
			game.batch.draw(levels[0], posMain, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

			
		}
		if(posMain == 0)
		{
			posMain = 0;
			
		}
		else{
			posMain--;
		}
		if(spaceshipY == 0) {
			spaceshipY = 0;
		}
		else
			spaceshipY++;

		game.batch.draw(spaceship, spaceshipY, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		game.batch.end();
		
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
