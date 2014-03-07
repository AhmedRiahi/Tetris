package resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagesReources {
	
	public static BufferedImage gameboyBackground1;
	public static BufferedImage gameboyBackground2;
	public static BufferedImage loadingBackground;
	public static BufferedImage mainMenuBackground;
	public static BufferedImage long_background;
	public static BufferedImage cursor;
	public static BufferedImage gray_button;
	public static BufferedImage gray_button_hover;
	public static BufferedImage arrow_right_button;
	public static BufferedImage arrow_left_button;
	public static BufferedImage arrow_up_button;
	public static BufferedImage arrow_down_button;
	public static BufferedImage arrow_down_button_hover;
	public static BufferedImage arrow_up_button_hover;
	public static BufferedImage arrow_left_button_hover;
	public static BufferedImage arrow_right_button_hover;
	public static BufferedImage start_button;
	public static BufferedImage start_button_hover;
	public static BufferedImage option_button;
	public static BufferedImage option_button_hover;
	public static BufferedImage exit_button;
	public static BufferedImage exit_button_hover;
	public static BufferedImage sound_button_on;
	public static BufferedImage sound_button_off;
	public static BufferedImage canvas_border;
	public static BufferedImage exit_x;
	public static BufferedImage pause_menu;
	public static BufferedImage score_background;
	public static BufferedImage plus_10;
	public static BufferedImage option_bg;
	public static BufferedImage level_bg;
	public static BufferedImage skin_bg;
	public static BufferedImage level_btn;
	public static BufferedImage level_pin;
	public static BufferedImage skin_red_btn;
	public static BufferedImage skin_blue_btn;
	public static BufferedImage back_btn;
	
	public static void loadReources() throws IOException{
		gameboyBackground1=ImageIO.read(new File("res/images/game_boy1.png"));
		gameboyBackground2=ImageIO.read(new File("res/images/game_boy2.png"));
		loadingBackground=ImageIO.read(new File("res/images/splash.png"));
		mainMenuBackground=ImageIO.read(new File("res/images/main_menu_background.png"));
		cursor=ImageIO.read(new File("res/images/cursor.png"));
		gray_button=ImageIO.read(new File("res/images/gray_button.png"));
		gray_button_hover=ImageIO.read(new File("res/images/gray_button_hover.png"));
		arrow_right_button=ImageIO.read(new File("res/images/right.png"));
		arrow_left_button=ImageIO.read(new File("res/images/left.png"));
		arrow_up_button=ImageIO.read(new File("res/images/up.png"));
		arrow_down_button=ImageIO.read(new File("res/images/down.png"));
		arrow_up_button_hover=ImageIO.read(new File("res/images/up_hover.png"));
		arrow_right_button_hover=ImageIO.read(new File("res/images/right_hover.png"));
		arrow_left_button_hover=ImageIO.read(new File("res/images/left_hover.png"));
		arrow_down_button_hover=ImageIO.read(new File("res/images/down_hover.png"));
		start_button=ImageIO.read(new File("res/images/start.png"));
		start_button_hover=ImageIO.read(new File("res/images/start_hover.png"));
		option_button=ImageIO.read(new File("res/images/option.png"));
		option_button_hover=ImageIO.read(new File("res/images/option_hover.png"));
		exit_button=ImageIO.read(new File("res/images/exit.png"));
		exit_button_hover=ImageIO.read(new File("res/images/exit_hover.png"));
		sound_button_on=ImageIO.read(new File("res/images/sound_on.png"));
		sound_button_off=ImageIO.read(new File("res/images/sound_off.png"));
		long_background=ImageIO.read(new File("res/images/long_bg.png"));
		canvas_border=ImageIO.read(new File("res/images/canvas_border.png"));
		exit_x=ImageIO.read(new File("res/images/exit_x.png"));
		pause_menu=ImageIO.read(new File("res/images/pause_menu.png"));
		score_background=ImageIO.read(new File("res/images/score_bg.png"));
		plus_10=ImageIO.read(new File("res/images/+10.png"));
		option_bg=ImageIO.read(new File("res/images/option_bg.png"));
		level_bg=ImageIO.read(new File("res/images/level_bg.png"));
		skin_bg=ImageIO.read(new File("res/images/skin_bg.png"));
		level_btn=ImageIO.read(new File("res/images/level_btn.png"));
		level_pin=ImageIO.read(new File("res/images/pin.png"));
		skin_red_btn=ImageIO.read(new File("res/images/red_btn.png"));
		skin_blue_btn=ImageIO.read(new File("res/images/blue_btn.png"));
		back_btn=ImageIO.read(new File("res/images/back.png"));
	}
}
