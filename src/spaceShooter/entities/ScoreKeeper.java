package spaceShooter.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import spaceShooter.Handler;

public class ScoreKeeper extends GameObject
{
	private static final int TOP_MARGIN = 50;
	private static final int LEFT_MARGIN = 20;
	
	private int score = 0;
	
	public ScoreKeeper(Handler handler)
	{
		super(handler, LEFT_MARGIN, TOP_MARGIN, null);
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics graphics)
	{
		Font ScoreFont = new Font(Font.SANS_SERIF, Font.BOLD, 32);
		graphics.setFont(ScoreFont);
		
		graphics.setColor(new Color(200, 200, 200));
		graphics.drawString("Score: " + score, (int)getX(), (int)getY());
	}
	
	public void IncrementScore(int score)
	{
		this.score += score;
	}
	
	public int getScore()
	{
		return score;
	}
}
