package display;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Window {
	public JFrame frame;
	public Canvas canvas;
	public Dimension dim;

	public void display() {
		frame = new JFrame("Tower of Hanoi");

		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		canvas = new Canvas();
		dim = new Dimension(1000, 700);

		canvas.setPreferredSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setMinimumSize(dim);

		frame.add(canvas);
		frame.pack();
	}
}