package spaceShooter.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import spaceShooter.Game;
import spaceShooter.Handler;

public class AsteroidManager extends GameObject
{
	private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	
	private int asteroidLimit = 15;
	private static final double spawnRate = 0.01;
	
	public AsteroidManager(Handler handler)
	{
		super(handler, 0, 0, null);
	}
	
	public void tick()
	{
		
	}
	
	public void tick(Player player, ScoreKeeper score)
	{
		if(asteroids.size() < asteroidLimit)
		{
			if(Math.random() <= spawnRate)
			{
				int size = (int)(Math.random() * 4);
				int screenSide = (int)(Math.random() * 2);
				int x = -Asteroid.TIER_SIZES[size];
				int y = -Asteroid.TIER_SIZES[size];
				
				if(screenSide == 0)
				{
					y = (int)(Math.random() * Game.HEIGHT);
				}
				else if(screenSide == 1)
				{
					x = (int)(Math.random() * Game.HEIGHT);
				}
				
				int angle = (int)(Math.random() * 360);
				
				asteroids.add(new Asteroid(getHandler(), x, y, angle, size));
			}
		}
		
		ArrayList<Bullet> bullets = player.getBullets();
		
		for(int i = 0; i < asteroids.size(); i++)
		{
			asteroids.get(i).tick();
			
			if(asteroids.get(i).isColliding(player))
			{
				player.setDead(true);
			}
			
			for(int x = 0; x < bullets.size(); x++)
			{	
				if(asteroids.get(i).isColliding(bullets.get(x)))
				{
					score.IncrementScore(5);
					player.killBullet(x);
					if(asteroids.get(i).getTier() != 0)
					{
						asteroids.add(new Asteroid(getHandler(), asteroids.get(i).getX(), asteroids.get(i).getY(), asteroids.get(i).getAngle() - 45, asteroids.get(i).getTier() - 1));
						asteroids.add(new Asteroid(getHandler(), asteroids.get(i).getX(), asteroids.get(i).getY(), asteroids.get(i).getAngle() + 45, asteroids.get(i).getTier() - 1));
					}
					asteroids.remove(i);
				}
			}
		}
	}
	
	public void render(Graphics graphics)
	{
		for(int i = 0; i < asteroids.size(); i++)
		{
			asteroids.get(i).render(graphics);
		}
	}
}
