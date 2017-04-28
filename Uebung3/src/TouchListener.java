import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class TouchListener implements SensorPortListener {

	static int LIMIT_PRESSED = 500;

	final static byte LEFT = 0;
	final static byte RIGHT = 1;

	static byte firstContact = -1;
	static byte lastContact = -1;
	static long timeLastContact = 0;

	public byte pos;

	public TouchListener(byte pos) {
		this.pos = pos;
	}

	public void stateChanged(SensorPort port, int oldValue, int newValue) {
		System.out.println("button " + this.pos + " port " + port.getId() + ": " + newValue);
		if (newValue < LIMIT_PRESSED) { // Gedrueckt
			if (firstContact != -1 && this.pos != lastContact && System.currentTimeMillis() - timeLastContact <= 100) {
				if (this.pos == LEFT) {
					Move.arcBackward(1, 0.33);

				} else {
					Move.arcBackward(0.33, 1);
				}
				try {
					Thread.sleep((Move.SPEED_STRAIGHT * 1000) / 360);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if (this.pos == LEFT) {
				Move.rotateRight();

			} else {
				Move.rotateLeft();
			}
			timeLastContact = System.currentTimeMillis();
			lastContact = this.pos;
			if (firstContact == -1) {
				firstContact = this.pos;
			}
		} else { // Nicht Gedrueckt
			if (firstContact == LEFT) {
				Move.arcLeft();
			} else if (firstContact == RIGHT) {
				Move.arcRight();
			} else {
				Move.straight();
			}
		}
	}

}
