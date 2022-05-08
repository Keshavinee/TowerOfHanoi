package states;

import sources.*;
import handlers.*;
import display.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.awt.Rectangle;

public class EndState3 extends State {
	public BufferedImage end3, end4;
	protected Rectangle bound;

	public EndState3() {
		bound = new Rectangle(350,600,300,100);
		end3 = Image.load("/images/timeup.png");
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
		g.drawImage(end3, 0, 0, 1000, 700, null);
		g.drawImage(end4, 350, 600, 300, 100, null);
	}
}