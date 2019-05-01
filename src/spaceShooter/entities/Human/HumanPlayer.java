package spaceShooter.entities.Human;

import java.awt.event.KeyEvent;

import spaceShooter.Handler;
import spaceShooter.KeyInput;
import spaceShooter.entities.Player;

public class HumanPlayer extends Player
{
	
	public HumanPlayer(Handler handler)
	{
		super(handler);
	}
	
	
	public void tick()
	{
		double acceleration = 0;
		double deltaAngle = 0;
		
		if(KeyInput.getKey(new Integer(KeyEvent.VK_W)))
		{
			acceleration += .04;
		}
		
		if(KeyInput.getKey(new Integer(KeyEvent.VK_S)))
		{
			acceleration -= .04;
		}
		
		if(KeyInput.getKey(new Integer(KeyEvent.VK_A)))
		{
			deltaAngle += 5;
		}
		
		if(KeyInput.getKey(new Integer(KeyEvent.VK_D)))
		{
			deltaAngle -= 5;
		}
		
		tick(acceleration, KeyInput.getKey(new Integer(KeyEvent.VK_SPACE)));
		setAngle(getAngle() + deltaAngle);
	}
}
