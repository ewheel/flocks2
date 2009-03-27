package creators;

import java.awt.*;
import behaviorTypes.*;
import guiAndAbstracts.Canvas;
import java.util.*;


/**
 * NAME : 
 * COURSE : CompSci 6
 * PURPOSE : 
 *  Represents a shape that orbits the center of the canvas.
 */
public class Attractor extends Mover
{
    /**
     * Construct a shape at the given position, with the given velocity, 
     * size, and color.
     *
     * @param center position of the shape
     * @param size dimensions of the shape
     * @param velocity speed and direction of the shape
     * @param color color of the shape
     * @param trailSize number of lines to draw following the shape
     */
	private Collection<Behavior> myBehaviorList;
	
    public Attractor (Point center,
                      Dimension size,
                      Point velocity,
                      Color color,
                      int trailSize)
    {
        super(center, size, velocity, color, trailSize);
        myBehaviorList = new ArrayList<Behavior>();
        myBehaviorList.add(new Attraction());
    }


    /**
     * Describes how to "animate" the shape by changing its state.
     *
     * Currently, moves the shape by one percent of its distance to the center of the canvas.
     *
     * @param bounds which encloses this shape
     */
    public void update (Canvas canvas)
    {
        // move shape by velocity
        super.update(canvas);
        // change other attributes of shape
        
    }


    /**
     * Describes how to draw the shape on the screen.
     *
     * Currently, draws the shape as a simple circle.
     *
     * @param pen used to paint shape on the screen
     */
    public void paint (Graphics pen)
    {
        // draw trail if it exists
        super.paint(pen);
        // draw shape
        pen.setColor(myColor);
        pen.fillOval(myCenter.x - mySize.width / 2,
                     myCenter.y - mySize.height / 2,
                     mySize.width, mySize.height);
    }
    
    public void computeNewVelocity(VelocityInfo info)
    {
    	for(Behavior b : myBehaviorList)
    	{
    		b.computeNewVelocity(this, info);
    	}
    	//TODO: fix this - hard-coded in just to make the project compile.
//    	Dimension bounds = info.getSize();
//        int dx = bounds.width / 2 - myCenter.x;
//        int dy = bounds.height / 2 - myCenter.y;
//        myVelocity.x = (int)(myVelocity.x + dx * 0.01);
//        myVelocity.y = (int)(myVelocity.y + dy * 0.01);
    }
}
