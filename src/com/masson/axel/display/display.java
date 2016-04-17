package com.masson.axel.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.masson.axel.state.StateMachine;

public class display extends Canvas implements Runnable
{
	private static final long serialVersionID = 1;
	
	public static void main(String[] args)
	{
		display Display = new display();
		JFrame jframe = new JFrame();
		// A oartur de là, on ajoute les propriétés de la fenêtre
		jframe.add(Display);
		jframe.pack();
		jframe.setTitle("SvinderPaces");
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		jframe.setResizable(false);
		Display.Start();
		
		
	}
	
	private boolean running = false;
	private Thread thread;
	
	public synchronized void Start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void Stop()
	{
		if(!running)
			return;
		running = false;
		thread = new Thread(this);
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int WIDTH = 800, HEIGHT = 600; 
	public int FPS;
	
	
	public static StateMachine state;
	
		
	
	
	public display()
	{
		//Définit la taille de la fenêtre
		this.setSize(WIDTH, HEIGHT);
		//Permet de ne pas cliquer sur la fenêtre à chaque
		//démarage du programme
		this.setFocusable(true);
		
		state = new StateMachine(this);
		state.setState((byte )0);
	}
	
	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		int frames = 0;

		this.createBufferStrategy(3);
		BufferStrategy bs = this.getBufferStrategy();
		while (running) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_TIME);

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				FPS = frames;
				frames = 0;
				System.out.println(FPS);
			}

			draw(bs);
			update(delta);

			try {
				Thread.sleep(((lastLoopTime - System.nanoTime()) + OPTIMAL_TIME) / 1000000);
			} catch (Exception e) {
			}
			;
		}
	}
	
	public void draw(BufferStrategy bs) {
		do {
			do {
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH + 50, HEIGHT + 50);
				
				state.draw(g);

				g.dispose();
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}

	public void update(double delta) {
		state.update(delta);
	}

	
	
}
