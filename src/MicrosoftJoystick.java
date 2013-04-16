
import java.util.ArrayList;
import java.util.Iterator;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component;

public class MicrosoftJoystick {
	
	Controller c;
	public MicrosoftJoystick(Controller controller)
	{
		this.c = controller;
	}

	public boolean get0()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._0).getPollData()==1.0f);
	}
	
	public boolean get1()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._1).getPollData()==1.0f);
	}
	
	public boolean get2()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._2).getPollData()==1.0f);
	}
	
	public boolean getY()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._3).getPollData()==1.0f);
	}	
	
	public boolean getLB()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._4).getPollData()==1.0f);
	}
	
	public boolean get5()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._5).getPollData()==1.0f);
	}
	
	public boolean get6()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._6).getPollData()==1.0f);
	}
	
	public boolean get7()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._7).getPollData()==1.0f);
	}
	
	public boolean get8()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._8).getPollData()==1.0f);
	}
	
	public float getY1()
	{
		c.poll();
		return c.getComponent(Component.Identifier.Axis.Y).getPollData();
	}
	

	public byte getX1()
	{
		c.poll();
		return (byte)Math.floor((c.getComponent(Component.Identifier.Axis.X).getPollData()+1)*127f);
	}
	
	public byte getY2()
	{
		c.poll();
		return (byte)Math.floor((c.getComponent(Component.Identifier.Axis.RZ).getPollData()+1)*127f);
	}
	
	public byte getX2()
	{
		c.poll();
		return (byte)Math.floor((c.getComponent(Component.Identifier.Axis.Z).getPollData()+1)*127f);
	}
	
	public static MicrosoftJoystick getGamepad()
	{
		System.out.println("Press A to select gamepad.");
		
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		ArrayList <MicrosoftJoystick>gamepads = new ArrayList<MicrosoftJoystick>();
		for ( Controller c : controllers )
		{
			System.out.println(c.getName());
			if ( c.getName().equals("SideWinder Joystick") )
				gamepads.add(new MicrosoftJoystick(c));
		}
		
		while ( true )
		{
			Iterator<MicrosoftJoystick> it = gamepads.iterator();
			while ( it.hasNext() )
			{
				MicrosoftJoystick thisGamepad = it.next();
				if ( thisGamepad.get1() )
					return thisGamepad;
			}
		}
		
	}
}
