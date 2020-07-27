package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash_Screen extends ScreenAdapter {
	SpriteBatch batch;

	int timer, translate;

	BitmapFont font;

	OrthographicCamera camera;

	Texture background, star1, star2, logo;

	final VirusFighter game;

	Splash_Screen(VirusFighter game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);
		camera.setToOrtho(false);
		game.batch.setProjectionMatrix(camera.combined);
		camera.update();
		background = new Texture("Backgrounds/NebulaAqua-Pink.png");
		star1 = new Texture("Levels/StarsSmall_1.png");
		star2 = new Texture("Levels/Stars-Big_1_1_PC.png");
		logo = new Texture("MainUi/vfImage.png");
		font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));

		timer = 0;
		translate = 0;
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		timer++;
		game.batch.begin();
		game.batch.draw(background, 0, 0, background.getWidth(), background.getHeight() * 10);
		game.batch.draw(star2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 10);
		game.batch.draw(star1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 10);
		if (timer > 300) {
			font.draw(game.batch, "Press Enter to start", 185, 11400);
			if (Gdx.input.isKeyPressed(Keys.ENTER)) {
				game.setScreen(new MainMenu(game));
				camera.setToOrtho(false);
				game.batch.setProjectionMatrix(camera.combined);
			}
		}
		if (translate < 150) {
			translate++;
			camera.translate(0, translate);
		}
		camera.update();

		game.batch.setProjectionMatrix(camera.combined);

		game.batch.draw(logo, -350, 11300, logo.getWidth(), logo.getHeight());

		game.batch.end();

	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
