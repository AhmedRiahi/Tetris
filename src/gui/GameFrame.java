package gui;

import eventListeners.GameboyListener;
import graphicsModels.SpecialButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import resources.ImagesReources;

public class GameFrame extends JFrame{

	private GameboyBody gameboyBody;
	private SpecialButton aButton,bButton,leftArrow,rightArrow,upArrow,downArrow; 
	
	public GameFrame(GameboyListener listener,GlobalScreen screen){
		//JPanel configuration
		this.gameboyBody=new GameboyBody(this,ImagesReources.gameboyBackground2);
		this.gameboyBody.setLayout(null);
		screen.setBounds(28,38,314,330);
		this.aButton=new SpecialButton(ImagesReources.gray_button,ImagesReources.gray_button_hover);
		this.bButton=new SpecialButton(ImagesReources.gray_button,ImagesReources.gray_button_hover);
		this.leftArrow=new SpecialButton(ImagesReources.arrow_left_button,ImagesReources.arrow_left_button_hover);
		this.rightArrow=new SpecialButton(ImagesReources.arrow_right_button,ImagesReources.arrow_right_button_hover);
		this.downArrow=new SpecialButton(ImagesReources.arrow_down_button,ImagesReources.arrow_down_button_hover);
		this.upArrow=new SpecialButton(ImagesReources.arrow_up_button,ImagesReources.arrow_up_button_hover);
		
		//bounds
		this.aButton.setBounds(220,454, 46,46);
		this.bButton.setBounds(286,435,46,46);
		this.leftArrow.setBounds(37,465,22,22);
		this.rightArrow.setBounds(97,465,22,22);
		this.upArrow.setBounds(68,435,22,22);
		this.downArrow.setBounds(66,495,22,22);
		
		//add buttons
		this.gameboyBody.add(this.aButton);
		this.gameboyBody.add(this.bButton);
		this.gameboyBody.add(this.downArrow);
		this.gameboyBody.add(this.leftArrow);
		this.gameboyBody.add(this.rightArrow);
		this.gameboyBody.add(this.upArrow);
		this.gameboyBody.add(screen);
		
		//JFrame configuration
		this.setContentPane(this.gameboyBody);
		this.setUndecorated(true);
		this.setBackground(new Color(0,0,0,0));
		this.setSize(new Dimension(370,609));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImagesReources.cursor , new Point(0,0), "img");
		this.setCursor (c);
		
		//eventListener
		this.aButton.addMouseListener(listener);
		this.bButton.addMouseListener(listener);
		this.leftArrow.addMouseListener(listener);
		this.rightArrow.addMouseListener(listener);
		this.upArrow.addMouseListener(listener);
		this.downArrow.addMouseListener(listener);
		
		//tag
		this.aButton.setTag("a");
		this.bButton.setTag("b");
		this.leftArrow.setTag("left");
		this.rightArrow.setTag("right");
		this.upArrow.setTag("up");
		this.downArrow.setTag("down");
	}
	
	public void changeSkin(BufferedImage background){
		this.gameboyBody.setBackground(background);
	}
}
