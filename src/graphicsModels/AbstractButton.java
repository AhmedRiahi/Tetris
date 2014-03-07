package graphicsModels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;


public abstract class AbstractButton extends JComponent{

	protected Image simpleImage,hoverImage;
	private boolean isHovering=false;
	protected String tag;
	
	public AbstractButton(){
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				isHovering=true;
				repaint();
			}
			
			public void mouseExited(MouseEvent e){
				isHovering=false;
				repaint();
			}
		});
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(this.isHovering){
			g.drawImage(this.hoverImage,0,0,this.getWidth(),this.getHeight(),null);
		}else{
			g.drawImage(this.simpleImage,0,0,this.getWidth(),this.getHeight(),null);
		}
	}

	
	
	
	//--------------------------__________** GETTER/SETTTER **__________--------------------------------
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
