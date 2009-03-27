package creators;

import guiAndAbstracts.Canvas;
import constantsFiles.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.*;

public class FlockMember extends Mover implements Constants
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
	private int myClock;
	private Random myRandomGenerator;
	private Point myAcceleration;
	
	public FlockMember (Point center,
            Dimension size,
            Point velocity,
            Color color,
            int trailSize)
	{
		super(center, size, velocity, color, trailSize);
		myClock = 1;
		myRandomGenerator = new Random();
		myAcceleration = new Point(0,0);
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
    }
	
	public void computeNewVelocity(VelocityInfo v)
	{
		System.out.println(myVelocity.x);
        System.out.println(myVelocity.y);
		Flock f = v.getFlock();
		Point direction = f.getAverageDirection(this);
		Point position = f.getAverageLocation(this);
		Point difference = new Point((int)Math.round(position.getX()-myCenter.getX()), (int)Math.round(position.getY()-myCenter.getY()));
		
		for(Mover m: f.getAdjacentMembers(this, FLOCKLET_RADIUS))
		{
			if(!m.equals(this))
			{
				if(m.getCenter().distance(this.getCenter()) < mySize.getWidth()*3)
				{
					Point tooclose = new Point((int)(this.getCenter().getX()-m.getCenter().getX()),
							(int)(this.getCenter().getY()-m.getCenter().getY()));
					tooclose = normalize(tooclose);
					myAcceleration.x += (int) tooclose.getX();
					myAcceleration.y += (int) tooclose.getY();
				}
			}
		}
		difference = normalize(difference);
		direction = normalize(direction);
		myAcceleration.x += (int) difference.getX();
		myAcceleration.y += (int) difference.getY();
		myAcceleration.x += (int) direction.getX();
		myAcceleration.y += (int) direction.getY();
		myVelocity.x += myAcceleration.x;
		myVelocity.y += myAcceleration.y;
		if(myClock % RANDOMIZER_TIME == 0)
		{
			myVelocity.x += (int) Math.round(3*myRandomGenerator.nextGaussian());
			myVelocity.y += (int) Math.round(3*myRandomGenerator.nextGaussian());
			myClock = 1;
		}
		else
		{
			myClock++;
		}
		myVelocity.x = limitSpeed(myVelocity.x, MAX_SPEED);
	    myVelocity.y = limitSpeed(myVelocity.y, MAX_SPEED);
	    myAcceleration.x = myAcceleration.y = 0;
        myCenter.x = wrapCoordinate(myCenter.x, (int) v.getSize().getWidth());
        myCenter.y = wrapCoordinate(myCenter.y, (int) v.getSize().getHeight());
        System.out.println(myVelocity.x);
        System.out.println(myVelocity.y);
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
        pen.fillRect(myCenter.x - mySize.width / 2,
                     myCenter.y - mySize.height / 2,
                     mySize.width, mySize.height);
    }
    
    protected int limitSpeed (int value, int max)
    {
    	if (value > MAX_SPEED)
        {
            value = MAX_SPEED;
        }
        else if (value < -1*MAX_SPEED)
        {
            value = -1*MAX_SPEED;
        }
        return value;
    }
    protected int wrapCoordinate (int value, int bounds)
    {
        return (value + bounds) % bounds;
    }
    protected Point normalize(Point p)
    {
    	double length = Math.sqrt(p.getX()*p.getX() + p.getY()*p.getY());
    	if (length != 0)
    	{
    	p.x = (int) Math.round(p.getX()/length);
    	p.y = (int) Math.round(p.getY()/length);
    	}
    	else
    	{
    		p.x = 0;
    		p.y = 0;
    	}
    	return p;
    }

}
