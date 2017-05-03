
public class StateFirst extends State {

	public int ForewardSpeed = 360;
	
	public StateFirst(Controller c) {
		super(c);
	}

	@Override
	void enter() {
		System.out.println("StateFirst");
		this.controller.resetTacho();
		this.controller.setMotorSpeed(this.ForewardSpeed);
		this.controller.moveStraight();
	}

	@Override
	void leave() {
		this.controller.stop();
	}
	
	public void handleBrighter() {
	}

	public void handleDarker() {
		this.controller.setState(this.controller.RotateRightState);
	}

	public void handleConstant() {
	}

}
