package spaceShooter.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import spaceShooter.Game;
import spaceShooter.Handler;

public class Asteroid extends GameObject
{
	
	public static final int[] TIER_SIZES = {20, 50, 80, 100};
	public static final double[] TIER_VELOCITIES = {2, 1.5, 1, .5};
	
	private int tier;
	private int angle;
	private double velocity;
	
	public Asteroid(Handler handler, double x, double y, int angle, int tier)
	{
		super(handler, x, y, null);
		setSize(new Rectangle((int)getX(), (int)getY(), TIER_SIZES[tier], TIER_SIZES[tier]));
		
		velocity = TIER_VELOCITIES[tier];
		this.tier = tier;
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
	}

	public void render(Graphics graphics)
	{
		graphics.setColor(Color.WHITE);
		graphics.fillOval((int)getX(), (int)getY(), TIER_SIZES[tier], TIER_SIZES[tier]);
	}
	
	public int getTier()
	{
		return tier;
	}
	
	public int getAngle()
	{
		return angle;
	}
	
	public double getVelocity()
	{
		return velocity;
	}
}
