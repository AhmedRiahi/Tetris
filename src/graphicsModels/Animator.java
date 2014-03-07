package graphicsModels;

import java.awt.Point;

import javax.swing.JComponent;

public class Animator extends Thread{

	public JComponent component;
	private Point start,end;
	private long time;
	private long distance;
	private long sleepTime;
	private int stepX,stepY;
	
	public Animator(JComponent component,Point start,Point end,int time){
		this.component=component;
		this.start=start;
		this.end=end;
		this.time=time;
		int diffX=(int) (end.getX()-start.getX());
		int diffY=(int) (end.getY()-start.getY());
		this.distance=(long)Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2));
		this.sleepTime=time/this.distance;
		
		this.stepX=start.getX()<end.getX()?1:-1;
		this.stepY=start.getY()<end.getY()?1:-1;
		
	}
	
	public void run(){
		while(this.component.getX()!=this.end.getX() || this.component.getY()!=this.end.getY()){
			int newY=0;
			int newX=0;
			if(this.component.getY()!=this.end.getY()){
				newY=(int)this.component.getY()+this.stepY;
			}else{
				newY=(int)this.component.getY();
			}
			if(this.component.getX()!=this.end.getX()){
				newX=(int)this.component.getX()+this.stepY;
			}else{
				newX=(int)this.component.getX();
			}
			
			this.component.setBounds(newX,newY,(int)this.component.getWidth(),(int)this.component.getHeight());
			this.component.getParent().repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.component.getParent().remove(this.component);
	}
}
