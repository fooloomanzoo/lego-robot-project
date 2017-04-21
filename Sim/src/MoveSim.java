import java.awt.*;

public class MoveSim extends MoveGeneric<MotorSim> {

	public MoveSim() {

		ch.aplu.robotsim.NxtRobot robot = new ch.aplu.robotsim.NxtRobot();
		MotorSim motLeft = new MotorSim(ch.aplu.robotsim.MotorPort.A);
		MotorSim motRight = new MotorSim(ch.aplu.robotsim.MotorPort.B);
		robot.addPart(motLeft);
		robot.addPart(motRight);
		this.setMotorLeft(motLeft);
		this.setMotorRight(motRight);

		Point[] mesh = { new Point(50, 0), new Point(25, 42), new Point(-25, 42), new Point(-50, 0),
				new Point(-25, -42), new Point(25, -42) };

		ch.aplu.robotsim.NxtContext.useTarget("sprites/target_red.gif", mesh, 350, 350);

	}

}
