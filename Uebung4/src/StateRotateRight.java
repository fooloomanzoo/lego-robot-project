
public class StateRotateRight extends State {
	private int RotationSpeed = 90;
	
	public StateRotateRight(Controller c) {
		super(c);
	}

	@Override
	void enter() {
		this.controller.stop();
		this.controller.setMotorSpeed(this.RotationSpeed);
		System.out.println("StateRotateRight");
//		this.controller.resetTacho();
		this.controller.rotateRight();
	}

	@Override
	void leave() {
		this.controller.stop();
	}
	
	public void handleBrighter() {
		this.controller.setState(this.controller.RotateLeftState);
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
