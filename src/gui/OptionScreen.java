package gui;

import java.awt.Point;

import graphicsModels.AnimatedBackground;
import graphicsModels.SpecialButton;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import manager.GameManager;
import resources.ImagesReources;

public class OptionScreen extends JLayeredPane{
	private AnimatedBackground background;
	private JLabel optionBG,levelBG,skinBG;
	private SpecialButton levelButton,redButton,blueButton,backButton;
	private GameManager gameManager;
	private JLabel[] pins=new JLabel[5];
	private final Point[] pinsPos=new Point[]{new Point(66,169),new Point(94,169),new Point(122,169),new Point(150,169),new Point(178,169)};
	
	public OptionScreen(GameManager gameManager){
		this.gameManager=gameManager;
		this.background=new AnimatedBackground(ImagesReources.long_background,20);
		this.optionBG=new JLabel(new ImageIcon(ImagesReources.option_bg));
		this.levelBG=new JLabel(new ImageIcon(ImagesReources.level_bg));
		this.skinBG=new JLabel(new ImageIcon(ImagesReources.skin_bg));
		this.levelButton=new SpecialButton(ImagesReources.level_btn,ImagesReources.level_btn);
		this.redButton=new SpecialButton(ImagesReources.skin_red_btn,ImagesReources.skin_red_btn);
		this.blueButton=new SpecialButton(ImagesReources.skin_blue_btn,ImagesReources.skin_blue_btn);
		this.backButton=new SpecialButton(ImagesReources.back_btn, ImagesReources.back_btn);
		
		
		//bounds
		this.background.setBounds(0,0,GlobalScreen.WIDTH,GlobalScreen.HEIGHT);
		this.optionBG.setBounds(32,30,250,280);
		this.levelBG.setBounds(45,95,214,100);
		this.skinBG.setBounds(50,190,214,100);
		this.levelButton.setBounds(218,152,33,35);
		this.redButton.setBounds(120,240,39,41);
		this.blueButton.setBounds(160,240,39,41);
		this.backButton.setBounds(0,0,35,37);
		
		//add
		this.add(this.background,1);
		this.add(this.optionBG,10);
		this.add(this.levelBG,3);
		this.add(this.skinBG,4);
		this.add(this.levelButton,2);
		this.add(this.redButton,2);
		this.add(this.blueButton,2);
		this.add(this.backButton,2);
		
		this.background.startAnimation();
		this.moveToBack(this.optionBG);
		this.moveToBack(this.background);
		
		//listener
		this.levelButton.addMouseListener(gameManager.getScreenButtonslistener());
		this.redButton.addMouseListener(gameManager.getScreenButtonslistener());
		this.blueButton.addMouseListener(gameManager.getScreenButtonslistener());
		this.backButton.addMouseListener(gameManager.getScreenButtonslistener());
		
		//TagName
		this.levelButton.setTag("level");
		this.redButton.setTag("red_skin");
		this.blueButton.setTag("blue_skin");
		this.backButton.setTag("back");
	}
	
	
	public void updatePins(int nbPins){
		this.removePins();
		for(int i=0;i<nbPins;i++){
			this.pins[i]=new JLabel(new ImageIcon(ImagesReources.level_pin));
			this.pins[i].setBounds((int)this.pinsPos[i].getX(),(int)this.pinsPos[i].getY(),25,13);
			this.add(this.pins[i]);
			this.moveToFront(this.pins[i]);
		}
	}
	
	public void removePins(){
		for(int i=0;i<this.pins.length;i++){
			if(pins[i]!=null){
				this.remove(this.pins[i]);
			}
		}
	}
	
	
}
