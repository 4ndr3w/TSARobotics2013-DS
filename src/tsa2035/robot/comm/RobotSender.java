package tsa2035.robot.comm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class RobotSender extends Thread {
	String robotIP;
	DatagramSocket socket;
	
	
	byte driveLeft, driveRight;
	byte collectorState = 0;
	byte liftState = 0;
	
	public RobotSender(String robotIP) throws SocketException
	{
		this.robotIP = robotIP;
		socket = new DatagramSocket();
	}
	
	public void setDrive(byte left, byte right)
	{
		System.out.println(left);
		driveLeft = left;
		driveRight = right;
	}
	
	public void setCollector(boolean state, boolean reverse)
	{
		collectorState = (byte) (state?(reverse?2:1):0);
	}
	
	public void setLift(boolean up)
	{
		liftState = (byte) (up?1:0);
	}
	
	public void run()
	{
		while ( true )
		{
			try {
				byte buffer[] = new byte[4];
				buffer[0] = driveLeft;
				buffer[1] = driveRight;
				buffer[2] = collectorState;
				buffer[3] = liftState;
				DatagramPacket pkt = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(robotIP), 8888);
				socket.send(pkt);
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
