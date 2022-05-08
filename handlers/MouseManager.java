package handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.Point;

public class MouseManager implements MouseListener, MouseMotionListener {
	private boolean leftpressed, rightpressed, mouseDragged;
	private int mouseX, mouseY;

	public Point prev, cur;

	// getters & setters
	public boolean isleftPressed() {
		return leftpressed;
	}

	public boolean isrightPressed() {
		return rightpressed;
	}

	public boolean ismouseDragged() {
		return mouseDragged;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	// Mouse events
	public void mouseDragged(MouseEvent e) {
		cur = e.getPoint();
		mouseDragged = true;
	}

	public void mouseMoved(MouseEvent e) {
		cur = e.getPoint();
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mousePressed(MouseEvent e) {
		prev = e.getPoint();

		if (e.getButton() == MouseEvent.BUTTON1) {
			leftpressed = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightpressed = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftpressed = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightpressed = false;
		}
	}

	public MouseManager() {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}