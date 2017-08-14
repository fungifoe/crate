package com.crategame.src;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends Canvas implements ActionListener{
	
	//Konstanter
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 5;
	public static final int HEIGTH = 10;
	public static final int SCALE = 32;
	public final String TITLE = "Crate Game";
	private String background = "/images/bkg.png";
	private int rows_completed = -5;
	private String curr_part = "menu";
	private int coins;

	Timer mainTimer;
	
	Player p;
	Controller c;
	Menu m;
	Scores s;
	Random r = new Random();
	Coins coin;
	
	//För timer fiende spawn
	long lastTime = System.nanoTime();
	double speed = 1.0; //TICKS FÖR FIENDER
	double ns = 1000000000 / speed;
	double delta = 0;
	int nextRow = 1;

	//Game loop och Skapa alla objekt här.
	public Game(){
		setFocusable(true);
		mainTimer = new Timer(10, this);
		mainTimer.start();
		c = new Controller();
		m = new Menu(this, s);
		s = new Scores(this);
		p = new Player(3,HEIGTH, this, c);
		coin = new Coins(this);
		addKeyListener(new KeyInput(p,m,s));
		coins = coin.loadCoins();
	}
	
	//Uppdaterar alla objekten
	@Override
	public void actionPerformed(ActionEvent ee) {
		if(curr_part == "game"){
			p.update();
			//Timer för fiender
			double ns = 1000000000 / speed;
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				if(nextRow==1){
					c.spawnEnemyRow();
					rowCompleted();
				}
				else{
					c.spawnSingleCoin();
				}
				c.update();
				delta--;
				nextRow = nextRow*(-1);
				}
		}
		repaint();
	}
	
	//Målar allt
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Arial", Font.PLAIN, 10));
		g2d.setColor(Color.BLACK);
		
		if(curr_part == "game"){
			g2d.drawImage(getBackgroundImage(), 0, 0, null);
			p.draw(g2d);
			c.draw(g2d);
			if(rows_completed > 0){
				g2d.drawString("ROWS: " + rows_completed, 100, 10);
			} else {
				g2d.drawString("START IN: " + rows_completed, 98, 10);
			}
			g2d.drawString("COINS: " + coins, 2, 10);
		}
		else if (curr_part == "menu"){
			m.draw(g2d);
			g2d.drawString("COINS: " + coins, 30, 100);
		} else if (curr_part == "scores"){
			s.draw(g2d);
		}
	}
	
	// Screen setup, init game
	public static void main(String args[]){
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGTH*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGTH*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGTH*SCALE));
		JFrame frame = new JFrame();
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

	public void rowCompleted(){
		rows_completed++;
		if(rows_completed > 1){
			speed += 0.02;
		}
	}
	
	public void startGame(){
		//Uppdaterar spawntid
		lastTime = System.nanoTime();
		speed = 1.0; //TICKS FÖR FIENDER
		ns = 1000000000 / speed;
		delta = 0;
		nextRow = 1;
		
		c.removeAll();
		p.setPosX(3);
		curr_part = "game";
	}
	
	public void endGame(){
		s.addScore(rows_completed);
		s.saveScores();
		coin.saveCoins();
		rows_completed = -5;
		c.removeAll();
		curr_part = "menu";
	}
	
	public void enterHighScore(){
		endGame();
		curr_part="scores";
	}
	
	public void exitHighScore(){
		curr_part="menu";
	}
	
	public Image getBackgroundImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(background));
		return i.getImage();
	}
	
	public String getCurrPart(){
		return curr_part;
	}
	
	public int getCoins(){
		return coins;
	}
	
	public void setCoins(int c){
		coins = c;
	}
}
