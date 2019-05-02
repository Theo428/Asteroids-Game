package spaceShooter.entities.AI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

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
	
	private int[] nodes;
	private int[][] connections;
	
	private double[] strengths;
	
	private AsteroidManager asteroids;
	
	public AIPlayer(Handler handler, AsteroidManager asteroids) 
	{
		super(handler);
		
		generateRandomNetwork(6, 5);
		
		brain = new BrainManager(handler, 6, 5, nodes, connections, strengths);
		
		this.asteroids = asteroids;
	}
	
	public AIPlayer(AIPlayer parent, AsteroidManager asteroids) 
	{
		super(parent.getHandler());

		this.asteroids = asteroids;
		
		//inherite and mutate
		
		brain = parent.getBrain();
		brain.mutate();
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
		
		double[] inputVals = getInputs();
		
		brain.tick(inputVals);
	}
	
	public void render(Graphics graphics)
	{
		super.render(graphics);
		
		brain.render(graphics);
	}
	
	public BrainManager getBrain()
	{
		return brain;
	}
	
	private void generateRandomNetwork(int numOfInputs, int numOfOutputs)
	{
		int generatedItem = (int)(Math.random() * 2);
		
		if(generatedItem == 0)
		{
			generateNode(numOfInputs, numOfOutputs);
		}
		else 
		{
			generateConnection(numOfInputs, numOfOutputs);
		}
	}
	
	private void generateNode(int numOfInputs, int numOfOutputs)
	{
		nodes = new int[1];
		connections = new int[2][2];
		
		strengths = new double[2];
		
		nodes[0] = 1;
		
		int inputID = (int)(Math.random() * (numOfInputs + 1)) - 1;
		int outputID = (int)(Math.random() * (numOfInputs)) + numOfInputs;
		
		connections[0][0] = inputID;
		connections[0][1] = numOfInputs + numOfInputs;
		

		connections[1][0] = numOfInputs + numOfInputs;
		connections[1][1] = outputID;
		
		strengths[0] = (Math.random() * 2) - 1;
		strengths[1] = (Math.random() * 2) - 1;
	}
	
	private void generateConnection(int numOfInputs, int numOfOutputs)
	{
		nodes = new int[0];
		connections = new int[1][2];
		
		strengths = new double[1];

		int inputID = (int)(Math.random() * (numOfInputs + 1)) - 1;
		int outputID = (int)(Math.random() * (numOfInputs)) + numOfInputs;
		
		connections[0][0] = inputID;
		connections[0][1] = outputID;
		
		strengths[0] = (Math.random() * 2) - 1;
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
}
