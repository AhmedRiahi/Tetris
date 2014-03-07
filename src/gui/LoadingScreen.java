package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import resources.ImagesReources;

public class LoadingScreen extends JPanel{
	
	public LoadingScreen(){
		this.setLayout(null);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ImagesReources.loadingBackground,0,0,this.getWidth(),this.getHeight(), null);
	}
}
