package spaceShooter.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import spaceShooter.Handler;

public abstract class GameObject 
{
	private double x, y;
	private Handler handler;
	private Rectangle size;
	
	public GameObject(Handler handler, double x, double y, Rectangle size)
	{
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.size = size;
	}
	
	public abstract void tick();
	public abstract void render(Graphics graphics);

	public double getX() 
	{
		return x;
	}

	public void setX(double x) 
	{
		this.x = x;
	}

	public double getY() 
	{
		return y;
	}

	public void setY(double y) 
	{
		this.y = y;
	}

	public Handler getHandler() 
	{
		return handler;
	}

	public void setHandler(Handler handler) 
	{
		this.handler = handler;
	}

	public Rectangle getSize() 
	{
		return size;
	}

	public void setSize(Rectangle size) 
	{
		this.size = size;
	}
	
	public void updateHitBox()
	{
		size.setLocation((int)getX(), (int)getY());
	}
	
	
	public boolean isColliding(GameObject object)
	{
		double[][] corners = {{object.getSize().getMinX(), object.getSize().getMinY()},
							  {object.getSize().getMinX(), object.getSize().getMaxY()},
							  {object.getSize().getMaxX(), object.getSize().getMaxY()},
							  {object.getSize().getMaxX(), object.getSize().getMinY()}};
		
		for(int i = 0; i < corners.length; i++)
		{
			if(corners[i][0] > getSize().getMinX() && corners[i][0] < getSize().getMaxX())
			{
				if(corners[i][1] > getSize().getMinY() && corners[i][1] < getSize().getMaxY())
				{
					System.out.println("true");

					System.out.println(corners[i][0] + " " + corners[i][1]);
					
					System.out.println(getSize().getMinX() + " " + getSize().getMinY());
					System.out.println(getSize().getMinX() + " " + getSize().getMaxY());
					System.out.println(getSize().getMaxX() + " " + getSize().getMaxY());
					System.out.println(getSize().getMaxX() + " " + getSize().getMinY());
					
					return true;
				}
			}
		}
		
		return false;
	}
}
