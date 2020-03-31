package Model;

import java.awt.Color;

public class Square {
	private int id;
	private SquareType type;
	private Color color;
	private CanvasCoords coord; // CanvasCoords
	private int height ;
	
	private Square left;
	private Square right;
	private Square up;
	private Square down;
	
	public Square(int id, int xcoord, int ycoord, Color color, SquareType type) {
		this.id = id;
		this.coord = new CanvasCoords(xcoord, ycoord);
		this.setColor(color);
		this.type = type;
		this.height = 1;
	}

//	getters
	public Color getColor() {
		return color;
	}
	public int getXcoord() {
		return coord.getX();
	}
	public int getYcoord() {
		return coord.getY();
	}
	public Square getLeft() {
		return left;
	}
	public Square getRight() {
		return right;
	}
	public Square getUp() {
		return up;
	}
	public Square getDown() {
		return down;
	}
	public int getId() {
		return id;
	}
	public SquareType getType() {
		return type;
	}
	public int getArrCoordX() {
		return Math.floorDiv(getXcoord(), 22);
	}
	public int getArrCoordY() {
		return Math.floorDiv(getYcoord(), 22);
	}
	public Integer getHeight() {
		return height;
	}
	
//	setters
	public void setColor(Color color) {
		this.color = color;
	}
	public void setLeft(Square left) {
		this.left = left;
	}
	public void setRight(Square right) {
		this.right = right;
	}
	public void setUp(Square up) {
		this.up = up;
	}
	public void setDown(Square down) {
		this.down = down;
	}
	public void setType(SquareType type) {
		this.type = type;
	}
	public void resetHeight() {
		this.height = 1;
	}
	public void incHeight() {
		if(height < 99) {
			this.height++;
		}
	}
	public void sinkHeight() {
		if(height > -99) {
			this.height--;
		}
	}
	

	
}
