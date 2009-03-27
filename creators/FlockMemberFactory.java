package creators;

import guiAndAbstracts.*;
import constantsFiles.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.*;

public class FlockMemberFactory extends Factory implements Constants
{
	public FlockMemberFactory ()
    {
        super("Create Flocks");
    }
	/**
     * Creates given number of attractors within the given canvas.
     *
     * @param canvas within which shapes should appear
     * @param number number of shapes to create
     * @param trailSize number of lines to draw following the shape
     */
    public void createMovers (Canvas canvas, SliderProperties information)
    {
    	int number = information.getNumberObjects(); 
    	int trailSize = information.getTrailSize();
        // current size of canvas
        Dimension bounds = canvas.getSize();
        // create proper movers
        int radius = FLOCK_INDIVIDUAL_RADIUS;//int radius = Math.min(bounds.width, bounds.height) / 2;
        int circumference = (int)(2 * Math.PI * radius);
        int size = circumference;//int size = circumference / number;
        for (float k = 0; k < 360; k += 360.0f / number)
        {
            canvas.add(
                new FlockMember(
                		new Point(canvas.nextIntInRange(bounds.width / 2, bounds.width),
                                canvas.nextIntInRange(bounds.height / 2, bounds.height)),
                    new Dimension(FLOCK_INDIVIDUAL_RADIUS,FLOCK_INDIVIDUAL_RADIUS),
                    new Point((int) Math.round(Math.random()*3),
                    		(int) Math.round(Math.random()*3)),
                    new Color(1 - k / 360, 0.5f, k / 360),
                    trailSize));
        }
    }
}
