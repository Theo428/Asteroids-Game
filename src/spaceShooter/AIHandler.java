package spaceShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import spaceShooter.entities.AsteroidManager;
import spaceShooter.entities.Player;
import spaceShooter.entities.AI.AIGame;

public class AIHandler extends Handler
{
	private ArrayList<AIGame> AIs; 
	private AIGame parent;
	
	int bestIndex = 0;
	
	private static final int AI_AMOUNT = 50;
	
	public AIHandler()
	{
		start();
	}
	
	public void tick()
	{
		boolean allDead = true;
		for(int i = 0; i < AIs.size(); i++)
		{
			if(!AIs.get(i).isDead())
			{
				AIs.get(i).tick();
				allDead = false;
			}
		}
		
		if(allDead)
		{
			parent = selectParent();
			restart(parent);
			allDead = false;
		}
	}
	
	public void render(Graphics graphics)
	{
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, Game.WIDTH, 3);
		graphics.fillRect(0, Game.HEIGHT - 3, Game.WIDTH, 3);
		graphics.fillRect(0, 0, 3, Game.HEIGHT);
		graphics.fillRect(Game.WIDTH - 3, 0, 3, Game.HEIGHT);


		for(int i = 0; i < AIs.size(); i++)
		{
			if(!AIs.get(i).isDead())
			{
				AIs.get(i).render(graphics, false);
			}
		}
		
		if(!AIs.get(bestIndex).isDead())
		{
			AIs.get(bestIndex).render(graphics, true);
		}
		else
		{
			for(int i = 1; i < AIs.size(); i++)
			{
				if(AIs.get(bestIndex).isDead())
				{
					bestIndex = i;
				}
				else if(AIs.get(i).getScore() > AIs.get(bestIndex).getScore() && !AIs.get(i).isDead())
				{
					bestIndex = i;
				}
			}
		}
	}
	
	private void start()
	{
		AIs = new ArrayList<AIGame>();
		
		for(int i = 0; i < AI_AMOUNT; i++)
		{
			AIs.add(new AIGame(this));
		}
	}
	
	private void restart(AIGame parent)
	{
		AIs = new ArrayList<AIGame>();
		
		for(int i = 0; i < AI_AMOUNT; i++)
		{
			AIs.add(new AIGame(parent, true));
		}
		
		AIs.add(new AIGame(parent, false));
		
		bestIndex = 0;
	}
	
	public AIGame selectParent()
	{
		int fitnessSum = 0;
		
		for(int i = 0; i < AIs.size(); i++)
		{
			fitnessSum += AIs.get(i).getScore();
		}
		
		int selection = (int)(Math.random() * fitnessSum);
		
		for(int i = 0; i < AIs.size(); i++)
		{
			selection -= AIs.get(i).getScore();
			
			if(selection <= 0)
			{
				return AIs.get(i);
			}
		}
		
		return null;
	}
}
