package objects;

import gui.Snake2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Language_old {

	//All Strings for the ui are starting with UI_
	//for JGameOverPanel
	public String UI_GAMEOVER;
	public String UI_NEWHIGHSCORE;
	public String UI_AGAIN;
	public String UI_SHOWALLHIGHSCORE;
	public String UI_YOURNAME;
	public String UI_DEATHWALL;
	public String UI_DEATHSELF;

	//for the JGamePanel
	public String UI_SLOWMOTION_OFF;
	public String UI_SLOWMOTION_ON;
	public String UI_DEBUGMODE_OFF;
	public String UI_DEBUGMODE_ON;
	public String UI_POSX;
	public String UI_POSY;
	public String UI_DIRECTION;
	public String UI_PAUSE;
	public String UI_START;

	//for JHighScorePanel
	public String UI_PLAYERNAME;
	public String UI_SCORE;
	public String UI_RESETHIGHSCORES;
	public String UI_SURE_DELET_HIGHSCORES;
	public String UI_SURE_DELET;

	//for JMenuPanel
	public String UI_START_GAME;
	public String UI_EXIT;

	//for JSettingsPanel
	public String UI_SPEED;
	public String UI_MAXRES;
	public String UI_DISPLAYWIDTH;
	public String UI_DISPLAYHEIGHT;
	public String UI_LANGUAGE;
	public String UI_EXPL_SPEED;
	public String UI_EXPL_LANGUAGE;//
	public String UI_NEEDS_RESTART;//
	public String UI_GAME_SETTINGS = "Gameplay-Settings";
	public String UI_RES_SETTINGS = "Resolution-Settings";
	public String UI_LANG_SETTINGS = "Language-Settings";
	public String UI_NEW_LANG = "add a new Language";

	//for JNewLanguagePanel
	public String UI_CURRENTLANG = "current Language";
	public String UI_NEWLANG = "new Language";
	public String UI_ADDLANG = "add Language";
	public String UI_NEWLANG_NAME = "Name of new language";
	public String UI_NEWLANG_SHORT = "Shortcut of the new Language";

	//strings used in several Panels
	public String UI_MENU;		//used in JGameOverPanel, JGamePanel, JHighScorePane, JSettingsPanel
	public String UI_HIGHSCORES; 	//used in JHighScorePanel, JMenuPanel
	public String UI_SETTINGS;		//used in JMenuPanel, JSettingsPanel

	//general stuff
	public String UI_YES;
	public String UI_NO;
	public String UI_CANCEL;
	public String UI_OK;

	File langFile;
	public ArrayList<String> allStrings = new ArrayList<>();		//an ArrayList with all of the strings in the current language
	public ArrayList<String> allVarNames = new ArrayList<>(); 		//an ArrayList with all the names of the variables of the strings used in the program

	public Language_old(Snake2 snake2) {
		langFile = new File("local_" + snake2.settings.LANG + ".txt");
		readLangFile();
		
		//fill allStrings
		Scanner reader = null;
		try {
			reader = new Scanner(langFile);
			String line;
			while(reader.hasNextLine()) {
				line = reader.nextLine();
				allVarNames.add(line.split(": ")[0]);
				allStrings.add(line.split(": ")[1]);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
		
	}
	
	public void readLangFile() {
		Scanner reader = null;
		try {
			reader = new Scanner(langFile);
			UI_GAMEOVER = reader.nextLine().split(": ")[1];
			UI_NEWHIGHSCORE = reader.nextLine().split(": ")[1];
			UI_AGAIN = reader.nextLine().split(": ")[1];
			UI_SHOWALLHIGHSCORE = reader.nextLine().split(": ")[1];
			UI_YOURNAME = reader.nextLine().split(": ")[1];
			UI_DEATHWALL = reader.nextLine().split(": ")[1];
			UI_DEATHSELF = reader.nextLine().split(": ")[1];
			UI_SLOWMOTION_OFF = reader.nextLine().split(": ")[1];
			UI_SLOWMOTION_ON = reader.nextLine().split(": ")[1];
			UI_DEBUGMODE_OFF = reader.nextLine().split(": ")[1];
			UI_DEBUGMODE_ON = reader.nextLine().split(": ")[1];
			UI_POSX = reader.nextLine().split(": ")[1];
			UI_POSY = reader.nextLine().split(": ")[1];
			UI_DIRECTION = reader.nextLine().split(": ")[1];
			UI_PAUSE = reader.nextLine().split(": ")[1];
			UI_START = reader.nextLine().split(": ")[1];
			UI_PLAYERNAME = reader.nextLine().split(": ")[1];
			UI_SCORE = reader.nextLine().split(": ")[1];
			UI_RESETHIGHSCORES = reader.nextLine().split(": ")[1];
			UI_SURE_DELET_HIGHSCORES = reader.nextLine().split(": ")[1];
			UI_SURE_DELET = reader.nextLine().split(": ")[1];
			UI_START_GAME = reader.nextLine().split(": ")[1];
			UI_EXIT = reader.nextLine().split(": ")[1];
			UI_MENU = reader.nextLine().split(": ")[1];
			UI_HIGHSCORES = reader.nextLine().split(": ")[1];
			UI_SETTINGS = reader.nextLine().split(": ")[1];
			UI_SPEED = reader.nextLine().split(": ")[1];
			UI_MAXRES = reader.nextLine().split(": ")[1];
			UI_DISPLAYWIDTH = reader.nextLine().split(": ")[1];
			UI_DISPLAYHEIGHT = reader.nextLine().split(": ")[1];
			UI_LANGUAGE = reader.nextLine().split(": ")[1];
			UI_EXPL_SPEED = reader.nextLine().split(": ")[1];
			UI_EXPL_LANGUAGE = reader.nextLine().split(": ")[1];
			UI_NEEDS_RESTART = reader.nextLine().split(": ")[1];
			UI_YES = reader.nextLine().split(": ")[1];
			UI_NO = reader.nextLine().split(": ")[1];
			UI_CANCEL = reader.nextLine().split(": ")[1];
			UI_OK = reader.nextLine().split(": ")[1];
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}
	
	public void fillFileWithVarNames() {
		
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(langFile));
			writer.write("UI_GAMEOVER: ");
			writer.newLine();
			writer.write("UI_NEWHIGHSCORE: ");
			writer.newLine();
			writer.write("UI_AGAIN: ");
			writer.newLine();
			writer.write("UI_SHOWALLHIGHSCORE: ");
			writer.newLine();
			writer.write("UI_YOURNAME: ");
			writer.newLine();
			writer.write("UI_DEATHWALL: ");
			writer.newLine();
			writer.write("UI_DEATHSELF: ");
			writer.newLine();
			writer.write("UI_SLOWMOTION_OFF: ");
			writer.newLine();
			writer.write("UI_SLOWMOTION_ON: ");
			writer.newLine();
			writer.write("UI_DEBUGMODE_OFF: ");
			writer.newLine();
			writer.write("UI_DEBUGMODE_ON: ");
			writer.newLine();
			writer.write("UI_POSX: ");
			writer.newLine();
			writer.write("UI_POSY: ");
			writer.newLine();
			writer.write("UI_DIRECTION: ");
			writer.newLine();
			writer.write("UI_PAUSE: ");
			writer.newLine();
			writer.write("UI_START: ");
			writer.newLine();
			writer.write("UI_PLAYERNAME: ");
			writer.newLine();
			writer.write("UI_SCORE: ");
			writer.newLine();
			writer.write("UI_RESETHIGHSCORES: ");
			writer.newLine();
			writer.write("UI_SURE_DELET_HIGHSCORES: ");
			writer.newLine();
			writer.write("UI_SURE_DELET: ");
			writer.newLine();
			writer.write("UI_START_GAME: ");
			writer.newLine();
			writer.write("UI_EXIT: ");
			writer.newLine();
			writer.write("UI_MENU: ");
			writer.newLine();
			writer.write("UI_HIGHSCORES: ");
			writer.newLine();
			writer.write("UI_SETTINGS: ");
			writer.newLine();
			writer.write("UI_SPEED: ");
			writer.newLine();
			writer.write("UI_MAXRES: ");
			writer.newLine();
			writer.write("UI_DISPLAYWIDTH: ");
			writer.newLine();
			writer.write("UI_DISPLAYHEIGHT: ");
			writer.newLine();
			writer.write("UI_LANGUAGE: ");
			writer.newLine();
			writer.write("UI_EXPL_SPEED: ");
			writer.newLine();
			writer.write("UI_EXPL_LANGUAGE: ");
			writer.newLine();
			writer.write("UI_NEEDS_RESTART: ");
			writer.newLine();
			writer.write("UI_YES: ");
			writer.newLine();
			writer.write("UI_NO: ");
			writer.newLine();
			writer.write("UI_CANCEL: ");
			writer.newLine();
			writer.write("UI_OK: ");
			writer.newLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public boolean newLang (ArrayList<String> words) {
		return true;
	}
}
