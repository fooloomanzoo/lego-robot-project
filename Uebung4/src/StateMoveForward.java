
public class StateMoveForward extends State {
	public int ForewardSpeed = 360;
	
	public StateMoveForward(Controller c) {
		super(c);
	}

	@Override
	void enter() {
		this.controller.setMotorSpeed(this.ForewardSpeed);
		this.controller.moveStraight();
	}

	@Override
	void leave() {
	}
	
	public void handleBrighter() {
		// Status wechseln --> Neuausrichten
		this.controller.setState(this.controller.AdjustState);
	}

	public void handleDarker() {
		// weiterhin geradeaus fahren
	}

	public void handleConstant() {
		// weiterhin geradeaus fahren
	}

}
