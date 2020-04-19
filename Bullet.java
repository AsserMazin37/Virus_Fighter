package gameData;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;



public class Bullet {
	
	
	public Vector2 bulletLocation = new Vector2(0,0);
	private Vector2 bulletVelocity = new Vector2(0,0);
	 CollisionRect rect;
	
	public Bullet(Vector2 sentLocation, Vector2 sentVelocity,Texture texture) {
		bulletLocation = new Vector2(sentLocation.x, sentLocation.y);
		bulletVelocity = new Vector2(sentVelocity.x, sentVelocity.y);
		this.rect=new CollisionRect (bulletLocation.x,bulletLocation.y, texture.getWidth()/235, texture.getHeight() / 30);
	}
	
	public void update() {
		
		bulletLocation.x += bulletVelocity.x;
		bulletLocation.y += bulletVelocity.y;
		    
		rect.move(bulletLocation.x+100f,bulletLocation.y);
		
	}
	

	public CollisionRect getCollisionRect() {
		return rect;
	}
	
} 