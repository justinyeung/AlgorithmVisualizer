package Algorithms;

import Model.CanvasCoords;
import Model.Square;
import Model.SquareType;

public class LinearGridSearch extends Search{
	
	public LinearGridSearch(CanvasCoords root, Square[][] squareArray) {
		super(root, squareArray);
		CanvasCoords result = search(squareArray, root);
		System.out.println(result.getArrayX() + ", " + result.getArrayY());
	}
	
//	search
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
