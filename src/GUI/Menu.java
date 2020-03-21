package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener{
	
	private JButton startButton;
	private JButton resetButton;
	private JButton wallButton;
	private JButton rootButton;
	private JButton destButton;
	private ButtonListener buttonListener;
	
	public Menu() {
		startButton = new JButton("Start");
		resetButton = new JButton("Reset");
		wallButton = new JButton("Wall");
		rootButton = new JButton("Root");
		destButton = new JButton("Destination");
		
		add(startButton);
		add(resetButton);
		add(wallButton);
		add(rootButton);
		add(destButton);
		
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
		// TODO Auto-generated method stub
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
