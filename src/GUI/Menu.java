package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Menu implements ActionListener{
	
	private JButton startButton;
	private JButton resetButton;
	private JButton wallButton;
	private JButton rootButton;
	private JButton destButton;
	private JButton raiseButton;
	private JButton sinkButton;
	private JButton eraserButton;
	private JComboBox<String> selectionBox;
	private ButtonListener buttonListener;
	private String[] selections = {
			"Breadth First Search", 
			"Depth First Search", 
			"Dijkstra's Shortest Path Algorithm"};
	JPanel firstline;
	JPanel secondline;
	JPanel lastline;
	private boolean animationRunning;
	
	public Menu(){
//		init components
		wallButton = new JButton("Wall");
		rootButton = new JButton("Root");
		destButton = new JButton("Destination");
		eraserButton = new JButton("Eraser");
		selectionBox = new JComboBox<String>(selections);
		startButton = new JButton("Start");
		resetButton = new JButton("Reset");
		raiseButton = new JButton("Raise Height");
		sinkButton = new JButton("Lower Height");
		
		firstline = new JPanel();
		secondline = new JPanel();
		lastline = new JPanel();
		
//		set layout
		firstline.setLayout(new FlowLayout());
		secondline.setLayout(new FlowLayout());
		lastline.setLayout(new FlowLayout());
		
//		add to menu
		firstline.add(wallButton);
		firstline.add(rootButton);
		firstline.add(destButton);
		firstline.add(eraserButton);
		secondline.add(raiseButton);
		secondline.add(sinkButton);
		lastline.add(selectionBox);
		lastline.add(resetButton);
		lastline.add(startButton);
		
//		action listeners
		startButton.addActionListener(this);
		resetButton.addActionListener(this);
		wallButton.addActionListener(this);
		rootButton.addActionListener(this);
		destButton.addActionListener(this);
		eraserButton.addActionListener(this);
		raiseButton.addActionListener(this);
		sinkButton.addActionListener(this);
	}
	
	public void setButtonListener(ButtonListener listener) {
		this.buttonListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		enable all buttons
		resetButton.setEnabled(true);
		wallButton.setEnabled(true);
		rootButton.setEnabled(true);
		destButton.setEnabled(true);
		raiseButton.setEnabled(true);
		sinkButton.setEnabled(true);
		eraserButton.setEnabled(true);
		
//		action listeners for jcomponents
//		run methods in grid
//		disable buttons
		if(e.getSource() == resetButton) {
			buttonListener.reset();
			startButton.setEnabled(true);
		}else if(e.getSource() == rootButton) {
			buttonListener.setRoot();
			rootButton.setEnabled(false);
		}else if(e.getSource() == destButton) {
			buttonListener.setDestination();
			destButton.setEnabled(false);
		}else if(e.getSource() == wallButton) {
			buttonListener.setWall();
			wallButton.setEnabled(false);
		}else if(e.getSource() == startButton) {
			System.out.println(selectionBox.getSelectedItem());
			buttonListener.start((String) selectionBox.getSelectedItem());
			startButton.setEnabled(false);
		}else if(e.getSource() == raiseButton) {
			buttonListener.raiseHeight();
			raiseButton.setEnabled(false);
		}else if(e.getSource() == sinkButton) {
			buttonListener.lowerHeight();
			sinkButton.setEnabled(false);
		}else if(e.getSource() == eraserButton) {
			buttonListener.erase();
			eraserButton.setEnabled(false);
		}
	}
}
