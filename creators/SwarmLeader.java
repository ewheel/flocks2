package creators;

import java.awt.*;
import java.util.Random;

import guiAndAbstracts.Canvas;


/**
 *  Represents a shape that moves randomly across the canvas.
 *  
 *  @author Robert C. Duvall
 */
public class SwarmLeader extends Mover
{
    private static final int MAX_SPEED = 20;


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
    public SwarmLeader (Point center,
                  Dimension size,
                  Point velocity,
                  Color color,
                  int trailSize)
    {
        super(center, size, velocity, color, trailSize);
    }


    /**
     * Describes how to "animate" the shape by changing its state.
     *
     * @param bounds which encloses this shape
     */
    public void update (Canvas canvas)
    {
        // move shape by velocity
        super.update(canvas);
        Dimension bounds = canvas.getSize();
        myCenter.x = wrapCoordinate(myCenter.x, bounds.width);
        myCenter.y = wrapCoordinate(myCenter.y, bounds.height);
    }


    /**
     * Describes how to draw a swarm member on the screen.
     *
     * @param pen used to paint shape on the screen
     */
    public void paint (Graphics pen)
    {
        // draw trail if it exists
        super.paint(pen);
        // draw shape as a changing line
        pen.setColor(myColor);
        pen.drawLine(myCenter.x - myVelocity.x / 2,
                     myCenter.y - myVelocity.y / 2, 
                     myCenter.x + myVelocity.x / 2, 
                     myCenter.y + myVelocity.y / 2);
    }


    /**
     * @return the given value constrained to the given bounds, 
     *         but if it goes off one side, it is mapped to the other side 
     */
    protected int wrapCoordinate (int value, int bounds)
    {
        return (value + bounds) % bounds;
    }


    /**
     * @return the given value constrained to the given bounds, 
     *         but if it goes off one side, it is set to that max value 
     */
    protected int limitSpeed (int value, int max)
    {
        if (value > MAX_SPEED)
        {
            value = MAX_SPEED;
        }
        else if (value < -MAX_SPEED)
        {
            value = -MAX_SPEED;
        }
        return value;
        // OR:
        // return (int)(20.0 * value / Math.abs(value));
    }


	public void computeNewVelocity(VelocityInfo v)
	{
		//Dimension bounds = canvas.getSize();
        // move randomly, but in the general direction currently moving
		Random r = new Random();
        myVelocity.x = limitSpeed(myVelocity.x + nextIntInRange(-5, 5, r), MAX_SPEED);
        myVelocity.y = limitSpeed(myVelocity.y + nextIntInRange(-5, 5, r), MAX_SPEED);
	}
	
	public int nextIntInRange (int min, int max, Random r)
    {
		return r.nextInt(max - min + 1) + min;
    }
}
