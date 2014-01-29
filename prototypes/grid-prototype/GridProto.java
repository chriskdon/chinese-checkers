/**
 * @(#)GridProto.java
 *
 *
 * @author Peter Pobojewski
 * @course COSC3F00
 * @version 1.00 2014/1/20
 *
 * This class is a basic version of the chinese checkers board grid that current considers immediate neighbours for possible moves only
 * There is nothing done on possible hops.
 */

public class GridProto {
    public GridProto() {
    	int[][] grid;
    	grid = createBoard();
    	for(int i=0; i<grid.length; i++) {
    		for(int j=0; j<grid[i].length; j++) {
    			testPosition(i, j, grid);
    		}
    	}
    }
    /* Creates the board, this code can probably be better optimized to save space if necessary
     */
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
    /* Attempts to place an int at position y,x
     */
    public void placePiece (int y, int x, int[][] board) {
    	try {
    	 board[y][x] = 1;
    	 System.out.println(y+", "+x);
    	}
    	catch (ArrayIndexOutOfBoundsException e) {
    		//System.out.println("Invalid location");
    	}
    }
    /* This function takes a co-ordinate on the board and returns neighbours according to rules statically established through ifs
     * See the grid supporting documentation for more information on how the array is formed
     */
    public void possiblePositions (int y, int x, int[][] board) {
		if (y>12 || (y>3 && y<8)) { // if y is between 4 and 8 and greater than 12
			//System.out.println("Case1");
			if(y==4) {
				placePiece(y-1, x-5, board);
				placePiece(y-1, x-5+1, board);
			}
			else if(y==13) {
				placePiece(y-1, x+5, board);
				placePiece(y-1, x+5+1, board);
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
				placePiece(y-1, x+4, board);
				placePiece(y-1, x+4+1, board);
			}
			else if(y==12) {
				placePiece(y-1, x-5, board);
				placePiece(y-1, x-5+1, board);
			}
			else {
				placePiece(y+1, x, board);
				placePiece(y+1, x+1, board);
			}
		}
    }
    public void downLeft (int y, int x, int[][] board) {
		if (y>10 || (y>3 && y<8)) { // if y is between 4 and 8 and greater than 10
			//System.out.println("Case1");
			if(y==11) {
				placePiece(y+2, x-5, board);
			}
			else if(y==12) {
				placePiece(y+2, x-6, board);
			}
			else {
				placePiece(y+2, x-2, board);	
			}
		}
		else { // if y is between 9 and 10 or less than 4
		//	System.out.println("Case3");
			if(y==3) {
				placePiece(y+2, x+4, board);
			}
			else if(y==4) {
				placePiece(y+2, x+3, board);
			}
			else {
				placePiece(y+2, x, board);	
			}
    	}
    }
    public void downRight (int y, int x, int[][] board) {
		if (y>10 || (y>3 && y<8)) { // if y is between 4 and 8 and greater than 10
			//System.out.println("Case1");
			if(y==11) {
				placePiece(y+2, x-3, board);
			}
			else if(y==12) {
				placePiece(y+2, x-4, board);
			}
			else {
				placePiece(y+2, x, board);	
			}
		}
		else { // if y is between 9 and 10 or less than 4
		//	System.out.println("Case3");
			if(y==3) {
				placePiece(y+2, x+6, board);
			}
			else if(y==4) {
				placePiece(y+2, x+5, board);
			}
			else {
				placePiece(y+2, x+2, board);	
			}
    	}
    }
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
			else if(y==5) {
				placePiece(y-2, x-5, board);
			}
			else {
				placePiece(y-2, x-2, board);	
			}
    	}
    }
    public void upperRight (int y, int x, int[][] board) {
		if (y>12 || (y>5 && y<9)) { // if y is between 4 and 8 and greater than 10
			//System.out.println("Case1");
			if(y==14) {
				placePiece(y-2, x+6, board);
			}
			else if(y==13) {
				placePiece(y-2, x+6, board);
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
			else if(y==5) {
				placePiece(y-2, x-3, board);
			}
			else {
				placePiece(y-2, x, board);	
			}
    	}
    }
    public void leftAndRight (int y, int x, int[][] board) {
    	placePiece(y, x-2, board);
    	placePiece(y, x+2, board);
    }
    public void printBoard (int[][] board) {
    	
    	for(int i=0; i<board.length; i++) {
    		for(int j=0; j<board[i].length; j++) {
    			System.out.print(board[i][j]);
    		}
    		System.out.println();
    	}
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         new GridProto();
    }
}
