package Algorithms;

import java.util.LinkedList;

import Model.CanvasCoords;
import Model.Square;

public abstract class Search {
	
	protected CanvasCoords root;
	protected boolean[][] visitedArray;
	protected LinkedList<CanvasCoords> sortOrderQueue;
	protected Node[][] previousNodes;
	protected Node destinationPath;
	public class Node{
		public CanvasCoords item;
		public Node next;
	}
	
	public Search(CanvasCoords root, Square[][] squareArray) {
		this.root = root;
		this.visitedArray = new boolean[63][25];
		sortOrderQueue = new LinkedList<>();
		previousNodes = new Node[63][25];
		for(int i = 0; i < 63; i++) {
			for(int j = 0; j < 25; j++) {
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
	
}
