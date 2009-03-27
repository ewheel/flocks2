package constantsFiles;

public interface Constants
{
	// Applet constants
    public static final String MOVERS_LABEL = "Number of shapes to create";
    public static final int MIN_MOVERS = 1;
    public static final int MAX_MOVERS = 512;
    public static final String TRAIL_LABEL = "Length of trail to display";
    public static final int MIN_TRAIL = 0;
    public static final int MAX_TRAIL = 128;
    // animate 25 times per second if possible
    public static final int DEFAULT_DELAY = 1000 / 25;  // in milliseconds
    
    
    //formation constants
    public static final String GENERATE = "generate";
    public static final String FORMATION = "Formation";
    public static final String CIRCLE_FORMATION = "Circle";
    public static final String LINE_FORMATION = "Line";
    public static final String RANDOM_FORMATION = "Random";
    
    
    public static final double FLOCKLET_RADIUS = 100.0;//Double.MAX_VALUE;
    public static final int MAX_SPEED = 12;
    public static final int FLOCK_INDIVIDUAL_RADIUS = 10;
    public static final int RANDOMIZER_TIME = 12;
}
