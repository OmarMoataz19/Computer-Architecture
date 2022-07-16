package view;

import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import module.*;
import java.awt.*;

public class TextEditor extends JPanel implements ActionListener {
	JTextArea t;

	// Controller c;
	boolean saved = false;
	BufferedWriter writer;
	String path = "";
	FileWriter write;
	File file;
	MainFrame f;

	// Controller c;
	JPanel p;

	public TextEditor(JPanel p) {
		this.f = f;
		this.p = p;
		// this.c = c;
		// this.panel = panel;
		p.setLayout(null);
		// setOpaque(true);
		// setBackground(Color.RED.darker().darker());
		t = new JTextArea();
		JScrollPane scroll = new JScrollPane(t);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(0, 36, 475, 400);
		JMenuBar mb = new JMenuBar();
		mb.setBounds(0, 0, 475, 38);
		JMenuItem mi1 = new JMenuItem("Clear");
		JMenuItem mi2 = new JMenuItem("Open");
		JMenuItem mi3 = new JMenuItem("Save");
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		JMenuItem rb = new JMenuItem("Run");
		rb.addActionListener(this);
		mb.add(mi1);
		mb.add(mi2);
		mb.add(mi3);
		mb.add(rb);
		this.p.add(mb);
		this.p.add(scroll);
		this.p.setVisible(true);
		this.p.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("Save")) {
			if (!saved) {
				JFileChooser j = new JFileChooser("f:");
				int r = j.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					file = new File(j.getSelectedFile().getAbsolutePath());
					try {
						write = new FileWriter(file, false);
						writer = new BufferedWriter(write);
						saved = true;
						path = j.getSelectedFile().getAbsolutePath();
						writer.write(t.getText());
						JOptionPane.showMessageDialog(this, "File saved succesfully.");
						writer.flush();
						writer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				} else
					JOptionPane.showMessageDialog(this, "File not saved.");
			} else {
				try {
					write = new FileWriter(file, false);
					writer = new BufferedWriter(write);
					writer.write(t.getText());
					JOptionPane.showMessageDialog(this, "File saved succesfully.");
					writer.flush();
					writer.close();

				} catch (Exception evt) {
					JOptionPane.showMessageDialog(this, evt.getMessage());
				}
			}

		} else if (s.equals("Open")) {
			JFileChooser j = new JFileChooser("f:");
			int r = j.showOpenDialog(null);
			if (r == JFileChooser.APPROVE_OPTION) {
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					String s1 = "", sl = "";
					FileReader fr = new FileReader(fi);
					BufferedReader br = new BufferedReader(fr);
					sl = br.readLine();
					while ((s1 = br.readLine()) != null) {
						sl = sl + "\n" + s1;
					}
					t.setText(sl);
				} catch (Exception evt) {
					JOptionPane.showMessageDialog(this, evt.getMessage());
				}
			} else
				JOptionPane.showMessageDialog(this, "File not opened.");
		} else if (s.equals("Clear")) {
			t.setText("");
		} else if (s.equals("Run")) {
			if (!saved) {
				JOptionPane.showMessageDialog(this, "You have to save the file first.");
			} else {
				// f.remove(this);
				Main m = new Main(path);
				m.run();
				this.p.removeAll();
				this.p.repaint();
				this.p.revalidate();

				RunPage rp = new RunPage(m, this.p);
			}
		}
	}
}
