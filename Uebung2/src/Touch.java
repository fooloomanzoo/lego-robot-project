import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Touch {
	final static int STEP_DEG = 5;    // 2 Grad

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Kroetenwanderung Innen");
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();
		
		TouchSensor t2 = new TouchSensor(SensorPort.S2);
		TouchSensor t3 = new TouchSensor(SensorPort.S3);
		
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			   public void buttonPressed(Button b) {
			      System.exit(0);
			   }

			   public void buttonReleased(Button b) {
			      // Nothing here
			   }
			});
		
		while(true){
			Motor.A.setSpeed((int) (TouchMove.SPEED_MEDIUM * TouchMove.MOTOR_A_CAL_SPEED));
			Motor.B.setSpeed((int) (TouchMove.SPEED_MEDIUM * TouchMove.MOTOR_B_CAL_SPEED));
			
			Motor.A.forward();
			Motor.B.forward();
			
			if(t2.isPressed()){
				System.out.println("rechts");
				TouchMove.rotateSimultaneDegreeBlocking(-STEP_DEG, TouchMove.SPEED_SLOW);
			}
			if(t3.isPressed()){
				System.out.println("links");
				TouchMove.rotateSimultaneDegreeBlocking(+STEP_DEG, TouchMove.SPEED_SLOW);
			}	
		}	
	}

}