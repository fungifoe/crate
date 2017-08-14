package com.crategame.src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Player {
	
	Position pos;
	Game g;
	Controller c;
	int x, y;
	
	private String playerImage = "/images/player.png";
	
	private LinkedList<Entity> entities = Controller.getEntities();
	
	public Player(int posX, int posY, Game g, Controller c){
		pos = new Position(posX, posY);
		this.g=g;
		this.c=c;
	}
	
	public void update(){
		x=pos.getX();
		y=pos.getY();
		checkCollision();
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getPlayerImage(),x,y,null);	
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT){
			pos.moveLeft();
		}
		else if (key == KeyEvent.VK_RIGHT){
			pos.moveRight();
		}
		
	}
	
	public void checkCollision(){
		for(int i = 0; i < entities.size(); i++){
			if(x==entities.get(i).getX() && y==entities.get(i).getY() && entities.get(i).getType()=="enemy"){
				g.endGame();
			}else if(x==entities.get(i).getX() && y==entities.get(i).getY() && entities.get(i).getType()=="coin"){
				c.removeEntity(entities.get(i));
				g.setCoins(g.getCoins()+1);
			}
		}
	}
	
	public void setPosX(int x){
		this.x=x;
	}

	public Image getPlayerImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(playerImage));
		return i.getImage();
	}
}
