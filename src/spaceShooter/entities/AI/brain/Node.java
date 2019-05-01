package spaceShooter.entities.AI.brain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import spaceShooter.Handler;
import spaceShooter.entities.GameObject;

public class Node extends GameObject
{

	private ArrayList<Connection> connections;
	
	private int id;
	private double inputVal = 0;
	private double output = 0;
	
	public Node(Handler handler, int id, int x, int y)
	{
		super(handler, x, y, null);
		
		this.id = id;
	}
	
	public void tick()
	{	
		if(inputVal >= 1)
		{
			output = 1;
		}
		else
		{
			output = 0;
		}
		
		resetInput();
	}
	
	public void render(Graphics graphics)
	{
		graphics.setColor(Color.WHITE);
		graphics.fillOval((int)getX(), (int)getY(), 5, 5);
		
		graphics.setColor(Color.GRAY);
		graphics.drawOval((int)getX(), (int)getY(), 5, 5);
	}
	
	public void resetInput()
	{
		inputVal = 0;
	}
	
	public double getOutput()
	{
		return output;
	}
	
	public void addInput(double val)
	{
		inputVal += val;
	}
	
	protected void setOutput(double output)
	{
		this.output = output;
	}
	
	public void addConnection(Node outputNode, double strength)
	{
		connections.add(new Connection(getHandler(), this, outputNode, strength));
	}
}
