package behaviorTypes;

import creators.*;
import java.util.*;

public class Racing implements Behavior
{
	private Random myRandomGenerator = new Random();
	
	public void computeNewVelocity(Mover m, VelocityInfo v)
	{
		if (m.getCenter().x < v.getSize().getWidth())
        {
            m.getVelocity().x = myRandomGenerator.nextInt(10) + 1;
        }
        else
        {
            m.getVelocity().x = 0;            
        }
	}
}
