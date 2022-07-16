// Java Program to create a text editor using java
package view;

import java.awt.Color;

import javax.swing.*;
import module.DataPath;
import module.Main;

@SuppressWarnings("serial")
public class RunPage extends JPanel {
	JTextArea t;

	JPanel p;

	public RunPage(Main m, JPanel p) {
		this.p = p;
		this.p.setLayout(null);
		t = new JTextArea();
		t.setText(m.computer.getDataPath().print);
		t.setEditable(false);
		JScrollPane scroll = new JScrollPane(t);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(0, 36, 475, 400);
		JMenuBar mb = new JMenuBar();
		mb.setBounds(0, 0, 475, 38);
		this.p.add(scroll);
		this.p.setVisible(true);
		this.p.repaint();
	}

}
