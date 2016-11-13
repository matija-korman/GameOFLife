package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Cell extends Rectangle{
	
	private boolean alive;
	private int matrixRow;
	private int matrixColumn;
	private long cellId;

	
	public Cell(){
		
	}
	
	public Cell(int positionX,int positionY, int width, int height, int row, int column){
		super(positionX, positionY,width,height);
		alive=false;
		matrixRow=row;
		matrixColumn=column;
		setStroke(Color.LIGHTGRAY);
		setStrokeType(StrokeType.INSIDE);
		setStrokeWidth(0.2);
		setFill(Color.WHITE);
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	public void setAlive(boolean life){
		this.alive=life;
	}
	
	public void giveLife(){
		setAlive(true);
		setFill(Color.rgb(131,186,140));
	}
	
	public void killCell(){
		setAlive(false);
		setFill(Color.WHITE);
	}
	
	public void setMatrixRow(int row){
		matrixRow=row;
	}
	
	public int getMatrixRow(){
		return matrixRow;
	}
	
	public void setMatrixColumn(int column){
		matrixColumn=column;
	}
	
	public int getMatrixColumn(){
		return matrixColumn;
	}
	
	public void setCellId(long id){
		this.cellId=id;
	}
	
	public long getCellId(){
		return this.cellId;
	}

}
