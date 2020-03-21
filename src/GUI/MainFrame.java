package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import Algorithms.LinearGridSearch;
import Model.CanvasCoords;
import Model.SquareType;

import java.awt.Graphics;

public class MainFrame extends JFrame {
	
	private Grid grid;
	private Menu menu;
	private boolean rootClick;
	private boolean destClick;
	private Timer timer;
	
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
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
//				System.out.println(e.getX() + "," + e.getY());
				
//				calculate array CanvasCoords given mouse address
				int x = Math.floorDiv(e.getX(), 20);
				int y = Math.floorDiv(e.getY(), 20);
				
//				change color of grid square depending on button press
				if(rootClick) {
//					paint on to grid, delete current root
					if(grid.existsRoot()) {
						int oldRootX = grid.getRootCoords().getX();
						int oldRootY = grid.getRootCoords().getY();
						grid.setColorTypeCoord(oldRootX, oldRootY, Color.white, SquareType.SAFE);
					}
					grid.setColorTypeCoord(x, y, Color.red, SquareType.ROOT);
					grid.repaint();
//					change root array coords in grid
					grid.setRootCoords(x, y);
				}else if(destClick){
//					paint on to grid
					grid.setColorTypeCoord(x, y, Color.blue, SquareType.DESTINATION);
					grid.repaint();
//					add to grid array of destinations
					grid.addDest(x, y);
				}else {
					grid.setColorTypeCoord(x, y, Color.black, SquareType.WALL);
					grid.repaint();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		
//		menu button functions (called from menu)
		menu.setButtonListener(new ButtonListener() {
			@Override
			public void reset() {
				for(int i = 0; i < 63; i++) {
					for(int j = 0; j < 25; j++) {
						if(grid.getType(i, j) != SquareType.SAFE) {
							grid.setColorTypeCoord(i, j, Color.white, SquareType.SAFE);
						}
					}
				}
				grid.repaint();
			}

			@Override
			public void setRoot() {
				if(rootClick) {
					rootClick = false;
				}else {
					rootClick = true;
					destClick = false;
				}
			}

			@Override
			public void setDestination() {
				if(destClick) {
					destClick = false;
				}else {
					destClick = true;
					rootClick = false;
				}
			}

			@Override
			public void setWall() {
				destClick = false;
				rootClick = false;
			}

			@Override
			public void start() {
				LinearGridSearch lgs = new LinearGridSearch(grid.getRootCoords(), grid.getArray());
				ActionListener paintListener = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
//						paint coordinates yellow from print order queue
						if(lgs.sortOrderQueue.size() > 0) {
							CanvasCoords head = lgs.sortOrderQueue.remove();
							int x = head.getArrayX();
							int y = head.getArrayY();
							grid.setColorTypeCoord(x, y, Color.yellow, SquareType.SEARCHED);
							grid.repaint();
						}else {
							timer.stop();
						}
						
					}
				};
				timer = new Timer(10, paintListener);
				timer.start();
			}
			
		});
		
//		sets jframe
		setVisible(true);
		setSize(1280,720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
}
