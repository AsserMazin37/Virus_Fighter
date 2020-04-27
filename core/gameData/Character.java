package gameData;

import com.mygdx.game.CharactersMenu;

public abstract class Character{

	private static int chSpeed;
	public static int getChSpeed() {
		return chSpeed;
	}
	private void setChSpeed(int chSpeed) {
		this.chSpeed = chSpeed;
	}
	public abstract void setBackground();
	public abstract void setCharacter();
	public abstract void setCharacterBullet();
	public abstract void setCharacterBulletSpeed();
	public abstract String getBackground();
	public abstract String getCharacter();
	public abstract String getCharacterBullet();
	public abstract int getCharacterBulletSpeed();
	public abstract void setMusic();
	public abstract String getMusic();
	Character(){
		if(CharactersMenu.getShipChoice() == 1) {
			setChSpeed(3);
		}
		else if(CharactersMenu.getShipChoice() == 2) {
			setChSpeed(5);
		}
		else
			setChSpeed(7);
	}
	
}