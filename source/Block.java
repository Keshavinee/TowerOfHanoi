package sources;

import java.awt.image.BufferedImage;

public class Block {
	public BufferedImage i;
	public int b;
	public int x, y;
	public int w, h;

	public Block(BufferedImage i, int b, int x, int y, int w, int h) {
		this.i = i;
		this.b = b;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
}