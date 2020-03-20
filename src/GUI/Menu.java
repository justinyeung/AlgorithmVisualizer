package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener{
	
	private JButton resetButton;
	
	public Menu() {
		resetButton = new JButton("Reset");
		add(resetButton);
		resetButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
