package com.crategame.src;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Coin implements Entity{

	Position pos;
	int x,y;
	final String type = "coin";
	
	private String coin_image = "/images/coin.png";
	
	public Coin(int posX, int posY){
		pos = new Position(posX, posY);
	}
	
	public void update() {
		pos.moveDown();
		x=pos.getX();
		y=pos.getY();
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getCoinImage(),x,y,null);
		
	}

	public Image getCoinImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(coin_image));
		return i.getImage();
	}
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getType(){
		return type;
	}
	
}
