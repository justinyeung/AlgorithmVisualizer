package Algorithms;

import java.util.LinkedList;

import Model.CanvasCoords;
import Model.Square;

public abstract class Search {
	
	protected CanvasCoords root;
	protected boolean[][] visitedArray;
	protected LinkedList<CanvasCoords> sortOrderQueue;
	
	public Search(CanvasCoords root, Square[][] squareArray) {
		this.root = root;
		this.visitedArray = new boolean[63][25];
		sortOrderQueue = new LinkedList<>();
	}
	
//	getter for sort order queue
	public LinkedList<CanvasCoords> getSortOrderQueue(){
		return sortOrderQueue;
	}
	
//	local methods
	protected boolean isVisited(Square square) {
//		convert from canvas coords to array coords
		return visitedArray[square.getArrCoordX()][square.getArrCoordY()];
	}
	
//	public abstract CanvasCoords search(Square[][] array, CanvasCoords root);
//	public abstract CanvasCoords search(Square square);
}
