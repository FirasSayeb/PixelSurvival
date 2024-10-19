package entity;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemy {

	public int x,y;
	public int speed;
	public int health;
	public BufferedImage idle;
	GamePanel gp;
	
	
	
	public Enemy(GamePanel gp) {
		this.gp=gp;
		setDefaultValues();
		getEnemyImage();
	}
	
	public void setDefaultValues() {
		this.x=60;
		this.y=gp.screenHeight/4; 
		this.speed=1;
		this.health=20;
		
	}
	
public void getEnemyImage() {
		
		try {
			
			idle=ImageIO.read(getClass().getResourceAsStream("/player/sprite_3.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

public void update(Player player) {
	
	if(collide(player) ) {
		player.health-=4; 
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
	
	if(this.x-player.x >0) {
		this.x-=speed;	
		
}else if(this.x-player.x <0) {
	this.x+=speed;	 
	
}if(this.y-player.y >0) {
	this.y-=speed;	
	}else if(this.y-player.y <0) {
this.y+=speed;	 

}

  
}
public void draw(Graphics2D g) {
	g.drawImage(idle,x,y,gp.tileSize*2,gp.tileSize*2,null);
	g.drawString("Health "+this.health, x+10, y+20);
}
    
public boolean collide(Player p) {
	return this.x-p.x==0 && this.y-p.y==0 ;
}
}




