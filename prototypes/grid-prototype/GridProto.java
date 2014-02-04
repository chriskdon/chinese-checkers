/**
 * @(#)GridProto.java
 *
 *
 * @author Peter Pobojewski
 * @course COSC3F00
 * @version 1.20 2014/2/2
 *
 * This class is a basic version of the chinese checkers board grid that considers all possible moves from a specific location.
 * For testing or reasoning please visit the testing board or position checking PDF.
 */

public class GridProto {
    public GridProto() {
    	int[][] grid;
    	grid = createBoard();
    /*	for(int i=0; i<grid.length; i++) {
    		for(int j=0; j<grid[i].length; j++) {
    			
    		}
    	} */
    	testPosition(13, 3,grid);
    }
	/* Prints a list of availible positions to the console.
	 * If a positon is unavailable, it will be skipped in printing it out
	 * The function accepts co-ordinates in a y,x fashion meaning [3,1] referring to the 3rd row and 2nd column
	 * @param y	the row of the location
	 * @param x	the col of the location
	 * @param grid	the chinese checkers board  */
    public void testPosition(int y, int x, int[][] grid) {
    	System.out.println("Testing: "+y+", "+x);
    	System.out.println("Immediate Neighbours:");
    	possiblePositions(y,x,grid);
    	System.out.println("Jumping Upper Left:");
    	upperLeft(y,x,grid);
    	System.out.println("Jumping Upper Right:");
    	upperRight(y,x,grid);
    	System.out.println("Jumping Down Left:");
    	downLeft(y,x,grid);
    	System.out.println("Jumping Down Right:");
    	downRight(y,x,grid);
    	System.out.println("Jumping Purely Left or Purely Right:");
    	leftAndRight(y,x,grid);
    	System.out.println("-------------------");
    }
   /* creates a ragged array to represent the chinese checkers board
	 * @return board	a new chinese checkers board  */
    public int[][] createBoard () {
    	int [][] board = new int[17][];
    	board[0]=new int[1];
    	board[1]=new int[2];
    	board[2]=new int[3];
    	board[3]=new int[4];
    	board[4]=new int[13];
    	board[5]=new int[12];
    	board[6]=new int[11];
    	board[7]=new int[10];
    	board[8]=new int[9];
    	board[16]=new int[1];
    	board[15]=new int[2];
    	board[14]=new int[3];
    	board[13]=new int[4];
    	board[12]=new int[13];
    	board[11]=new int[12];
    	board[10]=new int[11];
    	board[9]=new int[10];
    	return board;
    }
	/* Attempts to place an int at target location defined as y, x
	 * If the attempt to place a int in that location fails because of out of bounds, an exception is caught
	 * and the function continues. 
	 * @param y	the row of the location
	 * @param x	the col of the location
	 * @param grid	the chinese checkers board  */
    public void placePiece (int y, int x, int[][] board) {
    	try {
    	 board[y][x] = 1;
    	 System.out.println(y+", "+x);
    	}
    	catch (ArrayIndexOutOfBoundsException e) {
    		//System.out.println("Invalid location");
    	}
    }
	/* Defined as immediate neighbours function, it looks up the immediate places (within 1 space) that the piece can move to
	 * @param y	the row of the location
	 * @param x	the col of the location
	 * @param grid	the chinese checkers board  */
    public void possiblePositions (int y, int x, int[][] board) {
		if (y>12 || (y>3 && y<8)) { // if y is between 4 and 8 and greater than 12
			//System.out.println("Case1");
			if(y==4) {
				placePiece(y-1, x-5, board);
				placePiece(y-1, x-5+1, board);
			}
			else if(y==13) {
				placePiece(y-1, x+4, board);
				placePiece(y-1, x+4+1, board);
			}
			else {
				placePiece(y-1, x, board);
				placePiece(y-1, x+1, board);
			}
			placePiece(y, x-1, board);
			placePiece(y, x+1, board);
			placePiece(y+1, x-1, board);
			placePiece(y+1, x, board);
		}
		else if (y==8) { // dead middle of the board
			//System.out.println("Case2");
			placePiece(y-1, x, board);
			placePiece(y-1, x+1, board);
			placePiece(y, x-1, board);
			placePiece(y, x+1, board);
			placePiece(y+1, x, board);
			placePiece(y+1, x+1, board);
		}
		else { // if y is between 9 and 11 or less than 4
			//System.out.println("Case3");
			placePiece(y-1, x-1, board);
			placePiece(y-1, x, board);
			placePiece(y, x-1, board);
			placePiece(y, x+1, board);
			if(y==3) {
				placePiece(y+1, x+4, board);
				placePiece(y+1, x+4+1, board);
			}
			else if(y==12) {
				placePiece(y+1, x-5, board);
				placePiece(y+1, x-5+1, board);
			}
			else {
				placePiece(y+1, x, board);
				placePiece(y+1, x+1, board);
			}
		}
    }
	/* Determines the down-diagonal-left position that a piece has the possibility of moving to
	 * @param y	the row of the location
	 * @param x	the col of the location
	 * @param grid	the chinese checkers board  */
    public void downLeft (int y, int x, int[][] board) {
		if (y>10 || (y>3 && y<8)) { // if y is between 4 and 8 and greater than 10
			//System.out.println("Case1");
			if(y==11) {
				placePiece(y+2, x-5, board);
			}
			else if(y==12) {
				placePiece(y+2, x-6, board);
			}
			else if(y==7) {
				placePiece(y+2, x-1, board);
			}
			else {
				placePiece(y+2, x-2, board);	
			}
		}
		else { // if y is between 9 and 10 or less than 4
		//	System.out.println("Case3");
			if(y==3) {
				placePiece(y+2, x+3, board);
			}
			else if(y==2) {
				placePiece(y+2, x+4, board);
			}
			else {
				placePiece(y+2, x, board);	
			}
    	}
    }
	/* Determines the down-diagonal-right position that a piece has the possibility of moving to
	 * @param y	the row of the location
	 * @param x	the col of the location
	 * @param grid	the chinese checkers board  */
    public void downRight (int y, int x, int[][] board) {
		if (y>10 || (y>3 && y<8)) { // if y is between 4 and 8 and greater than 10
			//System.out.println("Case1");
			if(y==11) {
				placePiece(y+2, x-3, board);
			}
			else if(y==12) {
				placePiece(y+2, x-4, board);
			}
			else if(y==7) {
				placePiece(y+2, x+1, board);
			}
			else {
				placePiece(y+2, x, board);	
			}
		}
		else { // if y is between 9 and 10 or less than 4
		//	System.out.println("Case3");
			if(y==2) {
				placePiece(y+2, x+6, board);
			}
			else if(y==3) {
				placePiece(y+2, x+5, board);
			}
			else {
				placePiece(y+2, x+2, board);	
			}
    	}
    }
	/* Determines the up-diagonal-left position that a piece has the possibility of moving to
	 * @param y	the row of the location
	 * @param x	the col of the location
	 * @param grid	the chinese checkers board  */
    public void upperLeft (int y, int x, int[][] board) {
		if (y>12 || (y>5 && y<9)) { // if y is between 4 and 8 and greater than 10
			//System.out.println("Case1");
			if(y==14) {
				placePiece(y-2, x+4, board);
			}
			else if(y==13) {
				placePiece(y-2, x+3, board);
			}
			else {
				placePiece(y-2, x, board);	
			}
		}
		else { // if y is between 9 and 10 or less than 4
			//System.out.println("Case3");
			if(y==4) {
				placePiece(y-2, x-6, board);
			}
			if(y==9) {
				placePiece(y-2, x-1, board);
			}
			else if(y==5) {
				placePiece(y-2, x-5, board);
			}
			else {
				placePiece(y-2, x-2, board);	
			}
    	}
    }
	/* Determines the up-diagonal-right position that a piece has the possibility of moving to
	 * @param y	the row of the location
	 * @param x	the col of the location
	 * @param grid	the chinese checkers board  */
    public void upperRight (int y, int x, int[][] board) {
		if (y>12 || (y>5 && y<9)) { // if y is between 4 and 8 and greater than 10
			//System.out.println("Case1");
			if(y==14) {
				placePiece(y-2, x+6, board);
			}
			else if(y==13) {
				placePiece(y-2, x+5, board);
			}
			else {
				placePiece(y-2, x+2, board);	
			}
		}
		else { // if y is between 9 and 10 or less than 4
			//System.out.println("Case3");
			if(y==4) {
				placePiece(y-2, x-4, board);
			}
			if(y==9) {
				placePiece(y-2, x+1, board);
			}
			else if(y==5) {
				placePiece(y-2, x-3, board);
			}
			else {
				placePiece(y-2, x, board);	
			}
    	}
    }
	/* Determines the 2 space hop that a piece can possibly move by hopping left or right only
	 * @param y	the row of the location
	 * @param x	the col of the location
	 * @param grid	the chinese checkers board  */
    public void leftAndRight (int y, int x, int[][] board) {
    	placePiece(y, x-2, board);
    	placePiece(y, x+2, board);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         new GridProto();
    }
}
