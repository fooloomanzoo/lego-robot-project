import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class Move {

	/**************************** Eigenschaften **********************************/
	public NXTRegulatedMotor motorLeft;
	public NXTRegulatedMotor motorRight;

	final static int WHEEL_CIRC = 174; // in mm
	final static int AXLE_RADIUS = 110; // in mm

	/****************************** Konstanten ***********************************/
	final static int SLOW = 90; // 0.5 RPS
	final static int MEDIUM = 180; // 1 RPS
	final static int FAST = 270; // 1.5 RPS
	final static int MAX = 360; // 2 RPS

	/***************************** Konstruktoren *********************************/
	public Move() {
		this.motorLeft = Motor.A;
		this.motorRight = Motor.B;
	}

	public static void rotate(int deg, int speed) throws InterruptedException {
		if (deg > 0) {
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

		while (Motor.A.isMoving() || Motor.B.isMoving()) {
			Thread.sleep(10);
		}
		Motor.A.stop();
		Motor.B.stop();
	}

	// BEWEGUNG in gerader Richtung
	public static void straight(int dist, int speed) throws InterruptedException {
		int deg = (dist * 360) / WHEEL_CIRC; // Schneidet ab, statt zu runden

		Motor.A.setSpeed(speed);
		Motor.B.setSpeed(speed);
		Motor.A.rotate(deg, true);
		Motor.B.rotate(deg, true);

		while (Motor.A.isMoving() || Motor.B.isMoving()) {
			Thread.sleep(10);
		}

		Motor.A.stop();
		Motor.B.stop();
	}

	// BEWEGUNG in gerader Richtung
	public static void straight(int speed) throws InterruptedException {

		Motor.A.setSpeed(speed);
		Motor.B.setSpeed(speed);
		Motor.A.forward();
		Motor.B.forward();

		while (Motor.A.isMoving() || Motor.B.isMoving()) {
			Thread.sleep(10);
		}

		Motor.A.stop();
		Motor.B.stop();
	}

	// BEWEGUNG in bebogener Laufrichtung
	public static void arc(int speedA, int speedB) throws InterruptedException {

		Motor.A.setSpeed(speedA);
		Motor.B.setSpeed(speedB);
		Motor.A.forward();
		Motor.B.forward();

		while (Motor.A.isMoving() || Motor.B.isMoving()) {
			Thread.sleep(10);
		}

		Motor.A.stop();
		Motor.B.stop();
	}

}
