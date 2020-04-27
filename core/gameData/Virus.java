package gameData;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.LevelsMenu;

public class Virus {
	SpriteBatch batch;
	String background,character, virusMaster5 , virusBullets5 , bullet , virus1 , virus2 , virus3 , virus4 ,  asteroid, secondVirus;
	int bulletSpeed , virusBulletSpeed;
	private static int SPEED = 270;

	public Virus()
	{
		if(LevelsMenu.tag==0)
		{
			setVirus1();
		}
		else if(LevelsMenu.tag==1)
		{
			setAsteroid();
			setVirus2();
		}
		else if(LevelsMenu.tag==2)
		{
			
			setVirus3();
			setSecondVirus();
		}
	    else if(LevelsMenu.tag==3)
		{
	    	setVirus4();
	    	
		}
		else if(LevelsMenu.tag==4)
		{
			setVirus5();
			setVirusBullets5();
	    	setVirusBulletSpeed5();
	    	
		}
		/*else if(LevelsMenu.tag==5)
		{
			
		}*/
	}

	private void setVirus1() {
		this.virus1 = "viruses/virus3.png";
	}
	public String getVirus1() {
		return this.virus1;
	}
	
	public static int getVirus2Speed()
	{
		return SPEED;
	}
	private void setAsteroid() {
		asteroid="Levels/hiclipart.com2.png";
	}

	public String getAsteroid() {
		return asteroid;
	}

	public String getVirus2() {
		return this.virus2;
	}


	private void setVirus2() {
		this.virus2 = "viruses/virus3.png";	
	}

	
	private void setVirus3() {
		this.virus3 = "viruses/virus5.png";
	}
	public String getVirus3() {
		return this.virus3;
	}
	public String getSecondVirus() {
		return this.secondVirus;
	}

	private void setSecondVirus() {
		this.secondVirus = "viruses/virus5.png";
	}
	 private void virusOneMoveDown(Vector2 spaceShipLoc, Vector2 firstVirusLoc) {
		 if(firstVirusLoc.y > (spaceShipLoc.y + 30)) {
			 firstVirusLoc.y -= 10;
		 }
	 }

	private void virusOneMoveRight(Vector2 firstVirusLoc, int rightWidth) {
   	 if(firstVirusLoc.x < rightWidth) {
   		 firstVirusLoc.x += 10;
   	 }
    }

    private void virusOneMoveLeft(Vector2 firstVirusLoc, int leftWidth) {
   	 if(firstVirusLoc.x > leftWidth) {
   		 firstVirusLoc.x -= 10;
   	 }
    }

    private void virusTwoMoveDown(Vector2 spaceShipLoc, Vector2 secondVirusLoc) {
		 if(secondVirusLoc.y > (spaceShipLoc.y + 30)) {
			 secondVirusLoc.y -= 10;
		 }
	 }

    private void virusTwoMoveRight(Vector2 secondVirusLoc, int rightWidth) {
   	 if(secondVirusLoc.x < rightWidth) {
   		 secondVirusLoc.x += 10;
   	 }
	 }
    
    private void virusTwoMoveLeft(Vector2 secondVirusLoc, int leftWidth) {
   	 if(secondVirusLoc.x > leftWidth) {
   		 secondVirusLoc.x -= 10;
   	 }
	 }

	 public void generateRandom(Vector2 spaceShipLoc, Vector2 firstVirusLoc, Vector2 secondVirusLoc, int rightWidth, int leftWidth, Random virusOneRand, Random virusTwoRand, boolean firstVirusKilled, boolean secondVirusKilled) {
		 if(!firstVirusKilled) {
			 int virusOneMovement = virusOneRand.nextInt(6);
			 if(virusOneMovement == 0 || virusOneMovement == 3) {
				 virusOneMoveDown(spaceShipLoc, firstVirusLoc);
			 }
			 else if(virusOneMovement == 1 || virusOneMovement == 4) {
				 virusOneMoveRight(firstVirusLoc, rightWidth);
			 }
			 else if(virusOneMovement == 2 || virusOneMovement == 5) {
				 virusOneMoveLeft(firstVirusLoc, leftWidth); 
			 }
		 }
		 
		 if(!secondVirusKilled) {
			 int virusTwoMovement = virusTwoRand.nextInt(6);
			 if(virusTwoMovement == 0 || virusTwoMovement == 3) {
				 virusTwoMoveDown(spaceShipLoc, secondVirusLoc);
			 }
			 else if(virusTwoMovement == 1 || virusTwoMovement == 4) {
				 virusTwoMoveRight(secondVirusLoc, rightWidth);
			 }
			 else if(virusTwoMovement == 2 || virusTwoMovement == 5) {
				 virusTwoMoveLeft(secondVirusLoc, leftWidth); 
			 } 
		 }
	 }
	 
	private void setVirus4() {
		
		this.virus4 = "viruses/virus3.png";
	}

	public String getVirus4() {
		
		return virus4;
	}
	

	
	private void setVirus5() {
		this.virusMaster5 = "viruses/virus5.png";
	}

	public String getVirus5() {
		return this.virusMaster5;
	}
	public void setVirusBulletSpeed5()
    {
    	this.virusBulletSpeed = 3;
    }
	private void setVirusBullets5(){
		 this.virusBullets5 = "viruses/virus15.png";	
	}
	public String getVirusBullets5(){
		return this.virusBullets5;	
	}
	public int getVirusBulletSpeed5() {
		return this.virusBulletSpeed;
	}
	
	
	
}