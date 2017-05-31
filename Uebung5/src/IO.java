import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.util.TextMenu;

public class IO {
	
	public boolean isActive = false;
	public Game game;
	public Connection bluetooth;
	
	public IO(String mode) {
		listen();
		game = new Game();
		bluetooth = new Connection(game);
		bluetooth.connect();
		// game.start(mode);
	}
	
	private void listen() {
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				System.exit(0);
			}

			public void buttonReleased(Button b) {
				// Nothing here
			}
		});

	}
	public static void main(String[] args) {
		IO io = null;
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
	}

}
