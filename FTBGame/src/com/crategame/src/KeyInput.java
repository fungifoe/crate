package com.crategame.src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	Player p;
	Menu m;
	Scores s;
	
	public KeyInput(Player p, Menu m, Scores s){
		this.p=p;
		this.m=m;
		this.s=s;
	}
	
	public void keyPressed(KeyEvent e){
		p.keyPressed(e);
		m.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		s.keyReleased(e);
	}

}
