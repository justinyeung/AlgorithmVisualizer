package Model;

public class CanvasCoords {
//	canvas CanvasCoords
	private int x;
	private int y;
	
	public CanvasCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
//	getters
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getArrayX() {
		return Math.floorDiv(this.x, 22);
	}
	public int getArrayY() {
		return Math.floorDiv(this.y, 22);
	}
	
//	setters
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(CanvasCoords c) {
		return c.getX() == this.x && c.getY() == this.y;
	}
}
