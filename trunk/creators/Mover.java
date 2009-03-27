package creators;

import java.awt.*;
import java.util.List;
import java.util.LinkedList;

import guiAndAbstracts.Canvas;


/**
 * NAME : 
 * COURSE : CompSci 6
 * PURPOSE : 
 *  Represents a shape that moves on its own
 */
public abstract class Mover
{
    // state
    protected Point myCenter;
    protected Point myVelocity;
    protected Dimension mySize;
    protected Color myColor;
    private int myMaxTrail;
    private List<Point> myTrail;


    /**
     * Construct a shape at the given position, with the given velocity, 
     * size, and color.  Also a size for its trail (if implemented).
     *
     * @param center position of the shape
     * @param size dimensions of the shape
     * @param velocity speed and direction of the shape
     * @param color color of the shape
     * @param trailSize number of lines to draw following the shape
     */
    public Mover (Point center, Dimension size, Point velocity, Color color, int trailSize)
    {
        myCenter = new Point(center.x, center.y);
        mySize = new Dimension(size.width, size.height);
        myVelocity = new Point(velocity.x, velocity.y);
        myColor = color;
        
        myMaxTrail = trailSize;
        myTrail = new LinkedList<Point>();
        myTrail.add(new Point(myCenter));
    }


    /**
     * Describes how to "animate" the shape by changing its state.
     *
     * Currently, moves by the current velocity.
     *
     * @param bounds which encloses this shape
     */
    public void update (Canvas canvas)
    {
        myCenter.translate(myVelocity.x, myVelocity.y);
        myTrail.add(new Point(myCenter));
        if (myTrail.size() >= myMaxTrail)
        {
            myTrail.remove(0);
        }
    }


    /**
     * Describes how to draw the shape on the screen.
     *
     * Currently, draw a trail of the shape's previous positions if it exists.
     * 
     * @param pen used to paint shape on the screen
     */
    public void paint (Graphics pen)
    {
        if (myMaxTrail > 0)
        {
            drawTrail(pen);
        }
    }


    /**
     * Reports shape's center.
     *
     * @return center of shape
     */
    public Point getCenter ()
    {
        return myCenter;
    }

    /**
     * Reports shape's size.
     *
     * @return size of shape
     */
    public Dimension getSize ()
    {
        return mySize;
    }

    /**
     * Changes shape's color.
     */
    public void setSize (int width, int height)
    {
        mySize = new Dimension(width, height);
    }

	/**
     * Reports shape's velocity.
     *
     * @return velocity of shape
     */
    public Point getVelocity ()
    {
        return myVelocity;
    }

    /**
     * Reports shape's color.
     *
     * @return color of shape
     */
    public Color getColor ()
    {
        return myColor;
    }

    /**
     * Changes shape's color.
     */
    public void setColor (Color c)
    {
        myColor = c;
    }

    
    /**
     * Draws a trail connecting the shape's previous positions.
     * 
     * @param pen used to paint shape on the screen
     */
    private void drawTrail (Graphics pen)
    {
        pen.setColor(myColor);
        // draw trail
        Point previous = myTrail.get(0);
        for (int k = 1; k < myTrail.size(); k++)
        {
            Point current = myTrail.get(k);
            pen.drawLine(previous.x, previous.y, current.x, current.y);
            previous = current;
        }
    }
    
    public abstract void computeNewVelocity(VelocityInfo v);
}
