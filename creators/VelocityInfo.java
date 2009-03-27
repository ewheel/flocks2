package creators;

import java.awt.*;

public class VelocityInfo
{
	private Flock myFlock;
	private Dimension mySize;

	public VelocityInfo()
	{
		myFlock = null;
		mySize = null;
	}
	
	public Flock getFlock()
	{
		return myFlock;
	}

	public void setFlock(Flock flock)
	{
		myFlock = flock;
	}
	
	public Dimension getSize()
	{
		return mySize;
	}

	public void setSize(Dimension size)
	{
		this.mySize = size;
	}
}
