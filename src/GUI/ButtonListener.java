package GUI;

public interface ButtonListener {
	public abstract void setRoot();
	public abstract void setDestination();
	public abstract void setWall();
	public abstract void erase();
	public abstract void raiseHeight();
	public abstract void lowerHeight();
	public abstract void reset();
	public abstract void start(String algorithm);
}
