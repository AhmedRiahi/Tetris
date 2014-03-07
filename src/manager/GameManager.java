package manager;

import eventListeners.GameboyListener;
import eventListeners.KeyBoardListener;
import eventListeners.ScreenButtonsListener;
import graphicsModels.Canvas;
import graphicsModels.Drawable;
import gui.GameFrame;
import gui.GlobalScreen;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import kernel.GameLevel;
import kernel.GameLevel.Level;
import kernel.GameLogic;
import kernel.GameThread;
import resources.AudioResources;
import resources.ImagesReources;

public class GameManager {
	
	//GUI
	private GameFrame gameFrame;
	private GlobalScreen globalScreen;
	
	//Event listeners
	private GameboyListener gameboyListener;
	private ScreenButtonsListener screenButtonslistener;
	private KeyBoardListener keyBoardListener;
	
	//Kernel
	private GameThread gameThread;
	private GameLogic gameLogic;
	private GameLevel gameLevel;
	
	public GameManager(){
		this.gameboyListener=new GameboyListener();
		this.screenButtonslistener=new ScreenButtonsListener(this);
		this.globalScreen=new GlobalScreen(this);
		this.gameLevel=new GameLevel(Level.MEDIUM);
	}
	
	public void powerOn(){
		try {
			ImagesReources.loadReources();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			AudioResources.loadResources();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.gameFrame=new GameFrame(this.gameboyListener,this.globalScreen);
		this.gameFrame.setVisible(true);
		this.globalScreen.showLoadingScreen();
		SoundManager.playSound(AudioResources.SPLASH,false);
		try {
			Thread.sleep(1*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.globalScreen.showMainMenuScreen();
		SoundManager.playSound(AudioResources.BACKGROUND1,true);
	}
	
	public void powerOff(){
		System.exit(0);
	}
	
	public void startPlaying(){
		this.globalScreen.showGameScreen();
		Canvas canvas=this.globalScreen.getInGameScreen().getCanvas();
		int matrixWidth=canvas.getWidth()/Drawable.blockWidth;
		int matrixheight=canvas.getHeight()/Drawable.blockHeight;
		this.gameLogic=new GameLogic(matrixWidth,matrixheight);
		this.gameThread=new GameThread(this,this.globalScreen.getInGameScreen(),this.gameLogic);
		this.keyBoardListener=new KeyBoardListener(this.gameThread);
		canvas.addKeyListener(this.keyBoardListener);
		canvas.setFocusable(true);
		canvas.requestFocus();
		this.gameThread.start();
	}
	
	public void pauseGame(){
		this.globalScreen.getInGameScreen().showPausingMenu();
		this.gameThread.setPaused(true);
	}
	
	public void resumeGame(){
		this.globalScreen.getInGameScreen().hidePausingMenu();
		this.gameThread.setPaused(false);
	}

	public void gameOver(){
		this.gameThread.stopGame();
		this.globalScreen.showMainMenuScreen();
	}
	
	public void stopGame(){
		this.gameThread.stopGame();
		this.globalScreen.showMainMenuScreen();
	}
	
	public void showOptionMenu(){
		this.globalScreen.showOptionScreen();
		int nbPins=this.gameLevel.getLevel().getPinsNumber();
		this.globalScreen.getOptionScreen().updatePins(nbPins);
	}
	
	public void showMainMenu(){
		this.globalScreen.showMainMenuScreen();
	}
	
	public void changeLevel(){
		this.gameLevel.nextLevel();
		int nbPins=this.gameLevel.getLevel().getPinsNumber();
		this.globalScreen.getOptionScreen().updatePins(nbPins);
	}
	
	public void changeBlueSkin(){
		this.gameFrame.changeSkin(ImagesReources.gameboyBackground1);
	}
	
	public void changeRedSkin(){
		this.gameFrame.changeSkin(ImagesReources.gameboyBackground2);
	}
	
	//----------------------___________** GETTER/SETTER **___________----------------------

	public ScreenButtonsListener getScreenButtonslistener() {
		return screenButtonslistener;
	}
	
	public void setScreenButtonslistener(ScreenButtonsListener screenButtonslistener) {
		this.screenButtonslistener = screenButtonslistener;
	}
	
	public KeyBoardListener getKeyBoardListener() {
		return keyBoardListener;
	}
	
	public void setKeyBoardListener(KeyBoardListener keyBoardListener) {
		this.keyBoardListener = keyBoardListener;
	}

	public GameLevel getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(GameLevel gameLevel) {
		this.gameLevel = gameLevel;
	}

}
