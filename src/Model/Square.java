package Model;

import java.awt.Color;

public class Square {
	private int id;
	private Color color;
	private int xcoord;
	private int ycoord;
	
	private Square left;
	private Square right;
	private Square up;
	private Square down;
	
	public Square(int id, int xcoord, int ycoord, Color color) {
		this.id = id;
		this.setXcoord(xcoord);
		this.setYcoord(ycoord);
		this.setColor(color);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getXcoord() {
		return xcoord;
	}

	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}

	public int getYcoord() {
		return ycoord;
	}

	public void setYcoord(int ycoord) {
		this.ycoord = ycoord;
	}

	public Square getLeft() {
		return left;
	}

	public void setLeft(Square left) {
		this.left = left;
	}

	public Square getRight() {
		return right;
	}

	public void setRight(Square right) {
		this.right = right;
	}

	public Square getUp() {
		return up;
	}

	public void setUp(Square up) {
		this.up = up;
	}

	public Square getDown() {
		return down;
	}

	public void setDown(Square down) {
		this.down = down;
	}

	public int getId() {
		return id;
	}
}
