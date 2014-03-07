package kernel;

public class GameLevel {
	
	private Level level;
	private int speed;
	
	public enum Level{
		VERY_EASY(1),
		EASY(2),
		MEDIUM(3),
		HARD(4),
		VERY_HARD(5);
		
		private int pins;
		
		Level(int pins){
			this.pins=pins;
		}
		
		public int getPinsNumber(){
			return this.pins;
		}
	}
	
	public GameLevel(Level level){
		this.level=level;
		this.initSpeed();
	}
	
	public void initSpeed(){
		switch(this.level){
		case VERY_EASY:
			this.speed=10;
			break;
		case EASY:
			this.speed=7;
			break;
		case MEDIUM:
			this.speed=4;
			break;
		case HARD:
			this.speed=2;
			break;
		case VERY_HARD:
			this.speed=1;
			break;
		default:
			this.speed=10;
			break;
		}
	}
	
	public void nextLevel(){
		switch(this.level){
		case VERY_EASY:
			this.level=Level.EASY;
			break;
		case EASY:
			this.level=Level.MEDIUM;
			break;
		case MEDIUM:
			this.level=Level.HARD;
			break;
		case HARD:
			this.level=Level.VERY_HARD;
			break;
		case VERY_HARD:
			this.level=Level.VERY_EASY;
			break;
		}
		this.initSpeed();
	}
	
	//----------------------___________** GETTER/SETTER **___________----------------------
	
	public int getSpeed(){
		return this.speed;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}
