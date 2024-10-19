package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	
	public TileManager(GamePanel gp) {
		this.gp=gp;
		tile=new Tile[2];
		getTileImage();
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0]=new Tile();
			tile[0].image=ImageIO.read(getClass().getResourceAsStream("/player/grass.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
		
		int col=0;
		int row=0;
		int x=0;
		int y=0;
		
		while(col<gp.maxCol && row<gp.maxRow) {
			g.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x+=gp.tileSize;
			if(col==gp.maxCol) {
				col=0;
				x=0;
				y+=gp.tileSize;
				row++;
			}
		}
		
		
	}
	
}
