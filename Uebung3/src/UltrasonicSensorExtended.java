import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.UltrasonicSensor;

public class UltrasonicSensorExtended extends UltrasonicSensor {
	
	protected int iPortId;
	protected SensorPort port;
	protected short iNumListeners = 0;
	protected SensorPortListener[] iListeners = null;
	protected int iPreviousValue;

	public UltrasonicSensorExtended(SensorPort port) {
		super(port);
		this.port = port;
		this.iPortId = port.getType();
	}
	
	public int getPortId() {
		return iPortId;
	}
	
	public SensorPort getPort() {
		return port;
	}		
	
	// Threads fuer Listeners
	protected static class UltrasonicDistanceThread extends Thread {
		private static UltrasonicSensorExtended sensor = null;
		private static int update = 100; // Zeit, zwischen Updates
		private static int toleranz = 2; // Zeit, zwischen Updates
		static UltrasonicDistanceThread singleton = null;

		static synchronized UltrasonicDistanceThread get() {
			if (singleton == null) {
				singleton = new UltrasonicDistanceThread();
				singleton.setDaemon(true);
				singleton.setPriority(Thread.MAX_PRIORITY);
				System.out.println("new ThreadChain");
			}
			return singleton;
		}
		
		public static void setSensor(UltrasonicSensorExtended usEx) {
			UltrasonicDistanceThread.sensor = usEx;
		}
		
		public void run() {
			int distance = 255, olddistance = 255;
			while (!isInterrupted()) {
				distance = UltrasonicDistanceThread.sensor.getDistance();

				try {
					if (Math.abs(distance - olddistance) >= toleranz && distance != 255) { // 255 --> No Object in Range or Error occured
						System.out.println(distance);
						for (int i = 0; i < sensor.iNumListeners; i++) {
							sensor.iListeners[i].stateChanged(sensor.port, olddistance, distance);
						}
						olddistance = distance;
					}
					sleep(update);
				} catch (InterruptedException e) {
					interrupt();
				}
			}
		}
	}

//	public void addSensorPortListener(SensorPortListener usl) {
//		RegisteredListerners.add(usl);
//	}
    public void addSensorPortListener(SensorPortListener aListener)
    {
    	
        if (iListeners == null)
        {
            iListeners = new SensorPortListener[8];
			UltrasonicDistanceThread.setSensor(this);
			UltrasonicDistanceThread.get().start();
        }
        if (iNumListeners == 8) { // maximal 8 listener
        	iNumListeners = -1;
        	iListeners[0] = null;
        }
        iListeners[iNumListeners++] = aListener;
        System.out.println("new Listener " + iNumListeners);
    }
    
}
