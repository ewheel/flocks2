package guiAndAbstracts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.BevelBorder;

import creators.*;


/**
 * Animates any number of ovals that start at random positions, moving
 * in random directions, and "bounce" elastically, i.e., as a perfect 
 * reflection, when they hit the boundaries of the window.
 *
 * @author Robert C. Duvall
 */
@SuppressWarnings("serial")
public class Canvas extends JComponent
{
    // things to be animated
    //private List<Mover> myMovers;
    private Flock myFlock;
    // additional state for adding and removing of shapes during animation
    private List<Mover> myMoversToRemove;
    private ListIterator<Mover> myIterator;
    private Mover myCurrent;
    // like a dice, generates a series of random numbers
    private Random myGenerator;


    /**
     * Create a Canvas with the given size.
     * 
     * @param size width and height of canvas in pixels
     */
    public Canvas (Dimension size)
    {
        // distinguish canvas from the rest of the window
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        // initialize animation state
        myGenerator = new Random();
        //myMovers = new ArrayList<Mover>();
        myFlock = new Flock();
        myMoversToRemove = new ArrayList<Mover>();
    }


    /**
     * Remember given mover so it is painted on Canvas.
     * 
     * Note, movers are rendered in the order they are added.
     * 
     * @param m mover to be added to those that are drawn
     */
    public void add (Mover shape) 
    {
        //if (myIterator == null)  myMovers.add(shape);
    	if (myIterator == null)  myFlock.addIndividual(shape);
        else                     myIterator.add(shape);
    }
    
    
    /**
     * Forget given mover so it is not painted on Canvas.
     * 
     * @param m mover to be added to those that are drawn
     */
    public void remove (Mover shape)
    {
        //if (myIterator == null)       myMovers.remove(shape);
    	if (myIterator == null)       myFlock.removeIndividual(shape);
        else if (myCurrent == shape)  myIterator.remove();
        else                          myMoversToRemove.add(shape);
    }
    
    
    /**
     * Remove all shapes from the canvas.
     */
    public void clear ()
    {
        //if (myIterator == null)     myMovers.clear();
    	if (myIterator == null)     myFlock.clearMemberList();
        //else                        myMovers.addAll(myMovers);
    	else
    	{
    		List<Mover> movers = myFlock.getMemberList();
    		movers.addAll(movers);
    		myFlock.setMemberList(movers);
    	}
    }


    /**
     * Paint the contents of the canvas.
     *
     * Never called by you directly, instead called by Java runtime
     * when area of screen covered by this container needs to be 
     * displayed (i.e., creation, uncovering, change in status)
     *
     * @param pen used to paint shape on the screen
     */
    public void paintComponent (Graphics pen)
    {
        // distinguish canvas from the rest of the window
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getSize().width, getSize().height);
        // paint shapes to be animated
        //for (Mover m : myMovers)
        for (Mover m : myFlock.getMemberList())
        {
            m.paint(pen);
        }
    }


    /**
     * Called by each step of timer, multiple times per second.
     * 
     * This should update the state of the animated shapes by just
     * a little so they appear to move over time.
     */
    /*
     * TODO: Need to make a new class that holds individual Flocks.  There will be a List<Flock> that contains all flocks in the program.
     * Currently, a single flock is hard-coded in.
     * TODO: Move this update code to the new Flock Container class.  It does not belong in Canvas.
     * 
     * Professor Duvall - We have not refactored the update code yet.
     * 
     */
    
    public void update()
    {
        // animate each mover, taking care to add or remove new ones appropriately
        //myIterator = myMovers.listIterator();
    	myFlock.computeNextTurn(this.getSize());//will eventually make all necessary calculations for collision detection and calculates velocity for every flock member based on the 3 rules.
    	myIterator = myFlock.getMemberList().listIterator();
        while (myIterator.hasNext())
        {
            myCurrent = myIterator.next();
            if (myMoversToRemove.contains(myCurrent))
            {
                myMoversToRemove.remove(myCurrent);
                myIterator.remove();
            }
            else
            {
                myCurrent.update(this);
            }
        }
        myIterator = null;
        // clear out updates made during animation
        for (Mover current : myMoversToRemove)
        {
            //myMovers.remove(current);
        	myFlock.removeIndividual(current);
        }
        myMoversToRemove.clear();

        // BUGBUG: fix for the racer program
        ArrayList<Mover> racers = new ArrayList<Mover>();
        //for (Mover m : myMovers)
        for (Mover m : myFlock.getMemberList())
        {
            if (m instanceof creators.Racer)
            {
                racers.add(m);
            }
        }
        if (racers.size() > 0)
        {
            colorLeadingRacer(racers);
        }
    }


    /**
     * Returns a random value between min and max, inclusive
     *
     * @param min minimum possible value
     * @param max maximum possible value
     */
    public int nextIntInRange (int min, int max)
    {
        return myGenerator.nextInt(max - min + 1) + min;
    }


    /**
     * Returns a random value between min and max, inclusive
     * that is guaranteed not to be zero
     *
     * @param min minimum possible value
     * @param max maximum possible value
     */
    public int nextNonZeroIntInRange (int min, int max)
    {
        int result = 0;

        while (result == 0)
        {
            result = nextIntInRange(min, max);
        }

        return result;
    }
    
    
    /**
     * Colors the racer that is farthest ahead RED and all the other
     * racers that are behind BLACK.  If several racers all share the 
     * lead, the one with the least y-value should be colored RED.
     * 
     * @param racers a collection with at least one racer in it
     */
    private void colorLeadingRacer (ArrayList<Mover> racers)
    {
        // can assume that there is at least one racer
        Mover leader = racers.get(0);
        int maxx = leader.getCenter().x;
        for (Mover r : racers)
        {
            if (r.getCenter().x > maxx)
            {
                maxx = r.getCenter().x;
                leader = r;
            }
            r.setColor(Color.BLACK);
        }
        leader.setColor(Color.RED);
    }
}
