enum MotorState {
	FORWARD, BACKWARD, STOPPED
};

public abstract class MoveGeneric<MotorType extends AbstractMotor> {
	
	/**************************** Eigenschaften **********************************/
	public MotorState state = MotorState.STOPPED;
	
	public MotorType motorLeft;
	public MotorType motorRight;
	
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
	public MoveGeneric() {
	}
	
	public MoveGeneric(MotorType motorLeft, MotorType motorRight) {
		this.setMotorLeft(motorLeft);
		this.setMotorRight(motorRight);
	}
	
	public MoveGeneric(MotorType motorLeft, MotorType motorRight, double calDegMotorLeft, double calDegMotorRight, double calSpeedMotorLeft, double calSpeedMotorRight) {
		this.setMotorLeft(motorLeft);
		this.setMotorRight(motorRight);
		this.setCalibration(calDegMotorLeft, calDegMotorRight, calSpeedMotorLeft, calSpeedMotorRight);
	}
	
/******************************** Setter *************************************/
	public void setMotorLeft(MotorType motorLeft) {
		this.motorLeft = motorLeft;
	}

	public void setMotorRight(MotorType motorRight) {
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



