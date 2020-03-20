package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import java.awt.Graphics;

public class MainFrame extends JFrame {
	
	private Grid grid;
	private Menu menu;
	
	public MainFrame() {
		super("Algorithm Visualizer");
		
//		type of layout
		setLayout(new BorderLayout());
		
//		initialize components
		grid = new Grid();
		menu = new Menu();
		
//		add components to JFrame
		add(grid, BorderLayout.SOUTH);
		add(menu, BorderLayout.NORTH);
		
//		mouse listener
		grid.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + "," + e.getY());
				int x = Math.floorDiv(e.getX(), 20);
				int y = Math.floorDiv(e.getY(), 20);
				grid.setColorCoord(x, y, Color.black);
				grid.repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		
		
		
//		sets jframe
		setVisible(true);
		setSize(1280,720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
}
