package com.crategame.src;

import java.awt.Graphics2D;

public interface Entity {

	public void update();
	public void draw(Graphics2D g2d);
	
	public int getX();
	public int getY();
	public String getType();
	
}
