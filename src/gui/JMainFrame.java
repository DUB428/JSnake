package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class JMainFrame extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	Snake2 snake2;
	
	public JMainFrame(String title, Snake2 snake2) {
		this.snake2 = snake2;
		setTitle(title);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		//System.out.println("windowActivated");

	}

	@Override
	public void windowClosed(WindowEvent e) {
		//System.out.println("windowClosed");

	}

	@Override
	public void windowClosing(WindowEvent e) {
		snake2.isOpened = false;

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		//System.out.println("windowDeactivated");

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		//System.out.println("windowDeiconified");

	}

	@Override
	public void windowIconified(WindowEvent e) {
		//System.out.println("windowIconified");

	}

	@Override
	public void windowOpened(WindowEvent e) {
		//System.out.println("windowOpened");

	}

}
