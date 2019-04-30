package spaceShooter;

import java.awt.Color;
import java.awt.Graphics;

import spaceShooter.entities.AsteroidManager;
import spaceShooter.entities.Player;

public class AIHandler extends Handler
{
	private Player player;
	private AsteroidManager asteroids;
	
	public AIHandler()
	{
		player = new Player(this);
		asteroids = new AsteroidManager(this);
	}
	
	public void tick()
	{
		asteroids.tick(player);
		
		if(player.isDead())
		{
			asteroids = new AsteroidManager(this);
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
		
		asteroids.render(graphics);
	}
}
