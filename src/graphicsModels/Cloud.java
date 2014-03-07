package graphicsModels;

import java.awt.Graphics;
import java.awt.Image;

public class Cloud implements Drawable{
	
	private Image image;
	private int width,height;
	private int posX,posY;
	
	@Override
	public void draw(Graphics g) {
		//g.drawImage(image, posX,posY,width,height, null);
	}

	@Override
	public void drawAtPosition(Graphics g, int x, int y) {
		this.posX=x;
		this.posY=y;
		this.draw(g);
	}
	
	
	//-----------------_______ SETTER**GETTER _________------------------
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
