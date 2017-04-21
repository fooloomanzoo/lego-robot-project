import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class Move extends MoveGeneric {
	
	public Move() {
		this.setMotorLeft((AbstractMotor) Motor.A);
		this.setMotorRight((AbstractMotor) Motor.B);
	}
	
}
