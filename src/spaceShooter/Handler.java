package spaceShooter;

import java.awt.Color;
import java.awt.Graphics;

import spaceShooter.entities.Asteroid;
import spaceShooter.entities.AsteroidManager;
import spaceShooter.entities.HumanPlayer;
import spaceShooter.entities.Player;

public class Handler
{
	private Player player;
	private AsteroidManager asteroids;
	
	public Handler()
	{
		player = new HumanPlayer(this);
		asteroids = new AsteroidManager(this);
	}
	
	public void tick()
	{
		player.tick();
		asteroids.tick(player);
		
		if(player.isDead())
		{
			player = new HumanPlayer(this);
			asteroids = new AsteroidManager(this);
		}
	}
	
	public void render(Graphics graphics)
	{
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		player.render(graphics);
		asteroids.render(graphics);
	}
}
