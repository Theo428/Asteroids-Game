package spaceShooter.entities.AI.brain;

import java.awt.Color;
import java.awt.Graphics;

import spaceShooter.Handler;
import spaceShooter.entities.GameObject;

public class Connection extends GameObject
{
	
	private Node inputNode;
	private Node outputNode;
	
	private double multiplier;
	
	public Connection(Handler handler, Node inputNode, Node outputNode, double multiplier)
	{
		super(handler, inputNode.getX(), inputNode.getY(), null);
		this.inputNode = inputNode;
		this.outputNode = outputNode;
		this.multiplier = multiplier;
	}
	
	public void tick()
	{
		outputNode.addInput(inputNode.getOutput() * multiplier);
		//System.out.println(outputNode.getID() + ": " + inputNode.getOutput() * multiplier);
	}
	
	public void render(Graphics graphics)
	{
		if(multiplier < 0)
		{
			graphics.setColor(new Color((int)(255 * Math.abs(multiplier)), 0, 0));
		}
		else
		{
			graphics.setColor(new Color(0, (int)(255 * Math.abs(multiplier)), 0));
		}
		
		graphics.drawLine((int)inputNode.getX() + Node.RADIUS/2, (int)inputNode.getY() + Node.RADIUS/2, (int)outputNode.getX() + Node.RADIUS/2, (int)outputNode.getY() + Node.RADIUS/2);
	}
}
