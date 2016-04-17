package com.masson.axel.display;

import java.awt.BorderLayout;

import javax.swing.JApplet;

public class display_applet extends JApplet{
	
	private static final long serialVersionID = 1;
	
	
	// Un applet va permettre d'afficher le nombre de frame par secondes, ainsi que de pouvoir être vu depuis un site.
	
	private display Display = new display();
	
	public void init(){
		
		setLayout(new BorderLayout());
		add(Display);
		
		
	}
	
	public void Start(){
		
		Display.Start();
	}
	
	public void Stop(){
		
		Display.Stop();
	}
	

}
