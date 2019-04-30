package spaceShooter.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import spaceShooter.Game;
import spaceShooter.Handler;

public class Bullet extends GameObject
{
	private double angle = 0;
	private double velocity = 6;
	private int life = 0;
	public static final int LIFESPAN = 90;
	
	public Bullet(Handler handler, int x, int y, double angle)
	{
		super(handler, x, y, null);
		setSize(new Rectangle((int)getX(), (int)getY(), 4, 4));
		this.angle = angle;
	}

	public void tick() 
	{
		updateHitBox();
		
		setY(getY() - (velocity * Math.sin(Math.toRadians(angle))));
		setX(getX() + (velocity * Math.cos(Math.toRadians(angle))));

		if(getX() > Game.WIDTH)
		{
			setX(-getSize().getWidth());
		}
		else if(getX() < -getSize().getWidth())
		{
			setX(Game.WIDTH);
		}

		if(getY() > Game.HEIGHT)
		{
			setY(-getSize().getHeight());
		}
		else if(getY() < -getSize().getHeight())
		{
			setY(Game.HEIGHT);
		}
		
		life++;
	}

	public void render(Graphics graphics)
	{
		graphics.setColor(Color.BLUE);
		graphics.fillOval((int)getX(), (int)getY(), 4, 4);
	}
	
	public int getLife()
	{
		return life;
	}
}
