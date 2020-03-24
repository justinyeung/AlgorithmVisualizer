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
//		initialize shortest path boolean array
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
	
	@Override
	public void search() {
		
		CanvasCoords current;
		
//		initialize root distance as 0
		distances[root.getArrayX()][root.getArrayY()] = 0;
		
		for(int count = 0; count < numVertices - 1; count++) {
//			use nextMinDist to find next shortest distance from current square
//			returns CanvasCoords of new current square
			current = nextMinDist();
			
//			check if current is destination
			if(squareArray[current.getArrayX()][current.getArrayY()].getType() == SquareType.DESTINATION) {
				System.out.println(current.getArrayX()+", "+current.getArrayY());
				return;
			}
			
//			mark current square in visitedArray
			visitedArray[current.getArrayX()][current.getArrayY()] = true;
//			asdfasdfasdfsd
//			update distance values for adjacent squares (from node to root)
			for(int v = 0; v < numVertices; v++) {
//				update if not visited, 
//				if distance from root to node is through v is less than distance of v
				
			}
		}
	}
	
}
