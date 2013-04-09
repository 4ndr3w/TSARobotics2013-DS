import java.net.SocketException;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import tsa2035.robot.comm.RobotSender;




public class TSADriverStation {

	LogitechGamepad driver = null;
	LogitechGamepad operator = null;
	RobotSender comm;
	
	public TSADriverStation(LogitechGamepad driver, LogitechGamepad operator) throws SocketException
	{
		comm = new RobotSender("10.0.0.177");
		this.driver = driver;
		this.operator = operator;
	}
	
	public void update()
	{
		comm.setDrive((byte)driver.getY1(), (byte)driver.getY2());
		
		if ( driver.getA() )
			comm.setLift(true);
		else if ( driver.getB() )
			comm.setLift(false);
		
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Select driver gamepad");
		LogitechGamepad driver = LogitechGamepad.getGamepad();
		System.out.println("Driver selected\n");
		Thread.sleep(500);
		System.out.println("Select operator gamepad");
		LogitechGamepad operator = LogitechGamepad.getGamepad();
		System.out.println("Operator selected");

		TSADriverStation ds = new TSADriverStation(driver,operator);
		while ( true )
		{
			ds.update();
			Thread.sleep(50);
		}
	}

}
