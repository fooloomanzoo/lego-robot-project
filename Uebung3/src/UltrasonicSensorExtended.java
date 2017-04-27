import java.util.ArrayList;

import lejos.nxt.I2CPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.UltrasonicSensor;

public class UltrasonicSensorExtended extends UltrasonicSensor {

	public ArrayList<USListener> RegisteredListerners = new ArrayList<>();
	// public ArrayList<? extends SensorPortListener> RegisteredListerners = new
	// ArrayList<>();
	public UltrasonicDistanceThread listenerThread;

	public UltrasonicSensorExtended(I2CPort port) {
		super(port);
		listenerThread = new UltrasonicDistanceThread(this);
		listenerThread.start();

		// TODO Auto-generated constructor stub
	}

	public void addSensorPortListener(USListener usl) {
		RegisteredListerners.add(usl);
	}

}
