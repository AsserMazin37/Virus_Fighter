package gameData;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CharacterMenu;

public class Level4 extends Character{

	SpriteBatch batch;
	String background,character, virus, bullet;
	int bulletSpeed;
	static int start = 1000;

	public Level4(){
		setBackground();
		setCharacter();
		setCharacterBullet();
		setCharacterBulletSpeed();
	}

	@Override
	public void setBackground() {
		this.background = "NebulaRed.png";
	}

	@Override
	public void setCharacter() {
		if(CharacterMenu.getShipChoice() == 1) {
			this.character = "spaceShip1.png";
		}
		else if(CharacterMenu.getShipChoice() == 2) {
			this.character = "spaceShip2.png";
		}
		else {
			this.character = "spaceShip3.png";
		}
	}

	
	@Override
	public void setCharacterBullet() {
		this.bullet = "bullet2.png";
	}
	
	@Override
	public void setCharacterBulletSpeed() {
		this.bulletSpeed = 20;
	}

	@Override
	public String getBackground() {
		return this.background;
	}

	@Override
	public String getCharacter() {
		return this.character;
	}


	@Override
	public String getCharacterBullet() {
		return this.bullet;
	}

	@Override
	public int getCharacterBulletSpeed() {
		return this.bulletSpeed;
	}

	@Override
	public void setMusic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMusic() {
		// TODO Auto-generated method stub
		return null;
	}
}