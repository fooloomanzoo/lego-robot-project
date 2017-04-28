import lejos.nxt.*;

public class Kroete {

	final static int TIME_CONTACT_DIFF = 3000;
	public static int findAttempts = 0;

	public static void start() {
		System.out.println("Kroetenwanderung");
		System.out.println("Druecke Startbutton!");
		Button.waitForAnyPress();

		// Wenn Escape gedrueckt wird, endet das Programm
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				System.exit(0);
			}

			public void buttonReleased(Button b) {
				// Nothing here
			}
		});

		SensorPort.S3.setType(SensorConstants.TYPE_SWITCH);
		SensorPort.S3.addSensorPortListener(new TouchListener(TouchListener.LEFT));

		SensorPort.S2.setType(SensorConstants.TYPE_SWITCH);
		SensorPort.S2.addSensorPortListener(new TouchListener(TouchListener.RIGHT));

		Move.straight();
		while (true) {
			if (TouchListener.firstContact != -1 && (System.currentTimeMillis()
					- TouchListener.timeLastContact > TIME_CONTACT_DIFF)) {
				try {
					if (TouchListener.lastContact == TouchListener.RIGHT)
						Move.arcBackward(0.33, 1);
					else
						Move.arcBackward(1, 0.33);
					System.out.println("findAttempts " + findAttempts);
					Thread.sleep((Move.SPEED_STRAIGHT * 1000) / 180);
					if (TouchListener.firstContact == TouchListener.LEFT) {
						Move.arcLeft();
					} else {
						Move.arcRight();
					}
					Thread.sleep(TIME_CONTACT_DIFF + 100 * findAttempts * findAttempts);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					findAttempts++;
				}
			} else {
				findAttempts = 0;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Aufgabe 3.1 mit Threads");
		start();
	}

}
