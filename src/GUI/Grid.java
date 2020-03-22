package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import Model.CanvasCoords;
import Model.Square;
import Model.SquareType;

import java.awt.Graphics;
import java.util.ArrayList;

public class Grid extends JPanel{
	
	private Square[][] squareArray; // array coords
	private CanvasCoords currentRoot; // canvas coords
	private ArrayList<CanvasCoords> destArray;
	
	public Grid() {
//		initialize dimensions
		Dimension dim = getPreferredSize();
		dim.height = 500;
		setPreferredSize(dim);
		int count = 0;
		
//		initialize squares
		squareArray = new Square[63][25];
		for(int i = 0; i < 63; i++) {
			for(int j = 0; j < 25; j++) {
				squareArray[i][j] = new Square(count, i * 20, j * 20, Color.white, SquareType.SAFE);
				count++;
			}
		}
		
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
		
		destArray = new ArrayList<>();
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
	public void addDest(int x, int y) {
		CanvasCoords current = new CanvasCoords(x,y);
		destArray.add(current);
	}
	public void setSquareArray(Square[][] squareArr) {
		this.squareArray = squareArr;
	}
	
	
	public void paint(Graphics g) {
//		paint grid at init
		int x;
		int y;
		Color color;
		for(int i = 0; i < 63; i++) {
			for(int j = 0; j < 25; j++) {
				x = squareArray[i][j].getXcoord();
				y = squareArray[i][j].getYcoord();
				color = squareArray[i][j].getColor();
				g.setColor(color);
				g.fillRect(x, y, 20, 20);
				g.setColor(Color.black);
				g.drawRect(x, y, 20, 20);
			}
		}
	}
}
