/*
 * Knights Tour Finder
 * Adam Awany
 * NJIT ID: 31267624
 * New Jersey Institute of Technology
 * Prof. Jonathan Kapleau
 * CS280 - sec. 141
 * */

public class Main {
	public static final int LENGTH = 8;
	public static final int HEIGHT = 8;
	public static int count = 0;
	static int[][] solutionBoard = new int[HEIGHT][LENGTH];
	
	//class representing coordinates on the chess board. 
	//Note that the origin is the top left corner, the x axis is vertical and the y axis is horizontal
	static class Coordinates {
		public int x;
		public int y;
		
		public  Coordinates(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int x() {
			return this.x;
		}
		public int y() {
			return this.y;
		}
	}

	
	public static void main(String[] args) {
		//populate board with 0s (to mark unvisited squares)
		
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < LENGTH; j++) {
				solutionBoard[i][j] = 0;
			}
		}
		findTour();
	}
	
	public static void findTour() {
		// start at top-right corner
		Coordinates currentPosition = new Coordinates(0, 0);
		//make moves
		move(currentPosition, 1);
	}
	
	// takes as a parameter the current position and will make a move in each direction possible, as returned by getMoves()
	public static boolean move(Coordinates currentPosition, int moveNo) {
		//set current square as moveNo
		solutionBoard[currentPosition.x()][currentPosition.y()] = moveNo;
		count++;
		// if moveNo is the size of the board, we know we are done and should print and return true
		if (moveNo == HEIGHT*LENGTH) {	
			System.out.println("\nFound tour!\n");
			printBoard();
			System.out.println("\nn = " + count);
			return true;
		}
		
		//else, get a list of possible moves to make and recursively follow each one to an end
		else {
			Coordinates[] moves;
			moves = getMoves(currentPosition);
			for (int i = 0; i < moves.length; i++) {
				boolean tourFound = move(moves[i], moveNo + 1);
				if (tourFound) return true;
			}
			
			//if we made it out of the loop, we must backtrack, so reset this position and return false
			solutionBoard[currentPosition.x()][currentPosition.y()] = 0;
			return false;
		}
	}

	//returns a list of potential moves to be made, taking as a parameter the current position
	public static Coordinates[] getMoves(Coordinates current) {
		
		Coordinates[] moves = new Coordinates[0];
		
		//Check if you can make all 8 moves. If it's possible, and also not visited, add that move's coordinates to the list	
		if ((current.x() > 0) && (current.y() < LENGTH  - 2) && (solutionBoard[current.x() - 1][current.y() + 2] == 0))			// right 2, up 1
			moves = add(moves, new Coordinates(current.x() - 1, current.y() + 2));
		if ((current.x() > 1) && (current.y() < LENGTH - 1) && (solutionBoard[current.x() - 2][current.y() + 1] == 0))			// up 2, right 1
			moves = add(moves, new Coordinates(current.x() - 2, current.y() + 1));
		if ((current.x() > 1) && (current.y() > 0) && (solutionBoard[current.x() - 2][current.y() - 1] == 0))						// up 2, left 1
			moves = add(moves, new Coordinates(current.x() - 2, current.y() - 1));
		if ((current.x() > 0) && (current.y() > 1) && (solutionBoard[current.x() - 1][current.y() - 2] == 0))						// left 2, up 1
			moves = add(moves, new Coordinates(current.x() - 1,current.y() - 2));
		if ((current.x() < HEIGHT - 1) && (current.y() > 1) && (solutionBoard[current.x() + 1][current.y() - 2] == 0))			// left 2, down 1
			moves = add(moves, new Coordinates(current.x() + 1, current.y() - 2));
		if ((current.x() < HEIGHT - 2) && (current.y() > 0) && (solutionBoard[current.x() + 2][current.y() - 1] == 0))			// down 2, left 1
			moves = add(moves, new Coordinates(current.x() + 2, current.y() - 1));
		if ((current.x() < HEIGHT - 2) && (current.y() < LENGTH - 1) && (solutionBoard[current.x() + 2][current.y() + 1] == 0))	//down 2, right 1
			moves = add(moves, new Coordinates(current.x() + 2, current.y() + 1));	
		if ((current.x() < HEIGHT - 1) && (current.y() < LENGTH - 2 ) && (solutionBoard[current.x() + 1][current.y() + 2] == 0))	// right 2, down 1
			moves = add(moves, new Coordinates(current.x() + 1, current.y() + 2));
		
		return moves;
	}
	
	// adds an element to the array and makes room for it
	public static Coordinates[] add(Coordinates[] CoordsList, Coordinates newCoord) {
		Coordinates[] newList = new Coordinates[CoordsList.length + 1];
		for (int i = 0; i < CoordsList.length; i++) {
			newList[i] = CoordsList[i];
		}
		newList[newList.length - 1] = newCoord;
		return newList;
	}
	
	// prints the board, formatting single-digit numbers and adding spaces between neighboring squares
	public static void printBoard() {

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < LENGTH; j++) {
				if (solutionBoard[i][j] < 10)
					System.out.print("0" + solutionBoard[i][j]);
				else System.out.print(solutionBoard[i][j]);
				
				if (j < LENGTH - 1) System.out.print(" ");
				else System.out.println();
			}
		}
	}

}

