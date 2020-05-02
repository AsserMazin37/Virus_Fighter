package gameData;

import com.mygdx.game.CharactersMenu;

public abstract class Character{

	private static int chSpeed;
	private static String bullet;
	
	public abstract void setBackground();
	public abstract void setCharacter();
	//public abstract void setCharacterBullet();
	public abstract void setCharacterBulletSpeed();
	public abstract String getBackground();
	public abstract String getCharacter();
	//public abstract String getCharacterBullet();
	public abstract int getCharacterBulletSpeed();
	public abstract void setMusic();
	public abstract String getMusic();
	Character(){
		if(CharactersMenu.getShipChoice() == 1) {
			setChSpeed(3);
			setBullet("bullets/bullet2.png");
		}
		else if(CharactersMenu.getShipChoice() == 2) {
			setChSpeed(5);
			setBullet("bullets/xbullet.png");
		}
		else {
			setChSpeed(7);
			setBullet("bullets/new_bullet.png");
		}
	}
	public static String getBullet() {
		return bullet;
	}
	public static void setBullet(String bullet) {
		Character.bullet = bullet;
	}
	public static int getChSpeed() {
		return chSpeed;
	}
	private void setChSpeed(int chSpeed) {
		this.chSpeed = chSpeed;
	}
	
}
