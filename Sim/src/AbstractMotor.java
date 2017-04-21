
/**
 * Class that abstracts Motor for NXT and RobotSim
 */
public interface AbstractMotor {

	public void forward();

	public void backward();

	public void rotate(int angle, boolean immediateReturn);
	
	public void rotateTo(int angle);
	
	public void rotateTo(int angle, boolean immediateReturn);
	
	public void stop();

	public void setSpeed(int speed);

	public int getSpeed();

	public boolean isMoving();
	
	public void delay(int delay);
	
}
