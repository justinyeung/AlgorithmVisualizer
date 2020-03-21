package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener{
	
	private JButton startButton;
	private JButton resetButton;
	private JButton wallButton;
	private JButton rootButton;
	private JButton destButton;
	private JComboBox<String> selectionBox;
	private ButtonListener buttonListener;
	private String[] selections = {
			"Linear Grid Search", 
			"Depth First Search"};
	
	public Menu(){
//		init components
		startButton = new JButton("Start");
		resetButton = new JButton("Reset");
		wallButton = new JButton("Wall");
		rootButton = new JButton("Root");
		destButton = new JButton("Destination");
		selectionBox = new JComboBox<String>(selections);
		
//		set layout
		setLayout(new FlowLayout());
		
//		add to menu
		add(wallButton);
		add(rootButton);
		add(destButton);
		add(selectionBox);
		add(resetButton);
		add(startButton);
		
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
			System.out.println(selectionBox.getSelectedItem());
			buttonListener.start((String) selectionBox.getSelectedItem());
		}
	}
}
