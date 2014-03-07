package kernel;

import exception.UnknownShapeException;

public class Shape implements Cloneable{
	public final static int matrixWidth=5;
	public final static int matrixHeight=5;
	private int[][] data;
	private int x,y;
	private ShapeMorphologie morpho;
	
	public enum ShapeMorphologie{
		line,
		distSquare1,
		square,
		L1,
		L2,
		distSquare2,
		pique,
		T,
		b;
		
		public static ShapeMorphologie getRandomValues(){
			return values()[(int) (Math.random()*values().length)];
			//return values()[(int) (Math.random()*2)];
		}
	}
	
	public Shape(ShapeMorphologie s,int x,int y) throws UnknownShapeException{
		this.setMorpho(s);
		this.setX(x);
		this.setY(y);
		this.constructShape(s);	
	}

	public Shape(int[][] data,int x,int y){
		this.setData(data);
		this.setX(x);
		this.setY(y);
	}
	
	private void constructShape(ShapeMorphologie s) throws UnknownShapeException{
		switch(s){
		case L1:
		{
			int[][] temp={{0,1,0},{0,1,0},{1,1,0}};
			this.setData(temp);
			break;
		}
		case L2:
		{
			int[][] temp={{0,1,0},{0,1,0},{0,1,1}};
			this.setData(temp);
			break;
		}
		case distSquare1:
		{
			int[][] temp={{0,0,0},{0,1,1},{1,1,0}};
			this.setData(temp);
			break;
		}
		case distSquare2:
		{
			int[][] temp={{0,0,0},{1,1,0},{0,1,1}};
			this.setData(temp);
			break;
		}
		case line:
		{
			int[][] temp={{0,0,0,0,0},{0,0,0,0,0},{0,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0}};
			this.setData(temp);
			break;
		}
		case pique:
		{
			int[][] temp={{0,0,0},{0,1,0},{1,1,1}};
			this.setData(temp);
			break;
		}
		case square:
		{
			int[][] temp={{0,0,0},{0,1,1},{0,1,1}};
			this.setData(temp);
			break;
		}
		case T:
		{
			int[][] temp={{1,1,1},{0,1,0},{0,1,0}};
			this.setData(temp);
			break;
		}
		case b:
		{
			int[][] temp={{0,0,0},{1,1,1},{1,0,1}};
			this.setData(temp);
			break;
		}
		default:
			throw new UnknownShapeException();
		}
	}
	
	public void validateData(){
		boolean refreshed=false;
		int max=this.data.length;
		while(!refreshed && max>0){
			for(int i=0;i<this.data[0].length;i++){
				if(this.data[this.data.length-1][i]!=0){
					refreshed=true;
					break;
				}
			}
			if(refreshed){
				break;
			}else{
				max--;
				for(int i=this.data.length-1;i>0;i--){
					for(int j=0;j<this.data[0].length;j++){
						this.data[i][j]=this.data[i-1][j];
					}
				}
				for(int j=0;j<this.data[0].length;j++){
					this.data[0][j]=0;
				}
			}
		}
	}

	public void increaseY(int value){
		this.y+=value;
	}
	
	public void increaseX(int value){
		this.x+=value;
	}
	
	public void decreaseX(int value){
		this.x-=value;
	}
	
	public Shape clone(){
		int[][] temp=new int[this.data.length][this.data[0].length];
		for(int i=0;i<this.data.length;i++){
			temp[i]=this.data[i].clone();
		}
		return new Shape(temp,this.x,this.y);
	}
	
	public String toString(){
		String out = "";
		for(int i=0;i<this.data.length;i++){
			for(int j=0;j<this.data[0].length;j++){
				out+=this.data[i][j]+",";
			}
			out+="\n";
		}
		return out;
	}
	
	public int getMinBlockX(){
		for(int x=0;x<this.data[0].length;x++){
			for(int y=0;y<this.data.length;y++){
				if(this.data[y][x]==1){
					return x;
				}
			}
		}
		return 0;
	}
	
	public int getMaxBlockX(){
		for(int x=this.data[0].length-1;x>=0;x--){
			for(int y=0;y<this.data.length;y++){
				if(this.data[y][x]==1){
					return x;
				}
			}
		}
		return this.data[0].length-1;
	}
	
	public int getMaxBlockYAtColumn(int x){
		for(int y=this.data.length-1;y>=0;y--){
			if(this.data[y][x]==1){
				return y;
			}
		}
		return this.data.length-1;
	}
	
	public int getMinBlockY(){
		for(int y=0;y<this.data.length;y++){
			for(int x=0;x<this.data[0].length;x++){
				if(this.data[y][x]==1){
					return y+this.y;
				}
			}
		}
		return -1;
	}
	public boolean deleteLine(int lineNumber){
		boolean deleted=false;
		if(lineNumber>=this.y && lineNumber<this.y+this.data.length){
			int index=lineNumber-this.y;
			deleted=true;
			for(int i=0;i<this.data[index].length;i++){
				this.data[index][i]=0;
			}
		}
		return deleted;
	}
	
	public void updateData(){
		int[][] temp=new int[this.data.length][this.data[0].length];
		int tempY=this.data.length-1;
		for(int y=this.data.length-1;y>=0;y--){
			for(int x=0;x<this.data[0].length;x++){
				if(this.data[y][x]==1){
					temp[tempY--]=this.data[y];
					break;
				}
			}
		}
		this.data=temp;
	}
	
	public boolean isEmpty(){
		for(int i=0;i<this.data.length;i++){
			for(int j=0;j<this.data[0].length;j++){
				if(this.data[i][j]==1){
					return false;
				}
			}
		}
		return true;
	}
	//----------------------___________** GETTER/SETTER **___________----------------------
	public int[][] getData() {
		return data;
	}

	public void setData(int[][] data) {
		this.data = data;
		this.validateData();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public ShapeMorphologie getMorpho() {
		return morpho;
	}

	public void setMorpho(ShapeMorphologie morpho) {
		this.morpho = morpho;
	}
}
