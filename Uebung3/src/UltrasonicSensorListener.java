import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.Sound;

public class UltrasonicSensorListener implements SensorPortListener {
	final int DISTANCE_TO_OUTLINE = 6;
	final int DISTANCE_TO_WALL = 20 + DISTANCE_TO_OUTLINE;

	public void stateChanged(SensorPort aSource, int oldDistance, int newDistance) {
		if(newDistance > DISTANCE_TO_WALL) {
			try {
				Sound.playTone(232, 50);
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
