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
 *  Creates any number of Racer objects in a line down the left side of the canvas.
 */
public class RacerFactory extends Factory
{
    /**
     * Construct factory.
     */
    public RacerFactory ()
    {
        super("Create Racers");
    }


    /**
     * Creates given number of racers within the given canvas.
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
        Dimension size = new Dimension(20, bounds.height / number);
        // create each shapes and add to list
        for (float k = 0; k < number; k++)
        {
            canvas.add(
                new Racer(
                    new Point(0, (int)((k + 0.5) * size.height)),
                    size,
                    new Point(0, 0),
                    new Color(1 - (k / number), 0.5f, k / number),
                    trailSize));
        }
    }
    

}
