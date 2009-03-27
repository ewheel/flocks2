package creators;

import java.awt.*;
import java.util.List;

import guiAndAbstracts.Canvas;
import guiAndAbstracts.Factory;
import guiAndAbstracts.SliderProperties;

/**
 * NAME : 
 * COURSE : CompSci 6
 * PURPOSE : 
 *  Creates any number of Walker objects along a diagonal down the center of the canvas.
 */
public class WalkerFactory extends Factory
{
    /**
     * Construct factory.
     */
    public WalkerFactory ()
    {
        super("Create Walkers");
    }


    /**
     * Creates given number of walkers within the given canvas.
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
        // all shapes share the same size
        float width = bounds.width / (float)number;
        float height = bounds.height / (float)number;
        // create each shape and add to list
        for (float k = 0; k < number; k++)
        {
            canvas.add(
                new Walker(
                    new Point((int)((k + 0.5) * width),
                              (int)((k + 0.5) * height)),
                    new Dimension((int)width, (int)height),
                    new Point(0, 0),
                    new Color(1 - (k / number), 0.5f, k / number),
                    trailSize));
        }
    }
    
    /*public List<Point> generateFormation(SliderProperties information)
    {
    	return null;
    }*/
}
