
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class WalkAlong {
	public static void main(String[] args) {

		System.out.println("Kroetenwanderung");
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
		
		int stepDeg = 5;
		int stepDegCorrection = 90-stepDeg*(3);
		
		int speed_straight_a = (int) (WalkAlongMove.SPEED_FAST * WalkAlongMove.MOTOR_A_CAL_SPEED);
		int speed_straight_b = (int) (WalkAlongMove.SPEED_FAST * WalkAlongMove.MOTOR_B_CAL_SPEED);
		int speed_arc = WalkAlongMove.SPEED_SLOW;
		
		boolean b2, b3;
		
		int lastDir = 0;
		int timeContactDiff = 2500;
		long timeLastContact = 0;
		int lastContactCount = 0;
		int contactCount = 1;
		
		int distLastContact = (WalkAlongMove.WHEEL_CIRC * WalkAlongMove.SPEED_FAST * timeContactDiff) / (180 * 1000);
		int partsOfReturn = 8;
		
		while(true) {
			Motor.A.setSpeed(speed_straight_a);
			Motor.B.setSpeed(speed_straight_b);
			
			Motor.A.forward();
			Motor.B.forward();
			
			b2 = t2.isPressed();
			b3 = t3.isPressed();
			
			// wenn Kontakt besteht: rotieren
			
			if (b2 && !b3) {
				System.out.println("rechts");
				WalkAlongMove.rotate((int) ((-1)*stepDeg*(1 + 2.0/contactCount)), speed_arc);
				lastDir = -1;
			} 
			else if (!b2 && b3) {
				System.out.println("links");
				WalkAlongMove.rotate((int) (stepDeg*(1 + 2.0/contactCount)), speed_arc);
				lastDir = 1;
			} 
			else if (b2 && b3) {
				System.out.println("beide");
				WalkAlongMove.straight((-distLastContact) / partsOfReturn, WalkAlongMove.SPEED_FAST);
				WalkAlongMove.rotate((lastDir)*3*stepDeg, speed_arc);
			}
			if (b2 || b3) {
				timeLastContact = System.currentTimeMillis();
				lastContactCount = 0;
				contactCount++;
			}
			
			// Wenn der Kontakt verloren geht, wird in der letzten Drehrichtung entgegen gedreht und gefahren
			if (lastDir != 0 && (System.currentTimeMillis() - timeLastContact > timeContactDiff + 100* Math.pow(2, lastContactCount))) {
				
				WalkAlongMove.straight((-distLastContact) / partsOfReturn, WalkAlongMove.SPEED_FAST);
				WalkAlongMove.rotate((-lastDir)*stepDegCorrection, speed_arc);
				timeLastContact = System.currentTimeMillis();
				lastContactCount++;
				contactCount = 1;
			}
		}	

	}
}
