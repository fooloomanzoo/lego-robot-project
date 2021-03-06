import java.util.ArrayList;
import javax.microedition.lcdui.Graphics;
import lejos.nxt.LCD;

public class Display {

	private char spielfeld[];
	public final int HEIGHT = LCD.SCREEN_HEIGHT;
	public final int WIDTH = LCD.SCREEN_WIDTH;
	public Graphics g = new Graphics();
	public String[] sideMenuItems = {""};
	public CustomTextMenu sideMenu = new CustomTextMenu(sideMenuItems, 1, (int) (WIDTH/2.0/LCD.FONT_WIDTH), "Select:");
	
	public Display() {
		spielfeld = new char[9];
		for (int i = 0; i < spielfeld.length; i++) {
			spielfeld[i] = ' ';
		}
	}
	
	public void setField (int position, char c) {
		if (position > 0 && position <= 9) {
			if (spielfeld[position-1] == ' ') {
				spielfeld[position-1] = c;
				redraw("");
			} else {
				System.out.println("Game field already set at: " + position);
			}
		} else {
			System.out.println("Game field out of bound: " + position);
		}
	}
	
	public char getField (int position) {
		if (position > 0 && position <= 9) {
			return spielfeld[position-1];
		} else {
			System.out.println("Game field out of bound: " + position);
		}
		return ' ';
	}
	
	public void redraw(String message) {
		// clear display
		LCD.clear();
		
		// seperator line
		g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
		
		// game grid
		int cellWidth = WIDTH/10;
		int cellHeight = HEIGHT/5;
		g.drawLine(cellWidth*2, cellHeight, cellWidth*2, cellHeight*4);
		g.drawLine(cellWidth*3, cellHeight, cellWidth*3, cellHeight*4);
		g.drawLine(cellWidth, cellHeight*2, cellWidth*4, cellHeight*2);
		g.drawLine(cellWidth, cellHeight*3, cellWidth*4, cellHeight*3);
		
		// fields
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				g.drawChar(spielfeld[i+j*3], (int) (cellWidth*(i+1.5) - LCD.FONT_WIDTH/2), (int) (cellHeight*(j+1.5) - LCD.FONT_HEIGHT/2), 0);
			}
		}
		
		// message
		if (!message.equals("")) {
			g.drawString(message, (int) (WIDTH*3.0/4.0 - message.length()*LCD.FONT_WIDTH/2), (int) (HEIGHT/2.0 - LCD.FONT_HEIGHT/2), 0);
		}
	}

	public int read() {
		ArrayList<String> items = new ArrayList<String>();
		items.add("0");
		for (int i = 0; i < spielfeld.length; i++) {
			if (spielfeld[i] == ' ')
				items.add("" + (i+1));
		}
		sideMenuItems = new String[items.size()];
		for (int i = 0; i < sideMenuItems.length; i++) {
			sideMenuItems[i] = items.get(i);
		}
		sideMenu.setItems(sideMenuItems);
		int modeSel = sideMenu.select();
		return Integer.parseInt(sideMenuItems[modeSel]);
	}

}
