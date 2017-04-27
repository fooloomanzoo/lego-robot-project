
public class UltrasonicDistanceThread extends Thread {
	public UltrasonicSensorExtended sensor;
	
	public UltrasonicDistanceThread(UltrasonicSensorExtended us) {
		this.sensor = us;
	}
	
	public void run() {
		int distance;
		while (!isInterrupted()) {
			
			distance = this.sensor.getDistance();

			try {
				for (ArrayList this.sensor.RegisteredListerners: usl) {
					
				}
				for (int i = 0; i < this.sensor.RegisteredListerners.length; i++) {
					
				}
				Thread.sleep(500);
			} catch (InterruptedException e) {
				interrupt();
				System.out.println("Unterbrechung in sleep()");
			}
		}
	}
	
}
