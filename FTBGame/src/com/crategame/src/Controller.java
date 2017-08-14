package com.crategame.src;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

	static LinkedList<Entity> e = new LinkedList<Entity>();
	
	Entity TempEntity;
	Random r = new Random();
	
	public void draw(Graphics2D g2d){
		for(int i = 0; i < e.size(); i++){
			TempEntity = e.get(i);
			TempEntity.draw(g2d);
		}
	}
	
	public void update(){
		for(int i = 0; i < e.size(); i++){
			TempEntity = e.get(i);
			TempEntity.update();
			if(TempEntity.getY()>Game.HEIGTH*Game.SCALE+64){
				removeEntity(TempEntity);
			}
		}
	}
	
	public void addEntity(Entity entity){
		e.add(entity);
	}
	
	public void removeEntity(Entity entity){
		e.remove(entity);
	}
	
	public void spawnEnemyRow(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <=5;i++){
			list.add(i);
		}
		list.remove(r.nextInt(5));
		for(int i = 0; i<4;i++){
			addEntity(new Enemy(list.get(i),0));
		}
	}
	
	public void spawnSingleCoin(){
		addEntity(new Coin(r.nextInt(4)+1, 0));
	}
	
	public void removeAll(){
		e.clear();
	}
	
	public static LinkedList<Entity> getEntities(){
		return e;
	}
	

}
