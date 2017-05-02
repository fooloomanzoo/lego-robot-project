
public class StateAdjust extends State {
	private boolean RotationDirection = true; // true --> im Uhrzeigersinn, false --> gegen den Uhrzeigersinn
	private int RotationSpeed = 360;
	
	public StateAdjust(Controller c) {
		super(c);
	}

	@Override
	void enter() {
		this.controller.setMotorSpeed(this.RotationSpeed);
		if (this.RotationDirection) {
			this.controller.rotateRight();
		} else {
			this.controller.rotateLeft();
		}
	}

	@Override
	void leave() {
	}
	
	public void handleBrighter() {
		// Rotationsrichtung aendern
		this.RotationDirection = !this.RotationDirection;
		this.enter();
	}

	public void handleDarker() {
		// Weiterhin drehen
	}

	public void handleConstant() {
		// Status wechseln --> Geradeaus fahren
		this.controller.setState(this.controller.MoveForewardState);
	}


}
