
public class StateMoveForward extends State {
	public int ForewardSpeed = 180;
	
	public StateMoveForward(Controller c) {
		super(c);
	}

	@Override
	void enter() {
		System.out.println("StateMoveForeward");
		this.controller.setMotorSpeed(this.ForewardSpeed);
		this.controller.moveStraight();
	}

	@Override
	void leave() {
		this.controller.resetTacho();
	}
	
	public void handleBrighter() {
		// Status wechseln --> Neuausrichten
		if (this.controller.motorLeft.getTachoCount() > this.controller.motorRight.getTachoCount()) {
			this.controller.setState(this.controller.RotateRightState);
		} else {
			this.controller.setState(this.controller.RotateLeftState);
		}
	}

	public void handleDarker() {
		// weiterhin geradeaus fahren
	}

	public void handleConstant() {
		// weiterhin geradeaus fahren
	}

}
