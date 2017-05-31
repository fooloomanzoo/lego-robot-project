public class Game {

	public Display display;
	public String mode;

	public Game(Display display, String mode) {
		this.display = display;
		this.mode = mode;
		this.display.redraw("");
	}

	public void setField(int pos, boolean isActive) {
		if (pos == 0) {
			end();
		}

		try {
			this.display.setField(pos, isActive ? 'x' : 'o');
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void end() {
		// end game
	}

	public boolean checkEndState() {
		boolean end = false;
		char winner = this.checkWinState();
		switch (winner) {
		case 'x':
			if (this.mode.equals("master")) {
				this.display.redraw("You won");
			} else {
				this.display.redraw("You lost");
			}
			break;
		case 'o':
			if (this.mode.equals("slave")) {
				this.display.redraw("You won");
			} else {
				this.display.redraw("You lost");
			}
			break;
		case 'u':
			this.display.redraw("Tie");
			break;
		default:
			break;
		}
		if (winner != ' ')
			end = true;
		return end;
	}

	private char checkWinState() {
		char c;
		char tmp = ' ';
		char winner = ' ';
		int i;

		try {
			// vertical
			if (winner == ' ') {
				for (i = 1; i <= 3; i++) {
					c = display.getField(i);
					if (c != ' ') {
						tmp = display.getField(3 + i);
						if (tmp == c) {
							tmp = display.getField(6 + i);
							if (tmp == c) {
								winner = c;
							}
						}
					}
				}
			}
			
			// horizontal
			if (winner == ' ') {
				for (i = 1; i <= 3; i++) {
					c = display.getField(1 + 3*(i-1));
					if (c != ' ') {
						tmp = display.getField(2 + 3*(i-1));
						if (tmp == c) {
							tmp = display.getField(3 + 3*(i-1));
							if (tmp == c) {
								winner = c;
							}
						}
					}
				}
			}
				
			// diagonal
			// left-top to right-bottom
			if (winner == ' ') {
				c = display.getField(1);
				if (c != ' ') {
					tmp = display.getField(5);
					if (tmp == c) {
						tmp = display.getField(9);
						if (tmp == c) {
							winner = c;
						}
					}
				}
			}

			// right-top to left-bottom
			if (winner == ' ') {
				c = display.getField(3);
				if (c != ' ') {
					tmp = display.getField(5);
					if (tmp == c) {
						tmp = display.getField(7);
						if (tmp == c) {
							winner = c;
						}
					}
				}
			}
			
			
			// all are set
			if (winner == ' ') {
				tmp = ' ';
				for (i = 1; i <= 9; i++) {
					tmp = display.getField(i);
					if (tmp == ' ') {
						break;
					}
				}
				if (tmp != ' ') {
					winner = 'u';
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return winner;
	}

}
