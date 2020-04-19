package gameData;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion {

	
	public static final float FRAME_LENGTH = 0.1f; // sor3et el 7arka
	public static final int SET = 60;  // 3ashan azbat elexplosion eno ytl3 mn el virus
	public int SIZE;  // size el explosion
	public static final int IMAGE_SIZE = 30;   //size el sora kolha
	private static Animation<TextureRegion> anim = null;
	float x, y;
	float time;  // el explosion hy3od ad eh
	VirusLevel2 virus;
	public boolean remove = false;
	
	public Explosion (float x, float y,int SIZE) {
		this.x =x + SET;
		this.SIZE=SIZE;
		this.y = y + SET;
		time = 0;  
		
		if (anim == null)   
			anim = new Animation<TextureRegion>(FRAME_LENGTH,TextureRegion.split(new Texture("explosion2.png"),IMAGE_SIZE ,IMAGE_SIZE)[0]); 
		}
	
	public void update (float delta) {
		time += delta;
		if (anim.isAnimationFinished(time))
			remove = true;
	}
	
	public void render (SpriteBatch batch) { //barsm el explosion 3 howa animation fl satr 30 
		batch.draw (anim.getKeyFrame(time), x, y,SIZE, SIZE);
	} 
	
}
