package Algorithms;

import java.awt.Color;

import Model.CanvasCoords;
import Model.Square;
import Model.SquareType;

public abstract class Search {
	private CanvasCoords root;
	private Square[][] squareArray;
	protected boolean[][] visitedArray;
	
	public Search(CanvasCoords root, Square[][] squareArray) {
		this.root = root;
		this.squareArray = squareArray;
		this.visitedArray = new boolean[63][25];
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
	protected boolean isVisited(Square square) {
//		convert from canvas coords to array coords
		System.out.println("X: "+square.getArrCoordX());
		System.out.println("Y: "+square.getArrCoordY());
		return visitedArray[square.getArrCoordX()][square.getArrCoordY()];
	}
	
	public abstract CanvasCoords search(Square[][] array, CanvasCoords root);
	public abstract CanvasCoords search(Square square);
}
