package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UserName extends ApplicationAdapter implements TextInputListener {
	static String text;
	SpriteBatch batch;
    boolean check = true;
    public UserName() {
    	super();
    }
	@Override
	public void create () {
		batch = new SpriteBatch();
	}
	
	@Override
	public void render () {
		batch.begin();
			Gdx.input.getTextInput(this, "Enter Your Username", text, "username");	
			batch.end();
	}
	
	@Override
	public  void input (String text) {
		this.text = text;
		System.out.println(text);
		if(text.equalsIgnoreCase("seif")) {
			MainMenu.check = check;
			System.out.println(MainMenu.check);
		}
	}
	public static boolean get() {
		return MainMenu.check;
	}
	public static String getName() {
		MoveTo.text = text;
		return MoveTo.text;
	}
	@Override
	public void canceled () {
		text = "Cancelled";
		System.out.println(text);
	}

}

