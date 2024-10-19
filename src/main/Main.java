package main;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.setResizable(false);
        frame.setTitle("Pixel Survival"); 
        
        GamePanel gp = new GamePanel();
        JPanel labelPanel = new JPanel();  
        JLabel label = new JLabel("Kills: " + GamePanel.kills);
        
        label.setFont(new Font("Arial", Font.BOLD, 20)); 
        labelPanel.add(label);     
        frame.setLayout(new BorderLayout());   
        frame.add(labelPanel, BorderLayout.NORTH); 
        frame.add(gp, BorderLayout.CENTER);   
       
        frame.pack();
        frame.setVisible(true);    

        Timer timer = new Timer(100, e -> {
            label.setText("Kills: " + GamePanel.kills);
            if (!GamePanel.game) {
                showGameOverScreen(frame, gp, labelPanel, label, (Timer) e.getSource());
            }
        });
        timer.start();
        
        gp.startGameThread();
    }
    
    private static void showGameOverScreen(JFrame frame, GamePanel gp, JPanel labelPanel, JLabel label, Timer timer) {
        timer.stop(); 
        gp.stopGameThread(); 
        frame.remove(gp);
        frame.remove(labelPanel);
        
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
        
        JLabel gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));
        gameOverLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel scoreLabel = new JLabel("Score: " + GamePanel.kills);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        scoreLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 20));
        restartButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        
        restartButton.addActionListener(e -> {
            gp.resetGame(); 
            frame.remove(gameOverPanel); 
            frame.add(labelPanel, BorderLayout.NORTH); 
            frame.add(gp, BorderLayout.CENTER); 
            frame.revalidate();
            frame.repaint();
            
            
            gp.requestFocusInWindow();
            
            
            gp.startGameThread();
            
            
            Timer newTimer = new Timer(100, t -> {
                label.setText("Kills: " + GamePanel.kills);
                if (!GamePanel.game) {
                    showGameOverScreen(frame, gp, labelPanel, label, (Timer) t.getSource());
                }
            });  
            newTimer.start();
        });
        
        gameOverPanel.add(Box.createVerticalStrut(50));  
        gameOverPanel.add(gameOverLabel);
        gameOverPanel.add(Box.createVerticalStrut(20)); 
        gameOverPanel.add(scoreLabel);
        gameOverPanel.add(Box.createVerticalStrut(30)); 
        gameOverPanel.add(restartButton);
        
        frame.add(gameOverPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}
