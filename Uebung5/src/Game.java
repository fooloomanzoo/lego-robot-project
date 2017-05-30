import java.util.ArrayList;

import lejos.util.TextMenu;

public class Game {

	public Display display;
	public Connection bluetooth;
	public String mode;
	
	public Game() {
		display = new Display();
		bluetooth = new Connection(this);
		display.redraw();
		bluetooth.connect();
	}
	
	public void start(String mode) {
		this.mode = mode;
	}
	
	public void setField(int pos) {
		if (pos == 0) {
			end();
		}
		this.display.setFeld(pos, this.mode.equals("master") ? 'x' : (this.mode.equals("slave") ? 'o' : ' '));
		char winner = this.checkWinState();
		switch (winner) {
		case 'x':
			if (this.mode.equals("master")) {
				System.out.println("You won");
			} else {
				System.out.println("You lost");
			}
			end();
			break;
		case 'o':
			if (this.mode.equals("slave")) {
				System.out.println("You won");
			} else {
				System.out.println("You lost");
			}
			end();
			break;
		default:
			System.out.println("Next round");
			break;
		}
		
	}
	
	private void end() {
		// end game
		
	}
	
	private char checkWinState() {
		char c;
		char tmp = ' ';
		int i, j;
		
		// vertical
		for (i = 0; i < 3; i++) {
			c = display.getFeld(i+1);
			if (c != ' ') {
				for (j = 1; j < 3; j++) {
					tmp = display.getFeld(3*j + i+1);
					if (tmp != c)
						break;
				}
				if (j == 3 && tmp == c) {
					return c;
				}
			}
		}
		
		// horizontal
		for (i = 0; i < 3; i++) {
			c = display.getFeld(i+1);
			if (c != ' ') {
				for (j = 1; j < 3; j++) {
					tmp = display.getFeld(3*i + j+1);
					if (tmp != c)
						break;
				}
				if (j == 3 && tmp == c) {
					return c;
				}
			}
		}
		
		// diagonal
		// left-top to right-bottom
		c = display.getFeld(1);
		if (c != ' ') {
			tmp = display.getFeld(5);
			if (tmp == c) {
				tmp = display.getFeld(9);
				if (tmp == c) {
					return c;
				}
			}
		}

		// right-top to left-bottom
		c = display.getFeld(3);
		if (c != ' ') {
			tmp = display.getFeld(5);
			if (tmp == c) {
				tmp = display.getFeld(7);
				if (tmp == c) {
					return c;
				}
			}
		}
		
		return ' ';
	}

}
