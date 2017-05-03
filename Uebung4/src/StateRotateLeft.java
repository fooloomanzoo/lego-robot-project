
public class StateRotateLeft extends State {
	private int RotationSpeed = 90;
	
	public StateRotateLeft(Controller c) {
		super(c);
	}

	@Override
	void enter() {
		this.controller.stop();
		System.out.println("StateRotateLeft");
		this.controller.setMotorSpeed(this.RotationSpeed);
//		this.controller.resetTacho();
		this.controller.rotateLeft();
	}

	@Override
	void leave() {
		this.controller.stop();
	}
	
	public void handleBrighter() {
		this.controller.setState(this.controller.RotateRightState);
	}

	public void handleDarker() {
		// Status wechseln --> Geradeaus fahren
		this.controller.setState(this.controller.MoveForewardState);
	}

	public void handleConstant() {
		// Weiterhin drehen
//		this.controller.setState(this.controller.MoveForewardState);
	}

}
