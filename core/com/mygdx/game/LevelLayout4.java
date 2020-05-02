package com.mygdx.game;		
import java.util.ArrayList;		
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import gameData.*;
		
	public class LevelLayout4 extends ScreenAdapter{
		    private State state = State.RUN;
		
			SpriteBatch batch;
			
			int collision = 0;
			boolean remove = false;
			
			boolean win = false;
			boolean freeze = false;
			boolean freeze2 = false;
			Music music;
			int countDataBase = 0;
			Texture bullet, spaceShip, virus, background;
			int time = 0;
			Level4 l;
			Virus v;
			static boolean enteredLevel4 = false;
			Vector2 spaceShipLoc = new Vector2();
			int count2=0;
			int count3=0;
			int score = 0;
			ArrayList<Bullet1> bulletManager = new ArrayList<Bullet1>();
			ArrayList<Circle> bulletCircle = new ArrayList<Circle>();
			int bulletSpeed;
			BitmapFont font;
			float x=0f;
			float x2=0f;
			float x3=0f;
			float x4=0f;
			float vh=200;
			float vw=345;
			float vh2=200;
			float vw2=345;
			float vh3=200;
			float vw3=345;
			float vh4=200;
			float vw4=345;
			Circle vb1;
			Circle vb2;
			Circle vb3;
			Circle vb4;
			Circle s;
			Circle vs1;
			Circle vs2;
			Circle vs3;
			Circle vs4;
			Circle vs5;
			Circle vs6;
			Circle vs7;
			Circle vs8;
			boolean switchpos= false;
			boolean switchpos2= false;
			boolean switchpos3= false;
			boolean switchpos4= false;
			boolean hit= false;
			boolean hit2= true;
			boolean hit3= true;
			boolean hit4= true;
			boolean hit5= true;
			boolean c=true;
			boolean c2=true;
			boolean c3=true;
			boolean c4=true;
			boolean m=true;
			boolean re=false;
			boolean n1=true;
			boolean n2=true;
			boolean n3=true;
			boolean n4=true;
			boolean d1=true;
			boolean d2=true;
			boolean d3=true;
			boolean d4=true;
			boolean q=false;
			boolean q2=false;
			boolean q3=false;
			boolean q4=false;
			boolean a=false;
			boolean a2=false;
			boolean a3=false;
			boolean a4=false;
			
		    float virusx=0;
		    float virusy=300;
		    float virusx2=100;
		    float virusy2=300;
		    float virusx3=200;
		    float virusy3=300;
		    float virusx4=300;
		    float virusy4=300;
		    ShapeRenderer sr;
			
		    Texture window, pause;
		    String returnToGamePlay, press, backToCharacter, backToLevel, restart;
		    
		    final VirusFighter game;
		    
		    LevelLayout4(final VirusFighter game){
		    	window = new Texture("MainUi/Window.png");
				pause = new Texture("Levels/pause.png");
				
				backToCharacter = "C to choose a character";
				backToLevel = "ESC to choose another level";
				restart = "R to restart your game";
				returnToGamePlay = "A to return to your Game";
				press = "Press:";
		    	
		    	this.game = game;		
				batch = new SpriteBatch();
				l = new Level4();
				v = new Virus();
				background = new Texture(l.getBackground());

				spaceShip = new Texture(l.getCharacter());
				virus = new Texture(v.getVirus4());
				bullet = new Texture(Level4.getBullet());
				bulletSpeed = l.getCharacterBulletSpeed();
				font =new BitmapFont (Gdx.files.internal("fonts/myfont.fnt"));
				spaceShipLoc.x = 130;
				spaceShipLoc.y = -30;
				sr=new ShapeRenderer();
				music = Gdx.audio.newMusic(Gdx.files.internal(l.getMusic()));
				enteredLevel4 = true;
				if(MainMenu.disable == false) {
					music.play();
				}
			}
			
		    public void update() {
		    	if(Gdx.input.isKeyPressed(Keys.RIGHT)&& (spaceShipLoc.x < Gdx.graphics.getWidth() - spaceShip.getWidth() / 5 +80)) {
		    		spaceShipLoc.x += Level4.getChSpeed(); 
		    		
		    	}
		    	if(Gdx.input.isKeyPressed(Keys.LEFT) && (spaceShipLoc.x > -80)) {
		    		spaceShipLoc.x -= Level4.getChSpeed();
		    		
		    	}
		    	if(Gdx.input.isKeyJustPressed(Keys.R)) {
		    		music.stop();
		    		enteredLevel4 = false;
		    		game.setScreen(new Map (game));
		    	}
		    	if(Gdx.input.isKeyJustPressed(Keys.C)) {
		    		music.stop();
		    		enteredLevel4 = false;
		    		game.setScreen(new CharactersMenu (game));
		    	}
		    	if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
		    		music.stop();
		    		enteredLevel4 = false;
		    		game.setScreen(new LevelsMenu (game));
		    	}
		    	if(Gdx.input.isKeyJustPressed(Keys.P)) {
		    		pause();
		    		remove = true;
		    	}
		    	if(Gdx.input.isKeyJustPressed(Keys.A)) {
		    		resume();
		    	}
		    }
		    
			@Override
			public void render (float delta) {
				game.batch.begin();
				update();
				
				switch (state){
			    case RUN:{
				game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				if(freeze == false && freeze2 == false) {
					font.setColor(Color.GREEN);
			        font.getData().scaleX = 2f;
			        font.getData().scaleY = 2f;
			    	font.draw(game.batch, "Instructions",150,430);
			    	font.setColor(Color.WHITE);
			    	font.getData().scaleY = 0.99f;
			        font.getData().scaleX = 0.99f;
					font.draw(game.batch, "Stomach has 12\n viruses, trying to escape from you\n so to win you should get a score which is\nto destroy them before they escape" , 50, 350);
					font.draw(game.batch, "Your controls:\n right and left buttons for moving on\n the sides which is your only movement", 50, 270);
					font.draw(game.batch, " Press space to fire", 50, 200);
					font.draw(game.batch, "Press fire to start game", 180, 50);
					if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
						freeze = true;
					}
				}
				else if(freeze == true && freeze2 == false) {
					time++;
					score = count2+count3;
					x +=Gdx.graphics.getRawDeltaTime();
					x2 +=Gdx.graphics.getRawDeltaTime();
					x3 +=Gdx.graphics.getRawDeltaTime();
					x4 +=Gdx.graphics.getRawDeltaTime();
					game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			    	game.batch.draw(spaceShip, spaceShipLoc.x, spaceShipLoc.y, spaceShip.getWidth() / 5, spaceShip.getHeight() / 5);		    
	 		    	if(hit2==true) {
			    	game.batch.draw(virus, virusx,virusy,  vw, vh);
			    	}
			    	if(hit3==true) {
			    		game.batch.draw(virus, virusx2,virusy2,  vw2, vh2);
			    	}
			    	if(hit4==true) {
			    		game.batch.draw(virus, virusx3,virusy3,  vw3, vh3);
			    	}
			    	if(hit5==true) {
			    		game.batch.draw(virus, virusx4,virusy4,  vw4, vh4);
			    	}
			    		int counter = 0;
				    	
				    	bulletCircle.clear();
				    	while(counter < bulletManager.size()) {
				    		Bullet1 currentBullet = bulletManager.get(counter);
				    		currentBullet.update();
				    		if(remove == true) {
				    			currentBullet.bulletLocation.y = 1000;
				    		}
				    			if(currentBullet.bulletLocation.x > -110 && currentBullet.bulletLocation.x < Gdx.graphics.getWidth() && currentBullet.bulletLocation.y > -40 && currentBullet.bulletLocation.y < Gdx.graphics.getHeight()) {
					    			game.batch.draw(bullet, currentBullet.bulletLocation.x, currentBullet.bulletLocation.y, bullet.getWidth() / 5, bullet.getHeight() / 5);
					    			bulletCircle.add(new Circle(currentBullet.bulletLocation.x+113,currentBullet.bulletLocation.y+75,10));
					    		}
					    		else {
					    			bulletManager.remove(counter);
					    			if(bulletManager.size() > 0) {
					        			counter--;
					    			}
					    		}
				    		
				    		counter++;
				    	}
			    	
			    	
			    	
			    	if(c==true)
			    	{
			    	vb1= new Circle(virusx+185,virusy+105,virus.getWidth()/40);
			    	}
			    	if(c2==true)
			    	{
			    	vb2= new Circle(virusx2+185,virusy2+105,virus.getWidth()/40);
			    	}
			    	if(c3==true)
			    	{
			    	vb3= new Circle(virusx3+185,virusy3+105,virus.getWidth()/40);
			    	}
			    	if(c4==true)
			    	{
			    	vb4= new Circle(virusx4+185,virusy4+105,virus.getWidth()/40);
			    	}
					s= new Circle(spaceShipLoc.x+110,spaceShipLoc.y+65,spaceShip.getWidth()/40);    	
			    	
			    		if (switchpos==true )
				    	{
			
					    	if (virusx>-150 )
					    	{
					    		virusx-=1;
					    	}
					    	else if (virusx==-150)
					    	{
					    		virusx=-150;
					    		switchpos=false;
					    	}
				    	
				    	}
				    	else if (virusx<420 ) {
			
					    		virusx+=1;
					    	}
					    	 if (virusx==420)
					    	{
					    		virusx=420;
					    		switchpos=true;
					    	}
					     if(x>0.7f)
					    	{
					    	 x-=0.7;
					    	 virusy-=10;
					    	}
					     if (switchpos2==true )
					    	{
				
						    	if (virusx2>-150 )
						    	{
						    		virusx2-=1;
						    	}
						    	else if (virusx2==-150)
						    	{
						    		virusx2=-150;
						    		switchpos2=false;
						    	}
					    	
					    	}
					    	else if (virusx2<420 ) {
				
						    		virusx2+=1;
						    	}
						    	 if (virusx2==420)
						    	{
						    		virusx2=420;
						    		switchpos2=true;
						    	}
						     
					    	 if(Intersector.overlaps(vb1, s))
			 	 				{
					    		    count2++;
					    		    vh=150;
			 	 				}
					    	 if(Intersector.overlaps(vb2, s))
			 	 				{
					    		    count2++;
					    		    vh2=150;
			 	 				}
					    	 if(Intersector.overlaps(vb3, s))
			 	 				{
					    		    count2++;
					    		    vh3=150;
			 	 				}
					    	 if(x2>0.5f)
						    	{
						    	 x2-=0.5;
						    	 virusy2-=10;
						    	}
					    	 if (switchpos3==true )
						    	{
					
							    	if (virusx3>-150 )
							    	{
							    		virusx3-=1;
							    	}
							    	else if (virusx3==-150)
							    	{
							    		virusx3=-150;
							    		switchpos3=false;
							    	}
						    	
						    	}
						    	else if (virusx3<420 ) {
					
							    		virusx3+=1;
							    	}
							    	 if (virusx3==420)
							    	{
							    		virusx3=420;
							    		switchpos3=true;
							    	}
							    	 if (switchpos4==true )
								    	{
							
									    	if (virusx4>-150 )
									    	{
									    		virusx4-=1;
									    	}
									    	else if (virusx4==-150)
									    	{
									    		virusx4=-150;
									    		switchpos4=false;
									    	}
								    	
								    	}
								    	else if (virusx4<420 ) {
							
									    		virusx4+=1;
									    	}
									    	 if (virusx4==420)
									    	{
									    		virusx4=420;
									    		switchpos4=true;
									    	}
							    	 if(x3>0.3f)
								    	{
								    	 x3-=0.3;
								    	 virusy3-=10;
								    	}
							    	 if(x4>0.3f)
								    	{
								    	 x4-=0.3;
								    	 virusy4-=10;
								    	}
							    	 
			    	
			    	for(int i=0; i < bulletCircle.size();i++)
			    	{
			    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vb1))
		    			{
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;
		    				
		    				vh-=5;
		    				//game.batch.draw(virus, virusx,virusy,  virus.getWidth() / 3-5, virus.getHeight() / 3-5);
		    				break;
		    			}
		    		}
			    	for(int i=0; i < bulletCircle.size();i++)
			    	{
			    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vb2))
		    			{
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;
		    				vh2-=5;
		    				//game.batch.draw(virus, virusx,virusy,  virus.getWidth() / 3-5, virus.getHeight() / 3-5);
		    				break;
		    			}
		    		}for(int i=0; i < bulletCircle.size();i++)
			    	{
			    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vb3))
		    			{
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;
		    				vh3-=5;
		    				//game.batch.draw(virus, virusx,virusy,  virus.getWidth() / 3-5, virus.getHeight() / 3-5);
		    				break;
		    			}
		    		}
		    		for(int i=0; i < bulletCircle.size();i++)
			    	{
			    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vb4))
		    			{
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;
		    				vh4-=5;
		    				//game.batch.draw(virus, virusx,virusy,  virus.getWidth() / 3-5, virus.getHeight() / 3-5);
		    				break;
		    			}
			    	}
			    if (vh<=150)
			    {
			    	
			    	hit2=false;
			    	c=false;
			    	if(n1==true)
			    	{
			    		vs1= new Circle(virusx+185+50,virusy+105+40,virus.getWidth()/40);
			    	}
			    	if(d1==true)
			    	{
			    		vs2= new Circle(virusx+185-90,virusy+105-40,virus.getWidth()/40);
			    	}
			    	vb1.set(0,0,0);
			    	if(q==false)
			    	{
			    		game.batch.draw(virus, virusx+100,virusy+50,  virus.getWidth() / 4, virus.getHeight() / 4);
			    	}
			    	if(a==false)
			    	{
			    	game.batch.draw(virus, virusx-50,virusy-30,  virus.getWidth() / 4, virus.getHeight() / 4);
			    	}
			    	if(Intersector.overlaps(vs1, s))
		 				{
		    		    count2++;
		    		    n1=false;
		    		    q=true;
		    		    vs1.set(0,0,0);
		 				}
			    	if(Intersector.overlaps(vs2, s))
	 				{
	    		    count2++;
	    		    d1=false;
	    		    a=true;
	    		    vs2.set(0,0,0);
	 				}
			    	for(int i=0; i < bulletCircle.size();i++)
			    	{		    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vs1))
		    			{
		    				q=true;
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;	
		    				n1=false;
		    				collision++;
		    				vs1.set(0,0,0);
		    				break;
		    			}
		    		}
			    	for(int i=0; i < bulletCircle.size();i++)
			    	{		    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vs2))
		    			{
		    				a=true;
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;
		    				collision++;
		    				d1=false;
		    				vs2.set(0,0,0);
		    				break;
		    			}
		    		}
			    }
			    ////////////vb2
				if (vh2 <=140)
			    {
			    	
			    	hit3=false;
			    	c2=false;
			    	if(n2==true)
			    	{
			    		vs3= new Circle(virusx2+185+50,virusy2+105+40,virus.getWidth()/40);
			    	}
			    	if(d2==true)
			    	{
			    		vs4= new Circle(virusx2+185-90,virusy2+105-40,virus.getWidth()/40);
			    	}
			    	vb2.set(0,0,0);
			    	if(q2==false)
			    	{
			    		game.batch.draw(virus, virusx2+100,virusy2+50,  virus.getWidth() / 4, virus.getHeight() / 4);
			    	}
			    	if(a2==false)
			    	{
			    	game.batch.draw(virus, virusx2-50,virusy2-30,  virus.getWidth() / 4, virus.getHeight() / 4);
			    	}
			    	if(Intersector.overlaps(vs3, s))
		 				{
		    		    count2++;
		    		    n2=false;
		    		    q2=true;
		    		    vs3.set(0,0,0);
		 				}
			    	if(Intersector.overlaps(vs4, s))
						{
				    count2++;
				    d2=false;
				    a2=true;
				    vs4.set(0,0,0);
						}
			    	for(int i=0; i < bulletCircle.size();i++)
			    	{		    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vs3))
		    			{
		    				q2=true;
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;	
		    				collision++;
		    				n2=false;
		    				vs3.set(0,0,0);
		    				break;
		    			}
		    		}
			    	for(int i=0; i < bulletCircle.size();i++)
			    	{		    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vs4))
		    			{
		    				a2=true;
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;
		    				collision++;
		    				d2=false;
		    				vs4.set(0,0,0);
		    				break;
		    			}
		    		}
			    }
				if (vh3 <=110)
			    {
			    	
			    	hit4=false;
			    	c3=false;
			    	if(n3==true)
			    	{
			    		vs5= new Circle(virusx3+185+50,virusy3+105+40,virus.getWidth()/40);
			    	}
			    	if(d3==true)
			    	{
			    		vs6= new Circle(virusx3+185-90,virusy3+105-40,virus.getWidth()/40);
			    	}
			    	vb3.set(0,0,0);
			    	if(q3==false)
			    	{
			    		game.batch.draw(virus, virusx3+100,virusy3+50,  virus.getWidth() / 4, virus.getHeight() / 4);
			    	}
			    	if(a3==false)
			    	{
			    	game.batch.draw(virus, virusx3-50,virusy3-30,  virus.getWidth() / 4, virus.getHeight() / 4);
			    	}
			    	if(Intersector.overlaps(vs5, s))
		 				{
		    		    count2++;
		    		    n3=false;
	    				collision++;
		    		    q3=true;
		    		    vs5.set(0,0,0);
		 				}
			    	if(Intersector.overlaps(vs6, s))
						{
				    count2++;
				    d3=false;
				    a3=true;
				    vs6.set(0,0,0);
						}
			    	for(int i=0; i < bulletCircle.size();i++)
			    	{		    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vs5))
		    			{
		    				q3=true;
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;	
		    				collision++;
		    				n3=false;
		    				vs5.set(0,0,0);
		    				break;
		    			}
		    		}
			    	for(int i=0; i < bulletCircle.size();i++)
			    	{		    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vs6))
		    			{
		    				a3=true;
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;
		    				collision++;
		    				d3=false;
		    				vs6.set(0,0,0);
		    				break;
		    			}
		    		}
			    }
				if (vh4<=130)
			    {
			    	
			    	hit5=false;
			    	c4=false;
			    	if(n4==true)
			    	{
			    		vs7= new Circle(virusx4+185+50,virusy4+105+40,virus.getWidth()/40);
			    	}
			    	if(d4==true)
			    	{
			    		vs8= new Circle(virusx4+185-90,virusy4+105-40,virus.getWidth()/40);
			    	}
			    	vb4.set(0,0,0);
			    	if(q4==false)
			    	{
			    		game.batch.draw(virus, virusx4+100,virusy4+50,  virus.getWidth() / 4, virus.getHeight() / 4);
			    	}
			    	if(a4==false)
			    	{
			    	game.batch.draw(virus, virusx4-50,virusy4-30,  virus.getWidth() / 4, virus.getHeight() / 4);
			    	}
			    	if(Intersector.overlaps(vs7, s))
		 				{
		    		    count2++;
		    		    n4=false;
		    		    q4=true;
		    		    vs7.set(0,0,0);
		 				}
			    	if(Intersector.overlaps(vs8, s))
						{
				    count2++;
				    d4=false;
				    a4=true;
				    vs8.set(0,0,0);
						}
			    	for(int i=0; i < bulletCircle.size();i++)
			    	{		    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vs7))
		    			{
		    				q4=true;
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;	
		    				collision++;
		    				n4=false;
		    				vs7.set(0,0,0);
		    				break;
		    			}
		    		}
			    	for(int i=0; i < bulletCircle.size();i++)
			    	{		    		
		    			if (Intersector.overlaps(bulletCircle.get(i), vs8))
		    			{
		    				a4=true;
		    				bulletCircle.remove(i);
		    				bulletManager.remove(i);
		    				count3++;
		    				collision++;
		    				d4=false;
		    				vs8.set(0,0,0);
		    				break;
		    			}
		    		}
			    }

		    	if(time > 1000) {
		    		enteredLevel4 = false;
		    		music.stop();
		    		if(collision == 8) {
						spaceShipLoc.y++;
						font.setColor(Color.GREEN);
				        font.getData().scaleX = 2f;
				        font.getData().scaleY = 2f;
				    	font.draw(game.batch, "Well Done!",150,430);
				    	font.setColor(Color.WHITE);
				    	font.getData().scaleY = 0.99f;
				        font.getData().scaleX = 0.99f;
						font.draw(game.batch, "Number of destroyed viruses:(12) unlockes\n   the FALCON spaceship" , 30, 290);
						font.draw(game.batch, "  Get ready for the Mother Virus in the heart!", 10, 210);
						font.draw(game.batch, "Press Enter to return!", 180, 50);
						if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
							UserName.unlockSpaceShip = true;
							game.setScreen(new CharactersMenu(game));		    	
							}
			    	VirusFighter.played = 4;
			    	countDataBase++;
	            	if(countDataBase == 1) {
	            		try {
							databaseClass.database();
						} catch (ClassNotFoundException e) {
							e.getStackTrace();
						}
	            	}
	            	if(Gdx.input.isKeyPressed(Keys.ALT_LEFT))
	            	{
				    	music.stop();
			    		enteredLevel4 = false;
		                LevelsMenu.tag = LevelsMenu.tag+1;
		                game.setScreen(new Map(game));
	            	}
					}
		    		else {
						font.setColor(Color.RED);
				        font.getData().scaleX = 2f;
				        font.getData().scaleY = 2f;
				    	font.draw(game.batch, "Game Over",150,430);
				    	font.setColor(Color.WHITE);
				    	font.getData().scaleY = 0.99f;
				        font.getData().scaleX = 0.99f;
						font.draw(game.batch, "Number of destroyed viruses sadly \ndidn't unlock the next level" , 100, 350);
						font.draw(game.batch, "Unfortunately viruses escaped to the Mother Virus", 10, 270);
						font.draw(game.batch, "Press Enter to return!", 180, 50);
						font.draw(game.batch, "Press R to play again!", 180, 70);

						if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
							game.setScreen(new CharactersMenu(game));		    	
							}
						if(Gdx.input.isKeyJustPressed(Keys.R)) {
							game.setScreen(new Map(game));		    	
						}
		    		}
		    			
		    	}else {
			    	font.draw(game.batch,String.valueOf(collision),300,450);
		    		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			    		Bullet1 myBullet = new Bullet1(spaceShipLoc, new Vector2(0, bulletSpeed));
			    		bulletManager.add(myBullet);
			    	}
		    	}
		    	
				
					
		    	}
				//sr.begin(ShapeType.Filled);
			    break;
			    }
			    case PAUSE:{
			    	game.batch.draw(window, 160, 140, window.getWidth()/3,window.getHeight()/4);
			    	
			    	game.batch.draw(pause, 240, 380, pause.getWidth()/3,pause.getHeight()/5);
			    	
			    	font.getData().scaleX = 0.6f;
			    	font.draw(game.batch, press, 175, 350);
			    	font.draw(game.batch, returnToGamePlay, 175, 310);
			    	font.draw(game.batch, backToCharacter, 175, 270); 
			    	font.draw(game.batch, backToLevel, 175, 230);
			    	font.draw(game.batch, restart, 175, 190);
			    	font.getData().scaleX = 1;
			        break;
			    }
			    default:
			        break;
			    }
				game.batch.end();
		    	}
			
			@Override
			public void pause(){
			    this.state = State.PAUSE;
			    remove = true;
			}

			@Override
			public void resume(){
			    this.state = State.RUN;
			    remove = false;
			}
			
			@Override
			public void dispose () {
				super.dispose();
			}
		}
