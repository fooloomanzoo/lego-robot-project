import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;

public class MoveTests {

	final static int WHEEL_CIRC = 174;   // in mm
	final static int AXLE_RADIUS = 110;  // in mm

	final static int SPEED_SLOW = 90;    // 0.5 RPS
	final static int SPEED_MEDIUM = 180; // 1 RPS
	final static int SPEED_FAST = 360;   // 2 RPS

	public static void main(String[] args) {

//		/*******************************************************/
//		System.out.println("Druecke Startbutton!");
//		Button.waitForAnyPress();
//
//		System.out.println("Geradeaus Fahren");
//		moveMilimetersBlocking(50, SPEED_FAST); // 5 cm nach vorn
//		
//		/*******************************************************/
//		System.out.println("Druecke Startbutton!");
//		Button.waitForAnyPress();
//		
//		System.out.println("Drehung um eine Achse");
//		rotateDegreeBlocking(90, SPEED_SLOW);   // 90° nach rechts
//		
//		/*******************************************************/
//		System.out.println("Druecke Startbutton!");
//		Button.waitForAnyPress();
//		
//		System.out.println("Drehung um Mittelachse");
//		rotateSimultaneDegreeBlocking(45, SPEED_SLOW); // 90° nach rechts
		
		/*******************************************************/
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();
		
		System.out.println("Nikolaus");
		int speed = SPEED_MEDIUM;
		// Schritt 1
		moveMilimetersBlocking(500, speed);
		// Schritt 2
		rotateDegreeBlocking(135, speed);
		moveMilimetersBlocking( (int) (500 * Math.sqrt(2)), speed);
		// Schritt 3
		rotateDegreeBlocking(-45, speed);
		moveMilimetersBlocking(500, speed);
		// Schritt 4
		rotateDegreeBlocking(-45, speed);
		moveMilimetersBlocking( (int) (500 / Math.sqrt(2)), speed);
		// Schritt 5
		rotateDegreeBlocking(-90, speed);
		moveMilimetersBlocking( (int) (500 / Math.sqrt(2)), speed);
		// Schritt 6
		rotateDegreeBlocking(-135, speed);
		moveMilimetersBlocking(500, speed);
		// Schritt 7
		rotateDegreeBlocking(135, speed);
		moveMilimetersBlocking( (int) (500 * Math.sqrt(2)), speed);
		// Schritt 8
		rotateDegreeBlocking(-135, speed);
		moveMilimetersBlocking(500, speed);
		// Schritt 9
		moveMilimetersBlocking(-500, speed);
		
		System.exit(0);
	}

	// ROTATION mittels eines Motors mit Drehachse am fixen Rad
	// deg>0 => rechtsdrehung
	public static void rotateDegreeBlocking(int degree, int speed) {
		double distCirc = Math.abs(degree) * AXLE_RADIUS * 2 * Math.PI / 360;
		int deg = (int) (distCirc * 360) / WHEEL_CIRC; // Schneidet ab, statt zu
													   // runden
		int driveTime = (int) (distCirc * 360 * 1000) / (speed * WHEEL_CIRC); // leicht
																				// ungenau

		if (degree > 0) {
			Motor.B.setSpeed(speed);
			Motor.B.rotate(deg, true);
		} else {
			Motor.A.setSpeed(speed);
			Motor.A.rotate(deg, true);
		}

		try {
			Thread.sleep(driveTime); // eventuell laenger laufen lassen, wegen
								     // Zeitabbruch
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Motor.A.stop();
			Motor.B.stop();
			System.out.println("Motor A: " + Motor.A.getTachoCount() + "°\nMotor B: " + Motor.B.getTachoCount() + "°");
		}
	}
	
	// ROTATION mittels beider Motoren mit Drehachse in der Mitte der Räder
	// deg>0 => rechtsdrehung
	public static void rotateSimultaneDegreeBlocking(int degree, int speed) {
		double distCirc = Math.abs(degree) * AXLE_RADIUS * Math.PI / 360;
		int deg = (int) (distCirc * 360) / WHEEL_CIRC; // Schneidet ab, statt zu
													   // runden
		int driveTime = (int) (distCirc * 360 * 1000) / (speed * WHEEL_CIRC); // leicht
																				// ungenau

		if (degree > 0) {
			Motor.A.setSpeed(speed);
			Motor.B.setSpeed(speed);
			Motor.A.rotate(-deg, true);
			Motor.B.rotate(deg, true);
		} else {
			Motor.A.setSpeed(speed);
			Motor.B.setSpeed(speed);
			Motor.A.rotate(deg, true);
			Motor.B.rotate(-deg, true);
		}

		try {
			Thread.sleep(driveTime); // eventuell laenger laufen lassen, wegen
								     // Zeitabbruch
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Motor.A.stop();
			Motor.B.stop();
			System.out.println("Motor A: " + Motor.A.getTachoCount() + "°\nMotor B: " + Motor.B.getTachoCount() + "°");
		}
	}

	// BEWEGUNG in gerader Richtung
	public static void moveMilimetersBlocking(int dist, int speed) {
		int deg = (dist * 360) / WHEEL_CIRC; // Schneidet ab, statt zu runden
		int driveTime = (Math.abs(dist) * 360 * 1000) / (speed * WHEEL_CIRC); // leicht
																	// ungenau

		Motor.A.setSpeed(speed);
		Motor.B.setSpeed(speed);
		Motor.A.rotate(deg, true);
		Motor.B.rotate(deg, true);
		try {
			Thread.sleep(driveTime); // eventuell laenger laufen lassen, wegen
									 // Zeitabbruch
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			Motor.A.stop();
			Motor.B.stop();
			System.out.println("Motor A: " + Motor.A.getTachoCount() + "°\nMotor B: " + Motor.B.getTachoCount() + "°");
		}
	}

}
