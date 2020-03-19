package gameData;

public abstract class Level {

	public abstract void setBackground();
	public abstract void setCharacter();
	public abstract void setVirus();
	public abstract void setBullet();
	public abstract void setBulletSpeed();
	public abstract String getBackground();
	public abstract String getCharacter();
	public abstract String getVirus();
	public abstract String getBullet();
	public abstract int getBulletSpeed();
}