package spaceShooter.entities.AI.brain;

import spaceShooter.Handler;

public class InputNode extends Node
{
	
	public InputNode(Handler handler, int x, int y, int id)
	{
		super(handler, x, y, id);
	}
	
	public void tick()
	{
		for(int i = 0; i < connections.size(); i++)
		{
			connections.get(i).tick();
		}
	}
	
	public void setValue(double value)
	{
		setOutput(value);
	}
}
