package guiAndAbstracts;


import java.awt.Point;
import java.util.*;

import constantsFiles.*;
import reflection.*;
/**
 * An abstract command class that creates Mover subclasses.
 * 
 * @author Robert C Duvall
 */
public abstract class Factory implements Constants
{
    // name of this factory
    private String myName;


    /**
     * Create a factory with the given name.
     * 
     * @param name of this factory
     */
    public Factory (String name)
    {
        myName = name;
    }


    /**
     * Reports the name of this factory 
     * (displayed on the button used to activate it)
     * 
     * @return name of this factory
     */
    public String getName ()
    {
        return myName;
    }


    /**
     * Subclasses determine how to create the movers and add them to the canvas
     * 
     * @param target canvas that will display created movers
     * @param number number of movers to create
     * @param trailSize number of steps to draw in the trail
     */
    public abstract void createMovers (Canvas target, SliderProperties information);
    
    @SuppressWarnings("unchecked")
	public List<Point> generateFormation(SliderProperties information)
    {
    	return (List<Point>) Reflection.callMethod(this, GENERATE+information.getFormation()+FORMATION, information);  	
    }
    
    public List<Point> generateRandomFormation(SliderProperties information)
    {
    	List<Point> moverPositions = new ArrayList<Point>();
    	for(int n=0; n<information.getNumberObjects(); n++)
    	{
    		moverPositions.add(new Point(information.getCanvas().nextIntInRange(0, information.getBounds().width),
    				information.getCanvas().nextIntInRange(0, information.getBounds().width)));
    	}
    	return moverPositions;
    }
    
    public List<Point> generateCircleFormation(SliderProperties information)
    {
    	List<Point> moverPositions = new ArrayList<Point>();
    	Point center = new Point(information.getBounds().width / 2, information.getBounds().height / 2);
    	int radius = Math.min(information.getBounds().width, information.getBounds().height) / 2;
    	for (float k = 0; k < 360; k += 360.0f / information.getNumberObjects())
    	{
    		moverPositions.add(new Point((int)(center.x + Math.cos(Math.toRadians(k)) * radius),
                    (int)(center.y + Math.sin(Math.toRadians(k)) * radius)));
    	}
    	return moverPositions;
    }
    
    public List<Point> generateLineFormation(SliderProperties information)
    {
    	for(int n=0; n<information.getNumberObjects(); n++)
    	{
    		
    	}
    	return null;
    }
    
}
