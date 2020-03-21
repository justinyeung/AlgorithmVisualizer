package Model;

public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(int x, int y) {
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
	
//	setters
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Coordinates c) {
		return c.getX() == this.x && c.getY() == this.y;
	}
}
