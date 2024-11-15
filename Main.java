import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame{
	private static final int WIDTH =1024;
	private static final int HEIGHT=500;
	
	public Main () {
		super("RPG GAME");
		setSize(WIDTH, HEIGHT);
		Game play = new Game();
		((Component) play).setFocusable(true);
		setBackground(Color.BLACK);
		getContentPane().add(play);
		
		setVisible(true);
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				play.createFile();
				play.readFile();
			}			

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				play.writeToFile();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
		});

		
	}
	

	public static void main(String[] args) {
		Main run = new Main();
		

	}


}
