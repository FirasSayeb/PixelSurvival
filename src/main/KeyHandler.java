package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean upPressed;
	public boolean downPressed; 
	public boolean rightPressed;
	public boolean leftPressed;
	public boolean attack;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_W) {
            upPressed=true;		
 		}else if(code==KeyEvent.VK_D) {
 			rightPressed=true;
		}else if(code==KeyEvent.VK_A) {
			leftPressed=true;
		}else if(code==KeyEvent.VK_S) {
			downPressed=true;
		}else if(code==KeyEvent.VK_X) {
			attack=true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) { 
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_W) {
            upPressed=false;		
 		}else if(code==KeyEvent.VK_D) {
 			rightPressed=false;
		}else if(code==KeyEvent.VK_A) {
			leftPressed=false;
		}else if(code==KeyEvent.VK_S) {
			downPressed=false;
		}else if(code==KeyEvent.VK_X) {
			attack=false;
		}
		
	}

} 
