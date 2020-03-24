package Algorithms;

import Model.CanvasCoords;
import Model.Square;
import Model.SquareType;

public class DijkstrasAlgorithm extends Search{
	
	private Integer[][] distances;
	private final int numVertices = numI * numJ;
	
	public DijkstrasAlgorithm(CanvasCoords root, Square[][] squareArray) {
		super(root, squareArray);
		
//		initialize distances array to be infinity for every value
		this.distances = new Integer[numI][numJ];
		for(int i = 0; i < numI; i++) {
			for(int j = 0; j < numJ; j++) {
				distances[i][j] = Integer.MAX_VALUE;
			}
		}
		CanvasCoords result = search();
		System.out.println(result.getArrayX() + ", " + result.getArrayY());
	}

	private CanvasCoords nextMinDist() {
//		initialize min value
		int min = Integer.MAX_VALUE;
		CanvasCoords minCoord = new CanvasCoords(-1, -1);
		
//		loop through entire grid (squareArray)
		for(int i = 0; i < 63; i++) {
			for(int j = 0; j < 25; j++) {
				if(visitedArray[i][j] == false && distances[i][j] <= min) {
					min = distances[i][j];
					minCoord = new CanvasCoords(i*20, j*20);
				}
			}
		}
		return minCoord;
	}
	
	private int getDist(Square square) {
		return distances[square.getArrCoordX()][square.getArrCoordY()];
	}
	private int getDist(CanvasCoords coord) {
		return distances[coord.getArrayX()][coord.getArrayY()];
	}
	
	@Override
	public CanvasCoords search() {
		
		CanvasCoords current;
		
//		initialize root distance as 0
		distances[root.getArrayX()][root.getArrayY()] = 0;
		previousNodes[root.getArrayX()][root.getArrayY()].item = root;
		
		for(int count = 0; count < numVertices - 1; count++) {
//			use nextMinDist to find next shortest distance from current square
//			returns CanvasCoords of new current square
			current = nextMinDist(); // canvas coord
			
//			check if current is destination
			if(squareArray[current.getArrayX()][current.getArrayY()].getType() == SquareType.DESTINATION) {
				destinationPath = previousNodes[current.getArrayX()][current.getArrayY()];
				return current;
			}
			
//			mark current square in visitedArray
			visitedArray[current.getArrayX()][current.getArrayY()] = true;
			sortOrderQueue.add(current);
			
//			get adjacent squares
			Square right = squareArray[current.getArrayX()][current.getArrayY()].getRight();
			Square down = squareArray[current.getArrayX()][current.getArrayY()].getDown();
			Square left = squareArray[current.getArrayX()][current.getArrayY()].getLeft();
			Square up = squareArray[current.getArrayX()][current.getArrayY()].getUp();
			
//			update distance values for adjacent squares (from adjacent node to root)
//			update if not visited, 
//			sets distance if current dist + adjacent height < adjacent dist
			if(right != null && right.getType() != SquareType.WALL && !isVisited(right) && getDist(current) + right.getHeight() < getDist(right)) {
				distances[right.getArrCoordX()][right.getArrCoordY()] = getDist(current) + right.getHeight();
				previousNodes[right.getArrCoordX()][right.getArrCoordY()].item = new CanvasCoords(right.getXcoord(), right.getYcoord());
				previousNodes[right.getArrCoordX()][right.getArrCoordY()].next = previousNodes[current.getArrayX()][current.getArrayY()];
			}
			if(down != null && down.getType() != SquareType.WALL && !isVisited(down) && getDist(current) + down.getHeight() < getDist(down)) {
				distances[down.getArrCoordX()][down.getArrCoordY()] = getDist(current) + down.getHeight();
				previousNodes[down.getArrCoordX()][down.getArrCoordY()].item = new CanvasCoords(down.getXcoord(), down.getYcoord());
				previousNodes[down.getArrCoordX()][down.getArrCoordY()].next = previousNodes[current.getArrayX()][current.getArrayY()];
			}
			if(left != null && left.getType() != SquareType.WALL && !isVisited(left) && getDist(current) + left.getHeight() < getDist(left)) {
				distances[left.getArrCoordX()][left.getArrCoordY()] = getDist(current) + left.getHeight();
				previousNodes[left.getArrCoordX()][left.getArrCoordY()].item = new CanvasCoords(left.getXcoord(), left.getYcoord());
				previousNodes[left.getArrCoordX()][left.getArrCoordY()].next = previousNodes[current.getArrayX()][current.getArrayY()];
			}
			if(up != null && up.getType() != SquareType.WALL && !isVisited(up) && getDist(current) + up.getHeight() < getDist(up)) {
				distances[up.getArrCoordX()][up.getArrCoordY()] = getDist(current) + up.getHeight();
				previousNodes[up.getArrCoordX()][up.getArrCoordY()].item = new CanvasCoords(up.getXcoord(), up.getYcoord());
				previousNodes[up.getArrCoordX()][up.getArrCoordY()].next = previousNodes[current.getArrayX()][current.getArrayY()];
			}
			
		}
		return new CanvasCoords(-1, -1);
	}
	
}
