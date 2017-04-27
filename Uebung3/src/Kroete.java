import lejos.nxt.*;

public class Kroete {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Kroetenwanderung Threads");
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
		
		// rechter Sensor --> pos = 0
		SensorPort.S2.setType(SensorConstants.TYPE_SWITCH);
		SensorPort.S2.addSensorPortListener(new TouchListener(0));
		
		// linker Sensor --> pos = 1
		SensorPort.S3.setType(SensorConstants.TYPE_SWITCH);
		SensorPort.S3.addSensorPortListener(new TouchListener(1));
		
		while (true) {
			
		}		
		
	}

}
