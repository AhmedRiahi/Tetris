package kernel;

import graphicsModels.Canvas;
import gui.InGameScreen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;

import resources.AudioResources;

import kernel.GameLogic.StepEvent;
import manager.GameManager;
import manager.SoundManager;

public class GameThread extends Thread{
	
	private boolean paused=false;
	private boolean stoped=false;
	private GameLogic gameLogic;
	private InGameScreen inGameScreen;
	private Canvas canvas;
	private GameManager gameManager;
	
	public GameThread(GameManager gameManager,InGameScreen inGameScreen,GameLogic gameLogic){
		this.gameManager=gameManager;
		this.inGameScreen=inGameScreen;
		this.canvas=this.inGameScreen.getCanvas();
		this.gameLogic=gameLogic;
	}
	
	public void run(){
		while(!this.stoped){
			System.out.print("");
			while(!this.paused){
				List<StepEvent> steps=this.gameLogic.nextStep();
				for(int i=0;i<steps.size();i++){
					this.processStepEvent(steps.get(i));
				}			
				try {
					Thread.sleep(this.gameManager.getGameLevel().getSpeed()*100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void start(){
		if(this.paused){
			System.out.println("game resuming...");
			List<StepEvent> stepsEvent=this.gameLogic.nextStep();
			for(StepEvent step:stepsEvent){
				this.processStepEvent(step);
			}
		}else{
			System.out.println("game strating...");
			StepEvent stepEvent=this.gameLogic.start();
			this.processStepEvent(stepEvent);
			super.start();
		}
	}
	
	private void processStepEvent(StepEvent stepEvent){
		
		switch (stepEvent) {
		case breakingWall:
			SoundManager.playSound(AudioResources.BLOK, false);
			for(int i=0;i<this.gameLogic.getNbBrokenLines();i++){
				this.inGameScreen.addStarAnim();
			}
			this.inGameScreen.updateScore(this.gameLogic.getScore()*10);
			this.canvas.repaint();
			break;
		case normalFall:
		{
			SoundManager.playSound(AudioResources.FALL, false);
			this.canvas.repaint();
			break;
		}
		case gameOver:
		{
			this.gameManager.gameOver();
			break;
		}
		case spawnNewShape:
		{
			Shape shape=this.gameLogic.getCurrentShape();
			Shape nextShape=this.gameLogic.getNextShape();
			this.canvas.addNewShape(shape);
			this.canvas.showNextShape(nextShape);
			break;
		}
		default:
			break;
		}
	}
	
	public void keyPressed(int keyCode){
		switch(keyCode){
		case KeyEvent.VK_UP:
		{
			if(!this.paused){
				this.gameLogic.rotateRight();
				this.canvas.repaint();
			}
			break;
		}
		case KeyEvent.VK_DOWN:
		{
			if(!this.paused){
				List<StepEvent> steps=this.gameLogic.nextStep();
				for(int i=0;i<steps.size();i++){
					this.processStepEvent(steps.get(i));
				}
			}
			break;
		}
		case KeyEvent.VK_LEFT:
		{
			if(!this.paused){
				this.gameLogic.moveLeft();
				this.canvas.repaint();
			}
			break;
		}
		case KeyEvent.VK_RIGHT:
		{
			if(!this.paused){
				this.gameLogic.moveRight();
				this.canvas.repaint();
			}
			break;
		}
		case KeyEvent.VK_X:
		{
			if(!this.paused){
				this.gameLogic.rotateRight();
				this.canvas.repaint();
			}
			break;
		}
		case KeyEvent.VK_Z:
		{
			if(!this.paused){
				this.gameLogic.rotateLeft();
				this.canvas.repaint();
			}
			break;
		}
		case KeyEvent.VK_P:
		{
			if(this.paused){
				this.gameManager.resumeGame();
			}else{
				this.gameManager.pauseGame();
			}
			break;
		}
		case KeyEvent.VK_S:
		{
			SoundManager.changeVolumeState();
			break;
		}
		case KeyEvent.VK_Q:
		{
			this.gameManager.stopGame();
			break;
		}
		}
	}
	
	public void setPaused(boolean paused){
		this.paused=paused;
		System.out.println(this.paused);
	}

	public void stopGame(){
		this.stoped=true;
		this.paused=true;
	}
}