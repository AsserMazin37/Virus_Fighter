package gameData;

import com.badlogic.gdx.math.Vector2;

public class CollisionRect {
	
	float x, y;
	float width;
	int height;
	public Vector2 recLocation = new Vector2(0,0);

	public CollisionRect (Vector2 sentlocation, int width, int height) {
		recLocation = new Vector2(sentlocation.x, sentlocation.y);
		this.width = width;
		this.height = height;
	}
	
	public CollisionRect (float x, float y,float width,int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		
	}
	
	
	public void move (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean collidesWith (CollisionRect rect) {
		return  x+50 < rect.x + rect.width && y < rect.y + rect.height && x-50 + width > rect.x && y + height > rect.y; //min:8
	}
	
}

	

