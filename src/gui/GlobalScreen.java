package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import manager.GameManager;
import eventListeners.ScreenButtonsListener;

public class GlobalScreen extends JPanel implements MouseMotionListener{

	private GameManager gameManager;
	private LoadingScreen loadingScreen;
	private MainMenuScreen mainMenuScreen;
	private InGameScreen inGameScreen;
	private OptionScreen optionScreen;
	public static final int WIDTH=314,HEIGHT=330;
	
	
	public GlobalScreen(GameManager gameManager){
		this.gameManager=gameManager;
		this.setLayout(null);
		this.setBackground(Color.black);
		this.addMouseMotionListener(this);
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
	public void showLoadingScreen(){
		this.removeAll();
		this.loadingScreen=new LoadingScreen();
		this.loadingScreen.setBounds(0,0,WIDTH,HEIGHT);
		this.add(this.loadingScreen);
		this.repaint();
	}
	
	public void showMainMenuScreen(){
		this.removeAll();
		this.mainMenuScreen=new MainMenuScreen(this.gameManager);
		this.mainMenuScreen.setBounds(0,0,WIDTH,HEIGHT);
		this.add(this.mainMenuScreen);
		this.repaint();
	}
	
	public void showGameScreen(){
		this.removeAll();
		this.inGameScreen=new InGameScreen(this.gameManager);
		this.inGameScreen.setBounds(0,0,WIDTH,HEIGHT);
		this.add(this.inGameScreen);
		this.repaint();
	}
	
	public void showOptionScreen(){
		this.removeAll();
		this.optionScreen=new OptionScreen(this.gameManager);
		this.optionScreen.setBounds(0,0,WIDTH,HEIGHT);
		this.add(this.optionScreen);
		this.repaint();
	}


	//----------------------___________** GETTER/SETTER **___________----------------------
	public InGameScreen getInGameScreen() {
		return inGameScreen;
	}

	public void setInGameScreen(InGameScreen inGameScreen) {
		this.inGameScreen = inGameScreen;
	}
	
	public OptionScreen getOptionScreen(){
		return this.optionScreen;
	}
}
