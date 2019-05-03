package spaceShooter.entities.AI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import spaceShooter.Handler;
import spaceShooter.KeyInput;
import spaceShooter.entities.Asteroid;
import spaceShooter.entities.AsteroidManager;
import spaceShooter.entities.Player;
import spaceShooter.entities.AI.brain.BrainManager;

public class AIPlayer extends Player
{
	
	private BrainManager brain;
	
	private boolean[] outputs;
	private double[] inputVals;
	
	private int[] nodes = {};
	private int[][] connections = {};
	
	private double[] strengths = {};
	
	private AsteroidManager asteroids;
	
	public static final int numOfInputs = 6;
	public static final int numOfOutputs = 6;
	
	public AIPlayer(Handler handler, AsteroidManager asteroids) 
	{
		super(handler);
		
		brain = new BrainManager(handler, numOfInputs, numOfOutputs, nodes, connections, strengths);
		
		this.asteroids = asteroids;
	}
	
	public AIPlayer(AIPlayer parent, AsteroidManager asteroids, boolean mutate) 
	{
		super(parent.getHandler());

		this.asteroids = asteroids;
		
		//inherite and mutate
	}
	
	public void tick()
	{
		outputs = brain.getOutputs();
		
		boolean shoot = outputs[0];
		boolean forward = outputs[1];
		boolean backward = outputs[2];
		boolean left = outputs[3];
		boolean right = outputs[4];

		
		double acceleration = 0;
		double deltaAngle = 0;
		
		if(forward)
		{
			acceleration += .04;
		}
		
		if(backward)
		{
			acceleration -= .04;
		}
		
		if(left)
		{
			deltaAngle += 5;
		}
		
		if(right)
		{
			deltaAngle -= 5;
		}
		
		tick(acceleration, shoot);
		setAngle(getAngle() + deltaAngle);
		
		inputVals = getInputs();
		
		brain.tick(inputVals);
	}
	
	public void render(Graphics graphics)
	{
		super.render(graphics);
		
		brain.render(graphics);
	}

	
	private double[] getInputs()
	{

		Asteroid closestAsteroid = asteroids.getClosestAsteroid(getX(), getY());
		
		double asteroidDistance;
		double asteroidVelocity;
		double asteroidTier;
		double asteroidAngle;
		
		if(closestAsteroid != null)
		{
			asteroidDistance = Math.sqrt(Math.pow(closestAsteroid.getX() - getX(), 2) + Math.pow(closestAsteroid.getY() - getY(), 2));
			asteroidVelocity = closestAsteroid.getVelocity();
			asteroidTier = closestAsteroid.getTier();
		
			asteroidAngle = Math.toDegrees(Math.atan((closestAsteroid.getY() - getY()) / (closestAsteroid.getX() - getX())));
			
			if((closestAsteroid.getX() - getX()) < 0)
			{
				asteroidAngle += 180;
			}
			else if((closestAsteroid.getY() - getY()) < 0)
			{
				asteroidAngle += 360;
			}
		}
		else
		{
			asteroidDistance = 0;
			asteroidVelocity = 0;
			asteroidTier = 0;
			asteroidAngle = 0;
		}
		
		double velocity = getVelocity();
		double angle = getAngle();
		
		
				
		double[] inputs = {asteroidDistance, asteroidVelocity, asteroidTier, asteroidAngle, velocity, angle};
		
		return inputs;
	}

	public int[] getNodes() 
	{
		return nodes;
	}

	public void setNodes(int[] nodes) 
	{
		this.nodes = nodes;
	}

	public int[][] getConnections() 
	{
		return connections;
	}

	public void setConnections(int[][] connections) 
	{
		this.connections = connections;
	}

	public double[] getStrengths() 
	{
		return strengths;
	}

	public void setStrengths(double[] strengths) 
	{
		this.strengths = strengths;
	}
	
	public BrainManager getBrain()
	{
		return brain;
	}
}
