package entity;


import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player {

	public int x,y;
	public int speed;
	public int health;
	GamePanel gp;
	KeyHandler kh;
	public BufferedImage idle,attack1,attack2;
	public int spriteCounter=0;
	public int spriteNum=1;
	
	public Player(GamePanel gp,KeyHandler kh) {
		this.gp=gp;
		this.kh=kh;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		this.x=0;
		this.y=gp.screenHeight/2;
		this.speed=2;
		this.health=200;
	}
	
	public void getPlayerImage() {
		
		try {
			
			idle=ImageIO.read(getClass().getResourceAsStream("/player/sprite_0.png"));
			attack1=ImageIO.read(getClass().getResourceAsStream("/player/sprite_1.png"));
			attack2=ImageIO.read(getClass().getResourceAsStream("/player/sprite_2.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Enemy enemy) {
		
		if (this.health <= 0) {
	        GamePanel.game = false;
	        return; 
	    }
		addKill(enemy);
		
		if(collide(enemy) && kh.attack==true ) {
			enemy.health-=10;
		}
		if(x<-30) {
			x=-30;
		}else if(x>720) { 
			x=720;  
		}else if(y<-25) {
			y=-25;
		}else if(y>515) {
			y=515;  
		}    
		if(kh.upPressed==true) {
			this.y-=speed;
		}else if(kh.downPressed==true) {
			this.y+=speed;
		}else if(kh.rightPressed==true) {
			this.x+=speed;
		}else if(kh.leftPressed==true) { 
			this.x-=speed;
		}
		
		spriteCounter++;
		if(spriteCounter >10) {
			if(spriteNum==1) {
				spriteNum=2;
			}
			else if(spriteNum==2) {
				spriteNum=1;
			}
			spriteCounter=0;
		}
	}
		
		private void addKill(Enemy enemy) {
		
			if(enemy.health<=0) {
				GamePanel.kills++;
				enemy.setDefaultValues();
			}
		
	}

		public void draw(Graphics2D g) {
			BufferedImage image=idle;
			if(kh.attack==true) {
				if(spriteNum==1) {  
					image=attack1; 
				}
				else {
					image=attack2;
				}
				
			}
			g.drawImage(image,x,y,gp.tileSize*2,gp.tileSize*2,null);
			g.drawString("Health "+this.health, x+10, y+20);
		}
		
		public boolean collide(Enemy e) {
			return this.x-e.x==0 && this.y-e.y==0 ;
		}
		
	
	
}
