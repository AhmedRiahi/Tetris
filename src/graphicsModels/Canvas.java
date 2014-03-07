package graphicsModels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.Transient;
import java.util.ArrayList;

import javax.swing.JComponent;

import kernel.Shape;

public class Canvas extends JComponent{

	private ArrayList<TetrisShape> wall;
	private TetrisShape currentShape;
	private Cloud cloud;
	private Visualizer visualizer;
	
	public Canvas(Visualizer visualizer){
		this.wall=new ArrayList<TetrisShape>();
		this.visualizer=visualizer;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//this.drawGrid(g);
		this.drawBorder(g);
		for(TetrisShape shape:wall){
			shape.draw(g);
		}
		this.currentShape.draw(g);
		this.setBackground(new Color(0,0,0,50));
	}
	
	@Override
	@Transient
	public Dimension getPreferredSize() {
		return new Dimension((int)this.getBounds().getWidth(),(int)this.getBounds().getHeight());
	}
	
	public synchronized void addNewShape(Shape shape){
		if(this.currentShape!=null){
			TetrisShape ws=(TetrisShape) this.currentShape.clone();
			ws.setIsWall(true);
			this.wall.add(ws);
		}
		this.currentShape=new TetrisShape(shape);
		this.currentShape.setColor(this.getRandomColor());
		this.repaint();
	}
	
	public void drawGrid(Graphics g){
		for(int i=0;i<this.getHeight()/Drawable.blockHeight;i++){
			g.drawLine(this.getX(),i*Drawable.blockHeight, this.getWidth(),i*Drawable.blockWidth);
		}
		
		for(int i=0;i<this.getWidth()/Drawable.blockWidth;i++){
			g.drawLine(i*Drawable.blockWidth,0,i*Drawable.blockWidth,this.getHeight());
		}
	}
	
	public void drawBorder(Graphics g){
		g.setColor(new Color(100,0,0,150));
		g.drawRoundRect(0,0, this.getWidth()-1, this.getHeight(), 10, 10);
		g.setColor(new Color(0,0,0,50));
		g.fillRoundRect(0,0, this.getWidth()-1, this.getHeight(), 10, 10);
	}

	public Color getRandomColor(){
		int rand=(int) (Math.random()*7);
		switch(rand){
		case 0:
			return Color.red;
		case 1:
			return Color.magenta;
		case 2:
			return Color.blue;
		case 3:
			return Color.green;
		case 4:
			return Color.yellow;
		case 5:
			return Color.CYAN;
		case 6:
			return Color.orange;
		case 7:
			return Color.pink;
		}
		return Color.black;
	}

	public Cloud getCloud() {
		return cloud;
	}

	public void setCloud(Cloud cloud) {
		this.cloud = cloud;
	}

	public void showNextShape(Shape shape){
		this.visualizer.setShape(shape.clone());
		this.visualizer.repaint();
	}
}
