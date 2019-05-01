package spaceShooter.entities.AI.brain;

import spaceShooter.Handler;

public class OutputNode extends Node
{
	
	public OutputNode(Handler handler, int id, int x, int y)
	{
		super(handler, id, x, y);
	}
	
	public void tick()
	{
		
	}
	
	public boolean isOn()
	{
		return getOutput() >= 1;
	}
}
