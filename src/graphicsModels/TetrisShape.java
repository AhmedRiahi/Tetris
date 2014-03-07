package graphicsModels;

import java.awt.Color;
import java.awt.Graphics;

import kernel.Shape;

public class TetrisShape implements Drawable,Cloneable{
	private Shape abstractShape;
	private Color color;
	private boolean debugMode=false;
	private boolean isWall=false;
	
	public TetrisShape(Shape shape){
		this.abstractShape=shape;
		this.color=Color.GREEN;
	}

	@Override
	public void drawAtPosition(Graphics g,int x,int y) {
		this.abstractShape.setX(x);
		this.abstractShape.setY(y);
		this.draw(g);
	}
	
	
	@Override
	public void draw(Graphics g) {
		int[][] data=this.abstractShape.getData();
		int x=this.abstractShape.getX();
		int y=this.abstractShape.getY();
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[0].length;j++){
				if(data[i][j]==1){
					g.setColor(this.color);
					if(this.isWall){
						g.fillRect((x+j)*Drawable.blockWidth,(y+i)*Drawable.blockHeight,Drawable.blockWidth,Drawable.blockHeight);
						g.setColor(Color.darkGray);
						g.drawRect((x+j)*Drawable.blockWidth,(y+i)*Drawable.blockHeight,Drawable.blockWidth,Drawable.blockHeight);
					}else{
						g.fill3DRect((x+j)*Drawable.blockWidth,(y+i)*Drawable.blockHeight,Drawable.blockWidth,Drawable.blockHeight,true);
					}
				}else{
					if(this.debugMode){
						g.setColor(Color.red);
						g.fill3DRect((x+j)*Drawable.blockWidth,(y+i)*Drawable.blockHeight,Drawable.blockWidth,Drawable.blockHeight,true);
					}
				}
			}
		}
	}
	
	public Object clone(){
		Object o=null;
		try {
			o=super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public String toString(){
		return this.abstractShape.toString();
	}

	//----------------------___________** GETTER/SETTER **___________----------------------
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Shape getAbstractShape(){
		return this.abstractShape;
	}
	
	public void setIsWall(boolean isWall){
		this.isWall=isWall;
	}
}
