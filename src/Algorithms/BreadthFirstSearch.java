package Algorithms;

import java.util.LinkedList;

import Model.CanvasCoords;
import Model.Square;
import Model.SquareType;

public class BreadthFirstSearch extends Search{

	private LinkedList<Square> queue;
	private Square[][] squareArray;
	
	public BreadthFirstSearch(CanvasCoords root, Square[][] squareArray) {
		super(root, squareArray);
		queue = new LinkedList<Square>();
		this.squareArray = squareArray;
		CanvasCoords result = search();
		System.out.println(result.getArrayX() + ", " + result.getArrayY());
	}
	
	public CanvasCoords search() {
		
		Square current;
		
//		enqueue root to queue
		queue.add(this.squareArray[this.root.getArrayX()][this.root.getArrayY()]);
		previousNodes[root.getArrayX()][root.getArrayY()].item = new CanvasCoords(root.getX(), root.getY());
		visitedArray[root.getArrayX()][root.getArrayY()] = true;
		
//		bfs iteratively
		while(!queue.isEmpty()) {
			
//			dequeue current
			current = queue.remove();
			
//			add to sort order queue
			sortOrderQueue.add(new CanvasCoords(current.getXcoord(), current.getYcoord()));

//			check if destination, return canvascoords, set destinationpath node
			if(current.getType() == SquareType.DESTINATION) {
				destinationPath = previousNodes[current.getArrCoordX()][current.getArrCoordY()];
				return new CanvasCoords(current.getXcoord(), current.getYcoord());
			}
			
//			enqueue all adjacent squares
			Square right = current.getRight();
			Square down = current.getDown();
			Square left = current.getLeft();
			Square up = current.getUp();
			
//			if adjacent are safe
//			enqueue, mark visited
//			connect adjacent previousNodes linkedlist to current previousNodes linkedlist
			if(right != null && right.getType() != SquareType.WALL && !isVisited(right)) {
				queue.add(right);
				visitedArray[right.getArrCoordX()][right.getArrCoordY()] = true;
				previousNodes[right.getArrCoordX()][right.getArrCoordY()].item = new CanvasCoords(right.getXcoord(), right.getYcoord());
				previousNodes[right.getArrCoordX()][right.getArrCoordY()].next = previousNodes[current.getArrCoordX()][current.getArrCoordY()];
			}
			if(down != null && down.getType() != SquareType.WALL && !isVisited(down)) {
				queue.add(down);
				visitedArray[down.getArrCoordX()][down.getArrCoordY()] = true;
				previousNodes[down.getArrCoordX()][down.getArrCoordY()].item = new CanvasCoords(down.getXcoord(), down.getYcoord());
				previousNodes[down.getArrCoordX()][down.getArrCoordY()].next = previousNodes[current.getArrCoordX()][current.getArrCoordY()];
			}
			if(left != null && left.getType() != SquareType.WALL && !isVisited(left)) {
				queue.add(left);
				visitedArray[left.getArrCoordX()][left.getArrCoordY()] = true;
				previousNodes[left.getArrCoordX()][left.getArrCoordY()].item = new CanvasCoords(left.getXcoord(), left.getYcoord());
				previousNodes[left.getArrCoordX()][left.getArrCoordY()].next = previousNodes[current.getArrCoordX()][current.getArrCoordY()];
			}
			if(up != null && up.getType() != SquareType.WALL && !isVisited(up)) {
				queue.add(up);
				visitedArray[up.getArrCoordX()][up.getArrCoordY()] = true;
				previousNodes[up.getArrCoordX()][up.getArrCoordY()].item = new CanvasCoords(up.getXcoord(), up.getYcoord());
				previousNodes[up.getArrCoordX()][up.getArrCoordY()].next = previousNodes[current.getArrCoordX()][current.getArrCoordY()];
			}
		}
		
		return new CanvasCoords(-1, -1);
	}
	
}
