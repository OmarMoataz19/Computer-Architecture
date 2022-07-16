package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	JPanel cp;
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
	}

	public MainFrame() {
		cp = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TextEditor tx = new TextEditor(cp);
		this.setContentPane(cp);
		setBounds(500, 100, 500, 500);
		this.setVisible(true);
	}

}
