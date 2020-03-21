package Algorithms;

import java.util.Stack;

import Model.CanvasCoords;
import Model.Square;
import Model.SquareType;

public class DepthFirstSearch extends Search{
	
	private Stack<Square> stack;
	private Square[][] squareArray;
	
	public DepthFirstSearch(CanvasCoords root, Square[][] squareArray) {
		super(root, squareArray);
		stack = new Stack<Square>();
		this.squareArray = squareArray;
		CanvasCoords result = search();
		System.out.println(result.getArrayX() + ", " + result.getArrayY());
	}

	
	public CanvasCoords search() {
//		push root to stack
		System.out.println("root: "+this.root.getArrayX());
		System.out.println("root: "+this.root.getArrayY());
		stack.push(this.squareArray[this.root.getArrayX()][this.root.getArrayY()]);
		Square current;
		
//		dfs iteratively
		while(!stack.empty()) {
			
//			pop from top of stack
			current = stack.peek();
			
//			mark as visited, add to sort order queue
			visitedArray[current.getArrCoordX()][current.getArrCoordY()] = true;
			sortOrderQueue.add(new CanvasCoords(current.getXcoord(), current.getYcoord()));
			
//			check if destination
			if(current.getType() == SquareType.DESTINATION) {
				return new CanvasCoords(current.getXcoord(), current.getYcoord());
			}
			
//			push all adjacent squares to stack
			Square right = current.getRight();
			Square down = current.getDown();
			Square left = current.getLeft();
			Square up = current.getUp();
			
			if(right != null && right.getType() != SquareType.WALL && !isVisited(right)) {
				stack.push(right);
			}else if(down != null && down.getType() != SquareType.WALL && !isVisited(down)) {
				stack.push(down);
			}else if(left != null && left.getType() != SquareType.WALL && !isVisited(left)) {
				stack.push(left);
			}else if(up != null && up.getType() != SquareType.WALL && !isVisited(up)) {
				stack.push(up);
			}else {
				stack.pop();
			}
		}
		
		
		return new CanvasCoords(-1, -1);
	}

}
