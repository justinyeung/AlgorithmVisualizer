package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Model.Square;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Grid extends JPanel{
	
	private Square[][] squareArray;
	private int x;
	private int y;
	private Color color;
	
	public Grid() {
		Dimension dim = getPreferredSize();
		dim.height = 500;
		setPreferredSize(dim);
		int count = 0;
		
//		initialize squares
		squareArray = new Square[63][25];
		for(int i = 0; i < 63; i++) {
			for(int j = 0; j < 25; j++) {
				squareArray[i][j] = new Square(count, i * 20, j * 20, Color.white);
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
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void setColorCoord(int x, int y, Color color) {
		squareArray[x][y].setColor(color);
	}
	
	public void reset() {
		
	}
	
	public void paint(Graphics g) {
//		paint grid at init
		for(int i = 0; i < 63; i++) {
			for(int j = 0; j < 25; j++) {
				this.x = squareArray[i][j].getXcoord();
				this.y = squareArray[i][j].getYcoord();
				this.color = squareArray[i][j].getColor();
				g.setColor(color);
				g.fillRect(x, y, 20, 20);
				g.setColor(Color.black);
				g.drawRect(x, y, 20, 20);
			}
		}
	}
}
