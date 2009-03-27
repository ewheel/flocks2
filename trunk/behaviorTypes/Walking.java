package behaviorTypes;

import creators.Mover;
import creators.VelocityInfo;

public class Walking implements Behavior
{
	public void computeNewVelocity(Mover m, VelocityInfo v)
	{
		double randAngle = Math.toRadians(Math.random()*360);
        m.getVelocity().x = (int)(3 * Math.cos(randAngle));
        m.getVelocity().y = (int)(3 * Math.sin(randAngle));
	}

}
