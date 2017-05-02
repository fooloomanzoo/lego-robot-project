import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class Linienfolger {

	public static void main(String[] args) {
			
		System.out.println("Linienfolger");
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();

		Controller c = new Controller(SensorPort.S4, Motor.A, Motor.B);
		
		// Wenn Escape gedrueckt wird, endet das Programm
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				c.exitProgram();
				System.exit(0);
			}

			public void buttonReleased(Button b) {
				// Nothing here
			}
		});
		
		while(true) {
			
		}

	}

}
