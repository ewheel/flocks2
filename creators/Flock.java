package creators;
import java.util.*;
import java.awt.Dimension;
import java.awt.Point;
import constantsFiles.*;

public class Flock implements Constants
{
	private List<Mover> myMemberList;
	//private ListIterator myIterator;
	
	
	public Flock()
	{
		myMemberList = new ArrayList<Mover>();
	}
	
	public Flock(Flock f)
	{
		myMemberList = new ArrayList<Mover>(f.getMemberList());
	}
	
	/**
	 * Removes the first member of a flock if the flock is not empty
	 */
	public void removeMember()
	{
		if(!myMemberList.isEmpty())
			myMemberList.remove(0);
	}
	
	public void addMember(FlockMember member)
	{
		myMemberList.add(member);
	}
	
	public Point getAverageLocation(Mover mover)
	{
		Point averageLocation = new Point(0,0);
		List<Mover> adjacentMembers = this.getAdjacentMembers(mover, FLOCKLET_RADIUS);
		for(Mover m: adjacentMembers)
		{
			averageLocation.x += m.getCenter().getX();
			averageLocation.y += m.getCenter().getY();
		}
		averageLocation.x /= adjacentMembers.size();
		averageLocation.y /= adjacentMembers.size();
		return averageLocation;
	}
	
	public Point getAverageDirection(Mover mover)
	{
		Point averageDirection = new Point(0,0);
		List<Mover> adjacentMembers = this.getAdjacentMembers(mover, FLOCKLET_RADIUS);
		adjacentMembers.remove(mover);
		if(adjacentMembers.size() != 0)
		{
			for(Mover m: adjacentMembers)
			{
				averageDirection.x += m.getVelocity().getX();
				averageDirection.y += m.getVelocity().getY();
			}
			averageDirection.x /= adjacentMembers.size();
			averageDirection.y /= adjacentMembers.size();
		}
		
		return averageDirection;
	}
	
	//use java reflection for dynamic function calling
	public Point getPointAverage()
	{
		Point average = new Point();
		for(Mover m: myMemberList)
		{
			average.x += m.getVelocity().x;
			average.y += m.getVelocity().y;
		}
		average.x /= myMemberList.size();
		average.y /= myMemberList.size();
		return average;
	}
	
	public void addIndividual(Mover flocklet)
	{
		myMemberList.add(flocklet);
	}
	
	public void removeIndividual(Mover flocklet)
	{
		myMemberList.remove(flocklet);
	}
	
	public List<Mover> getMemberList()
	{
		return myMemberList;
	}
	
	public void setMemberList(List<Mover> memberList)
	{
		myMemberList = memberList;
	}
	
	public void clearMemberList()
	{
		myMemberList.clear();
	}
	
	public List<Mover> getAdjacentMembers(Mover flocklet, double radius)
	{
		List<Mover> adjacentMembers = new ArrayList<Mover>();
		for(Mover m: myMemberList)
		{
			if(m.getCenter().distance(flocklet.getCenter()) < radius)
			{
				adjacentMembers.add(m);
			}
		}
		return adjacentMembers;
	}
	
	public void computeNextTurn(Dimension size)
	{
		for(Mover m: myMemberList)
		{
			VelocityInfo info = new VelocityInfo();
			info.setFlock(new Flock(this));
			info.setSize(size);
			m.computeNewVelocity(info);
		}
	}
	
}
