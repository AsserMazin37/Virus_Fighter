package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class VirusBullet {
	
	public Vector2 virusLocation= new Vector2(0,0);
	private Vector2 virusVelocity = new Vector2(0,0);
	
	public VirusBullet(Vector2 sentVirusLocation, Vector2 sentVirusVelocity) {
		virusLocation = new Vector2(sentVirusLocation.x, sentVirusLocation.y);
		virusVelocity = new Vector2(sentVirusVelocity.x, sentVirusVelocity.y);
	}
	
	public void update() {
		virusLocation.y -= virusVelocity.y;
	}
}