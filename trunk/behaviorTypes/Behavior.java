package behaviorTypes;

import creators.*;

public interface Behavior 
{
	public void computeNewVelocity(Mover m, VelocityInfo v);
}
