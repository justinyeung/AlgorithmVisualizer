package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Menu extends JPanel implements ActionListener{
	
	private JButton startButton;
	private JButton resetButton;
	private JButton wallButton;
	private JButton rootButton;
	private JButton destButton;
	protected JTextArea resultArea;
	private ButtonListener buttonListener;
	
	public Menu(){
//		init components
		startButton = new JButton("Start");
		resetButton = new JButton("Reset");
		wallButton = new JButton("Wall");
		rootButton = new JButton("Root");
		destButton = new JButton("Destination");
//		resultArea = new JTextArea("Welcome to Algorithm Visualizer");
		
//		set layout
		setLayout(new FlowLayout());
		
//		add to menu
		add(wallButton);
		add(rootButton);
		add(destButton);
		add(resetButton);
		add(startButton);
//		add(new JScrollPane(resultArea));
		
//		action listeners
		startButton.addActionListener(this);
		resetButton.addActionListener(this);
		wallButton.addActionListener(this);
		rootButton.addActionListener(this);
		destButton.addActionListener(this);
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
			buttonListener.start();
		}
	}
}
