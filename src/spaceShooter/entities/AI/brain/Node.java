package spaceShooter.entities.AI.brain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import spaceShooter.Handler;
import spaceShooter.entities.GameObject;

public class Node extends GameObject
{

	protected ArrayList<Connection> connections;
	
	private int id;
	private double inputVal = 0;
	private double output = 0;
	
	public static final int RADIUS = 20;
	
	public Node(Handler handler, int x, int y, int id)
	{
		super(handler, x, y, null);
		
		connections = new ArrayList<Connection>();
		
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
		
		for(int i = 0; i < connections.size(); i++)
		{
			connections.get(i).tick();
		}
		
		resetInput();
	}
	
	public void render(Graphics graphics)
	{
		for(int i = 0; i < connections.size(); i++)
		{
			connections.get(i).render(graphics);
		}
		
		if(output < 1)
		{
			graphics.setColor(Color.RED);
		}
		else
		{
			graphics.setColor(Color.GREEN);
		}
		
		graphics.fillOval((int)getX(), (int)getY(), RADIUS, RADIUS);
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
	
	public int getID()
	{
		return id;
	}
	
	protected void setOutput(double output)
	{
		this.output = output;
	}
	
	public void addConnection(Node outputNode, double strength)
	{
		connections.add(new Connection(getHandler(), this, outputNode, strength));
	}
	
	public boolean isOn()
	{
		return getOutput() >= 1;
	}

}
