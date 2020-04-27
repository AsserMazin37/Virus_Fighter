package gameData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VirusLevel2 { 
	public static int SPEED = 270;
	private static Texture texture;
	
	int width;
	int hight;
	
	float x, y;
	CollisionRect rect;

	public boolean remove = false;
	
	public VirusLevel2 (int x) {

		this.x = x;
		this.y = Gdx.graphics.getHeight();
		if (texture == null)	
			texture = new Texture("viruses/virus3.png");
		
		this.rect=new CollisionRect(x,y,texture.getWidth() / 7, texture.getHeight() / 7);

		
		
	}
	
	public void update (float delta) {
		y -= SPEED * delta;   
		if (y < -80)           // law wesl l ta7t 5ales 
			remove = true;
		
		rect.move(x, y);

}	
	
	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y,texture.getWidth() / 7, texture.getHeight() / 7);

		
	}
	
	public CollisionRect getCollisionRect() {
		return rect;
	}
	
	public float getx () {
		return x;
	}
	
	public float gety () {
		return y;
	}
}

