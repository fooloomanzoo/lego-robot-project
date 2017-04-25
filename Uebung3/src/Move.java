import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public abstract class Move {
	
	/**************************** Eigenschaften **********************************/
	public NXTRegulatedMotor motorLeft;
	public NXTRegulatedMotor motorRight;
	
	public double calDegMotorLeft = 1;
	public double calDegMotorRight = 1;
	public double calSpeedMotorLeft = 1;
	public double calSpeedMotorRight = 1;

/****************************** Konstanten ***********************************/
	final static int SLOW   = 90;  // 0.5 RPS
	final static int MEDIUM = 180; // 1   RPS
	final static int FAST   = 270; // 1.5 RPS
	final static int MAX    = 360; // 2   RPS

/***************************** Konstruktoren *********************************/
	public Move() {
		this.setMotorLeft(Motor.A);
		this.setMotorRight(Motor.A);
	}
	
	public Move(NXTRegulatedMotor motorLeft, NXTRegulatedMotor motorRight) {
		this.setMotorLeft(Motor.A);
		this.setMotorRight(Motor.A);
	}
	
	public Move(NXTRegulatedMotor motorLeft, NXTRegulatedMotor motorRight, double calDegMotorLeft, double calDegMotorRight, double calSpeedMotorLeft, double calSpeedMotorRight) {
		this.setMotorLeft(motorLeft);
		this.setMotorRight(motorRight);
		this.setCalibration(calDegMotorLeft, calDegMotorRight, calSpeedMotorLeft, calSpeedMotorRight);
	}
	
/******************************** Setter *************************************/
	public void setMotorLeft(NXTRegulatedMotor motorLeft) {
		this.motorLeft = motorLeft;
	}

	public void setMotorRight(NXTRegulatedMotor motorRight) {
		this.motorRight = motorRight;
	}

	public double getCalDegMotorLeft() {
		return calDegMotorLeft;
	}

	public void setCalibration(double calDegMotorLeft, double calDegMotorRight, double calSpeedMotorLeft, double calSpeedMotorRight) {
		this.calDegMotorLeft = calDegMotorLeft;
		this.calDegMotorRight = calDegMotorRight;
		this.calSpeedMotorLeft = calSpeedMotorLeft;
		this.calSpeedMotorRight = calSpeedMotorRight;
	}
	
/************************** Bewegungsfunktionen ******************************/
/* Distanzen in mm (int): 
 * 		positiv -> nach vorne      | negativ -> nach hinten 			     */
/* Winkel in Grad (int):    
 * 		positiv -> im Urzeigersinn | negativ -> gegen den Urzeigersinn 		 */
	
/* Geradeaus fahren */
	public void straight(int dist, int speed) {
		this.motorLeft.setSpeed(speed);
		this.motorRight.setSpeed(speed);
		this.motorLeft.forward();
		this.motorRight.forward();
		this.delay(200);
		this.motorLeft.stop();
		this.motorRight.stop();
	}

/* auf Position rotieren */
	public void rotate(int deg, int speed) {
		
	}
	
/* Kreisabschnitt fahren */
	public void arc() {
		
	}
	
	
/* Ellipsenabschnitt fahren */
	public void curve() {
		
	}
	
/* Zeit in ms, in der die Aktion durchgeführt werden soll (abhaengig von super-Klasse */
	public void delay(int duration) {
		try {
			Thread.sleep(duration);
			System.out.println("stop");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}



