package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;

public class myGame extends ApplicationAdapter implements TextInputListener{
	String text;
	
	@Override
	public void create () {
		
	}
	
	@Override
	public void render () {
		if(Gdx.input.justTouched())
		{
			Gdx.input.getTextInput(this, "Enter Your Username", text, "username");
		}
	}
	
	@Override
	public void input (String text) {
		this.text = text;
		System.out.println(text);
	}
	
	@Override
	public void canceled () {
		text = "Cancelled";
		System.out.println(text);
	}
}