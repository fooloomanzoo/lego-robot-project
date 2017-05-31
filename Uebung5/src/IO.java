import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.util.TextMenu;

public class IO {

	public String mode;
	public Game game;
	public Display display;
	public Connection bluetooth;
	
	public IO(String mode) {
		this.mode = mode;
		bluetooth = new Connection(mode);
		bluetooth.connect();
	}
	
	public void start() {
		display = new Display();
		game = new Game(display, mode);
		listen();
	}
	
	private void listen() {
		int pos;
		boolean endState = false, isActive = false;
		if (mode.equals("master")) {
			isActive = true;
		}
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				System.exit(0);
			}

			public void buttonReleased(Button b) {
				// Nothing here
			}
		});
		while (endState == false) {
			if (isActive) {
				pos = display.read();
				if (pos == 0) {
					display.redraw("You lost");
					break;
				}
				bluetooth.send(pos); 
			} else {
				pos = bluetooth.receive();
				if (pos == 0) {
					display.redraw("You won");
					break;
				}
			}
			game.setField(pos, isActive);
			endState = game.checkEndState();
			isActive = !isActive;
		}
		Button.waitForAnyPress();
		end();
	}

	private void end() {
		LCD.clear();
		bluetooth.disconnect();
		Button.waitForAnyPress();
	}

	public static void main(String[] args) {
		IO io = null;
		System.out.println("Tic Tac Toe");
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();
		String[] items = {"master", "slave"};
		TextMenu menu = new TextMenu(items, 1, "Select a bluetooth mode:");
		
	    int modeSel = menu.select();
	    switch (modeSel) {
			case 0:
				io = new IO("master");
				break;
	
			default:
				io = new IO("slave");
				break;
		}
	    io.start();
	}

}
