package com.crategame.src;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Scores {

	private String highscore_bkg = "/images/highscore_bkg.png";
	private LinkedList<Integer> highScores = new LinkedList<Integer>();
	
	Random r = new Random();
	Game g;
	
	public Scores(Game g){
		this.g=g;
		for(int i = 0; i<10; i++){
			highScores.add(0);
		}
		readScores();
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getHighScoreBackgroundImage(), 0, 0, null);
		
		//PRINT HIGHSCORES
		int scaler = 25;
		g2d.setFont(new Font("Sans", Font.BOLD, 15));
		
		for(int i = 0; i<10; i++){
			g2d.drawString(i+1+":",(Game.WIDTH/5*Game.SCALE),(Game.HEIGTH/4*Game.SCALE)+5+scaler*i);
			g2d.drawString("" + highScores.get(9-i), (Game.WIDTH/5*Game.SCALE)+80,(Game.HEIGTH/4*Game.SCALE)+5+scaler*i);
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_ENTER && g.getCurrPart() == "scores"){
			g.exitHighScore();
		}
	}
	
	public void addScore(int score){
		highScores.add(score);
		Collections.sort(highScores);
		highScores.removeFirst();
		saveScores();
	}
	
	
	public void saveScores(){
		Collections.sort(highScores);
		writeScore();
	}
	
	public void writeScore(){
		String temp = null;
		File scores = new File("scores.txt");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(scores);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
			for (int i = 0; i < 10; i++){
				temp = highScores.get(i).toString();
				writer.write(temp);
				writer.newLine();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException x){
			x.printStackTrace();
		}
		
	}
	
	public void readScores(){
		Path scores = Paths.get("scores.txt");
		int temp;
		try(BufferedReader reader = Files.newBufferedReader(scores, StandardCharsets.UTF_8)){
			String line = null;
			while((line = reader.readLine()) != null){
				temp = Integer.parseInt(line);
				addScore(temp);
			}
		} catch (IOException x){
			System.out.println("CANT READ SCORES");
			x.printStackTrace();
		}
	}
	
	public Image getHighScoreBackgroundImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(highscore_bkg));
		return i.getImage();
	}
	
}
