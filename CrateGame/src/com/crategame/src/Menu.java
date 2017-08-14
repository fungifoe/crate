package com.crategame.src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Menu {
	
	private String menu = "/images/menu_bkg.png";
	private String start_button = "/images/menu_start.png";
	private String others_button = "/images/menu_others.png";
	private String select = "/images/menu_select.png";
	int select_pos_y = (Game.HEIGTH*Game.SCALE)/2;
	int start_pos_y = (Game.HEIGTH*Game.SCALE)/2;
	int others_pos_y = ((Game.HEIGTH*Game.SCALE)/2)+32;
	
	Game g;
	Scores s;
	
	public Menu(Game g, Scores s){
		this.g=g;
		this.s=s;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getMenuBackgroundImage(), 0,0,null);
		g2d.drawImage(getSelectImage(), (Game.WIDTH*Game.SCALE)*2/7-20, select_pos_y, null);
		g2d.drawImage(getStartButtonImage(), (Game.WIDTH*Game.SCALE)*2/7,(Game.HEIGTH*Game.SCALE)/2,null);
		g2d.drawImage(getOthersButtonImage(), (Game.WIDTH*Game.SCALE)*2/7, ((Game.HEIGTH*Game.SCALE)/2)+32, null);
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if((key==KeyEvent.VK_DOWN || key==KeyEvent.VK_UP) && select_pos_y==start_pos_y){
			select_pos_y=others_pos_y;
		} else if ((key==KeyEvent.VK_DOWN || key==KeyEvent.VK_UP) && select_pos_y==others_pos_y){
			select_pos_y=start_pos_y;
		} else if (key==KeyEvent.VK_ENTER && select_pos_y==start_pos_y){
			g.startGame();
		} else if (key==KeyEvent.VK_ENTER && select_pos_y==others_pos_y){
			g.enterHighScore();
		}
	}
	
	public Image getMenuBackgroundImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(menu));
		return i.getImage();
	}
	public Image getStartButtonImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(start_button));
		return i.getImage();
	}
	
	public Image getOthersButtonImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(others_button));
		return i.getImage();
	}
	
	public Image getSelectImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(select));
		return i.getImage();
	}

}
