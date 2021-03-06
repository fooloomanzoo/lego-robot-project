import lejos.nxt.Button;
import lejos.nxt.Motor;

public class U01A04 {

	public static void main(String[] args) {
		/*******************************************************/
		System.out.println("Geradeaus Fahren");
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();

		Move.moveMilimetersBlocking(50, Move.SPEED_MEDIUM); // 5 cm nach vorn
		
		/*******************************************************/
		System.out.println("Drehung um eine Achse");
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();

		Move.rotateDegreeBlocking(90, Move.SPEED_SLOW);   // 90 Grad nach rechts

		System.out.println("Tacho links: " + Motor.A.getTachoCount() + "\nTacho rechts: " + Motor.B.getTachoCount());
		
		/*******************************************************/
		System.out.println("Drehung um Mittelachse");
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();
		
		Move.rotateSimultaneDegreeBlocking(-45, Move.SPEED_SLOW); // 90 Grad nach rechts

		System.out.println("Tacho links: " + Motor.A.getTachoCount() + "\nTacho rechts: " + Motor.B.getTachoCount());

		Button.waitForAnyPress();
		System.exit(0);
	}

}
