package eventListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import kernel.GameThread;

public class KeyBoardListener implements KeyListener{

	
	private GameThread gameThread;
	
	public KeyBoardListener(GameThread gameThread){
		this.gameThread=gameThread;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.gameThread.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
