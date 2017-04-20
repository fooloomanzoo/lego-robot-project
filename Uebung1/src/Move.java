import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;

public class Move {

	final static int WHEEL_CIRC = 174;   // in mm
	final static int AXLE_RADIUS = 110;  // in mm

	// TODO: Motorleistung in Abhängigkeit zur Geschwindigkeit und Pos. bestimmen
	// <-- (maxSpeed,...)
	final static int SPEED_SLOW = 90;    // 0.5 RPS
	final static int SPEED_MEDIUM = 180; // 1 RPS
	final static int SPEED_FAST = 360;   // 2 RPS
	
	final static double MOTOR_A_CAL_DEG = 1.005;
	final static double MOTOR_B_CAL_DEG = 1.005;
	final static double MOTOR_A_CAL_SPEED = 1;
	final static double MOTOR_B_CAL_SPEED = 1.005;

	// Motorkalibrierung erfolgt nur nach Berechnung der Fahrtzeit 
	// -> TODO: reale Position miteinbeziehen
	
	// ROTATION mittels eines Motors mit Drehachse am fixen Rad
	// deg > 0 => Rechtsdrehung
	public static void rotateDegreeBlocking(int degree, int speed) {
		double distCirc = Math.abs(degree) * AXLE_RADIUS * 2 * Math.PI / 360;
		int deg = (int) (distCirc * 360) / WHEEL_CIRC; // Schneidet ab, statt zu
													   // runden
		int driveTime = (int) (distCirc * 360 * 1000) / (speed * WHEEL_CIRC); // leicht
																			  // ungenau

		if (degree > 0) {
			Motor.B.setSpeed((int) (speed * MOTOR_B_CAL_SPEED));
			Motor.B.rotate((int) (deg * MOTOR_B_CAL_DEG), true);
		} else {
			Motor.A.setSpeed((int) (speed * MOTOR_A_CAL_SPEED));
			Motor.A.rotate((int) (deg * MOTOR_A_CAL_DEG), true);
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
			System.out.println("A: " + Motor.A.getTachoCount() + " Grad\nB: " + Motor.B.getTachoCount() + " Grad");
		}
	}
	
	// ROTATION mittels beider Motoren mit Drehachse in der Mitte der Achse
	// deg > 0 => Rechtsdrehung
	public static void rotateSimultaneDegreeBlocking(int degree, int speed) {
		double distCirc = Math.abs(degree) * AXLE_RADIUS * Math.PI / 360;
		int deg = (int) (distCirc * 360) / WHEEL_CIRC; // Schneidet ab, statt zu
													   // runden
		int driveTime = (int) (distCirc * 360 * 1000) / (speed * WHEEL_CIRC); // leicht
																				// ungenau

		if (degree > 0) {
			Motor.A.setSpeed((int) (speed * MOTOR_A_CAL_SPEED));
			Motor.B.setSpeed((int) (speed * MOTOR_B_CAL_SPEED));
			Motor.A.rotate((int) (-deg * MOTOR_A_CAL_DEG), true);
			Motor.B.rotate((int) (deg * MOTOR_B_CAL_DEG), true);
		} else {
			Motor.A.setSpeed((int) (speed * MOTOR_A_CAL_SPEED));
			Motor.B.setSpeed((int) (speed * MOTOR_B_CAL_SPEED));
			Motor.A.rotate((int) (deg * MOTOR_A_CAL_DEG), true);
			Motor.B.rotate((int) (-deg * MOTOR_B_CAL_DEG), true);
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
			System.out.println("A: " + Motor.A.getTachoCount() + " Grad\nB: " + Motor.B.getTachoCount() + " Grad");
		}
	}

	// BEWEGUNG in gerader Richtung
	public static void moveMilimetersBlocking(int dist, int speed) {
		int deg = (dist * 360) / WHEEL_CIRC; // Schneidet ab, statt zu runden
		int driveTime = (Math.abs(dist) * 360 * 1000) / (speed * WHEEL_CIRC); // leicht
																	// ungenau

		Motor.A.setSpeed((int) (speed * MOTOR_A_CAL_SPEED));
		Motor.B.setSpeed((int) (speed * MOTOR_B_CAL_SPEED));
		Motor.A.rotate((int) (deg * MOTOR_A_CAL_DEG), true);
		Motor.B.rotate((int) (deg * MOTOR_B_CAL_DEG), true);
		try {
			Thread.sleep(driveTime); // eventuell laenger laufen lassen, wegen
									 // Zeitabbruch
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			Motor.A.stop();
			Motor.B.stop();
			System.out.println("A: " + Motor.A.getTachoCount() + " Grad\nB: " + Motor.B.getTachoCount() + " Grad");
		}
	}

}
