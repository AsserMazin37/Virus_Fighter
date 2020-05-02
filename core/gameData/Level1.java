package gameData;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CharactersMenu;

public class Level1 extends Character{

	SpriteBatch batch;
	String background,character, virus, bullet,music;
	int bulletSpeed;
	

	public Level1(){
		setBackground();
		setCharacter();
		//setCharacterBullet();
		setCharacterBulletSpeed();
		setMusic();
		
	}

	@Override
	public void setBackground() {
		this.background = "Backgrounds/NebulaBlue.png";
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
	
	/*@Override
	public void setCharacterBullet(String bullet) {
		this.bullet = "bullets/xbullet.png";
	}*/
	
	@Override
	public void setCharacterBulletSpeed() {
		this.bulletSpeed = 20;
	}

	@Override
	public String getBackground() {
		// TODO Auto-generated method stub
		return background;
	}

	@Override
	public String getCharacter() {
		// TODO Auto-generated method stub
		return character;
	}

	/*@Override
	public String getCharacterBullet() {
		// TODO Auto-generated method stub
		return bullet;
	}
*/
	@Override
	public int getCharacterBulletSpeed() {
		// TODO Auto-generated method stub
		return bulletSpeed;
	}

	@Override
	public void setMusic() {
		// TODO Auto-generated method stub
		music = "music/level1.mp3";
	}

	@Override
	public String getMusic() {
		// TODO Auto-generated method stub
		return music;
	}
}
