package Game3;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	int 	row ;
	int 	col ;
	Image   image;
	
	
	
	public Tile(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		this.image = null;
		try {
			this.image = ImageIO.read(new File("images/noPlant.png")).getScaledInstance(130, 130, 1);
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	
}
