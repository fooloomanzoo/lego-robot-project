
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class WalkAlong {
	public static void main(String[] args) {

		System.out.println("Kroetenwanderung Innen");
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();
		
		TouchSensor t2 = new TouchSensor(SensorPort.S2);
		TouchSensor t3 = new TouchSensor(SensorPort.S3);
		
		// Wenn Escape gedrueckt wird, endet das Programm
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			   public void buttonPressed(Button b) {
			      System.exit(0);
			   }

			   public void buttonReleased(Button b) {
			      // Nothing here
			   }
			});
		
		int step_deg = 20;
		int speed_straight_a = (int) (WalkAlongMove.SPEED_MEDIUM * WalkAlongMove.MOTOR_A_CAL_SPEED);
		int speed_straight_b = (int) (WalkAlongMove.SPEED_MEDIUM * WalkAlongMove.MOTOR_B_CAL_SPEED);
		int speed_arc = WalkAlongMove.SPEED_SLOW;
		
		boolean b2, b3;
		
		int lastDir = 0;
		int timeContactDiff = 5000;
		long timeLastContact = 0;
		
		while(true) {
			Motor.A.setSpeed(speed_straight_a);
			Motor.B.setSpeed(speed_straight_b);
			
			Motor.A.forward();
			Motor.B.forward();
			
			b2 = t2.isPressed();
			b3 = t3.isPressed();
			
			
			// *********** ALTERNATIVE 1 **************/
			// wenn Kontakt besteht: rotieren
			
			if (b2 && !b3) {
				System.out.println("rechts");
				WalkAlongMove.rotate((-1)*step_deg, speed_arc);
				lastDir = -1;
			} 
			else if (!b2 && b3) {
				System.out.println("links");
				WalkAlongMove.rotate(step_deg, speed_arc);
				lastDir = 1;
			} 
			else if (b2 && b3) {
				System.out.println("beide");
				WalkAlongMove.rotate((-1)*step_deg, speed_arc);
				lastDir = -1;
				b2 = t2.isPressed();
				b3 = t3.isPressed();
				if (b2 && b3) {
					WalkAlongMove.rotate(3*step_deg, speed_arc);
					lastDir = 1;
				}
			}
			if (b2 || b3) {
				timeLastContact = System.currentTimeMillis();
			}
			
			// *********** ALTERNATIVE 2 **************/
			// solange noch Kontakt besteht: rotieren
//			while (b2 || b3) {
//				if (b2 && !b3) {
//					System.out.println("rechts");
//					Move.rotate((-1)*step_deg, speed_arc);
//					lastDir = -1;
//				} 
//				else if (!b2 && b3) {
//					System.out.println("links");
//					Move.rotate(step_deg, speed_arc);
//					lastDir = 1;
//				} 
//				else if (b2 && b3) {
//					System.out.println("beide");
//					Move.rotate((-1)*step_deg, speed_arc);
//					lastDir = -1;
//					b2 = t2.isPressed();
//					b3 = t3.isPressed();
//					if (b2 && b3) {
//						Move.rotate(3*step_deg, speed_arc);
//						lastDir = 1;
//					}
//				}
//				b2 = t2.isPressed();
//				b3 = t3.isPressed();
//				timeLastContact = System.currentTimeMillis();
//			}
			
			// Wenn der Kontakt verloren geht, wird in der letzten Drehrichtung entgegen gedreht und gefahren
			if (lastDir != 0 && (System.currentTimeMillis() - timeLastContact < timeContactDiff)) {
				WalkAlongMove.rotate((-1)*lastDir*step_deg, speed_arc);
				WalkAlongMove.straight(WalkAlongMove.WHEEL_CIRC / 2, WalkAlongMove.SPEED_MEDIUM);
			}
		}	

	}
}
