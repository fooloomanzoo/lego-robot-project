
public class StateAdjust extends State {
	private boolean RotationDirection = true; // true --> im Uhrzeigersinn, false --> gegen den Uhrzeigersinn
	private int RotationSpeed = 90;
	
	public StateAdjust(Controller c) {
		super(c);
	}

	@Override
	void enter() {
		this.RotationDirection = !this.RotationDirection;
		this.controller.setMotorSpeed(this.RotationSpeed);
		if (this.RotationDirection) {
			this.controller.rotateRight();
		} else {
			this.controller.rotateLeft();
		}
	}

	@Override
	void leave() {
		this.controller.stop();
	}
	
	public void handleBrighter() {
		this.enter();
	}

	public void handleDarker() {
		// Status wechseln --> Geradeaus fahren
		this.controller.setState(this.controller.MoveForewardState);
	}

	public void handleConstant() {
		// Weiterhin drehen
	}


}
