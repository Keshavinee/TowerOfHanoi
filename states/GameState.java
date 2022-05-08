package states;

import sources.*;
import handlers.*;
import display.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import java.util.Stack;

public class GameState extends State {
	boolean pause = false;
	int moves = 7;
	double time = 5.00;

	String str = "The objective of this puzzle is to move entire stack of disks from Start rod to end rod, obeying the following rules:";
	String r1 = "1)Only one disk can be moved at a time.";
	String r2 = "2)Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack.";
	String r3 = "3)No disk may be placed on top of a smaller disk.";

	Font font = new Font(Font.SERIF,Font.BOLD,15);

	protected Rectangle bound, pile1, pile2, pile3, b1, b2;

	public BufferedImage img, d1, d2, d3,resume,restart;
	public Stack<Block> stk1;
	public Stack<Block> stk2;
	public static Stack<Block> stk3;
	
	int x, y;

	public GameState() {
		img = Image.load("/images/img.jpg");
		d1 = Image.load("/images/dice1.png");
		d2 = Image.load("/images/dice2.png");
		d3 = Image.load("/images/dice3.png");
		resume = Image.load("/images/resume.png");
		restart = Image.load("/images/restart.png");


		stk1 = new Stack<Block>();
		stk2 = new Stack<Block>();
		stk3 = new Stack<Block>();

		stk1.push(new Block(d1, 3, 125, 500, 150, 50));
		stk1.push(new Block(d2, 2, 150, 450, 100, 50));
		stk1.push(new Block(d3, 1, 175, 400, 50, 50));

		pile1 = new Rectangle(75, 300, 250, 250);
		pile2 = new Rectangle(375, 300, 250, 250);
		pile3 = new Rectangle(675, 300, 250, 250);

		b1 = new Rectangle(0, 600, 100, 100);
		b2 = new Rectangle(900, 600, 100, 100);
		
	}

