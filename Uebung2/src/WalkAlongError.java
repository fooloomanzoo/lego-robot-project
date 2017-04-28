import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;

public class WalkAlongError {
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
		
		int speed_straight_a = WalkAlongMove.SPEED_FAST;
		int speed_straight_b = WalkAlongMove.SPEED_FAST;
		
		boolean b2, b3;
		double arc_factor = 0.9;
		
		
		Motor.A.setSpeed(speed_straight_a);
		Motor.B.setSpeed(speed_straight_b);
		
		Motor.A.forward();
		Motor.B.forward();
		
		while(true) {
	
			b2 = t2.isPressed();
			b3 = t3.isPressed();
			
			if (b2 || b3) {
				if (b2) {
					speed_straight_a = (int) (speed_straight_a * arc_factor);
					Motor.A.setSpeed(speed_straight_a);
				}
				else if (b3) {
					speed_straight_b = (int) (speed_straight_b * arc_factor);
					Motor.B.setSpeed(speed_straight_b);
				}
				//ERROR
				while(true) {
					b2 = t2.isPressed();
					b3 = t3.isPressed();
					if (b2) {
						Motor.A.stop();
						Motor.B.stop();
						Motor.B.rotate(-360);
						while(Motor.B.isMoving()) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						};
						Motor.B.stop();
					}
					else if (b3) {
						Motor.A.stop();
						Motor.B.stop();
						Motor.A.rotate(-360);
						while(Motor.A.isMoving()) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						};
						Motor.A.stop();
					} else {
						Motor.A.setSpeed(speed_straight_a);
						Motor.B.setSpeed(speed_straight_b);
						Motor.A.forward();
						Motor.B.forward();
					}
				}
			}
			
		}
	}
}