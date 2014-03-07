package eventListeners;

import graphicsModels.SpecialButton;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameboyListener implements MouseListener{

	private Robot robot;
	
	public GameboyListener(){
		try {
			this.robot=new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		String tag=((SpecialButton)e.getSource()).getTag();
		if(tag.equals("a")){
			this.robot.keyPress(KeyEvent.VK_Z);
			this.robot.keyRelease(KeyEvent.VK_Z);
		}else{
			if(tag.equals("b")){
				this.robot.keyPress(KeyEvent.VK_X);
				this.robot.keyRelease(KeyEvent.VK_X);
			}else{
				if(tag.equals("left")){
					this.robot.keyPress(KeyEvent.VK_LEFT);
					this.robot.keyRelease(KeyEvent.VK_LEFT);
				}else{
					if(tag.equals("right")){
						this.robot.keyPress(KeyEvent.VK_RIGHT);
						this.robot.keyRelease(KeyEvent.VK_RIGHT);
					}else{
						if(tag.equals("up")){
							this.robot.keyPress(KeyEvent.VK_UP);
							this.robot.keyRelease(KeyEvent.VK_UP);
						}else{
							if(tag.equals("down")){
								this.robot.keyPress(KeyEvent.VK_DOWN);
								this.robot.keyRelease(KeyEvent.VK_DOWN);
							}
						}
					}
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
}
