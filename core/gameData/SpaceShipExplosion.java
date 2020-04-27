package gameData;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpaceShipExplosion {
	public static final float FRAME_LENGTH = 0.1f;
	public static final int SET = 50;
	public static final int SIZE = 50;
	public static final int IMAGE_SIZE = 30;
	private static Animation<TextureRegion> anim = null;
	float x,y;
	float time;
	public  boolean remove = false;
	
	public SpaceShipExplosion(float x, float y) {
		this.x = x + SET;
		this.y = y + SET;
		time = 0;
		if(anim == null)
			anim = new Animation<TextureRegion>(FRAME_LENGTH, TextureRegion.split(new Texture("Levels/explosion.png"), IMAGE_SIZE, IMAGE_SIZE)[0]);
		
	}
	public void update(float delta) {
		time += delta;
		if(anim.isAnimationFinished(time))
			remove = true;
	}
	public void render(SpriteBatch batch) {
		batch.draw(anim.getKeyFrame(time), x, y, SIZE, SIZE);
	}
}
