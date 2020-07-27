package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public class Map extends ScreenAdapter {
	SpriteBatch batch;
	Texture img;
	Texture spaceship;
	Texture[] levels;
	Texture star1, star2, star3, star4;
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
	int tempPos = 0;
	int starsCount = 0;
	final VirusFighter game;

	public Map(final VirusFighter game) {
		this.game = game;

		batch = new SpriteBatch();
		img = new Texture("Backgrounds/NebulaBlue.png");
		levels = new Texture[6];
		levels[0] = new Texture("Levels/level1.png");
		levels[1] = new Texture("Levels/level2.png");
		levels[2] = new Texture("Levels/level3.png");
		levels[3] = new Texture("Levels/level4.png");
		levels[4] = new Texture("Levels/level5.png");
		levels[5] = new Texture("Levels/level6.png");
		star1 = new Texture("Levels/StarsSmall_1.png");
		star2 = new Texture("Levels/Stars-Big_1_1_PC.png");

		if (CharactersMenu.shipChoice == 1) {
			spaceship = new Texture("SpaceShips/spaceship1inverted.png");
		} else if (CharactersMenu.shipChoice == 2) {
			spaceship = new Texture("SpaceShips/spaceship2inverted.png");
		} else {
			spaceship = new Texture("SpaceShips/spaceship3inverted.png");
		}
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		game.batch.begin();
		game.batch.draw(img, 0, 0, Gdx.graphics.getWidth() * 10, Gdx.graphics.getHeight());
		if (starsCount > -500)
			starsCount -= 2.7f;
		else
			starsCount = 0;
		game.batch.draw(star1, starsCount, 0, Gdx.graphics.getWidth() * 20, Gdx.graphics.getHeight());
		game.batch.draw(star1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.draw(star1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		game.batch.draw(star2, starsCount, 0, Gdx.graphics.getWidth() * 20, Gdx.graphics.getHeight());
		game.batch.draw(star2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (LevelsMenu.tag == 1) {
			game.batch.draw(levels[0], posMain2, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

			if (posMain2 == -500) {
				posMain2 = -500;
			} else {
				posMain2 -= 2;
			}
			game.batch.draw(levels[1], pos2, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (pos2 == 0) {
				pos2 = 0;
				game.setScreen(new MoveTo(game));
			} else
				pos2 -= 2;
		} else if (LevelsMenu.tag == 2) {
			game.batch.draw(levels[1], tempPos, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (tempPos == -500) {
				tempPos = -500;
			} else
				tempPos -= 2;

			game.batch.draw(levels[2], pos3, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (pos3 == 0) {
				pos3 = 0;
				game.setScreen(new MoveTo(game));

			} else
				pos3 -= 2;
		} else if (LevelsMenu.tag == 3) {
			game.batch.draw(levels[2], tempPos, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (tempPos == -500) {
				tempPos = -500;
			} else
				tempPos -= 2;
			game.batch.draw(levels[3], pos4, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (pos4 == 0) {
				pos4 = 0;
				game.setScreen(new MoveTo(game));

			} else
				pos4 -= 2;
		} else if (LevelsMenu.tag == 4) {
			game.batch.draw(levels[3], tempPos, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (tempPos == -500) {
				tempPos = -500;
			} else
				tempPos -= 2;
			game.batch.draw(levels[4], pos5, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (pos5 == 0) {
				pos5 = 0;
				game.setScreen(new MoveTo(game));

			} else
				pos5 -= 2;
		} else if (LevelsMenu.tag == 5) {
			game.batch.draw(levels[4], tempPos, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (tempPos == -500) {
				tempPos = -500;
			} else
				tempPos -= 2;
			game.batch.draw(levels[5], pos6, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (pos6 == 0) {
				pos6 = 0;
				game.setScreen(new MoveTo(game));

			} else
				pos6 -= 2;
		} else if (LevelsMenu.tag == 5) {
			game.batch.draw(levels[5], tempPos, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (tempPos == -500) {
				tempPos = -500;
			} else
				tempPos -= 2;
			game.batch.draw(levels[6], pos7, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (pos7 == 0) {
				pos7 = 0;
				game.setScreen(new MoveTo(game));

			} else
				pos7 -= 2;
		} else if (LevelsMenu.tag == 0) {
			game.batch.draw(levels[0], posMain, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
		if (posMain == 0) {
			posMain = 0;
			game.setScreen(new MoveTo(game));
		} else {
			posMain -= 2;
		}
		if (spaceshipX == 0) {
			spaceshipX = 0;
		} else
			spaceshipX += 2;
		if (LevelsMenu.tag != 0) {
			game.batch.draw(spaceship, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		} else
			game.batch.draw(spaceship, spaceshipX, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		game.batch.end();
	}

	@Override
	public void dispose() {
		game.batch.dispose();

	}
}
