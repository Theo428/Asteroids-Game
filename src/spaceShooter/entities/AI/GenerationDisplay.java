package spaceShooter.entities.AI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import spaceShooter.Handler;
import spaceShooter.entities.GameObject;

public class GenerationDisplay extends GameObject
{
	private static final int TOP_MARGIN = 100;
	private static final int LEFT_MARGIN = 20;
	
	private int genNumber = 0;
	
	public GenerationDisplay(Handler handler, int genNumber)
	{
		super(handler, LEFT_MARGIN, TOP_MARGIN, null);
		
		this.genNumber = genNumber;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics graphics)
	{
		Font ScoreFont = new Font(Font.SANS_SERIF, Font.BOLD, 32);
		graphics.setFont(ScoreFont);
		
		graphics.setColor(new Color(200, 200, 200));
		graphics.drawString("Gen: " + genNumber, (int)getX(), (int)getY());
	}
	
	public int getGeneration()
	{
		return genNumber;
	}
}
