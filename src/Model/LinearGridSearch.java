package Model;

import java.awt.Color;

public class LinearGridSearch {
	private CanvasCoords root;
	private Square[][] squareArray;
	private boolean[][] visitedArray;
	
	public LinearGridSearch(CanvasCoords root, Square[][] squareArray) {
		this.root = root;
		this.squareArray = squareArray;
		this.visitedArray = new boolean[63][25];
		CanvasCoords result = search(squareArray, root);
		System.out.println(result.getArrayX() + ", " + result.getArrayY());
	}
	
//	getters
	public Square[][] getSquareArray() {
		return this.squareArray;
	}
	
//	setters
	public void setVisited(int x, int y) {
//		parameters are array coords
		squareArray[x][y].setType(SquareType.SEARCHED);
		squareArray[x][y].setColor(Color.yellow);
	}
	
//	local methods
	private boolean isVisited(Square square) {
//		convert from canvas coords to array coords
		System.out.println("X: "+square.getArrCoordX());
		System.out.println("Y: "+square.getArrCoordY());
		return visitedArray[square.getArrCoordX()][square.getArrCoordY()];
	}
	
	public CanvasCoords search(Square[][] array, CanvasCoords root) {
		return search(array[root.getX()][root.getY()]);
	}
	public CanvasCoords search(Square square) {
		Square right = square.getRight();
		Square down = square.getDown();
		Square left = square.getLeft();
		Square up = square.getUp();
		
		if(square.getType() == SquareType.DESTINATION) {
			return new CanvasCoords(square.getXcoord(), square.getYcoord());
		}
		
//		mark visited in squareArray and visitedArray
		setVisited(square.getArrCoordX(), square.getArrCoordY());
		visitedArray[square.getArrCoordX()][square.getArrCoordY()] = true;
		
//		traverse through neighbouring squares
		if(right != null && right.getType() != SquareType.WALL && !isVisited(right)) {
			return search(square.getRight());
		}else if(down != null && down.getType() != SquareType.WALL && !isVisited(down)) {
			return search(square.getDown());
		}else if(left != null && left.getType() != SquareType.WALL && !isVisited(left)) {
			return search(square.getLeft());
		}else if(up != null && up.getType() != SquareType.WALL && !isVisited(up)) {
			return search(square.getUp());
		}
		return new CanvasCoords(-1, -1);
	}
}
