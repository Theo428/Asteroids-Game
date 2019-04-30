package spaceShooter.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import spaceShooter.Game;
import spaceShooter.Handler;

public class Player extends GameObject
{
	private double velocity = 0;
	private double angle = 0;
	
	private boolean dead = false;
	
	private double airResistance = .008;
	private static final double MAX_VELOCITY = 5;
	
	private static final Image shipImg = Toolkit.getDefaultToolkit().getImage("assets/textures/player/ship.png");
	private static final int IMAGE_HEIGHT = 154;
	private static final int IMAGE_WIDTH = 224;
	private static final double SCALE_FACTOR = .3;
	private AffineTransform transform;
	
	private static final double BULLET_DELAY =  1 * 60;
	private int bulletCounter = 0;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	
	public Player(Handler handler)
	{
		super(handler, Game.WIDTH/2 - ((IMAGE_WIDTH * SCALE_FACTOR) / 2), Game.HEIGHT / 2 - ((IMAGE_HEIGHT * SCALE_FACTOR) / 2), null);
		setSize(new Rectangle((int)getX(), (int)getY(), (int)(IMAGE_WIDTH * SCALE_FACTOR), (int)(IMAGE_HEIGHT * SCALE_FACTOR)));
		transform = new AffineTransform();
	}

	
	public void tick()
	{
		tick(0, false);
	}
	
	public void tick(double acceleration, boolean shoot)
	{
		updateHitBox();
		
		if(acceleration != 0 && velocity < MAX_VELOCITY)
		{
			velocity += acceleration;
		}		
		else if(velocity > MAX_VELOCITY)
		{
			velocity = MAX_VELOCITY;
		}
		else if(velocity > airResistance)
		{
			velocity -= airResistance;
		}
		else if(velocity < -airResistance)
		{
			velocity += airResistance;
		}
		else
		{
			velocity = 0;
		}
		
		setY(getY() - (velocity * Math.sin(Math.toRadians(angle))));
		setX(getX() + (velocity * Math.cos(Math.toRadians(angle))));
		
		if(getX() > Game.WIDTH)
		{
			setX(-IMAGE_WIDTH * SCALE_FACTOR);
		}
		else if(getX() < -IMAGE_WIDTH)
		{
			setX(Game.WIDTH);
		}
		
		if(getY() > Game.HEIGHT)
		{
			setY(-IMAGE_HEIGHT * SCALE_FACTOR);
		}
		else if(getY() < -IMAGE_HEIGHT)
		{
			setY(Game.HEIGHT);
		}
		
		if(shoot && bulletCounter > BULLET_DELAY)
		{
			bullets.add(new Bullet(getHandler(), (int)(getX() + ((IMAGE_WIDTH * SCALE_FACTOR) / 2)), (int)(getY() + ((IMAGE_HEIGHT * SCALE_FACTOR) / 2)), angle));
			bulletCounter = 0;
		}
		
		for(int i = 0; i < bullets.size(); i++)
		{
			bullets.get(i).tick();
			
			if(bullets.get(i).getLife() == Bullet.LIFESPAN)
			{
				bullets.remove(i);
			}
		}
		
		bulletCounter++;
	}
	
	
	public void render(Graphics graphics)
	{
		Graphics2D graphics2d = (Graphics2D)graphics;
		
		transform.setToTranslation(getX(), getY());
		transform.scale(SCALE_FACTOR, SCALE_FACTOR);
		transform.rotate(Math.toRadians(360 - angle), (IMAGE_WIDTH / 2), (IMAGE_HEIGHT / 2));
		
		graphics2d.drawImage(shipImg, transform, null);
		
		for(int i = 0; i < bullets.size(); i++)
		{
			bullets.get(i).render(graphics);
		}
	}
	
	public double getAngle()
	{
		return angle;
	}
	
	public void setAngle(double angle)
	{
		this.angle = angle;
	}
	
	public double getVelocity()
	{
		return velocity;
	}
	
	public ArrayList<Bullet> getBullets()
	{
		return bullets;
	}
	
	public void killBullet(int index)
	{
		bullets.remove(index);
	}
	
	public void setDead(boolean dead)
	{
		this.dead = dead;
	}
	
	public boolean isDead()
	{
		return dead;
	}
}
