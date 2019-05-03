package spaceShooter.entities.AI.brain;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import spaceShooter.Game;
import spaceShooter.Handler;
import spaceShooter.entities.GameObject;

public class BrainManager extends GameObject
{
	private ArrayList<ArrayList<Node>> nodes;
	
	public static final double TOP_PERCENTAGE = .025;
	public static final double LEFT_PERCENTAGE = .35;
	
	public static final double BRAIN_WIDTH_PERCENTAGE = .4;
	public static final double BRAIN_HEIGHT_PERCENTAGE = .3;
	
	
	public BrainManager(Handler handler, int numOfInputs, int numOfOutputs, int[] nodesPerColumn, int[][] connections, double[] connectionStrengths)
	{
		super(handler, (int)(LEFT_PERCENTAGE * Game.WIDTH), (int)(TOP_PERCENTAGE * Game.HEIGHT), null);
		setSize(new Rectangle((int)getX(), (int)getY(), (int)(BRAIN_WIDTH_PERCENTAGE * Game.WIDTH), (int)(BRAIN_HEIGHT_PERCENTAGE * Game.HEIGHT)));
		
		nodes = new ArrayList<ArrayList<Node>>();
		
		int nodeID = -numOfInputs - numOfOutputs;
		
		//add inputs to node array
		nodes.add(new ArrayList<Node>());
		
		for(int i = 0; i < numOfInputs; i++)
		{
			nodes.get(0).add(new InputNode(handler, (int)getX(), (int)(getY() + ((getSize().getHeight() / numOfInputs)/2) + (i * (getSize().getHeight() / numOfInputs - 1))), nodeID));
			nodeID++;
		}
		
		nodes.get(0).add(new BiasNode(handler, (int)getX() + 20, (int)getY() + (int)getSize().getHeight() + 10));
		
		nodeID++;

		//add outputs to node array
		nodes.add(new ArrayList<Node>());
		
		for(int i = 0; i < numOfOutputs; i++)
		{
			nodes.get(1).add(new OutputNode(handler, (int)(getX() + getSize().getWidth()), (int)(getY() + ((getSize().getHeight() / numOfOutputs)/2) + (i * (getSize().getHeight() / numOfOutputs))), nodeID));
			nodeID++;
		}
		
		//add all other nodes
		for(int i = 0; i < nodesPerColumn.length; i++)
		{
			nodes.add(nodes.size() - 2, new ArrayList<Node>());
			
			for(int j = 0; j < nodesPerColumn[i]; j++)
			{
				nodes.get(i).add(new Node(handler, (int)(getX() + ((i + 1) * (getSize().getHeight() / (nodesPerColumn.length + 1)))), (int)(getY() + ((getSize().getHeight() / nodesPerColumn[i])/2) +  (j * (getSize().getHeight() / nodesPerColumn[i]))), nodeID));
				nodeID++;
			}
		}
		
		//add connections
		for(int i = 0; i < connections.length; i++)
		{
			getNodeByID(connections[i][0]).addConnection(getNodeByID(connections[i][1]), connectionStrengths[i]);
		}
		
	}
	
	public void tick()
	{
		double[] inputVals = new double[nodes.get(0).size()];
		
		for(int i = 0; i < inputVals.length; i++)
		{
			inputVals[i] = 0;
		}
		
		tick(inputVals);
	}
	
	public void tick(double[] inputVals)
	{
		//set input values
		for(int i = 0; i < nodes.get(0).size() - 1; i++)
		{
			nodes.get(0).get(i).setOutput(inputVals[i]);
		}

		//tick nodes in order
		for(int i = 0; i < nodes.size(); i++)
		{
			
			for(int j = 0; j < nodes.get(i).size(); j++)
			{
				nodes.get(i).get(j).tick();
			}
		}
		
	}
	
	public void render(Graphics graphics)
	{
		//render nodes
		for(int i = 0; i < nodes.size(); i++)
		{
			
			for(int j = 0; j < nodes.get(i).size(); j++)
			{
				nodes.get(i).get(j).render(graphics);
			}
		}
	}
	
	public boolean[] getOutputs()
	{
		boolean[] outputVals = new boolean[nodes.get(nodes.size() - 1).size()];
		
		for(int i = 0; i < nodes.get(nodes.size() - 1).size(); i++)
		{
			outputVals[i] = nodes.get(nodes.size() - 1).get(i).isOn();
		}
		
		return outputVals;
	}
	
	public Node getNodeByID(int id)
	{
		for(int i = 0; i < nodes.size(); i++)
		{
			for(int x = 0; x < nodes.get(i).size(); x++)
			{
				if(nodes.get(i).get(x).getID() == id)
				{
					return nodes.get(i).get(x);
				}
			}
		}
		
		return null;
	}
	
}
