
import java.util.ArrayList;
import java.util.Iterator;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component;

public class LogitechGamepad {
	
	Controller c;
	public LogitechGamepad(Controller controller)
	{
		this.c = controller;
	}

	public boolean getX()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._0).getPollData()==1.0f);
	}
	
	public boolean getA()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._1).getPollData()==1.0f);
	}
	
	public boolean getB()
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
	
	public boolean getRB()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._5).getPollData()==1.0f);
	}
	
	public boolean getLT()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._6).getPollData()==1.0f);
	}
	
	public boolean getRT()
	{
		c.poll();
		return (boolean)(c.getComponent(Component.Identifier.Button._7).getPollData()==1.0f);
	}
	
	public byte getY1()
	{
		c.poll();
		return (byte)Math.floor(c.getComponent(Component.Identifier.Axis.Y).getPollData()*127f);
	}
	

	public byte getX1()
	{
		c.poll();
		return (byte)Math.floor(c.getComponent(Component.Identifier.Axis.X).getPollData()*127f);
	}
	
	public byte getY2()
	{
		c.poll();
		return (byte)Math.floor(c.getComponent(Component.Identifier.Axis.RZ).getPollData()*127f);
	}
	
	public byte getX2()
	{
		c.poll();
		return (byte)Math.floor(c.getComponent(Component.Identifier.Axis.Z).getPollData()*127f);
	}
	
	public static LogitechGamepad getGamepad()
	{
		System.out.println("Press A to select gamepad.");
		
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		ArrayList <LogitechGamepad>gamepads = new ArrayList<LogitechGamepad>();
		for ( Controller c : controllers )
		{
			if ( c.getName().equals("Logitech Dual Action") )
				gamepads.add(new LogitechGamepad(c));
		}
		
		if ( gamepads.size() == 0 )
			return null;
		
		while ( true )
		{
			Iterator<LogitechGamepad> it = gamepads.iterator();
			while ( it.hasNext() )
			{
				LogitechGamepad thisGamepad = it.next();
				if ( thisGamepad.getA() )
					return thisGamepad;
			}
		}
		
	}
}
