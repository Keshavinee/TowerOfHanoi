package states;

import sources.*;
import handlers.*;
import display.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.awt.Rectangle;


public class MenuState extends State {
	public BufferedImage menu1, menu2;
	protected Rectangle bound;

	public MenuState() {
		bound = new Rectangle(700,500,300,200);
		menu1 = Image.load("/images/menu1.png");
		menu2 = Image.load("/images/menu2.jpeg");
	}

	public void update(MouseManager mouseManager) {
		int x,y;
		if (mouseManager.prev != null) {
			x = (int) mouseManager.prev.getX();
			y = (int) mouseManager.prev.getY();

			if (bound.contains(x, y)) {
				State.setState(new GameState());
			}
		}
		
	}

	public void draw(Graphics g) {
		g.drawImage(menu1, 0, 0, 1000, 700, null);
		g.drawImage(menu2, 700, 500, 300, 200, null);
	}
}