import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class Controller {
	public State CurrentState = null;
	public State MoveForewardState = null;
	// public State AdjustState = null;
	public State ExitState = null;
	public State FirstState = null;
	public State RotateLeftState = null;
	public State RotateRightState = null;

	public LightSensorExtended sensor = null;
	public NXTRegulatedMotor motorLeft = null;
	public NXTRegulatedMotor motorRight = null;

	public Controller(SensorPort sp, NXTRegulatedMotor motorLeft, NXTRegulatedMotor motorRight) {
		addMotors(motorLeft, motorRight);
		addListener(sp);
		addStates();
		setState(FirstState);
	}

	private void addMotors(NXTRegulatedMotor ml, NXTRegulatedMotor mr) {
		motorLeft = ml;
		motorRight = mr;
	}

	private void addStates() {
		MoveForewardState = new StateMoveForward(this);
		// AdjustState = new StateAdjust(this);
		ExitState = new StateExit(this);
		FirstState = new StateFirst(this);
		RotateLeftState = new StateRotateLeft(this);
		RotateRightState = new StateRotateRight(this);
	}

	private void addListener(SensorPort sp) {
		sensor = new LightSensorExtended(sp);
		sensor.addSensorPortListener(new SensorPortListener() {

			@Override
			public void stateChanged(SensorPort aSource, int oldValue, int newValue) {

				// if (Math.abs(newValue - oldValue) < 10) {
				// System.out.println("constant: " + newValue);
				// CurrentState.handleConstant();
				// } else if (newValue > oldValue) {
				// System.out.println("brighter: " + newValue);
				// CurrentState.handleBrighter();
				// } else if (newValue < oldValue) {
				// System.out.println("darker: " + newValue);
				// CurrentState.handleDarker();
				// }

				// if (newValue > 540) {
				//// System.out.println("brighter: " + newValue);
				// CurrentState.handleBrighter();
				// } else if (newValue < 420) {
				//// System.out.println("darker: " + newValue);
				// CurrentState.handleDarker();
				// } else {
				//// System.out.println("constant: " + newValue);
				// CurrentState.handleConstant();
				// }

				if (Math.abs(newValue - oldValue) > 5) {
					if (newValue > oldValue) {
//						System.out.println("brighter: " + newValue);
						CurrentState.handleBrighter();
					} else {
//						System.out.println("darker: " + newValue);
						CurrentState.handleDarker();
					}
				} else {
					CurrentState.handleConstant();
//					 System.out.println("constant: " + newValue);
				}

			}

		});
	}

	public void setState(State s) {
		if (CurrentState != null) {
			CurrentState.leave();
		}
		CurrentState = s;
		CurrentState.enter();
	}

	public void exitProgram() {
		CurrentState.exitProgram();
		sensor.setFloodlight(false);
	}

	// Rotation nach links
	public void rotateLeft() {
		motorLeft.backward();
		motorRight.forward();
	}

	// Rotation nach rechts
	public void rotateRight() {
		motorRight.backward();
		motorLeft.forward();
	}

	// Geradeaus Fahren
	public void moveStraight() {
		motorLeft.forward();
		motorRight.forward();
	}

	// Geradeaus Fahren
	public void stop() {
		motorLeft.stop();
		motorRight.stop();
	}

	// Motor-Geschwindigkeit setzen
	public void setMotorSpeed(int speed) {
		motorLeft.setSpeed(speed);
		motorRight.setSpeed(speed);
	}

	// Motor-Position zuruecksetzen
	public void resetTacho() {
		motorLeft.resetTachoCount();
		motorRight.resetTachoCount();
	}
}
