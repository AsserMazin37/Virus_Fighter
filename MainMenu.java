package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MainMenu extends ScreenAdapter {
	SpriteBatch batch;
		
	Rectangle startRec;
	Rectangle exitRec;
	Rectangle musicRec;
	Rectangle infoRec;
	Rectangle closeRec;
	ShapeRenderer sr;

	BitmapFont font;

	public static boolean check = false;
	static boolean show = false;
	public static boolean pause;

	Texture img;
	Texture startButton;
	Texture exitButton;
	Texture musicButton;
	Texture info,seifModel;
	Texture background;
	Texture closeButton;
	Texture name;
	Texture fire;
	Texture credits;
	String status;

	final VirusFighter game;
	
	public MainMenu(final VirusFighter game) {
		this.game = game;
		batch = new SpriteBatch();
	    font =new BitmapFont (Gdx.files.internal("myfont.fnt"));
		img = new Texture("MainUi/BG.png");
		startButton = new Texture("MainUi/Start_BTN.png");
		exitButton = new Texture("MainUi/Exit_BTN.png");
		info = new Texture("MainUi/Info_BTN.png");
		status = "MainUi/Active_Music_BTN.png";
		musicButton = new Texture(status);
		name = new Texture("MainUi/vfImage.png");
		startRec = new Rectangle();
		exitRec = new Rectangle();
		musicRec = new Rectangle();
		infoRec = new Rectangle();
		closeRec = new Rectangle();
		background = new Texture("MainUi/Table_01.png");
		seifModel = new Texture("models/seifModel.png");
		closeButton = new Texture("MainUi/Close_BTN.png");
		credits = new Texture("MainUi/credits.png");
		sr = new ShapeRenderer();
	}
	@Override
	public void render(float delta) {
		
		game.batch.begin();
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		startRec.set(210, 200, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		exitRec.set(210, 300, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		musicRec.set(10, 10, Gdx.graphics.getWidth()/11, Gdx.graphics.getHeight()/9);
		infoRec.set(50,10,Gdx.graphics.getWidth()/11, Gdx.graphics.getHeight()/9);
		closeRec.set(450,50,closeButton.getWidth()/8,closeButton.getHeight()/8);	
		game.batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		game.batch.draw(musicButton, 10, 410, Gdx.graphics.getWidth()/14, Gdx.graphics.getHeight()/12);

		if(musicRec.contains(mouseX,mouseY)) {

			if(Gdx.input.justTouched() && pause == true) {
				pause = false;
				status = "MainUi/Active_Music_BTN.png";
				musicButton = new Texture(status);		
			}

			else if(Gdx.input.justTouched() && pause == false) {
				pause = true;
				status = "MainUi/Music_BTN.png";
				musicButton = new Texture(status);	
			}
			
		}
		if(infoRec.contains(mouseX,mouseY)) {
			game.batch.draw(info, 70, 410, Gdx.graphics.getWidth()/14, Gdx.graphics.getHeight()/12);
			if(Gdx.input.justTouched()) {
				show = true;
			}
			
		}
		else {
			game.batch.draw(info, 70, 410, Gdx.graphics.getWidth()/13, Gdx.graphics.getHeight()/11);
		}
		if(show == true) {
			game.batch.draw(background, 100, 50, Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/1.2f);
			game.batch.draw(credits, 230, 370, credits.getWidth()/2.5f,credits.getHeight()/2.5f);
			game.batch.draw(seifModel, 95, 30, Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/1.2f);
			game.batch.draw(closeButton, 450, 400, closeButton.getWidth()/8,closeButton.getHeight()/8);
			if(closeRec.contains(mouseX,mouseY)) {
				game.batch.draw(closeButton, 451, 400, closeButton.getWidth()/9,closeButton.getHeight()/9);
				if(Gdx.input.justTouched())
					show = false;
			}


		}	

		else if(show == false){
		game.batch.draw(name, -20, 100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if(startRec.contains(mouseX,mouseY)) {
			game.batch.draw(startButton, 235, 200, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);
			if(Gdx.input.justTouched()) {
				game.setScreen(new UserName(game));
			}
		}
		else
			game.batch.draw(startButton, 210, 200, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);

		if(exitRec.contains(mouseX,mouseY)) {
			game.batch.draw(exitButton, 235, 100, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/8);
			if(Gdx.input.justTouched()) {
				Gdx.app.exit();
			
			}
		}
		else {
			game.batch.draw(exitButton, 210, 100, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
		}
		if(check == true) {
			game.setScreen(new CharacterMenu(game));
			
		}
		}	

		

		game.batch.end();
		/*sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.RED);
		sr.rect(rec5.x, rec5.y,closeButton.getWidth()/8,closeButton.getHeight()/8);
		sr.end();*/
	}
	@Override
	public void dispose () {
		game.batch.dispose();
		img.dispose();
	}
}
