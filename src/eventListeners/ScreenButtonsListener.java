package eventListeners;

import graphicsModels.AbstractButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import manager.GameManager;
import manager.SoundManager;

public class ScreenButtonsListener implements MouseListener{

	private GameManager gameManager;
	
	public ScreenButtonsListener(GameManager gameManager){
		this.gameManager=gameManager;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String tag=((AbstractButton)e.getSource()).getTag();
		if(tag.equals("exit")){
			this.gameManager.powerOff();
		}else{
			if(tag.equals("start_game")){
				this.gameManager.startPlaying();
			}else{
				if(tag.equals("option")){
					this.gameManager.showOptionMenu();
				}else{
					if(tag.equals("exit_ingame")){
						this.gameManager.stopGame();
					}else{
						if(tag.equals("sound_ingame")){
							SoundManager.changeVolumeState();
						}else{
							if(tag.equals("level")){
								this.gameManager.changeLevel();
							}else{
								if(tag.equals("red_skin")){
									this.gameManager.changeRedSkin();
								}else{
									if(tag.equals("blue_skin")){
											this.gameManager.changeBlueSkin();
									}else{
										if(tag.equals("back")){
											this.gameManager.showMainMenu();
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
