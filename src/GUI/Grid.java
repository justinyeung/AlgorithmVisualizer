package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import Model.CanvasCoords;
import Model.Square;
import Model.SquareType;

import java.awt.Graphics;
import java.util.ArrayList;

public class Grid extends JPanel{
	
	private Square[][] squareArray; // array coords
	private CanvasCoords currentRoot; // canvas coords
	
	private Font font = new Font("Arial", Font.BOLD, 14);
//	TODO: turn destArray to canvas coords
//	private ArrayList<CanvasCoords> destArray; // array coords
	
	public Grid() {
//		initialize
		Dimension dim = getPreferredSize();
		dim.height = 500;
		setPreferredSize(dim);
		int count = 0;
//		destArray = new ArrayList<>();
		
//		initialize squares
		squareArray = new Square[63][25];
		for(int i = 0; i < 63; i++) {
			for(int j = 0; j < 25; j++) {
				squareArray[i][j] = new Square(count, i * 20, j * 20, Color.white, SquareType.SAFE);
				count++;
			}
		}
		
//		initialize default root
		squareArray[20][12].setColor(Color.red);
		squareArray[20][12].setType(SquareType.ROOT);
		setRootCoords(20*20, 12*20);
		
//		initialize default destination
		squareArray[43][12].setColor(Color.blue);
		squareArray[43][12].setType(SquareType.DESTINATION);
//		addDest(43, 12);
		
		
//		initialize square links
		for(int i = 0; i < 63; i++) {
			for(int j = 0; j < 25; j++) {
				if(i > 0) {
					squareArray[i][j].setLeft(squareArray[i - 1][j]);
				}
				if(i < 62) {
					squareArray[i][j].setRight(squareArray[i + 1][j]);
				}
				if(j > 0) {
					squareArray[i][j].setUp(squareArray[i][j - 1]);
				}
				if(j < 24) {
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
	public void raiseHeight(int x, int y) {
//		add logic to create hills
		Square current = squareArray[x][y];
		Square right = current.getRight();
		Square down = current.getDown();
		Square left = current.getLeft();
		Square up = current.getUp();
		
		
		

		
//		right
		if(current.getRight() != null && right.getHeight() < current.getHeight()) {
			raiseHeight(right.getArrCoordX(), right.getArrCoordY());
		}
//		down
		if(current.getDown() != null && down.getHeight() >= 0 && down.getHeight() < current.getHeight()) {
			raiseHeight(down.getArrCoordX(), down.getArrCoordY());
		}
//		left
		if(current.getLeft() != null && left.getHeight() >= 0 && left.getHeight() < current.getHeight()) {
			raiseHeight(left.getArrCoordX(), left.getArrCoordY());
		}
//		up
		if(current.getUp() != null && up.getHeight() >= 0 && up.getHeight() < current.getHeight()) {
			raiseHeight(up.getArrCoordX(), up.getArrCoordY());
		}

		current.incHeight();
		
		if(current.getHeight() == 0) {
			return;
		}
	}
	public void lowerHeight(int x, int y) {
//		add logic to create hills
		squareArray[x][y].sinkHeight();
		
//		right
		
//		down
		
//		left
		
//		up
		
		
	}
	
	public void paint(Graphics g) {
//		paint grid at init
		super.paint(g);
		g.setFont(font);
		int x;
		int y;
		Color color;
		for(int i = 0; i < 63; i++) {
			for(int j = 0; j < 25; j++) {
				Square current = squareArray[i][j];
				x = current.getXcoord(); // canvas coords
				y = current.getYcoord();
				color = current.getColor();
//				draw color grids according to squareArray
				g.setColor(color);
				g.fillRect(x, y, 20, 20);
//				draw rectangle borders
				g.setColor(Color.black);
				g.drawRect(x, y, 20, 20);
//				draw height inside grid
				g.drawString(current.getHeight().toString(), x+8, y+15);
			}
		}
	}
}
