import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class TouchListener implements SensorPortListener {
	
	public static Move move = new Move();
	public int pos;
	
	public TouchListener(int pos) {
		this.pos = pos;
	}
	
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		System.out.println(aSource.getId());
		if (this.pos == 0) {
			try {
				move.rotate(-10, Move.SLOW);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (this.pos == 1) {
			try {
				move.straight(100, Move.SLOW);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
