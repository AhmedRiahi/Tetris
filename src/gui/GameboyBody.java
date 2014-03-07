package gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import resources.ImagesReources;

public class GameboyBody extends JPanel implements MouseMotionListener,MouseListener{

	private GameFrame parent;
	private int x,y;
	private BufferedImage background;
	
	public GameboyBody(GameFrame parent,BufferedImage background){
		this.parent=parent;
		this.background=background;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.background,0,0,370,609, null);
	}
	
	public void setBackground(BufferedImage background){
		this.background=background;
		this.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x=e.getXOnScreen()-this.x;
		int y=e.getYOnScreen()-this.y;
		this.parent.setLocation(x,y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		this.x=e.getX();
		this.y=e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
