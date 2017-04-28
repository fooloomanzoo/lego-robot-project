import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class Move {
	static NXTRegulatedMotor motorLeft = Motor.A;
	static NXTRegulatedMotor motorRight = Motor.B;
	
	/**************************** Eigenschaften **********************************/
	final static int WHEEL_CIRC = 174; // in mm
	final static int AXLE_RADIUS = 110; // in mm

	/****************************** Konstanten ***********************************/
	final static int SLOW = 90; // 0.5 RPS
	final static int MEDIUM = 180; // 1 RPS
	final static int FAST = 270; // 1.5 RPS
	final static int MAX = 360; // 2 RPS
	
	static int SPEED_ROTATE = SLOW;
	static int SPEED_STRAIGHT = FAST;
	static double ARC_FACTOR = 0.96;
	
	static int THREAD_WAIT = 2;
	
	/***************************** Konstruktoren *********************************/


	public static void stop() {
		motorLeft.stop();
		motorRight.stop();
	}

	// Rotation nach links
	public static void rotateLeft() {
		motorLeft.setSpeed(SPEED_ROTATE);
		motorRight.setSpeed(SPEED_ROTATE);
		motorLeft.backward();
		motorRight.forward();
	}
	
	// Rotation nach links
	public static void rotateRight() {
		motorLeft.setSpeed(SPEED_ROTATE);
		motorRight.setSpeed(SPEED_ROTATE);
		motorLeft.forward();
		motorRight.backward();
	}

	// ROTATION mittels beider Motoren mit Drehachse in der Mitte der Achse
	// deg > 0 => Rechtsdrehung
	public static void rotate(int degree) throws InterruptedException {
		rotate(degree, false);
	}
	public static void rotate(int degree, boolean immediateReturn) throws InterruptedException {

		if (degree > 0) {
			motorLeft.setSpeed(SPEED_ROTATE);
			motorRight.setSpeed(SPEED_ROTATE);
			motorLeft.rotate(degree, true);
			motorRight.rotate(degree, true);
		} else {
			motorLeft.setSpeed(SPEED_ROTATE);
			motorRight.setSpeed(SPEED_ROTATE);
			motorLeft.rotate(degree, true);
			motorRight.rotate(degree, true);
		}
		
		if (!immediateReturn) {
			waitWhileMoving();
			stop();
		}
	}
	
	// BEWEGUNG in gerader Richtung
	public static void forward()  {
		motorLeft.setSpeed(SPEED_STRAIGHT);
		motorRight.setSpeed(SPEED_STRAIGHT);
		motorLeft.forward();
		motorRight.forward();
	}
	
	public static void backward() {
		motorLeft.setSpeed(SPEED_STRAIGHT);
		motorRight.setSpeed(SPEED_STRAIGHT);
		motorLeft.forward();
		motorRight.forward();
	}
	
	// BEWEGUNG in gerader Richtung in vorgebener Entfernung
	public static void straight()  {
		forward();
	}
	public static void straight(boolean direction) {
		if (direction) {
			forward();
		} else {
			backward();
		}
	}
	public static void straight(int dist) throws InterruptedException {
		straight(dist, false);
	}
	public static void straight(int dist, boolean immediateReturn) throws InterruptedException {
		int deg = (dist * 360) / WHEEL_CIRC;

		motorLeft.setSpeed(SPEED_STRAIGHT);
		motorRight.setSpeed(SPEED_STRAIGHT);
		motorLeft.rotate(deg, true);
		motorRight.rotate(deg, true);

		if (!immediateReturn) {
			waitWhileMoving();
			stop();
		}
	}

	// BEWEGUNG in bebogener Laufrichtung
	public static void arcLeft() {
		arcForward(ARC_FACTOR, 1);
	}
	public static void arcRight() {
		arcForward(1, ARC_FACTOR);
	}
	public static void arcForward(double factorLeft, double factorRight) {
		motorLeft.setSpeed((int) (SPEED_STRAIGHT * factorLeft));
		motorRight.setSpeed((int) (SPEED_STRAIGHT * factorRight));
		motorLeft.forward();
		motorRight.forward();
	}
	public static void arcBackward(double factorLeft, double factorRight) {
		motorLeft.setSpeed((int) (SPEED_STRAIGHT * factorLeft));
		motorRight.setSpeed((int) (SPEED_STRAIGHT * factorRight));
		motorLeft.backward();
		motorRight.backward();
	}
	
	public static void waitWhileMoving() throws InterruptedException {
		while (motorLeft.isMoving() || motorRight.isMoving()) {
			Thread.sleep(THREAD_WAIT);
		}
	}
}
