import java.awt.Image;

import javax.swing.ImageIcon;

public class Boss extends Enemies{
	
public Image getshipImg;
public int getX;
public int getY;
public int getW;
public int getH;


	public Boss() {
		super();
	}
	
	public Boss(int x, int y) {
		super(x, y, new ImageIcon("bossidle.gif"));
	}
}