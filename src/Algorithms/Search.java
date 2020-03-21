package Algorithms;

import java.awt.Color;
import java.util.ArrayList;

import Model.CanvasCoords;
import Model.Square;
import Model.SquareType;

public abstract class Search {
	private CanvasCoords root;
	protected boolean[][] visitedArray;
	public ArrayList<CanvasCoords> sortOrder;
	
	public Search(CanvasCoords root, Square[][] squareArray) {
		this.root = root;
		this.visitedArray = new boolean[63][25];
		sortOrder = new ArrayList<>();
	}
		
//	local methods
	protected boolean isVisited(Square square) {
//		convert from canvas coords to array coords
		return visitedArray[square.getArrCoordX()][square.getArrCoordY()];
	}
	
	public abstract CanvasCoords search(Square[][] array, CanvasCoords root);
	public abstract CanvasCoords search(Square square);
}
