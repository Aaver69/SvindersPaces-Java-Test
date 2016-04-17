package com.masson.axel.game_screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.masson.axel.display.display;

public class Player implements KeyListener {
	
	private final double speed = 5.0d ;
	private BufferedImage pSprite;
	private Rectangle rect; 
	private double xPosition, yPosition;
	private int width, height;
	
	private boolean left = false, right = false;
	
	
	public Player(double xPosition, double yPosition, int width, int height )
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.width = width;
		this.height = height;
		
		rect = new Rectangle((int)xPosition, (int)yPosition, width, height);
		
		try{
			URL url = this.getClass().getResource("/com/masson/axel/Images/Player.png");
			pSprite = ImageIO.read(url);
		}catch(IOException e){}
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(pSprite, (int)xPosition, (int)yPosition, width, height, null);
	}
	
	public void update(double delta)
	{
		if(right && !left && xPosition < display.WIDTH - width)
		{
			xPosition += speed*delta;
			rect.x = (int) xPosition;
		}
		else if(!right && left && xPosition > 10)
		{
			xPosition -= speed*delta;
			rect.x = (int) xPosition;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
		{	
			right = true;
		}
		else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
		{	
			left = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
		{	
			right = false;
		}
		else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
		{	
			left = false;
		}

	}
	
	

}
