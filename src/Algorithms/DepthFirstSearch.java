package Algorithms;

import java.util.Stack;

import Model.CanvasCoords;
import Model.Square;
import Model.SquareType;

public class DepthFirstSearch extends Search{
	
	private Stack<Square> stack;
	
	public DepthFirstSearch(CanvasCoords root, Square[][] squareArray) {
		super(root, squareArray);
		stack = new Stack<Square>();
		CanvasCoords result = search();
		System.out.println(result.getArrayX() + ", " + result.getArrayY());
	}

	@Override
	public CanvasCoords search() {
		
		Square current;
		
//		push root to stack
		stack.push(this.squareArray[this.root.getArrayX()][this.root.getArrayY()]);
		previousNodes[root.getArrayX()][root.getArrayY()].item = root;
		
//		dfs iteratively
		while(!stack.empty()) {
			
//			pop from top of stack
			current = stack.peek();
			
//			mark as visited, add to sort order queue
			visitedArray[current.getArrCoordX()][current.getArrCoordY()] = true;
			sortOrderQueue.add(new CanvasCoords(current.getXcoord(), current.getYcoord()));
			
//			check if destination
			if(current.getType() == SquareType.DESTINATION) {
				destinationPath = previousNodes[current.getArrCoordX()][current.getArrCoordY()];
				return new CanvasCoords(current.getXcoord(), current.getYcoord());
			}
			
//			push all adjacent squares to stack
			Square right = current.getRight();
			Square down = current.getDown();
			Square left = current.getLeft();
			Square up = current.getUp();
			
			if(right != null && right.getType() != SquareType.WALL && !isVisited(right)) {
				stack.push(right);
				previousNodes[right.getArrCoordX()][right.getArrCoordY()].item = new CanvasCoords(right.getXcoord(), right.getYcoord());
				previousNodes[right.getArrCoordX()][right.getArrCoordY()].next = previousNodes[current.getArrCoordX()][current.getArrCoordY()];
			}else if(down != null && down.getType() != SquareType.WALL && !isVisited(down)) {
				stack.push(down);
				previousNodes[down.getArrCoordX()][down.getArrCoordY()].item = new CanvasCoords(down.getXcoord(), down.getYcoord());
				previousNodes[down.getArrCoordX()][down.getArrCoordY()].next = previousNodes[current.getArrCoordX()][current.getArrCoordY()];
			}else if(left != null && left.getType() != SquareType.WALL && !isVisited(left)) {
				stack.push(left);
				previousNodes[left.getArrCoordX()][left.getArrCoordY()].item = new CanvasCoords(left.getXcoord(), left.getYcoord());
				previousNodes[left.getArrCoordX()][left.getArrCoordY()].next = previousNodes[current.getArrCoordX()][current.getArrCoordY()];
			}else if(up != null && up.getType() != SquareType.WALL && !isVisited(up)) {
				stack.push(up);
				previousNodes[up.getArrCoordX()][up.getArrCoordY()].item = new CanvasCoords(up.getXcoord(), up.getYcoord());
				previousNodes[up.getArrCoordX()][up.getArrCoordY()].next = previousNodes[current.getArrCoordX()][current.getArrCoordY()];
			}else {
				stack.pop();
			}
		}
		return new CanvasCoords(-1, -1);
	}

}
