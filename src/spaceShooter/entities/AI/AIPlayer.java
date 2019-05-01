package spaceShooter.entities.AI;

import java.awt.event.KeyEvent;

import spaceShooter.Handler;
import spaceShooter.KeyInput;
import spaceShooter.entities.Player;

public class AIPlayer extends Player
{

	public AIPlayer(Handler handler) 
	{
		super(handler);
	}
	
	public AIPlayer(AIPlayer parent) 
	{
		super(parent.getHandler());
		
		//inherite and mutate
	}
	
	public void tick()
	{
		boolean shoot = false;
		boolean forward = false;
		boolean backward = false;
		boolean left = false;
		boolean right = false;

		
		double acceleration = 0;
		double deltaAngle = 0;
		
		if(forward)
		{
			acceleration += .04;
		}
		
		if(backward)
		{
			acceleration -= .04;
		}
		
		if(left)
		{
			deltaAngle += 5;
		}
		
		if(right)
		{
			deltaAngle -= 5;
		}
		
		tick(acceleration, shoot);
		setAngle(getAngle() + deltaAngle);
	}
	
	public void mutate()
	{
		
	}
}
