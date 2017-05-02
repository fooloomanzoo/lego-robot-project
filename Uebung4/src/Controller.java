import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class Controller {
	public State CurrentState = null;
	public State MoveForewardState = null;
	public State AdjustState = null;
	public State ExitState = null;
	
	public LightSensorExtended sensor = null;
	public NXTRegulatedMotor motorLeft = null;
	public NXTRegulatedMotor motorRight = null;
	
//	private static int deltaValue = 10; // Toleranz der Lichtmessung
	
	public Controller(SensorPort sp, NXTRegulatedMotor motorLeft, NXTRegulatedMotor motorRight) {
		addMotors(motorLeft, motorRight);
		addStates();
		addListener(sp);
		setState(MoveForewardState);
	}

	private void addMotors(NXTRegulatedMotor ml, NXTRegulatedMotor mr) {
		motorLeft = ml;
		motorRight = mr;
	}

	private void addStates() {
		MoveForewardState = new StateMoveForward(this);
		AdjustState = new StateAdjust(this);
		ExitState = new StateExit(this);
	}

	private void addListener(SensorPort sp) {
		sensor = new LightSensorExtended(sp);
		sensor.addSensorPortListener(new SensorPortListener() {

			@Override
			public void stateChanged(SensorPort aSource, int oldValue, int newValue) {
				System.out.println("new: " + newValue + " old: " + oldValue);
				if (newValue > oldValue) {
					CurrentState.handleBrighter();
				} else if (newValue < oldValue) {
					CurrentState.handleDarker();
				} else {
					CurrentState.handleConstant();
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
	
	// Rotation nach links
	public void rotateRight() {
		motorLeft.forward();
		motorRight.backward();
	}
	
	// Geradeaus Fahren
	public void moveStraight() {
		motorLeft.forward();
		motorRight.forward();
	}
	
	// Motor-Geschwindigkeit setzen
	public void setMotorSpeed(int speed) {
		motorLeft.setSpeed(speed);
		motorRight.setSpeed(speed);
	}
}
