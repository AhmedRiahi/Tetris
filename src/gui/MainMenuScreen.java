package gui;

import graphicsModels.AnimatedBackground;
import graphicsModels.SpecialButton;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import manager.GameManager;
import resources.ImagesReources;


public class MainMenuScreen extends JLayeredPane{
	
	private SpecialButton start,option,exit;
	private AnimatedBackground background;
	private JLabel menuBg;
	
	
	public MainMenuScreen(GameManager gameManager){
		this.background=new AnimatedBackground(ImagesReources.long_background,20);
		this.menuBg=new JLabel(new ImageIcon(ImagesReources.mainMenuBackground));
		this.start=new SpecialButton(ImagesReources.start_button,ImagesReources.start_button_hover);
		this.option=new SpecialButton(ImagesReources.option_button,ImagesReources.option_button_hover);
		this.exit=new SpecialButton(ImagesReources.exit_button,ImagesReources.exit_button_hover);
		
		//bounds
		this.background.setBounds(0,0,GlobalScreen.WIDTH,GlobalScreen.HEIGHT);
		this.menuBg.setBounds(32,30,250,280);
		this.start.setBounds(85,105,145,48);
		this.option.setBounds(85,165,145,48);
		this.exit.setBounds(85,225,145,48);
		
		
		//add
		this.add(this.background);
		this.add(this.menuBg);
		this.add(this.start);
		this.add(this.option);
		this.add(this.exit);
		
		
		//listener
		this.exit.addMouseListener(gameManager.getScreenButtonslistener());
		this.start.addMouseListener(gameManager.getScreenButtonslistener());
		this.option.addMouseListener(gameManager.getScreenButtonslistener());
		
		//tag
		this.start.setTag("start_game");
		this.exit.setTag("exit");
		this.option.setTag("option");
		
		this.moveToBack(this.menuBg);
		this.moveToBack(this.background);
		this.background.startAnimation();
	}
	
	
	/*public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ImagesReources.mainMenuBackground,0,0,this.getWidth(),this.getHeight(), null);
	}*/
}
