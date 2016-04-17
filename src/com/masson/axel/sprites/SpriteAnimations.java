package com.masson.axel.sprites;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.masson.axel.timer.Timer;

import java.awt.image.BufferedImage;
public class SpriteAnimations {
	
	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private byte currentSprite;

	private boolean loop = false;
	private boolean play = false;
	private boolean destroyAfterAnim = false;
	
	private Timer timer;
	
	private int animationSpeed = 0;
	private double xPosition = 0;
	private double yPosition = 0;
	
	
	public SpriteAnimations(double xPosition, double yPosition, int animationSpeed)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.animationSpeed = animationSpeed;
		
		timer = new Timer();
	}
	
	public void draw(Graphics2D g)
	{	if (isSpriteAnimDestroyed())
		return;
	
	g.drawImage(sprites.get(currentSprite),(int)getxPosition() , (int)getyPosition(), null);
	}
	public void update(double delta)
	{
		if (isSpriteAnimDestroyed())
			return;
		
		if (loop && !play)
			LoopAnimation();
		
		if(play && !loop)
			PlayAnimation();
			
		
	}
	
	public void AddSprite(BufferedImage spriteMap, int xPosition, int yPosition, int height, int width)
	{
		sprites.add(spriteMap.getSubimage(xPosition, yPosition, width, height));
	}
	
	public void PlayerAnimations(boolean play, boolean destroyAfterAnim)
	{
		this.play = play;
		this.setDestroyAfterAnim(destroyAfterAnim);	
	}
	
	public void StopAnimation()
	{
		loop = false;
		play = false;
	}
	public void ResetAnimation()
	{
		loop = false;
		play = false;
		currentSprite = 0;
	}
	
	public void PlayAnimation()
	{
		if (timer.TimerEvent(animationSpeed) && currentSprite != sprites.size()-1 && !isDestroyAfterAnim())
		{
			play = false;
			currentSprite = 0;
			
		}
		else if(timer.TimerEvent(animationSpeed) && currentSprite == sprites.size()-1 && isDestroyAfterAnim())
		{	
			sprites = null;
		}
		else if (timer.TimerEvent(animationSpeed) && currentSprite != sprites.size()-1)
		{	
			currentSprite++;
		}
	}
	
	
	public byte getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(byte currentSprite) {
		this.currentSprite = currentSprite;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public void LoopAnimation()
	{
		if(timer.TimerEvent(animationSpeed) && currentSprite == sprites.size()-1)
		{	//Va reseter la position du sprite
			currentSprite = 0;
			timer.ResetTimer();
		}
		else if (timer.isTimerReady(animationSpeed) && currentSprite != sprites.size()-1)
		{	//Va changer la vitesse du sprite
			currentSprite++;
		}
	 
	}
	
	public boolean isSpriteAnimDestroyed()
	{
		if(sprites == null)
		{
			return true;
		}
		
		return false;
		
	}

	public double getxPosition() {
		return xPosition;
	}

	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}

	public double getyPosition() {
		return yPosition;
	}

	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}

	public boolean isDestroyAfterAnim() {
		return destroyAfterAnim;
	}

	public void setDestroyAfterAnim(boolean destroyAfterAnim) {
		this.destroyAfterAnim = destroyAfterAnim;
	}
	
	
	

}
