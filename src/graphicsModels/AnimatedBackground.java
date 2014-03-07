package graphicsModels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;

public class AnimatedBackground extends JComponent implements Runnable{

	private BufferedImage background;
	private int lastY=0;
	private boolean stopAnimation=false;
	private int speed;
	
	public AnimatedBackground(BufferedImage image,int speed){
		this.speed=speed;
		this.background=image;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image temp=this.background.getSubimage(0, this.lastY,(int)this.getBounds().getWidth(),(int)this.getBounds().getHeight());
		g.drawImage(temp,(int)this.getBounds().getX(),(int)this.getBounds().getY(),(int)this.getBounds().getWidth(),(int)this.getBounds().getHeight(),this);
	}
	
	
	public void run(){
		while(!this.stopAnimation){
			if(this.lastY>=this.background.getHeight()-this.getBounds().getHeight()){
				this.lastY=0;
			}else{
				this.lastY++;
			}
			this.repaint();
			try {
				Thread.sleep(this.speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void startAnimation(){
		new Thread(this).start();
	}
}
