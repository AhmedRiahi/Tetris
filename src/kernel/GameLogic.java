package kernel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import kernel.Shape.ShapeMorphologie;
import utils.MyMath;
import exception.UnknownShapeException;

public class GameLogic {
	
	public enum StepEvent{
		breakingWall,
		spawnNewShape,
		normalFall,
		gameOver,
	}
	
	private int[][] binaryWall;
	private ArrayList<Shape> wallShapes;
	private Shape currentShape;
	private Shape nextShape;
	private int matrixWidth;
	private int matrixHeight;
	private int nbBrokenLines;
	private int score;
	
	public GameLogic(int matrixWidth,int matrixHeight){
		this.matrixHeight=matrixHeight;
		this.matrixWidth=matrixWidth;
		this.binaryWall=new int[this.matrixHeight][this.matrixWidth];
		this.wallShapes=new ArrayList<Shape>();
	}
	
	public StepEvent start(){
		try {
			this.currentShape=new Shape(ShapeMorphologie.getRandomValues(),0,0);
			this.currentShape.setX(this.matrixWidth/2-3);
			this.currentShape.setY(-3);
			this.nextShape=new Shape(ShapeMorphologie.getRandomValues(),0,0);
		} catch (UnknownShapeException e) {
			e.printStackTrace();
		}
		return StepEvent.spawnNewShape;
	}
	
	public synchronized List<StepEvent> nextStep(){
		this.nbBrokenLines=0;
		List<StepEvent> steps=new ArrayList<StepEvent>();
		boolean isShapeUnder=this.shapeUnder(this.currentShape);
		if(!isShapeUnder){
			this.currentShape.increaseY(1);
			steps.add(StepEvent.normalFall);
		}else{
			int minY=this.currentShape.getMinBlockY();
			if(minY<=0){
				steps.add(StepEvent.gameOver);
			}else{
				this.addToWall(this.currentShape);
				List<Integer> completeLines=this.checkCompleteLines();
				while(!completeLines.isEmpty()){
					this.nbBrokenLines+=completeLines.size();
					steps.add(StepEvent.breakingWall);
					this.breakLines(completeLines.toArray(new Integer[completeLines.size()]));
					this.removeEmptyShapes();
					this.updateWall();
					completeLines=this.checkCompleteLines();
				}
				this.score+=this.nbBrokenLines;
				//spawn new shape
				try {
					this.currentShape=this.nextShape.clone();
					this.currentShape.setX(this.matrixWidth/2-2);
					this.currentShape.setY(-4);
					this.nextShape=new Shape(ShapeMorphologie.getRandomValues(),0,0);
					steps.add(StepEvent.spawnNewShape);
				} catch (UnknownShapeException e) {
					e.printStackTrace();
				}
			}
		}
		this.printBinaryWall();
		return steps;
	}
	
	public void rotateRight(){
		int[][] temp=this.currentShape.getData().clone();
		temp=MyMath.rotateMatrix(temp,-90);
		Shape tempShape=new Shape(temp,this.currentShape.getX(),this.currentShape.getY());
		int minX=tempShape.getMinBlockX();
		boolean rigthToBorder=tempShape.getX()+minX>=0;
		int maxX=tempShape.getMaxBlockX();
		boolean leftToBorder=tempShape.getX()+maxX<this.matrixWidth;
		if(rigthToBorder && leftToBorder){
			boolean collidate=this.collidate(temp,this.currentShape.getX(),this.currentShape.getY());
			if(!collidate){
				this.currentShape.setData(temp);
			}
		}
	}
	
	public void rotateLeft(){
		int[][] temp=this.currentShape.getData().clone();
		temp=MyMath.rotateMatrix(temp,90);
		Shape tempShape=new Shape(temp,this.currentShape.getX(),this.currentShape.getY());
		int minX=tempShape.getMinBlockX();
		boolean rigthToBorder=tempShape.getX()+minX>=0;
		int maxX=tempShape.getMaxBlockX();
		boolean leftToBorder=tempShape.getX()+maxX<this.matrixWidth;
		if(rigthToBorder && leftToBorder){
			boolean collidate=this.collidate(temp,this.currentShape.getX(),this.currentShape.getY());
			if(!collidate){
				this.currentShape.setData(temp);
			}
		}
	}
	
	public void moveLeft(){
		int minX=this.currentShape.getMinBlockX();
		boolean rigthToBorder=this.currentShape.getX()+minX>0;
		if(rigthToBorder){
			this.currentShape.decreaseX(1);
			boolean collidate=this.collidate(this.currentShape);
			if(collidate){
				this.currentShape.increaseX(1);
			}
		}
	}
	
	public void moveRight(){
		int maxX=this.currentShape.getMaxBlockX();
		boolean leftToBorder=this.currentShape.getX()+maxX<this.matrixWidth-1;
		if(leftToBorder){
			this.currentShape.increaseX(1);
			boolean collidate=this.collidate(this.currentShape);
			if(collidate){
				this.currentShape.decreaseX(1);
			}
		}
	}
	
