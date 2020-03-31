package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
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

public class MainFrame extends JFrame {
	
	private Grid grid;
	private Menu menu;
	private JPanel menupanel;
	private boolean wallClick;
	private boolean rootClick;
	private boolean destClick;
	private boolean raiseHeight;
	private boolean lowerHeight;
	private boolean raiseHill;
	private boolean lowerHill;
	private boolean erase;
	private boolean eraseHeight;
	private Timer timer;
	private boolean resetClicked = false;
//	private boolean animationRunning = false;
	
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
				int x = Math.floorDiv(e.getX(), 22);
				int y = Math.floorDiv(e.getY(), 22);
				
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
				}else if(wallClick) {
					grid.setColorTypeCoord(x, y, Color.black, SquareType.WALL);
					grid.repaint();
				}else if(destClick){
//					paint on to grid
					grid.setColorTypeCoord(x, y, Color.blue, SquareType.DESTINATION);
					grid.repaint();
//					add to grid array of destinations
//					grid.addDest(x, y);
				}else if(raiseHill) {
					grid.raiseHill(x,y);
					grid.repaint();
				}else if(lowerHill) {
					grid.lowerHill(x,y);
					grid.repaint();
				}else if(raiseHeight) {
					grid.raiseHeight(x, y);
					grid.repaint();
				}else if(lowerHeight) {
					grid.lowerHeight(x, y);
					grid.repaint();
				}else if(erase) {
					if(grid.getType(x, y) != SquareType.ROOT) {
						grid.setColorTypeCoord(x, y, Color.white, SquareType.SAFE);
						grid.repaint();
					}
				}else if(eraseHeight) {
					grid.resetHeight(x, y);
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
				wallClick = false;
				rootClick = true;
				destClick = false;
				raiseHeight = false;
				lowerHeight = false;
				erase = false;
				raiseHill = false;
				lowerHill = false;
				eraseHeight = false;
			}

			@Override
			public void setDestination() {
				wallClick = false;
				rootClick = false;
				destClick = true;
				raiseHeight = false;
				lowerHeight = false;
				erase = false;
				raiseHill = false;
				lowerHill = false;
				eraseHeight = false;
			}

			@Override
			public void setWall() {
				wallClick = true;
				rootClick = false;
				destClick = false;
				raiseHeight = false;
				lowerHeight = false;
				erase = false;
				raiseHill = false;
				lowerHill = false;
				eraseHeight = false;
			}
			
			@Override
			public void raiseHill() {
				wallClick = false;
				rootClick = false;
				destClick = false;
				raiseHeight = false;
				lowerHeight = false;
				erase = false;
				raiseHill = true;
				lowerHill = false;
				eraseHeight = false;
			}

			@Override
			public void lowerHill() {
				wallClick = false;
				rootClick = false;
				destClick = false;
				raiseHeight = false;
				lowerHeight = false;
				erase = false;
				raiseHill = false;
				lowerHill = true;
				eraseHeight = false;
			}
			
			@Override
			public void raiseHeight() {
				wallClick = false;
				rootClick = false;
				destClick = false;
				raiseHeight = true;
				lowerHeight = false;
				erase = false;
				raiseHill = false;
				lowerHill = false;
				eraseHeight = false;
			}

			@Override
			public void lowerHeight() {
				wallClick = false;
				rootClick = false;
				destClick = false;
				raiseHeight = false;
				lowerHeight = true;
				erase = false;
				raiseHill = false;
				lowerHill = false;
				eraseHeight = false;
			}
			
			@Override
			public void erase() {
				wallClick = false;
				rootClick = false;
				destClick = false;
				raiseHeight = false;
				lowerHeight = false;
				erase = true;
				raiseHill = false;
				lowerHill = false;
				eraseHeight = false;
			}
			
			@Override
			public void eraseHeight() {
				wallClick = false;
				rootClick = false;
				destClick = false;
				raiseHeight = false;
				lowerHeight = false;
				erase = false;
				raiseHill = false;
				lowerHill = false;
				eraseHeight = true;
			}

			@Override
			public void reset() {
//				stop any animations
				resetClicked = true;
//				animationRunning = false;
				
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
				grid.setRootCoords(20*22, 12*22);
				
//				initialize default destination
				grid.setColorTypeCoord(43, 12, Color.blue, SquareType.DESTINATION);
				
				grid.repaint();
			}
			
			@Override
			public void start(String algorithm) {
				LinkedList<CanvasCoords> sortQueue;
				Stack<CanvasCoords> pathList = new Stack<>();
				Node dest;
				
//				reset the boolean that disabled animations
//				disable all button functionalities
				resetClicked = false; //true when reset clicked during animation
//				animationRunning = true; //true when animation is running
				wallClick = false;
				rootClick = false;
				destClick = false;
				raiseHeight = false;
				lowerHeight = false;
				erase = false;
				raiseHill = false;
				lowerHill = false;
				eraseHeight = false;
				
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
//							animationRunning = false;
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
//							animationRunning = false;
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
		setSize(1405,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
}
