package com.crategame.src;

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

public class Coins {

	Game g;
	
	public Coins(Game g){
		this.g=g;
	}
	
	public void saveCoins(){
		String temp = null;
		File file = new File("coins.txt");
		FileOutputStream fos;
		try{
			fos = new FileOutputStream(file);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
			temp = Integer.toString(g.getCoins());
			writer.write(temp);
			writer.close();
		}catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException x){
				x.printStackTrace();
		}
	}
	
	public int loadCoins(){
		Path scores = Paths.get("coins.txt");
		int temp = -1;
		try(BufferedReader reader = Files.newBufferedReader(scores, StandardCharsets.UTF_8)){
			String line = null;
			while((line = reader.readLine()) != null){
				temp = Integer.parseInt(line);
			}
		} catch (IOException x){
			System.out.println("CANT READ SCORES");
			x.printStackTrace();
		}
		return temp;
	}
	
	
}
