package spaceShooter;

import java.awt.Color;
import java.awt.Graphics;

import spaceShooter.entities.AsteroidManager;
import spaceShooter.entities.Player;
import spaceShooter.entities.ScoreKeeper;
import spaceShooter.entities.Human.HumanPlayer;

public class HumanHandler extends Handler
{
	private Player player;
	private AsteroidManager asteroids;
	private ScoreKeeper score;
	
	private int tickCount = 0;
	
	public HumanHandler()
	{
		player = new HumanPlayer(this);
		asteroids = new AsteroidManager(this);
		score = new ScoreKeeper(this);
	}
	
	public void tick()
	{
		player.tick();
		asteroids.tick(player, score);
		score.tick();
		
		if(tickCount == 30)
		{
			score.IncrementScore(1);
			tickCount = 0;
		}
		
		if(player.isDead())
		{
			player = new HumanPlayer(this);
			asteroids = new AsteroidManager(this);
			score = new ScoreKeeper(this);
			
			tickCount = 0;
		}
		
		tickCount++;
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
		
		player.render(graphics);
		asteroids.render(graphics);
		score.render(graphics);
	}
	
	public void asteroidShot()
	{
		score.IncrementScore(10);
	}
}
