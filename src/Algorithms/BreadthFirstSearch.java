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
		queue.push(this.squareArray[this.root.getArrayX()][this.root.getArrayY()]);
		
//		bfs iteratively
		while(!queue.isEmpty()) {
			
//			dequeue current
			current = queue.remove();
			
//			add to sort order queue
			sortOrderQueue.add(new CanvasCoords(current.getXcoord(), current.getYcoord()));

//			check if destination
			if(current.getType() == SquareType.DESTINATION) {
				return new CanvasCoords(current.getXcoord(), current.getYcoord());
			}
			
//			enqueue all adjacent squares
			Square right = current.getRight();
			Square down = current.getDown();
			Square left = current.getLeft();
			Square up = current.getUp();
			
			if(right != null && right.getType() != SquareType.WALL && !isVisited(right)) {
				queue.add(right);
				visitedArray[right.getArrCoordX()][right.getArrCoordY()] = true;
			}
			if(down != null && down.getType() != SquareType.WALL && !isVisited(down)) {
				queue.add(down);
				visitedArray[down.getArrCoordX()][down.getArrCoordY()] = true;
			}
			if(left != null && left.getType() != SquareType.WALL && !isVisited(left)) {
				queue.add(left);
				visitedArray[left.getArrCoordX()][left.getArrCoordY()] = true;
			}
			if(up != null && up.getType() != SquareType.WALL && !isVisited(up)) {
				queue.add(up);
				visitedArray[up.getArrCoordX()][up.getArrCoordY()] = true;
			}
		}
		
		return new CanvasCoords(-1, -1);
	}
	
}
