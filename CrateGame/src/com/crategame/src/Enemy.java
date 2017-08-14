package com.crategame.src;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy implements Entity{
	Position pos;
	int x,y;
	final String type = "enemy";
	
	private String enemyImage = "/images/enemy.png";
	
	public Enemy(int posX, int posY){
		pos = new Position(posX, posY);
	}
	
	public void update(){
		pos.moveDown();
		x=pos.getX();
		y=pos.getY();
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getEnemyImage(),x,y,null);	
	}
	
	public Image getEnemyImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(enemyImage));
		return i.getImage();
	}
	
	public int getY(){
		return y;
	}
	
	public int getX(){
		return x;
	}
	
	public String getType(){
		return type;
	}

}
