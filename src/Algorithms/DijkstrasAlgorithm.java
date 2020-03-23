package Algorithms;

import Model.CanvasCoords;
import Model.Square;

public class DijkstrasAlgorithm extends Search{
	
	private Integer[][] distances;
	private boolean[][] shortestPath;
	private final int numVertices = numI * numJ;

	public DijkstrasAlgorithm(CanvasCoords root, Square[][] squareArray) {
		super(root, squareArray);
		
		int count = 0;
//		initialize distances array
		this.distances = new Integer[numI][numJ];
		for(int i = 0; i < numI; i++) {
			for(int j = 0; j < numJ; j++) {
				distances[i][j] = Integer.MAX_VALUE;
				count++;
			}
		}
		System.out.println(count);
//		initialize shortest path boolean array
		shortestPath = new boolean[numI][numJ];
		
//		initialize root distance as 0
		distances[root.getArrayX()][root.getArrayY()] = 0;

	}

	@Override
	public CanvasCoords search() {
		return null;
	}
	
}
