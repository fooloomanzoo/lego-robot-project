import javax.microedition.sensor.UltrasonicChannelInfo;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class ultraKroete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UltrasonicSensorExtended us = new UltrasonicSensorExtended(SensorPort.S1);
		USListener usl = new USListener();
		us.addSensorPortListener(usl);
	}

}
