package spaceShooter;

public class Main {

	public static void main(String[] args)
	{
		Game[] games = new Game[2];
		
		games[0] = new Game(false);
		games[1] = new Game(true);
		

		new Window(Game.WIDTH * games.length, Game.HEIGHT, Game.WINDOW_NAME, games);
		
		long lastTime = System.nanoTime();
		double TickPerSecond = 60;
		double NanosecondPerTick = 1000000000 / TickPerSecond;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		
		while(true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime)/ NanosecondPerTick;
			lastTime = now;
			while(delta >= 1)
			{
				for(int i = 0; i < games.length; i++)
				{
					games[i].tick();
				}
				delta--;
			}
			
			for(int i = 0; i < games.length; i++)
			{
				games[i].render();
			}
			
			frames++;
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
			
		}

	}

}
