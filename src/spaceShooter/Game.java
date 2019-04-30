package spaceShooter;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas
{
	public static final int WIDTH = 600;
	public static final int HEIGHT = 720;
	
	public static final String WINDOW_NAME = "Asteroids";
	
	private Handler handler;
	private boolean isAI;
	
	public Game(boolean isAI)
	{
		super();
		
		this.isAI = isAI;
		
		if(isAI)
		{
			handler = new AIHandler();
		}
		else
		{
			handler = new HumanHandler();
		}
		
		this.setFocusable(true);
		KeyInput keyInput = new KeyInput();
		MouseInput mouseInput = new MouseInput();
		
		this.addKeyListener(keyInput);
		this.addMouseListener(mouseInput);
		
	}

	public void tick() 
	{
		handler.tick();
	}

	public void render() 
	{
		BufferStrategy bfs = this.getBufferStrategy();
		if(bfs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = bfs.getDrawGraphics();
		
		handler.render(graphics);
		
		graphics.dispose();
		bfs.show();
	}
}
