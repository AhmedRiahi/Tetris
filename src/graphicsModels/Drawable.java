package graphicsModels;

import java.awt.Graphics;

public interface Drawable {
	final int blockWidth=12;
	final int blockHeight=12;
	
	
	public void draw(Graphics g);
	public void drawAtPosition(Graphics g,int x,int y);
}
