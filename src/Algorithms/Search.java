package Algorithms;

import java.util.LinkedList;

import Model.CanvasCoords;
import Model.Square;

public abstract class Search {
	
	protected CanvasCoords root;
	protected boolean[][] visitedArray;
	protected Square[][] squareArray;
	protected LinkedList<CanvasCoords> sortOrderQueue;
	protected Node[][] previousNodes;
	protected Node destinationPath;
	
	protected final int numI = 63;
	protected final int numJ = 25;
	
	public class Node{
		public CanvasCoords item;
		public Node next;
	}
	
	public Search(CanvasCoords root, Square[][] squareArray) {
		this.root = root;
		this.squareArray = squareArray;
		this.visitedArray = new boolean[numI][numJ];
		sortOrderQueue = new LinkedList<>();
		previousNodes = new Node[numI][numJ];
		for(int i = 0; i < numI; i++) {
			for(int j = 0; j < numJ; j++) {
				previousNodes[i][j] = new Node();
			}
		}
	}
	
//	getter for sort order queue
	public LinkedList<CanvasCoords> getSortOrderQueue(){
		return sortOrderQueue;
	}
	public Node[][] getPreviousNodes(){
		return previousNodes;
	}
	public Node getDestinationPath() {
		return destinationPath;
	}
	
//	local methods
	protected boolean isVisited(Square square) {
//		convert from canvas coords to array coords
		return visitedArray[square.getArrCoordX()][square.getArrCoordY()];
	}
	
	public abstract CanvasCoords search();
	
}
