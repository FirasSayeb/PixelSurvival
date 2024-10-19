package main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JPanel;

import entity.Enemy;
import entity.Player;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable {
 
    public final int initialSize = 16;
    public final int scale = 3;
    public final int tileSize = initialSize * scale;
    public final int maxCol = 16;
    public final int maxRow = 12; 
    public final int screenWidth = tileSize * maxCol;
    public final int screenHeight = tileSize * maxRow;
    public static int kills=0;
    public static boolean game=true;
    Thread thread;
    KeyHandler kh = new KeyHandler();
   TileManager tg=new TileManager(this);
   Player player=new Player(this,kh);   
   Enemy enemy=new Enemy(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

   

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }
    public void stopGameThread() {
        thread = null;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (thread != null && game) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
    	 if (game) {
    		 player.update(enemy);  
    	        enemy.update(player);        
         } else {
             stopGameThread(); 
         }
    	
       }    
        
    public void resetGame() {
        
        player.setDefaultValues();
        
        kills = 0;
        
        game = true; 
    }


  
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tg.draw(g2);
        player.draw(g2);
        enemy.draw(g2);
        g2.dispose();
    }
    
    
    

   
}
