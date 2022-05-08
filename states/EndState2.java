package states;

import sources.*;
import handlers.*;
import display.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.awt.Rectangle;

public class EndState2 extends State {
	public BufferedImage end2, end4;
	protected Rectangle bound;

	public EndState2() {
		bound = new Rectangle(350,600,300,100);
		end2 = Image.load("/images/nomoves.jpeg");
		end4 = Image.load("/images/replay.png");
	}

	public void update(MouseManager mouseManager) {
		int x,y;

		if (mouseManager.prev != null) {
			x = (int) mouseManager.prev.getX();
			y = (int) mouseManager.prev.getY();

			if (bound.contains(x, y)) {
				State.setState(new MenuState());
			}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(end2, 0, 0, 1000, 700, null);
		g.drawImage(end4, 350, 600, 300, 100, null);
	}
}