	public void update(MouseManager m) {
		int x1, x2, y1, y2;

		if (time == 0.0) {State.setState(new EndState3());}
		
		else if (moves == 0) {State.setState(new EndState2());}
		
		else if (stk3.size()==3) {State.setState(new EndState1());}
		
		else { if (m.isleftPressed() && m.ismouseDragged()) {
			x1 = (int) m.prev.getX();
			x2 = (int) m.cur.getX();
			y1 = (int) m.prev.getY();
			y2 = (int) m.cur.getY();

			
			if (b1.contains(x1, y1)) {
				State.setState(new MenuState());
			}
			
			else if (b2.contains(x1, y1)) {
				pause = true;
			}

			else { if (!pause) {
				
			if (!stk1.empty()) {
				bound = new Rectangle(stk1.peek().x, stk1.peek().y, stk1.peek().w, stk1.peek().h);

				if (bound.contains(x1, y1)) {
					stk1.peek().x = x2;
					stk1.peek().y = y2;
							
					if (pile2.contains(m.cur)) {
						if (stk2.empty()) {
							stk1.peek().x = 425 + (3 - stk1.peek().b) * 25;
							stk1.peek().y = 500;
							stk2.push(stk1.peek());
							stk1.pop();
						}

						else {
							if (stk2.peek().b > stk1.peek().b) {
								stk1.peek().x = stk2.peek().x + 25;
								stk1.peek().y = stk2.peek().y - 50;
								stk2.push(stk1.peek());
								stk1.pop();
							}

							else {
								stk2.push(stk1.peek());
								stk1.pop();
								if (stk1.empty()) {
									stk2.peek().x = 125 + (3 - stk2.peek().b) * 25;
									stk2.peek().y = 500;
									stk1.push(stk2.peek());
									stk2.pop();
								} else {
									stk2.peek().x = stk1.peek().x + 25;
									stk2.peek().y = stk1.peek().y - 50;
									stk1.push(stk2.peek());
									stk2.pop();
								}
							}
						}
					}

					if (pile3.contains(m.cur)) {
						if (stk3.empty()) {
							stk1.peek().x = 725 + (3 - stk1.peek().b) * 25;
							stk1.peek().y = 500;
							stk3.push(stk1.peek());
							stk1.pop();
						}

						else {
							if (stk3.peek().b > stk1.peek().b) {
								stk1.peek().x = stk3.peek().x + 25;
								stk1.peek().y = stk3.peek().y - 50;
								stk3.push(stk1.peek());
								stk1.pop();
							}

							else {
								stk3.push(stk1.peek());
								stk1.pop();
								if (stk1.empty()) {
									stk3.peek().x = 125 + (3 - stk3.peek().b) * 25;
									stk3.peek().y = 500;
									stk1.push(stk3.peek());
									stk3.pop();
								} else {
									stk3.peek().x = stk1.peek().x + 25;
									stk3.peek().y = stk1.peek().y - 50;
									stk1.push(stk3.peek());
									stk3.pop();
								}
							}
						}
					}
				}
			}
			
			if (!stk2.empty()) {
				bound = new Rectangle(stk2.peek().x, stk2.peek().y, stk2.peek().w, stk2.peek().h);

				if (bound.contains(x1, x2)) {
					stk2.peek().x = x2;
					stk2.peek().y = y2;

					if (pile1.contains(m.cur)) {
						if (stk1.empty()) {
							stk2.peek().x = 125 + (3 - stk2.peek().b) * 25;
							stk2.peek().y = 500;
							stk1.push(stk2.peek());
							stk2.pop();
						}

						else {
							if (stk1.peek().b > stk2.peek().b) {
								stk2.peek().x = stk1.peek().x + 25;
								stk2.peek().y = stk1.peek().y - 50;
								stk1.push(stk2.peek());
								stk2.pop();
							}

							else {
								stk1.push(stk2.peek());
								stk2.pop();
								if (stk2.empty()) {
									stk1.peek().x = 425 + (3 - stk1.peek().b) * 25;
									stk1.peek().y = 500;
									stk2.push(stk1.peek());
									stk1.pop();
								} else {
									stk1.peek().x = stk2.peek().x + 25;
									stk1.peek().y = stk2.peek().y - 50;
									stk2.push(stk1.peek());
									stk1.pop();
								}
							}
						}
					}

					if (pile3.contains(m.cur)) {
						if (stk3.empty()) {
							stk2.peek().x = 725 + (3 - stk2.peek().b) * 25;
							stk2.peek().y = 500;
							stk3.push(stk1.peek());
							stk2.pop();
						}

						else {
							if (stk3.peek().b > stk2.peek().b) {
								stk2.peek().x = stk3.peek().x + 25;
								stk2.peek().y = stk3.peek().y - 50;
								stk3.push(stk1.peek());
								stk2.pop();
							}

							else {
								stk3.push(stk2.peek());
								stk2.pop();
								if (stk2.empty()) {
									stk3.peek().x = 125 + (3 - stk3.peek().b) * 25;
									stk3.peek().y = 500;
									stk2.push(stk3.peek());
									stk3.pop();
								} else {
									stk3.peek().x = stk2.peek().x + 25;
									stk3.peek().y = stk2.peek().y - 50;
									stk2.push(stk3.peek());
									stk3.pop();
								}
							}
						}
					}
				}
			}

			if (!stk3.empty()) {
				bound = new Rectangle(stk3.peek().x, stk3.peek().y, stk3.peek().w, stk3.peek().h);

				if (bound.contains(x1,x2)) {
					stk3.peek().x = x2;
					stk3.peek().y = y2;

					if (pile2.contains(m.cur)) {
						if (stk2.empty()) {
							stk3.peek().x = 425 + (3 - stk3.peek().b) * 25;
							stk3.peek().y = 500;
							stk2.push(stk3.peek());
							stk3.pop();
						}

						else {
							if (stk2.peek().b > stk3.peek().b) {
								stk3.peek().x = stk2.peek().x + 25;
								stk3.peek().y = stk2.peek().y - 50;
								stk2.push(stk3.peek());
								stk3.pop();
							}

							else {
								stk2.push(stk3.peek());
								stk3.pop();
								if (stk3.empty()) {
									stk2.peek().x = 125 + (3 - stk2.peek().b) * 25;
									stk2.peek().y = 500;
									stk3.push(stk2.peek());
									stk2.pop();
								} else {
									stk2.peek().x = stk3.peek().x + 25;
									stk2.peek().y = stk3.peek().y - 50;
									stk3.push(stk2.peek());
									stk2.pop();
								}
							}
						}
					}

					if (pile1.contains(m.cur)) {
						if (stk1.empty()) {
							stk3.peek().x = 725 + (3 - stk3.peek().b) * 25;
							stk3.peek().y = 500;
							stk1.push(stk3.peek());
							stk3.pop();
						}

						else {
							if (stk1.peek().b > stk3.peek().b) {
								stk3.peek().x = stk1.peek().x + 25;
								stk3.peek().y = stk1.peek().y - 50;
								stk1.push(stk3.peek());
								stk3.pop();
							}

							else {
								stk1.push(stk3.peek());
								stk3.pop();
								if (stk3.empty()) {
									stk1.peek().x = 125 + (3 - stk1.peek().b) * 25;
									stk3.peek().y = 500;
									stk3.push(stk1.peek());
									stk1.pop();
								} else {
									stk1.peek().x = stk3.peek().x + 25;
									stk1.peek().y = stk3.peek().y - 50;
									stk3.push(stk1.peek());
									stk1.pop();
								}
							}
						}
					}
				}
			}}  
		}}}
	}

    public void draw(Graphics g) {
		g.drawImage(img, 0, 0, 1000, 700, null);
		g.setColor(Color.darkGray);

		// constant lines
		g.drawLine(200, 300, 200, 550);
		g.drawLine(500, 300, 500, 550);
		g.drawLine(800, 300, 800, 550);
		g.drawLine(75, 550, 325, 550);
		g.drawLine(375, 550, 625, 550);
		g.drawLine(675, 550, 925, 550);

		// rules of the game
		g.setFont(font);
		g.drawString(str,60 ,40);
		g.drawString(r1,90 ,70);
		g.drawString(r2,90 ,100);
		g.drawString(r3,90 ,130);
		g.drawString("Remaining Time: "+time, 60, 200);
		g.drawString("Number of Moves Allowed: "+moves, 740, 200);

		// loading icons
		g.drawImage(restart, 0, 600, 100, 100, null);
		g.drawImage(resume, 900, 600, 100, 100, null);

		// position of block changes
		for (int i = 0; i < stk1.size(); i++) {
			Block t = stk1.get(i);
			g.drawImage(t.i, t.x, t.y, t.w, t.h, null);
		}

		for (int i = 0; i < stk2.size(); i++) {
			Block t = stk2.get(i);
			g.drawImage(t.i, t.x, t.y, t.w, t.h, null);
		}

		for (int i = 0; i < stk3.size(); i++) {
			Block t = stk3.get(i);
			g.drawImage(t.i, t.x, t.y, t.w, t.h, null);
		}

	}

	
}