package gameData;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CharactersMenu;

public class Level4 extends Character{

	SpriteBatch batch;
	String background,character, virus, bullet,music;
	int bulletSpeed;
	static int start = 1000;

	public Level4(){
		setBackground();
		setCharacter();
		setCharacterBulletSpeed();
		setMusic();
	}

	@Override
	public void setBackground() {
		this.background = "Backgrounds/NebulaRed.png";
	}

	@Override
	public void setCharacter() {
		if(CharactersMenu.getShipChoice() == 1) {
			this.character = "SpaceShips/spaceShip1.png";
		}
		else if(CharactersMenu.getShipChoice() == 2) {
			this.character = "SpaceShips/spaceShip2.png";
		}
		else {
			this.character = "SpaceShips/spaceShip3.png";
		}
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
	public int getCharacterBulletSpeed() {
		return this.bulletSpeed;
	}

	@Override
	public void setMusic() {
		// TODO Auto-generated method stub
		this.music = "music/mission3.mp3";
	}

	@Override
	public String getMusic() {
		// TODO Auto-generated method stub
		return this.music;
	}
}
