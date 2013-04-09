import java.net.SocketException;

import tsa2035.robot.comm.RobotSender;




public class TSADriverStation {

	LogitechGamepad driver = null;
	LogitechGamepad operator = null;
	RobotSender comm;
	
	public TSADriverStation(String robotIP, LogitechGamepad driver, LogitechGamepad operator) throws SocketException
	{
		comm = new RobotSender(robotIP);
		this.driver = driver;
		this.operator = operator;
		comm.start();
	}
	
	public void update()
	{
		comm.setDrive(driver.getY1(), driver.getY2());
		
		if ( driver.getA() )
			comm.setLift(true);
		else if ( driver.getB() )
			comm.setLift(false);
		
		if ( driver.getLB() )
			comm.setCollector(true, false);
		else if ( driver.getLT() )
			comm.setCollector(true, true);
		else
			comm.setCollector(false, false);
		
	}
	
	public static void main(String[] args) throws Exception {
		String robotIP = "10.0.0.177";
		
		System.out.println("Select driver gamepad");
		LogitechGamepad driver = LogitechGamepad.getGamepad();
		System.out.println("Driver selected\n");
		Thread.sleep(500);
		System.out.println("Select operator gamepad");
		LogitechGamepad operator = LogitechGamepad.getGamepad();
		System.out.println("Operator selected");

		System.out.println("Sending data to robot at "+robotIP);
		
		TSADriverStation ds = new TSADriverStation(robotIP, driver,operator);
		while ( true )
		{
			ds.update();
			Thread.sleep(50);
		}
	}

}
