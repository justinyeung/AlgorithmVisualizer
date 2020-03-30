package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Algorithms.BreadthFirstSearch;
import Algorithms.DepthFirstSearch;
import Algorithms.DijkstrasAlgorithm;
import Algorithms.Search.Node;
import Model.CanvasCoords;
import Model.SquareType;

import java.awt.Graphics;

public class MainFrame extends JFrame {
	
	private Grid grid;
	private Menu menu;
	private JPanel menupanel;
	private boolean rootClick;
	private boolean destClick;
	private boolean raiseHeight;
	private boolean lowerHeight;
	private Timer timer;
	private boolean resetClicked;
	
	public MainFrame() {
		super("Algorithm Visualizer");
		
//		initialize components
		grid = new Grid();
		menu = new Menu();
		
//		set type of layout for jframe
		setLayout(new BorderLayout());
		
//		set layout and panel for menu buttons
		menupanel = new JPanel();
		menupanel.setLayout(new BorderLayout());

//		add grid and menupanel to layout of JFrame
		add(grid, BorderLayout.PAGE_END);
		add(menupanel, BorderLayout.PAGE_START);
//		add jframes to menupanel
		menupanel.add(menu.firstline, BorderLayout.PAGE_START);
		menupanel.add(menu.secondline, BorderLayout.CENTER);
		menupanel.add(menu.lastline, BorderLayout.PAGE_END);
		
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
//				calculate array CanvasCoords given mouse address
				int x = Math.floorDiv(e.getX(), 20);
				int y = Math.floorDiv(e.getY(), 20);
				
//				change color of grid square depending on button press
				if(rootClick) {
//					paint on to grid, delete current root
					if(grid.existsRoot()) {
						int oldRootX = grid.getRootCoords().getArrayX();
						int oldRootY = grid.getRootCoords().getArrayY();
						grid.setColorTypeCoord(oldRootX, oldRootY, Color.white, SquareType.SAFE);
					}
					grid.setColorTypeCoord(x, y, Color.red, SquareType.ROOT);
					grid.repaint();
//					change root canvas coords
					grid.setRootCoords(e.getX(), e.getY());
				}else if(destClick){
//					paint on to grid
					grid.setColorTypeCoord(x, y, Color.blue, SquareType.DESTINATION);
					grid.repaint();
//					add to grid array of destinations
//					grid.addDest(x, y);
				}else if(raiseHeight) {
					grid.raiseHeight(x, y);
					grid.repaint();
				}else if(lowerHeight) {
					grid.lowerHeight(x, y);
					grid.repaint();
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
			public void setRoot() {
				rootClick = true;
				destClick = false;
				raiseHeight = false;
				lowerHeight = false;
			}

			@Override
			public void setDestination() {
				rootClick = false;
				destClick = true;
				raiseHeight = false;
				lowerHeight = false;
			}

			@Override
			public void setWall() {
				rootClick = false;
				destClick = false;
				raiseHeight = false;
				lowerHeight = false;
			}
			
			@Override
			public void raiseHeight() {
				rootClick = false;
				destClick = false;
				raiseHeight = true;
				lowerHeight = false;
			}

			@Override
			public void lowerHeight() {
				rootClick = false;
				destClick = false;
				raiseHeight = false;
				lowerHeight = true;
			}

			@Override
			public void reset() {
//				stop any animations
				resetClicked = true;
				
				for(int i = 0; i < grid.numI; i++) {
					for(int j = 0; j < grid.numJ; j++) {
						if(grid.getType(i, j) != SquareType.SAFE) {
							grid.setColorTypeCoord(i, j, Color.white, SquareType.SAFE);
						}
						grid.resetHeight(i, j);
					}
				}
//				reset default root
				grid.setColorTypeCoord(20, 12, Color.red, SquareType.ROOT);
				grid.setRootCoords(20*20, 12*20);
				
//				initialize default destination
				grid.setColorTypeCoord(43, 12, Color.blue, SquareType.DESTINATION);
				
				grid.repaint();
			}
			
			@Override
			public void start(String algorithm) {
				LinkedList<CanvasCoords> sortQueue;
				Stack<CanvasCoords> pathList = new Stack<>();
				Node dest;
				
				resetClicked = false;
				
//				conditional statement for which algorithm to use
				if(algorithm == "Breadth First Search") {
//					get sortorderqueue from bfs
					BreadthFirstSearch bfs = new BreadthFirstSearch(grid.getRootCoords(), grid.getArray());
					sortQueue = bfs.getSortOrderQueue();
					System.out.println("n: "+sortQueue.size());
					dest = bfs.getDestinationPath();
				}else if(algorithm == "Depth First Search") {
					DepthFirstSearch dfs = new DepthFirstSearch(grid.getRootCoords(), grid.getArray());
					sortQueue = dfs.getSortOrderQueue();
					System.out.println("n: "+sortQueue.size());
					dest = dfs.getDestinationPath();
				}else if(algorithm == "Dijkstra's Shortest Path Algorithm") {
					DijkstrasAlgorithm djk = new DijkstrasAlgorithm(grid.getRootCoords(), grid.getArray());
					sortQueue = djk.getSortOrderQueue();
					System.out.println("n: "+sortQueue.size());
					dest = djk.getDestinationPath();
				}else {
					sortQueue = new LinkedList<>();
					dest = null;
					System.out.println("No algorithm selected");
				}
				
//				iterate through shortest path to add to queue for printing
				while(dest != null) {
					pathList.add(dest.item);
					dest = dest.next;
				}
				
//				action listener for timer below
				ActionListener paintListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
//						paint coordinates yellow from print order queue
						if(resetClicked) {
//							if reset is clicked, stop animation
							timer.stop();
							resetClicked = false;
						}else if(!sortQueue.isEmpty()) {
							CanvasCoords head = sortQueue.remove();
							int x = head.getArrayX();
							int y = head.getArrayY();
							grid.setColorTypeCoord(x, y, Color.gray, SquareType.SEARCHED);
							grid.repaint();
						}else if(!pathList.isEmpty()) {
							CanvasCoords head = pathList.pop();
							int x = head.getArrayX();
							int y = head.getArrayY();
							grid.setColorTypeCoord(x, y, Color.yellow, SquareType.SHORTESTPATH);
							grid.repaint();
						}else {
							timer.stop();
						}
					}
				};
				
//				timer for paint animation
				timer = new Timer(5, paintListener);
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
