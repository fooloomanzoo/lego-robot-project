import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;

public class WalkAlongShort {
	public static void main(String[] args) {

		System.out.println("Kroetenwanderung Short");
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();
		
		TouchSensor t2 = new TouchSensor(SensorPort.S2); // rechts
		TouchSensor t3 = new TouchSensor(SensorPort.S3); // links
		
		// Wenn Escape gedrueckt wird, endet das Programm
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			   public void buttonPressed(Button b) {
			      System.exit(0);
			   }

			   public void buttonReleased(Button b) {
			      // Nothing here
			   }
			});
		
		int speed_straight = WalkAlongMove.SPEED_FAST;
		
		boolean b2, b3;
		double arc_factor = 0.9;
		
		
		Motor.A.setSpeed(speed_straight);
		Motor.B.setSpeed(speed_straight);
		
		Motor.A.forward();
		Motor.B.forward();
		
		while(true) {
	
			b2 = t2.isPressed();
			b3 = t3.isPressed();
			
			if (b2 || b3) {
				speed_straight = WalkAlongMove.SPEED_MEDIUM;
				if (b2) {
					Motor.A.setSpeed((int) (speed_straight * arc_factor));
					Motor.B.setSpeed((int) (speed_straight ));
				}
				else if (b3) {
					Motor.A.setSpeed((int) (speed_straight ));
					Motor.B.setSpeed((int) (speed_straight * arc_factor));
				}
				//ERROR
				while(true) {
					Motor.A.forward();
					Motor.B.forward();
					b2 = t2.isPressed();
					b3 = t3.isPressed();
					if (b2) {
						Motor.A.stop();
						Motor.B.stop();
						Motor.B.rotate(-360);
						while(Motor.B.isMoving()) {};
					}
					else if (b3) {
						Motor.A.stop();
						Motor.B.stop();
						Motor.A.rotate(-360);
						while(Motor.A.isMoving()) {};
					}
				}
			}
			
		}
	}
}
