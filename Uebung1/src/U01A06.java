import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;

public class U01A06 {
	public static void main(String[] args) {
		/*******************************************************/
		System.out.println("Haus vom Nikolaus");
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();
	
		int speed = Move.SPEED_MEDIUM;
		// Schritt 1
		Move.moveMilimetersBlocking(500, speed);
		// Schritt 2
		Move.rotateSimultaneDegreeBlocking(135, speed);
		Move.moveMilimetersBlocking( (int) (500 * Math.sqrt(2)), speed);
		// Schritt 3
		Move.rotateSimultaneDegreeBlocking(-135, speed);
		Move.moveMilimetersBlocking(500, speed);
		// Schritt 4
		Move.rotateSimultaneDegreeBlocking(-45, speed);
		Move.moveMilimetersBlocking( (int) (500 / Math.sqrt(2)), speed);
		// Schritt 5
		Move.rotateSimultaneDegreeBlocking(-90, speed);
		Move.moveMilimetersBlocking( (int) (500 / Math.sqrt(2)), speed);
		// Schritt 6
		Move.rotateSimultaneDegreeBlocking(-135, speed);
		Move.moveMilimetersBlocking(500, speed);
		// Schritt 7
		Move.rotateSimultaneDegreeBlocking(135, speed);
		Move.moveMilimetersBlocking( (int) (500 * Math.sqrt(2)), speed);
		// Schritt 8
		Move.rotateSimultaneDegreeBlocking(-135, speed);
		Move.moveMilimetersBlocking(500, speed);
		// Schritt 9
		Move.moveMilimetersBlocking(-500, speed);
		Move.rotateSimultaneDegreeBlocking(-90, speed);
		
		System.exit(0);
	}
}
