package states;

import java.awt.Graphics;
import handlers.*;


public abstract class State {
	private static State curState = null;

	public static void setState(State state) {
		curState = state;
	}

	public static State getState() {
		return curState;
	}

	public abstract void update(MouseManager mouseManager);

	public abstract void draw(Graphics g);
}