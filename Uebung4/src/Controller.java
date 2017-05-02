import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class Controller {
	public State CurrentState = null;
	public State MoveForewardState = null;
	public State AdjustState = null;
	public State ExitState = null;
	public LightSensorExtended sensor = null;
	
	private static int deltaValue = 10; // Toleranz der Lichtmessung
	
	public Controller() {
		addStates();
		addListener();
		setState(MoveForewardState);
	}

	private void addStates() {
		MoveForewardState = new StateMoveForward(this);
		AdjustState = new StateAdjust(this);
		ExitState = new StateExit(this);
	}

	private void addListener() {
		sensor = new LightSensorExtended(SensorPort.S4);
		sensor.addSensorPortListener(new SensorPortListener() {

			@Override
			public void stateChanged(SensorPort aSource, int oldValue, int newValue) {
				System.out.println("new: " + newValue + " old: " + oldValue);
//				if (newValue - oldValue >= 0) {
//					
//				} else {
//
//				}
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
	}
}
