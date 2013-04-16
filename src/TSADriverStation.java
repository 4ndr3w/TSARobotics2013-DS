import java.net.SocketException;

import tsa2035.robot.comm.RobotSender;




public class TSADriverStation {

	LogitechGamepad driver = null;
	MicrosoftJoystick operator = null;
	RobotSender comm;
	
	public TSADriverStation(String robotIP, LogitechGamepad driver, MicrosoftJoystick operator) throws SocketException
	{
		comm = new RobotSender(robotIP);
		this.driver = driver;
		this.operator = operator;
		comm.start();
	}
	
	
	public int constrain(int v)
	{
		if ( v > 254 )
			v = 254;
		else if (v < 0)
			v = 0;
		return v;
		
	}
	
	public void update()
	{	
		comm.setDrive(driver.getY1(), driver.getY2());
		if ( operator.getY1() < -0.8 )
			comm.setLift(true, true);
		else if ( operator.getY1() > 0.8 )
			comm.setLift(false, true);
		
		if ( operator.get0() )
			comm.setCollector(true, false);
		else if ( operator.get1() )
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
		MicrosoftJoystick operator = MicrosoftJoystick.getGamepad();
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
