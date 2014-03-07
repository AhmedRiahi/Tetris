package utils;

public class MyMath {
	
	public static int[][] rotateMatrix(int[][] data,double angle){
		angle=Math.toRadians(angle);
		int[][] result=new int[data.length][data[0].length];
		int centerY=(int) Math.round(data.length/2f);
		int centerX=(int) Math.round(data[0].length/2f);
		for(int y=0;y<data.length;y++){
			for(int x=0;x<data[0].length;x++){
				int posX=(int) (((int)Math.cos(angle))*(x-centerX+1)+-Math.sin(angle)*(y-centerY+1));
				int posY=(int) (Math.sin(angle)*(x-centerX+1)+((int)Math.cos(angle))*(y-centerY+1));
				result[posY+centerX-1][posX+centerY-1]=data[y][x];
			}
		}
		return result;
	}
	
	
	public static double degreeToRadian(int degree){
		double radian=(degree*Math.PI/180);
		return radian;
	}
}
