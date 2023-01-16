package main;

public class Game implements Runnable {
	private Thread gameThread;
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private static boolean stop = false;
	private final int FPS_SET = 20;
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);

		
		startGameLoop();
		
	}
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	@Override
	public void run() {
		
		double timePerFrame = 1_000_000_000.0/ FPS_SET;
		long now = System.nanoTime();
		long lastFrame = System.nanoTime();
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		while(true) {
			now = System.nanoTime();
			if(now - lastFrame >= timePerFrame) {
				gamePanel.repaint();
				lastFrame = now;
				frames++;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS:" + frames);
				frames = 0;
				gamePanel.moveDown();
			}
			
			if(stop) {
			gameThread.stop();
			}
		}
		
	}
	public static void setStop(boolean value) {
		stop = value;
	}
	
}
