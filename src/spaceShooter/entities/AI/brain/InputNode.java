package spaceShooter.entities.AI.brain;

import spaceShooter.Handler;

public class InputNode extends Node
{
	
	public InputNode(Handler handler, int id, int x, int y)
	{
		super(handler, id, x, y);
	}
	
	public void tick(int value)
	{
		setOutput(value);
	}
}
