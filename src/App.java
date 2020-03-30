import javax.swing.SwingUtilities;

import GUI.MainFrame;

public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new MainFrame();
			}
			
		});
	}
}
