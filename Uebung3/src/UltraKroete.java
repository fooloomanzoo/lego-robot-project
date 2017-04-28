import lejos.nxt.SensorPort;

public class UltraKroete {

	public static void main(String[] args) {
		System.out.println("Aufgabe 3.2 mit Ultrasoundsensor");
		
		UltrasonicSensorExtended us = new UltrasonicSensorExtended(SensorPort.S1);
		us.addSensorPortListener(new UltrasonicSensorListener());
		
		Kroete.start();
	}

}