	private boolean shapeUnder(Shape shape){
		int[][] temp=shape.getData();
		int posX=shape.getX();
		int posY=shape.getY();
		for(int x=0;x<temp[0].length;x++){
			int y=shape.getMaxBlockYAtColumn(x);
			if(temp[y][x]==1){
				if(posY+y<this.matrixHeight-1){
					if(posY+y+1>=0){
						if(this.binaryWall[posY+y+1][posX+x]==1){
							return true;
						}
					}
				}else{
					return true;
				}
			}
		}
		return false;
	}
	
	private synchronized void addToWall(Shape shape){
		this.wallShapes.add(shape);
		int[][] temp=shape.getData();
		for(int y=0;y<temp.length;y++){
			for(int x=0;x<temp[0].length;x++){
				if(temp[y][x]==1){
					this.binaryWall[y+shape.getY()][x+shape.getX()]=1;
				}
			}
		}
		//System.out.println("add to wall");
		//this.printBinaryWall();
	}
	
	private boolean collidate(Shape shape){
		return this.collidate(shape.getData(),shape.getX(),shape.getY());
	}
	
	private boolean collidate(int[][] data,int posX,int posY){
		if(posY>0){
			for(int y=0;y<data.length;y++){
				for(int x=0;x<data[0].length;x++){
					if(data[y][x]==1){
						if(this.binaryWall[posY+y][posX+x]==1){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private List<Integer> checkCompleteLines(){
		List<Integer> completeLines=new ArrayList<Integer>();
		boolean isComplete=true;
		for(int y=0;y<this.binaryWall.length;y++){
			for(int x=0;x<this.binaryWall[0].length;x++){
				if(this.binaryWall[y][x]!=1){
					isComplete=false;
					break;
				}
			}
			if(isComplete){
				completeLines.add(y);
			}
			isComplete=true;
		}
		return completeLines;
	}
	
	private synchronized void breakLines(Integer[] integers){
		ArrayList<Shape> oldShapes=new ArrayList<Shape>();
		ArrayList<Shape> deletedShapes=new ArrayList<Shape>();
		for(int i=0;i<integers.length;i++){
			for(Shape shape:this.wallShapes){
				Shape oldShape=shape.clone();
				boolean deleted=shape.deleteLine(integers[i]);
				if(deleted){
					shape.updateData();
					deletedShapes.add(shape);
					oldShapes.add(oldShape);
				}
			}
		}
		for(int i=0;i<oldShapes.size();i++){
			this.removeShapeFromWall(oldShapes.get(i));
		}
		
		for(int i=0;i<deletedShapes.size();i++){
			this.updateWallRegion(deletedShapes.get(i));
		}
	}
	
	private synchronized void updateWall(){
		boolean changed=true;
		while(changed){
			changed=false;
			for(Shape shape:this.wallShapes){
				while(!this.shapeUnder(shape)){
					this.removeShapeFromWall(shape);
					shape.increaseY(1);
					this.updateWallRegion(shape);
					changed=true;
				}
			}
		}
		//System.out.println("update wall");
		//this.printBinaryWall();
	}
	
	private void updateWallRegion(Shape newShape){
		int[][] data=newShape.getData();
		for(int y=0;y<data.length;y++){
			for(int x=0;x<data[0].length;x++){
				if(data[y][x]==1){
					this.binaryWall[newShape.getY()+y][newShape.getX()+x]=1;
				}
			}
		}
	}
	
	private void removeShapeFromWall(Shape shape){
		int[][] data=shape.getData();
		for(int y=0;y<data.length;y++){
			for(int x=0;x<data[0].length;x++){
				if(data[y][x]==1){
					this.binaryWall[shape.getY()+y][shape.getX()+x]=0;
				}
			}
		}
	}
	
	public synchronized void removeEmptyShapes(){
		Iterator<Shape> i=this.wallShapes.iterator();
		while(i.hasNext()){
			Shape shape=i.next();
			if(shape.isEmpty()){
				i.remove();
			}
		}
	}
	
	private void printBinaryWall(){
		System.out.println("Wall =======");
		for(int i=0;i<this.binaryWall.length;i++){
			System.out.println(Arrays.toString(this.binaryWall[i]));
		}
		System.out.println("===========================================\n\n");
	}
	
	//----------------------___________** GETTER/SETTER **___________----------------------

	public Shape getCurrentShape() {
		return currentShape;
	}

	public Shape getNextShape(){
		return this.nextShape;
	}
	
	public void setCurrentShape(Shape currentShape) {
		this.currentShape = currentShape;
	}

	public int getScore(){
		return this.score;
	}
	
	public int getNbBrokenLines() {
		return this.nbBrokenLines;
	}
}
