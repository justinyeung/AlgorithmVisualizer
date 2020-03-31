package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import Model.CanvasCoords;
import Model.Square;
import Model.SquareType;

import java.awt.Graphics;

public class Grid extends JPanel{
	
	private Square[][] squareArray; // array coords
	private CanvasCoords currentRoot; // canvas coords
	protected final int numI = 63; // length and width of grid
	protected final int numJ = 25;
	
	private Font font = new Font("Arial", Font.BOLD, 14);
//	TODO: turn destArray to canvas coords
//	private ArrayList<CanvasCoords> destArray; // array coords
	
	public Grid() {
//		initialize
		Dimension dim = getPreferredSize();
		dim.height = 550;
		setPreferredSize(dim);
		int count = 0;
//		destArray = new ArrayList<>();
		
//		initialize squares
		squareArray = new Square[numI][numJ];
		for(int i = 0; i < numI; i++) {
			for(int j = 0; j < numJ; j++) {
				squareArray[i][j] = new Square(count, i * 22, j * 22, Color.white, SquareType.SAFE);
				count++;
			}
		}
		
//		initialize default root
		squareArray[20][12].setColor(Color.red);
		squareArray[20][12].setType(SquareType.ROOT);
		setRootCoords(20*22, 12*22);
		
//		initialize default destination
		squareArray[43][12].setColor(Color.blue);
		squareArray[43][12].setType(SquareType.DESTINATION);
//		addDest(43, 12);
		
		
//		initialize square links
		for(int i = 0; i < numI; i++) {
			for(int j = 0; j < numJ; j++) {
				if(i > 0) {
					squareArray[i][j].setLeft(squareArray[i - 1][j]);
				}
				if(i < numI - 1) {
					squareArray[i][j].setRight(squareArray[i + 1][j]);
				}
				if(j > 0) {
					squareArray[i][j].setUp(squareArray[i][j - 1]);
				}
				if(j < numJ-1) {
					squareArray[i][j].setDown(squareArray[i][j + 1]);
				}
			}
		}
	}
	
//	getters
	public Color getColor(int x, int y) {
		return squareArray[x][y].getColor();
	}
	public CanvasCoords getRootCoords() {
		return currentRoot;
	}
	public boolean existsRoot() {
		return currentRoot != null;
	}
	public SquareType getType(int x, int y) {
		return squareArray[x][y].getType();
	}
	public Square[][] getArray(){
		return this.squareArray;
	}
	
//	setters
	public void setColorTypeCoord(int x, int y, Color color, SquareType type) {
//		array coords
		squareArray[x][y].setColor(color);
		squareArray[x][y].setType(type);
	}
	public void setRootCoords(int x, int y) {
//		canvas coords
		if(this.existsRoot()) {
			currentRoot.setX(x);
			currentRoot.setY(y);
		}else {
			currentRoot = new CanvasCoords(x, y);
		}
	}
//	public void addDest(int x, int y) {
////		array coords
//		CanvasCoords current = new CanvasCoords(x,y);
//		destArray.add(current);
//	}
	public void resetHeight(int x, int y) {
		squareArray[x][y].resetHeight();
	}
	public void setSquareArray(Square[][] squareArr) {
		this.squareArray = squareArr;
	}
	public void raiseHill(int x, int y) {
//		get surrounding squares to create hill
		Square current = squareArray[x][y];
		Square right = current.getRight();
		Square down = current.getDown();
		Square left = current.getLeft();
		Square up = current.getUp();
		
//		right
		if(current.getRight() != null && right.getHeight() < current.getHeight()) {
			raiseHill(right.getArrCoordX(), right.getArrCoordY());
		}
//		down
		if(current.getDown() != null && down.getHeight() < current.getHeight()) {
			raiseHill(down.getArrCoordX(), down.getArrCoordY());
		}
//		left
		if(current.getLeft() != null && left.getHeight() < current.getHeight()) {
			raiseHill(left.getArrCoordX(), left.getArrCoordY());
		}
//		up
		if(current.getUp() != null && up.getHeight() < current.getHeight()) {
			raiseHill(up.getArrCoordX(), up.getArrCoordY());
		}
		
//		increase current height
		current.incHeight();
		
//		stop recurstion at height 0
		if(current.getHeight() == 0) {
			return;
		}
	}
	public void lowerHill(int x, int y) {
//		get surrounding squares to create hill
		Square current = squareArray[x][y];
		Square right = current.getRight();
		Square down = current.getDown();
		Square left = current.getLeft();
		Square up = current.getUp();
		
//		right
		if(current.getRight() != null && right.getHeight() > current.getHeight()) {
			lowerHill(right.getArrCoordX(), right.getArrCoordY());
		}
//		down
		if(current.getDown() != null && down.getHeight() > current.getHeight()) {
			lowerHill(down.getArrCoordX(), down.getArrCoordY());
		}
//		left
		if(current.getLeft() != null && left.getHeight() > current.getHeight()) {
			lowerHill(left.getArrCoordX(), left.getArrCoordY());
		}
//		up
		if(current.getUp() != null && up.getHeight() > current.getHeight()) {
			lowerHill(up.getArrCoordX(), up.getArrCoordY());
		}
		
//		increase current height
		current.sinkHeight();
		
//		stop recurstion at height 0
		if(current.getHeight() == 0) {
			return;
		}
	}
	public void raiseHeight(int x, int y) {
		Square current = squareArray[x][y];
		current.incHeight();
	}
	public void lowerHeight(int x, int y) {
		Square current = squareArray[x][y];
		current.sinkHeight();
	}
	
	public void paint(Graphics g) {
//		paint grid at init
		super.paint(g);
		g.setFont(font);
		int x;
		int y;
		Color color;
		for(int i = 0; i < numI; i++) {
			for(int j = 0; j < numJ; j++) {
				Square current = squareArray[i][j];
				x = current.getXcoord(); // canvas coords
				y = current.getYcoord();
				color = current.getColor();
//				draw color grids according to squareArray
				g.setColor(color);
				g.fillRect(x, y, 22, 22);
//				draw rectangle borders
				g.setColor(Color.black);
				g.drawRect(x, y, 22, 22);
//				draw height inside grid
				Integer height = current.getHeight();
				if(height >= 0 && height <= 9) {
//					0 <= x <= 9
					g.drawString(current.getHeight().toString(), x+8, y+16);
				}else if(height >= -9 && height < 0) {
//					-9 <= x <= -1
					g.drawString(current.getHeight().toString(), x+5, y+16);
				}else if(height > 9){
//					x > 9
					g.drawString(current.getHeight().toString(), x+4, y+16);
				}else {
//					x < -9
					g.drawString(current.getHeight().toString(), x+1, y+16);
				}
				
			}
		}
	}
}
