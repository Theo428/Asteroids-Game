package spaceShooter.entities.AI;

import java.awt.Graphics;

import spaceShooter.Handler;
import spaceShooter.entities.AsteroidManager;
import spaceShooter.entities.GameObject;
import spaceShooter.entities.Player;
import spaceShooter.entities.ScoreKeeper;

public class AIGame extends GameObject
{

	private AIPlayer player;
	private AsteroidManager asteroids;
	private ScoreKeeper score;
	private GenerationDisplay gen;
	
	private int tickCount = 0;
	
	public AIGame(Handler handler)
	{
		super(handler, 0, 0, null);
		asteroids = new AsteroidManager(handler);
		player = new AIPlayer(handler, asteroids);
		score = new ScoreKeeper(handler);
		gen = new GenerationDisplay(handler, 0);
	}
	
	public AIGame(AIGame parent)
	{
		super(parent.getHandler(), 0, 0, null);
		
		asteroids = new AsteroidManager(parent.getHandler());
		player = new AIPlayer(parent.getPlayer(), asteroids);
		score = new ScoreKeeper(parent.getHandler());
		gen = new GenerationDisplay(parent.getHandler(), parent.getGerneration().getGeneration() + 1);
	}
	
	public void tick()
	{
		player.tick();
		asteroids.tick(player, score);
		score.tick();
		gen.tick();
		
		if(tickCount == 30)
		{
			score.IncrementScore(1);
			tickCount = 0;
		}
		
		tickCount++;
	}

	public void render(Graphics graphics) 
	{
		render(graphics, false);
	}
	
	public void render(Graphics graphics, boolean draw)
	{
		if(draw)
		{
			player.render(graphics);
			asteroids.render(graphics);
			player.render(graphics);
			score.render(graphics);
			gen.render(graphics);
		}
	}
	
	public boolean isDead()
	{
		return player.isDead();
	}
	
	public int getScore()
	{
		return score.getScore();
	}
	
	public AIPlayer getPlayer()
	{
		return player;
	}
	
	public GenerationDisplay getGerneration()
	{
		return gen;
	}
}
