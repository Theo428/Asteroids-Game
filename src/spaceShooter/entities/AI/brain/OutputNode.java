package spaceShooter.entities.AI.brain;

import spaceShooter.Handler;

public class OutputNode extends Node
{
	
	public OutputNode(Handler handler, int x, int y, int id)
	{
		super(handler, x, y, id);
	}
	
	public boolean isOn()
	{
		return getOutput() >= 1;
	}
}
