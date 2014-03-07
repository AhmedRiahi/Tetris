package graphicsModels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.Transient;

import javax.swing.JComponent;
import javax.swing.JPanel;

import kernel.Shape;

public class Visualizer extends JComponent{
	
	private TetrisShape shape;
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//this.setBackground(new Color(0,0,0,0));
		if(this.shape!=null){
			this.shape.draw(g);
		}
	}
	
	@Override
	@Transient
	public Dimension getPreferredSize() {
		return new Dimension((int)this.getBounds().getWidth(),(int)this.getBounds().getHeight());
	}
	
	
	public void setShape(Shape shape){
		this.shape=new TetrisShape(shape);
	}
}
