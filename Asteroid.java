package gameData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Asteroid { 
	public static int SPEED = 360;
	private static Texture texture;
	
	int width;
	int hight;
	
	float x, y;
	CollisionRect rect;

	public boolean remove = false;
	
	public Asteroid (int x) {

		this.x = x;
		this.y = Gdx.graphics.getHeight();
		if (texture == null)	
			texture = new Texture("hiclipart.com2.png");
		
		this.rect=new CollisionRect(x,y,texture.getWidth()/2.5f, texture.getHeight()/4);

		
		
	}
	
	public void update (float delta) {
		y -= SPEED * delta;   
		if (y < -80)           // law wesl l ta7t 5ales 
			remove = true;
		
		rect.move(x-70, y-10);

}
	
	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y,texture.getWidth() / 6, texture.getHeight() / 6);

		
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

