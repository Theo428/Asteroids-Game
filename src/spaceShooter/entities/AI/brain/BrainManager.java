package spaceShooter.entities.AI.brain;

import java.awt.Graphics;
import java.util.ArrayList;

import spaceShooter.Handler;
import spaceShooter.entities.GameObject;

public class BrainManager extends GameObject
{
	ArrayList<InputNode> inputs; 
	ArrayList<OutputNode> outputs;
	
	ArrayList<Node> nodes;
	
	
	public BrainManager(Handler handler, int numOfInputs, int numOfOutputs)
	{
		super(handler, 0, 0, null);
		
		nodes = new ArrayList<Node>();
		
		inputs = new ArrayList<InputNode>();
		
		for(int i = 0; i < numOfInputs; i++)
		{
			inputs.add(new InputNode(handler, 0, 0, 0));
		}
		
		outputs = new ArrayList<OutputNode>();
		
		for(int i = 0; i < numOfOutputs; i++)
		{
			outputs.add(new OutputNode(handler, 0, 0, 0));
		}
	}
	
	public void tick()
	{
		double[] inputVals = new double[inputs.size()];
		
		for(int i = 0; i < inputVals.length; i++)
		{
			inputVals[i] = 0;
		}
		
		tick(inputVals);
	}
	
	public void tick(double[] inputs)
	{
		
	}
	
	public void render(Graphics graphics)
	{
		
	}
	
	public boolean[] getOutputs()
	{
		boolean[] outputVals = new boolean[outputs.size()];
		
		return outputVals
	}
	
}
