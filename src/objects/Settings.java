package objects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class Settings {

	File dataFile = new File("settings.txt");
	
	//variables which can be consulted
	//When they have a value it is a default value which is needed in case the file must be created.
	public String TITLE = "Snake";		//Name of the game
	public String VERSION = "0.2.0";	//Version of the game
	public int BORDERWIDTH = 6;			//Width of the Border of the GameField in pixels 
	public int BARWIDHT = 21;			//At the Moment Estimated//Width of the Bar on top of the frame, which is comming from the OS itself(that one with the X-Button). 
	public int SLEEPTIME = 125;			//Time the programm waits between two nextSteps(). Meaning between to times rendering everything and the snake is going one step further
	public int[] HIGHSCORE = {0, 0, 0, 0, 0};	//the top 5 HIGHSCOREs	HIGHSCORES[0] is the best One;
	public String[] NAMES = {"PLAYER", "PLAYER", "PLAYER", "PLAYER", "PLAYER"};	//Names of the Players who made a highscore
	public boolean USEMAXRESOLUTION = true; 	//Shows if the programm should use the resolution of the Monitor or an other, lower one.
	public String DEFAULTNAME = "PLAYER"; 		//The default name of the player
	public int DISPWIDTH = 800;			//DisplayWidth of the programm, but this will be only used if USEMAXRESOLUTION == false
	public int DISPHEIGHT = 600;		//DisplayHeight of the programm, but this will be only used if USEMAXRESOLUTION == false
	public String[] SUPPORTEDLANG = {"DE", "EN"};	//Array of all supported languages
	public String LANG = "EN";			//String of the current language
	public boolean DEBUGMODE = false;	//Indicates of the debugmode is on, can only be change in the settings.txt
	
	public Settings() {
		//make File if it does not exist
		if(!dataFile.exists()) {
			try {
				dataFile.createNewFile();
				writeFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//read dataFile
		readFile();
	}
	
	public void readFile(){
		Scanner reader = null;
		try {
			reader = new Scanner(dataFile);
			TITLE = reader.nextLine().split(": ")[1];
			VERSION = reader.nextLine().split(": ")[1];
			BORDERWIDTH = Integer.valueOf(reader.nextLine().split(": ")[1]);
			BARWIDHT = Integer.valueOf(reader.nextLine().split(": ")[1]);
			SLEEPTIME = Integer.valueOf(reader.nextLine().split(": ")[1]);
			String[] highScore = reader.nextLine().split(": ")[1].split(",");
			for(int i = 0; i < HIGHSCORE.length; i++) {
				HIGHSCORE[i] = Integer.valueOf(highScore[i]);
			}
			String[] names = reader.nextLine().split(": ")[1].split(",");
			NAMES = names;
			DEFAULTNAME = reader.nextLine().split(": ")[1];
			USEMAXRESOLUTION = Boolean.valueOf(reader.nextLine().split(": ")[1]);
			DISPWIDTH = Integer.valueOf(reader.nextLine().split(": ")[1]);
			DISPHEIGHT = Integer.valueOf(reader.nextLine().split(": ")[1]);
			String[] supLang = reader.nextLine().split(": ")[1].split(",");
			SUPPORTEDLANG = supLang;
			LANG = reader.nextLine().split(": ")[1];
			DEBUGMODE = Boolean.valueOf(reader.nextLine().split(": ")[1]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}
	
	public void writeFile() {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(dataFile));
			writer.write("TITLE: " + TITLE);
			writer.newLine();
			writer.write("VERSION: " + VERSION);
			writer.newLine();
			writer.write("BORDERWIDTH: " + BORDERWIDTH);
			writer.newLine();
			writer.write("BARWIDHT: " + BARWIDHT);
			writer.newLine();
			writer.write("SLEEPTIME: " + SLEEPTIME);
			writer.newLine();
			writer.write("HIGHSCORE: ");
			for(int i = 0; i < HIGHSCORE.length; i++) {
				writer.write(HIGHSCORE[i] + ",");
			}
			writer.newLine();
			writer.write("NAMES: ");
			for(int i = 0; i < NAMES.length; i++) {
				writer.write(NAMES[i] + ",");
			}
			writer.newLine();
			writer.write("DEFAULTNAME: " + DEFAULTNAME);
			writer.newLine();
			writer.write("USEMAXRESOLUTION: " + USEMAXRESOLUTION);
			writer.newLine();
			writer.write("DISPWIDTH: " + DISPWIDTH);
			writer.newLine();
			writer.write("DISPHEIGHT: " + DISPHEIGHT);
			writer.newLine();
			writer.write("SUPPORTEDLANG: ");
			for(int i = 0; i < SUPPORTEDLANG.length; i++) {
				writer.write(SUPPORTEDLANG[i] + ",");
			}
			writer.newLine();
			writer.write("LANG: " + LANG);
			writer.newLine();
			writer.write("DEBUGMODE: " + DEBUGMODE);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean addHIGHSCORE(int score, String name) {
		/*
		 * Adds a new Score to the HIGHSCOREs if it is high enough. It returns true if the score was
		 * added, it returns false it the score wasn't added.
		 */
		//Set the used name as default, so it appears the next time allready in the textfield for the name
		DEFAULTNAME = name;
		
		//check if the score is within the top 5. (Is it worth to be in the HIGHSCORE?).
		if(score < HIGHSCORE[4]) {
			return false;
		} else {
			int newPlace = 99;
			//find the new Place of the new Score in the HIGHSCORE
			if(score >= HIGHSCORE[0]) {
				newPlace = 0;
			} else if(score >= HIGHSCORE[1]) {
				newPlace = 1;
			} else if(score >= HIGHSCORE[2]) {
				newPlace = 2;
			} else if(score >= HIGHSCORE[3]) {
				newPlace = 3;
			} else if(score >= HIGHSCORE[4]) {
				newPlace = 4;
			}
			if(newPlace != 99) {
				//Rerange the HIGHSCORE.
				for(int i = HIGHSCORE.length-2; i > newPlace-1; i--) {
					HIGHSCORE[i+1] = HIGHSCORE[i];
					NAMES[i+1] = NAMES[i];
				}
				HIGHSCORE[newPlace] = score;
				NAMES[newPlace] = name;
				writeFile();
			} else {
				System.out.println("ERROR: SCORE WAS BIGGER THAN HIGHSCORE[4] BUT FAILED THE MATCHING!!");
			}
			return true;
		}
	}
	
	public void resetHIGHSCORE() {
		for(int i = 0; i < HIGHSCORE.length; i++) {
			HIGHSCORE[i] = 0;
		}
		
		for(int i = 0; i < NAMES.length; i++) {
			NAMES[i] = "PLAYER";
		}
		writeFile();
	}
	
	public boolean isNewHighScore(int score) {
		return score > HIGHSCORE[4];
	}
}
