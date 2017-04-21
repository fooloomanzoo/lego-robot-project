import ch.aplu.robotsim.Motor;
import ch.aplu.robotsim.MotorPort;

public class MotorSim extends Motor implements AbstractMotor {

	public MotorSim(MotorPort port) {
		super(port);
	}

	public void rotate(int angle, boolean immediateReturn) {
		int speed = this.getSpeed();
		int duration = (1000 * angle) / speed;
		this.forward();
		this.delay(duration);
		this.stop();
	}

	public void rotateTo(int angle) {
		// TODO Auto-generated method stub
		
	}

	public void rotateTo(int angle, boolean immediateReturn) {
		// TODO Auto-generated method stub
		
	}
	
	public void delay(int duration) {
		try {
			Thread.sleep(duration);
			System.out.println("stop");
			this.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
