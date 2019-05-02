package spaceShooter.entities.AI.brain;

import java.awt.Color;
import java.awt.Graphics;

import spaceShooter.Handler;

public class BiasNode extends InputNode
{
	
	public BiasNode(Handler handler, int x, int y)
	{
		super(handler, x, y, -1);
	}
	
	public void render(Graphics graphics)
	{
		for(int i = 0; i < connections.size(); i++)
		{
			connections.get(i).render(graphics);
		}
		
		graphics.setColor(Color.ORANGE);
		
		graphics.fillOval((int)getX(), (int)getY(), RADIUS, RADIUS);
	}
	
	public double getOutput()
	{
		return 1;
	}
}
