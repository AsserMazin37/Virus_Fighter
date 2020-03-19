package gameData;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CharacterMenu;

public class Level1 extends Level{

	SpriteBatch batch;
	String background,character, virus, bullet;
	int bulletSpeed;
	static int start = 1000;

	public Level1(){
		super();
		setBackground();
		setCharacter();
		setVirus();
		setBullet();
		setBulletSpeed();
	}

	@Override
	public void setBackground() {
		this.background = "BG.png";
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
	public void setVirus() {
		this.virus = "virus3.png";
	}


	@Override
	public void setBullet() {
		this.bullet = "bullet2.png";
	}
	
	@Override
	public void setBulletSpeed() {
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
	public String getVirus() {
		return this.virus;
	}

	@Override
	public String getBullet() {
		return this.bullet;
	}

	@Override
	public int getBulletSpeed() {
		return this.bulletSpeed;
	}
}