package application;

import java.util.ArrayList;
import java.util.List;

public class CellMatrix extends Thread {
	

	private long matrixId;
	
	private Cell[][] cells;
	private List<Cell> CellList=new ArrayList<Cell>(); //Cells will be added to a List as well. The list will be saved in the database
	private int numRows;
	private int numColumns;
	int positionX; // position of the upper left corner of the Matrix on x-axis of the graphical interface
	int positionY; // position of the upper left corner of the Matrix on the y-axis of the graphical interface
	public int lifeCycles;
	private String matrixName;
	
	
	public CellMatrix(){
		
	}
		
	/**
	 * Creates new matrix of cells that will be drawn in the Scene
	 * starting from the position x,y
	 * @param x x coordinate 
	 * @param y y coordinate
	 * @param rows number of rows in the matrix
	 * @param columns number of columns in the matrix
	 */
	public CellMatrix(int x, int y, int rows, int columns){
		numRows=rows;
		numColumns=columns;
		positionX=x;
		positionY=y;
		lifeCycles=0;
		cells=new Cell[rows][columns];
		for(int i=0;i<numRows;i++){
			for(int j=0;j<numColumns;j++){
				//Creates new cell in the matrix at row i, column j 
				//and specifies the position of that cell in the Scene
				//the width and height of each cell is 5
				cells[i][j]=new Cell((x+(5*i)),(y+(5*j)),5,5,i,j);
				CellList.add(cells[i][j]);
			}
		}
			
	}
	
	
	
	//Changes the population of the current matrix according to the RULES of Game of Life
	//It checks the life status of each cell as well as the number of its alive
	//neighbors and changes the state of the cell accordingly
	public CellMatrix evolveMatrix(){
		CellMatrix newCellMatrix=new CellMatrix(positionX, positionY, numRows,numColumns);
		
		for(int i=0;i<numRows;i++){
			for(int j=0;j<numColumns;j++){
				int numAliveNeighbors=this.getNumAliveNeighbors(i,j);
				
				if(cells[i][j].getAlive()==true && numAliveNeighbors<2){
					newCellMatrix.getCells()[i][j].killCell();
				}
				else if(cells[i][j].getAlive()==true && numAliveNeighbors>=2 && numAliveNeighbors<=3){
					newCellMatrix.getCells()[i][j].giveLife();
				}
				else if(cells[i][j].getAlive()==true && numAliveNeighbors>3){
					newCellMatrix.getCells()[i][j].killCell();
				}
				else if(cells[i][j].getAlive()==false && numAliveNeighbors==3){
					newCellMatrix.getCells()[i][j].giveLife();
				}
				
			}
		}
		
		return newCellMatrix;
	}
	
	
	
	/**
	 * For the cell at a certain position in the matrix it checks the number of alive neighbors
	 * @param i matrix row the cell is located in
	 * @param j matrix column the cell is located in
	 * @return number of alive neighbors of the cell
	 */
	
	private int getNumAliveNeighbors(int i,int j){
		int numAliveNeighbors=0;
		
		//We have to be careful that the neighbor that we are checking is not outside of the matrix boundaries
		if(!(i-1<0)){
			if(cells[i-1][j].getAlive()==true){
				numAliveNeighbors++;
			}
		}
		
		if(!(j-1<0)){
			if(cells[i][j-1].getAlive()==true){
				numAliveNeighbors++;
			}
		}
		
		if(!(i+1>=numRows)){
			if(cells[i+1][j].getAlive()==true){
				numAliveNeighbors++;
			}
		}
		if(!(j+1>=numColumns)){
			if(cells[i][j+1].getAlive()==true){
				numAliveNeighbors++;
			}
		}
		if(!(i-1<0)&&!(j-1<0)){
			if(cells[i-1][j-1].getAlive()==true){
				numAliveNeighbors++;
			}
		}
		if(!(i+1>=numRows)&&!(j-1<0)){
			if(cells[i+1][j-1].getAlive()==true){
				numAliveNeighbors++;
			}
		}
		if(!(i-1<0)&&!(j+1>=numColumns)){
			if(cells[i-1][j+1].getAlive()==true){
				numAliveNeighbors++;
			}
		}
		if(!(i+1>=numRows)&&!(j+1>=numColumns)){
			if(cells[i+1][j+1].getAlive()==true){
				numAliveNeighbors++;
			}
		}
		
		return numAliveNeighbors;

	}
	
	//Kills all cells in the matrix
	public void killAllCells(){
		for(int i=0;i<numRows;i++){
    		for(int j=0;j<numColumns;j++){
    			if(getCells()[i][j].getAlive()){
    				getCells()[i][j].killCell();
    			}
    		}
    	}
	}
	
	public Cell[][] getCells(){
		return cells;
	}
	
	public List<Cell> getListCells(){
		return CellList;
	}
	
	public int getNumRows(){
		return numRows;
	}
	
	@SuppressWarnings("unused")
	private void setNumRows(int num){
		numRows=num;
	}
	
	public int getNumColumns(){
		return numColumns;
	}
	
	public void setNumColumns(int num){
		numColumns=num;
	}
	
	public int getPositionX(){
		return positionX;
	}
	
	public int getPositionY(){
		return positionY;
	}
	
	public int getLifeCycles(){
		return lifeCycles;
	}
	
	public String getMatrixName(){
		return this.matrixName;
	}
	
	public void setMatrixName(String name){
		this.matrixName=name;
	}
	
	public void setMatrixId(long id){
		this.matrixId=id;
	}
	
	public long getMatrixId(){
		return this.matrixId;
	}
	
	public void setCellList(List<Cell> cells){
		this.CellList=cells;
	}
	
	public  List<Cell> getCellList(){
		return this.CellList;
	}
}
