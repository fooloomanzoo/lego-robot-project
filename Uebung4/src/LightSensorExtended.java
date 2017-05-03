import lejos.nxt.ADSensorPort;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class LightSensorExtended extends LightSensor {

	protected int iPortId;
	protected SensorPort port;
	protected short iNumListeners = 0;
	protected SensorPortListener[] iListeners = null;
	protected int iPreviousValue;

	public LightSensorExtended(SensorPort port) {
		super(port, true);
		this.port = port;
		this.iPortId = port.getType();
		//verzögerung der Lampe um ersten Messfehler zu eliminieren
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getPortId() {
		return iPortId;
	}

	public SensorPort getPort() {
		return port;
	}

	// Threads fuer Listeners
	protected static class BrightnessThread extends Thread {
		private static LightSensorExtended sensor = null;
		private static int update = 1; // Zeit, zwischen Updates
		static BrightnessThread singleton = null;

		static synchronized BrightnessThread get() {
			if (singleton == null) {
				singleton = new BrightnessThread();
				singleton.setDaemon(true);
				singleton.setPriority(Thread.MAX_PRIORITY);
			}
			return singleton;
		}

		public static void setSensor(LightSensorExtended usEx) {
			BrightnessThread.sensor = usEx;
		}

		public void run() {
			int newValue = 0, oldValue = 0; // 0 --> dunkel?
			while (!isInterrupted()) {
				newValue = BrightnessThread.sensor.readNormalizedValue();

				try {
					for (int i = 0; i < sensor.iNumListeners; i++) {
						sensor.iListeners[i].stateChanged(sensor.port, oldValue, newValue);
					}
					oldValue = newValue;
					sleep(update);
				} catch (InterruptedException e) {
					interrupt();
				}
			}
		}
	}

	public void addSensorPortListener(SensorPortListener aListener) {

		if (iListeners == null) {
			iListeners = new SensorPortListener[8];
			BrightnessThread.setSensor(this);
			BrightnessThread.get().start();
		}
		if (iNumListeners == 8) { // maximal 8 listener
			iNumListeners = -1;
			iListeners[0] = null;
		}
		iListeners[iNumListeners++] = aListener;
	}

}
