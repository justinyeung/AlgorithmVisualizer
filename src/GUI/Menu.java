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
	private JComboBox<String> selectionBox;
	private ButtonListener buttonListener;
	private String[] selections = {
			"Breadth First Search", 
			"Depth First Search"};
	JPanel firstline;
	JPanel secondline;
	JPanel lastline;
	
	public Menu(){
//		init components
		wallButton = new JButton("Wall");
		rootButton = new JButton("Root");
		destButton = new JButton("Destination");
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
		raiseButton.addActionListener(this);
		sinkButton.addActionListener(this);
	}
	
	public void setButtonListener(ButtonListener listener) {
		this.buttonListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		action listeners for jcomponents
		if(e.getSource() == resetButton) {
			buttonListener.reset();
		}else if(e.getSource() == rootButton) {
			buttonListener.setRoot();
		}else if(e.getSource() == destButton) {
			buttonListener.setDestination();
		}else if(e.getSource() == wallButton) {
			buttonListener.setWall();
		}else if(e.getSource() == startButton) {
			System.out.println(selectionBox.getSelectedItem());
			buttonListener.start((String) selectionBox.getSelectedItem());
		}
	}
}
