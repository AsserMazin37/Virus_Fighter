package com.mygdx.game.desktop;

import org.lwjgl.opengl.XRandR.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.VirusFighter;
import com.mygdx.game.Map;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config;
		config = new LwjglApplicationConfiguration();	
		
		new LwjglApplication(new VirusFighter(), config);
	}
}
