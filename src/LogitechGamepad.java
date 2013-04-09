
import java.util.ArrayList;
import java.util.Iterator;

import org.omg.IOP.ComponentIdHelper;

import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component;

public class LogitechGamepad {
	
	Controller c;
	public LogitechGamepad(Controller controller)
	{
		this.c = controller;
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
	
	public int getY1()
	{
		c.poll();
		return (int)Math.floor((c.getComponent(Component.Identifier.Axis.Y).getPollData()+1f)*127f);
	}
	

	public int getX1()
	{
		c.poll();
		return (int)Math.floor((c.getComponent(Component.Identifier.Axis.X).getPollData()+1f)*127f);
	}
	
	public int getY2()
	{
		c.poll();
		return (int)Math.floor((c.getComponent(Component.Identifier.Axis.RZ).getPollData()+1f)*127f);
	}
	
	public int getX2()
	{
		c.poll();
		return (int)Math.floor((c.getComponent(Component.Identifier.Axis.Z).getPollData()+1f)*127f);
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
