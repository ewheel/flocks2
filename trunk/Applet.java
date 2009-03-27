import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import creators.*;
import java.awt.event.*;
import java.util.Map;
import java.util.HashMap;

import guiAndAbstracts.*;
import constantsFiles.*;

/**
 * Creates an applet that be viewed over the web.
 *
 * @author Robert C. Duvall
 */
@SuppressWarnings("serial")
public class Applet extends JApplet implements Constants
{
    

    // state
    private Canvas myDisplay;
    private Timer myTimer;
    private boolean myIsPlaying;

    /**
     * Initializes the applet --- called by the browser.
     */
    public void init ()
    {
        // create container to display animations
    	myIsPlaying = true;
        init(new Dimension(Integer.parseInt(getParameter("width")),
                           Integer.parseInt(getParameter("height"))));
    }


    /**
     * Initializes the applet --- called by main.
     */
    public void init (Dimension size)
    {
        // create container to display animations
        myDisplay = new Canvas(size);
        myDisplay.setPreferredSize(size);
        myDisplay.setSize(size);
        myDisplay.setFocusable(true);
        myDisplay.requestFocus();

        // create user interface controls
        Map<String, RangeSlider> sliderMap = new HashMap<String, RangeSlider>();
        sliderMap.put(MOVERS_LABEL, new RangeSlider(MOVERS_LABEL, MIN_MOVERS, MAX_MOVERS));
        sliderMap.put(TRAIL_LABEL, new RangeSlider(TRAIL_LABEL, MIN_TRAIL, MAX_TRAIL));
        JPanel sliders = new JPanel();
        for(String s: sliderMap.keySet())
        {
        	sliders.add(sliderMap.get(s));
        }
        // add commands to test here
        ButtonPanel commands = new ButtonPanel(myDisplay, sliderMap);
        commands.add(new BouncerFactory());
        commands.add(new RacerFactory());
        commands.add(new WalkerFactory());
        commands.add(new AttractorFactory());
        commands.add(new SwarmFactory());
        commands.add(new FlockMemberFactory());
        
        //Play button
        JButton play = new JButton("Play");
        play.addActionListener(new ActionListener()
	        {
	            public void actionPerformed (ActionEvent e)
	            {
	            	playButtonAction();
	            }
	        });
        commands.add(play);
        
        //pause button
        JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener()
	        {
	            public void actionPerformed (ActionEvent e)
	            {
	            	pauseButtonAction();
	            }
	        });
        commands.add(pause);
        
        //step button
        JButton step = new JButton("Step");
        step.addActionListener(new ActionListener()
	        {
	            public void actionPerformed (ActionEvent e)
	            {
	            	stepButtonAction();
	            }
	        });
        commands.add(step);
        
        //play.addActionListener(new ActionListener)
        
        // add our user interface components to applet
        getContentPane().add(commands, BorderLayout.NORTH);
        getContentPane().add(myDisplay, BorderLayout.CENTER);
        getContentPane().add(sliders, BorderLayout.SOUTH);
    }
    
    
    /**
     * Starts the applet's action, i.e., starts the animation.
     */
    public void start ()
    {
        // create a timer to animate the canvas
        myTimer = new Timer(DEFAULT_DELAY, 
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    updateDisplay();
                }
            });
        // start the animation
        myTimer.start();
        myIsPlaying = true;
    }
    
    public void updateDisplay()
    {
    	myDisplay.update();
        myDisplay.repaint();
    }
    
    public void playButtonAction()
    {
    	myTimer.start();
    	myIsPlaying = true;
    }
    
    public void pauseButtonAction()
    {
    	myTimer.stop();
    	myIsPlaying = false;
    }
    
    public void stepButtonAction()
    {
    	if(myIsPlaying)
    		pauseButtonAction();
    	updateDisplay();
    }
    /**
     * Stops the applet's action, i.e., the animation.
     */
    public void stop ()
    {
        // stop the animation
        myTimer.start();
    }
}
