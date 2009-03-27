package creators;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import guiAndAbstracts.Canvas;
import guiAndAbstracts.Factory;
import guiAndAbstracts.SliderProperties;

/**
 *  Creates any number of follower objects that follow a single leader.
 *  
 *  @author Robert C. Duvall
 */
public class SwarmFactory extends Factory
{
    /**
     * Construct factory.
     */
    public SwarmFactory ()
    {
        super("Create Swarm");
    }


    /**
     * Creates given number of followers within the given canvas.
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
        int size = 25;
        // create leader that moves randomly around
        Mover leader =
            new SwarmLeader(
                new Point(canvas.getSize().width / 2,
                          canvas.getSize().width / 2),
                new Dimension(size, size),
                new Point(0, 0),
                Color.BLUE,
                trailSize);
        canvas.add(leader);

        // create followers that all follow the first leader
        for (int k = 0; k < number; k++)
        {
            size = 16;
            canvas.add(
                new SwarmFollower(
                    new Point(canvas.nextIntInRange(size / 2, bounds.width - size / 2),
                              canvas.nextIntInRange(size / 2, bounds.width - size / 2)),
                    new Dimension(size, size),
                    new Point(0, 0),
                    Color.GREEN,
                    trailSize,
                    leader));
        }
    }
    
    public List<Point> generateRandomFormation(SliderProperties information)
    {
    	List<Point> moverPositions = new ArrayList<Point>();
    	for(int n=0; n<information.getNumberObjects(); n++)
    	{
    		moverPositions.add(new Point(information.getCanvas().nextIntInRange(information.getMemberSize() / 2, information.getBounds().width - information.getMemberSize() / 2),
    				information.getCanvas().nextIntInRange(information.getMemberSize() / 2, information.getBounds().width - information.getMemberSize() / 2)));
    	}
    	return moverPositions;
    }
    

}
