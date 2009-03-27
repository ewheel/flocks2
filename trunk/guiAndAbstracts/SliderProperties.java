package guiAndAbstracts;
import java.awt.Dimension;

import constantsFiles.*;

public class SliderProperties implements Constants 
{
	private Canvas myCanvas;
	private int myTrailSize;
	private int myNumberObjects;
	private String myFormation;
	private int myMemberSize; //used for setting the size of a swarm element
	private Dimension myBounds;
	
	public SliderProperties(Canvas canvas)
	{
		myCanvas = canvas;
		myTrailSize = 0;
		myNumberObjects = 1;
		myFormation = RANDOM_FORMATION;
		myMemberSize = 10;
		myBounds = canvas.getSize();
	}

	public int getNumberObjects() {
		return myNumberObjects;
	}

	public void setNumberObjects(int myNumberObjects) {
		this.myNumberObjects = myNumberObjects;
	}

	public int getTrailSize() {
		return myTrailSize;
	}

	public void setTrailSize(int myTrailSize) {
		this.myTrailSize = myTrailSize;
	}
	
	public String getFormation() {
		return myFormation;
	}

	public void setFormation(String myFormation) {
		this.myFormation = myFormation;
	}
	
	public Canvas getCanvas()
	{
		return myCanvas;
	}
	
	public void setCanvas(Canvas canvas)
	{
		myCanvas = canvas;
	}
	
	public int getMemberSize()
	{
		return myMemberSize;
	}
	
	public void setMemberSize(int memberSize)
	{
		myMemberSize = memberSize;
	}
	
	public Dimension getBounds()
	{
		return myBounds;
	}
	
	public void setMemberSize(Dimension bounds)
	{
		myBounds = bounds;
	}
}
