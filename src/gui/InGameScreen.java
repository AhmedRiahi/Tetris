package gui;

import graphicsModels.AnimatedBackground;
import graphicsModels.Animator;
import graphicsModels.Canvas;
import graphicsModels.Drawable;
import graphicsModels.SpecialButton;
import graphicsModels.Visualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import manager.GameManager;
import resources.ImagesReources;


public class InGameScreen extends JLayeredPane{
	
	private JLabel scoreLabel,score;
	private Canvas canvas;
	private	Visualizer visualizer;
	private SpecialButton soundButton,exitButton,pauseButton;
	private AnimatedBackground background;
	private JLabel canvasBorder;
	private GameManager gameManager;
	private JComponent pausingLabel;

	
	public InGameScreen(GameManager gameManager){
		this.setLayout(null);
		this.gameManager=gameManager;
		this.background=new AnimatedBackground(ImagesReources.long_background,50);
		this.canvasBorder=new JLabel(new ImageIcon(ImagesReources.canvas_border));
		this.scoreLabel=new JLabel(new ImageIcon(ImagesReources.score_background));
		this.score=new JLabel("0");
		this.visualizer=new Visualizer();
		this.canvas=new Canvas(this.visualizer);
		this.soundButton=new SpecialButton(ImagesReources.sound_button_on, ImagesReources.sound_button_on);
		this.exitButton=new SpecialButton(ImagesReources.exit_x,ImagesReources.exit_x);
		this.pausingLabel=new JLabel(new ImageIcon(ImagesReources.pause_menu));
		
		//bounds
		this.background.setBounds(0,0,GlobalScreen.WIDTH,GlobalScreen.HEIGHT);
		this.scoreLabel.setBounds(0,0,150,30);
		this.score.setBounds(80, 0, 70, 30);
		this.soundButton.setBounds(200,0,30,30);
		this.exitButton.setBounds(230,0,30,30);
		this.canvas.setBounds(67,54,180,276);
		this.canvasBorder.setBounds(60,45,195,290);
		this.visualizer.setBounds(250,52,Drawable.blockWidth*5,Drawable.blockWidth*5);
		this.pausingLabel.setBounds((int)(this.canvas.getBounds().getX()+this.canvas.getBounds().getWidth()/2-90),(int)(this.canvas.getBounds().getY()+this.canvas.getBounds().getHeight()/2-40),180,80);
		
		//add components
		this.add(this.background,new Integer(0));
		this.add(this.scoreLabel,2);
		this.add(this.score,1);
		this.add(this.soundButton,3);
		this.add(this.exitButton);
		this.add(this.canvas,4);
		this.add(this.visualizer,5);
		
		this.background.startAnimation();
		this.moveToBack(this.background);
		this.moveToFront(this.canvas);
		this.score.setFont(new Font("Arial",Font.ROMAN_BASELINE,16));
		this.score.setForeground(Color.white);
		
		//TagName
		this.exitButton.setTag("exit_ingame");
		this.soundButton.setTag("sound_ingame");
		
		//actionListener
		this.exitButton.addMouseListener(this.gameManager.getScreenButtonslistener());
		this.soundButton.addMouseListener(this.gameManager.getScreenButtonslistener());
	}
	
	public void showPausingMenu(){
		this.add(this.pausingLabel);
		this.moveToFront(this.pausingLabel);
		this.repaint();
	}
	
	public void hidePausingMenu(){
		this.remove(this.pausingLabel);
		this.repaint();
	}
	
	public void addStarAnim(){
		JLabel label=new JLabel(new ImageIcon(ImagesReources.plus_10));
		label.setBounds(45,200,30,30);
		this.add(label);
		this.moveToFront(label);
		Animator animator=new Animator(label,label.getLocation(),new Point(0,0),10);
		animator.start();
	}
	//----------------------___________** GETTER/SETTER **___________----------------------
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public void updateScore(int value){
		this.score.setText(value+"");
	}
}
