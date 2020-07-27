package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelLayout6 extends ScreenAdapter {

	SpriteBatch batch;
	Texture Tile, background, star1, star2;
	Texture[] man, manJump;
	int index = 0;
	int indexJ = 0;
	OrthographicCamera camera;
	int manX = 80;
	int tileX = 0;
	int backgroundX = -550;
	int stars = -550;
	boolean jump = false;
	boolean down = false;
	int indexY = 40;
	final VirusFighter game;

	LevelLayout6(VirusFighter game) {
		this.game = game;
		batch = new SpriteBatch();

		Tile = new Texture("Levels/brain.png");
		background = new Texture("Backgrounds/NebulaBlue.png");
		man = new Texture[43];
		man[0] = new Texture("man/run_000.png");
		man[1] = new Texture("man/run_001.png");
		man[2] = new Texture("man/run_002.png");
		man[3] = new Texture("man/run_003.png");
		man[4] = new Texture("man/run_004.png");
		man[5] = new Texture("man/run_005.png");
		man[6] = new Texture("man/run_006.png");
		man[7] = new Texture("man/run_007.png");
		man[8] = new Texture("man/run_008.png");
		man[9] = new Texture("man/run_009.png");
		man[10] = new Texture("man/run_010.png");
		man[11] = new Texture("man/run_011.png");
		man[12] = new Texture("man/run_012.png");
		man[13] = new Texture("man/run_013.png");
		man[14] = new Texture("man/run_014.png");
		man[15] = new Texture("man/run_015.png");
		man[16] = new Texture("man/run_016.png");
		man[17] = new Texture("man/run_017.png");
		man[18] = new Texture("man/run_018.png");
		man[19] = new Texture("man/run_019.png");
		man[20] = new Texture("man/run_020.png");
		man[21] = new Texture("man/run_021.png");
		man[22] = new Texture("man/run_022.png");
		man[23] = new Texture("man/run_023.png");
		man[24] = new Texture("man/run_024.png");
		man[25] = new Texture("man/run_025.png");
		man[26] = new Texture("man/run_026.png");
		man[27] = new Texture("man/run_027.png");
		man[28] = new Texture("man/run_028.png");
		man[29] = new Texture("man/run_029.png");
		man[30] = new Texture("man/run_030.png");
		man[31] = new Texture("man/run_031.png");
		man[32] = new Texture("man/run_032.png");
		man[33] = new Texture("man/run_033.png");
		man[34] = new Texture("man/run_034.png");
		man[35] = new Texture("man/run_035.png");
		man[36] = new Texture("man/run_036.png");
		man[37] = new Texture("man/run_037.png");
		man[38] = new Texture("man/run_038.png");
		man[39] = new Texture("man/run_039.png");
		man[40] = new Texture("man/run_040.png");
		man[41] = new Texture("man/run_041.png");
		man[42] = new Texture("man/run_042.png");

		star1 = new Texture("Levels/StarsSmall_1.png");
		star2 = new Texture("Levels/Stars-Big_1_1_PC.png");

		manJump = new Texture[31];
		manJump[0] = new Texture("manJump/jump_000.png");
		manJump[1] = new Texture("manJump/jump_001.png");
		manJump[2] = new Texture("manJump/jump_002.png");
		manJump[3] = new Texture("manJump/jump_003.png");
		manJump[4] = new Texture("manJump/jump_004.png");
		manJump[5] = new Texture("manJump/jump_005.png");
		manJump[6] = new Texture("manJump/jump_006.png");
		manJump[7] = new Texture("manJump/jump_007.png");

		manJump[8] = new Texture("manJump/jump_010.png");
		manJump[9] = new Texture("manJump/jump_011.png");
		manJump[10] = new Texture("manJump/jump_012.png");
		manJump[11] = new Texture("manJump/jump_013.png");
		manJump[12] = new Texture("manJump/jump_014.png");
		manJump[13] = new Texture("manJump/jump_015.png");
		manJump[14] = new Texture("manJump/jump_016.png");
		manJump[15] = new Texture("manJump/jump_017.png");

		manJump[16] = new Texture("manJump/jump_020.png");
		manJump[17] = new Texture("manJump/jump_021.png");
		manJump[18] = new Texture("manJump/jump_022.png");
		manJump[19] = new Texture("manJump/jump_023.png");
		manJump[20] = new Texture("manJump/jump_024.png");
		manJump[21] = new Texture("manJump/jump_025.png");
		manJump[22] = new Texture("manJump/jump_026.png");
		manJump[23] = new Texture("manJump/jump_027.png");

		manJump[24] = new Texture("manJump/jump_030.png");
		manJump[25] = new Texture("manJump/jump_031.png");
		manJump[26] = new Texture("manJump/jump_032.png");
		manJump[27] = new Texture("manJump/jump_033.png");
		manJump[28] = new Texture("manJump/jump_034.png");
		manJump[29] = new Texture("manJump/jump_035.png");
		manJump[30] = new Texture("manJump/jump_036.png");

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		game.batch.begin();
		stars--;

		if (index < 40) {
			backgroundX -= 3;
			index += 3;
		} else
			index = 0;

		game.batch.draw(background, backgroundX, 0, Gdx.graphics.getWidth() * 20, Gdx.graphics.getHeight());
		game.batch.draw(star1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.draw(star2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.draw(star1, stars, 0, Gdx.graphics.getWidth() * 20, Gdx.graphics.getHeight());
		game.batch.draw(star2, stars, 0, Gdx.graphics.getWidth() * 20, Gdx.graphics.getHeight());
		game.batch.draw(Tile, tileX, -130, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 2);

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			jump = true;
		}
		if (jump == true) {

			if (indexJ < 29) {
				indexY += 8;
				indexJ += 1f;
			} else {
				indexJ = 0;
				down = true;
				jump = false;

			}
			game.batch.draw(manJump[indexJ], manX, indexY, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 3);
		} else
			game.batch.draw(man[index], manX, indexY, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 3);
		if (down = true) {
			if (indexY > 40) {
				indexY -= 3f;
			} else {
				indexY = 40;
				down = false;
			}
		}

		game.batch.end();
	}

}
