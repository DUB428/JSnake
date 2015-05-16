package gui;

import objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

public class Snake2 {

	private static Snake2 instance;
	public int displayWidth = 800;
	public int displayHeight = 600;
	public EntitySnake snake;
	public Food food;
	public World world;
	public Settings settings;
	public Language lang;
	
	public JGameField pnlField;
	public JFrame mainFrame;
	public JPanel pnlMain;
	public JMenuPanel pnlMenu;
	public JGamePanel pnlGame;
	public JHighScoresPanel pnlHighScores;
	public JSettingsPanel pnlSettings;
	public JGameOverPanel pnlGameOver;
	public JNewLangPanel pnlNewLang;
	
	//public boolean debugMode;
	public boolean slowMotion;
	public boolean isRunning;
	public boolean newBestScore;
	public boolean isOpened;
	
	public char gameOver;
	
	//Fonts;
	public Font FONT_TITLE = new Font("TimesRoman", Font.BOLD, 25);
	public Font FONT_MENUTEXT = new Font("TimesRoman", Font.BOLD, 18);
	public Font FONT_TEXT = new Font("TimesRoman", Font.PLAIN, 15);
	public Font FONT_SMALL_TITLE = new Font("TimesRoman", Font.BOLD, 20);
	public Font FONT_SMALL_TEXT = new Font("TimesRoman", Font.PLAIN, 10);

	LangStringGroup lsg;
	
	public static void main(String[] args) {
		init();
		gameLoop();
		cleanUp();
		}
	
	private static void init() {
		instance = new Snake2();
		instance.world = new World(instance);
		instance.food = new Food(instance);
		instance.snake = new EntitySnake(instance);
		instance.settings = new Settings();
		instance.lang = new Language(instance);
		int fieldWidth = findFieldLength(instance.displayWidth-20, instance.world.worldWidth);
		int fieldHeight = findFieldLength(instance.displayHeight-instance.settings.BARWIDHT, instance.world.worldHeight);
		instance.pnlField = new JGameField(fieldWidth, fieldHeight, instance);
		instance.mainFrame = new JMainFrame(instance.settings.TITLE + "  " + instance.settings.VERSION, instance);
		instance.pnlMain = new JPanel();
		instance.pnlMenu = new JMenuPanel(instance);
		instance.pnlGame = new JGamePanel(instance);
		instance.pnlHighScores = new JHighScoresPanel(instance);
		instance.pnlSettings = new JSettingsPanel(instance);
		instance.pnlGameOver = new JGameOverPanel(instance);
		instance.pnlNewLang = new JNewLangPanel(instance);
		
		instance.isRunning = false;
		instance.slowMotion = false;
		//instance.debugMode = false;
		instance.newBestScore = false;
		instance.isOpened = true;
		
		instance.gameOver = 'n';

		instance.lsg = instance.lang.getStrings("Snake2");

		//Ändert Sprache beim JOptionPane
		UIManager.put("OptionPane.cancelButtonText", instance.lsg.getString("Cancel"));
		UIManager.put("OptionPane.noButtonText", instance.lsg.getString("No"));
		UIManager.put("OptionPane.okButtonText", instance.lsg.getString("Ok"));
		UIManager.put("OptionPane.yesButtonText", instance.lsg.getString("Yes"));
		
		//Fügt KeyBindings zum Steuern der Schlange hinzu
		Action leftAction = new LeftAction();
		Action rightAction = new RightAction();
		Action upAction = new UpAction();
		Action downAction = new DownAction();
		int mapName = JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
		InputMap imap = instance.pnlGame.getInputMap(mapName);
		KeyStroke aKey = KeyStroke.getKeyStroke('a');
		KeyStroke sKey = KeyStroke.getKeyStroke('s');
		KeyStroke wKey = KeyStroke.getKeyStroke('w');
		KeyStroke dKey = KeyStroke.getKeyStroke('d');
		KeyStroke upKey = KeyStroke.getKeyStroke("UP");
		KeyStroke downKey = KeyStroke.getKeyStroke("DOWN");
		KeyStroke leftKey = KeyStroke.getKeyStroke("LEFT");
		KeyStroke rightKey = KeyStroke.getKeyStroke("RIGHT");
		imap.put(aKey, "left");
		imap.put(sKey, "down");
		imap.put(wKey, "up");
		imap.put(dKey, "right");
		imap.put(leftKey, "left");
		imap.put(downKey, "down");
		imap.put(upKey, "up");
		imap.put(rightKey, "right");
		ActionMap amap = instance.pnlGame.getActionMap();
		amap.put("left", leftAction);
		amap.put("right", rightAction);
		amap.put("up", upAction);
		amap.put("down", downAction);
		
		//Stellt grösses des JFrames ein
		if(instance.settings.USEMAXRESOLUTION) {
			//Findet die Bildschirmgroesse raus
			//Findet alle Bildschirme samt Auflösung
			GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] devices = g.getScreenDevices();
			
			
			if(devices.length > 1) {
			//Wenn es mehr als ein Bildschirm gibt findet es den grösseren (den mit mehr Pixeln) raus
				ArrayList<Integer> pixels = new ArrayList<>();
				for(int i = 0; i < devices.length; i++) {
					pixels.add(devices[i].getDisplayMode().getWidth() * devices[i].getDisplayMode().getHeight());
				}
				//int[] pixels2 = pixels.clone();
				//Arrays.sort(pixels);
				//pixels[pixels.length-1];
				int mainDisplay = pixels.indexOf(Collections.max(pixels));
				instance.displayWidth = devices[mainDisplay].getDisplayMode().getWidth();
				instance.displayHeight = devices[mainDisplay].getDisplayMode().getHeight();
			} else {
				//Wenn es ein Bildschirm gibt
				instance.displayWidth = devices[0].getDisplayMode().getWidth();
				instance.displayHeight = devices[0].getDisplayMode().getWidth();
			}
		} else {
			instance.displayWidth = instance.settings.DISPWIDTH;
			instance.displayHeight = instance.settings.DISPHEIGHT;
		}
		
		//pnlMain
		instance.pnlMenu.setVisible(true);
		
		instance.pnlMain.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		instance.pnlMain.add(instance.pnlMenu, c);
		instance.pnlMain.add(instance.pnlGame, c);
		instance.pnlMain.add(instance.pnlHighScores, c);
		instance.pnlMain.add(instance.pnlSettings, c);
		instance.pnlMain.add(instance.pnlGameOver, c);
		instance.pnlMain.add(instance.pnlNewLang, c);
		
		//mainFrame
//		instance.mainFrame.setSize(instance.settings.MENUWIDTH, instance.settings.MENUHEIGHT);
		instance.mainFrame.setSize(instance.displayWidth, instance.displayHeight);
		instance.mainFrame.setVisible(true);
		instance.mainFrame.add(instance.pnlMain);
		
	}
	
	private static void gameLoop() {
		instance.snake.snakePointsX.clear();
		instance.snake.snakePointsY.clear();
		instance.snake.direction = 'n';
		instance.snake.snakePointsX.add((int)(instance.world.worldWidth/2));
		instance.snake.snakePointsY.add((int)(instance.world.worldWidth/2));
		
		while(instance.isOpened) {
			
			if(instance.settings.DEBUGMODE) {
				instance.pnlGame.lblPosX.setText(instance.lsg.getString("PosX") + ": " + instance.snake.getPosX());
				instance.pnlGame.lblPosY.setText(instance.lsg.getString("PosY") + ": " + instance.snake.getPosY());
				instance.pnlGame.lblDirection.setText(instance.lsg.getString("Direction") + ": " + instance.snake.direction);
				instance.pnlGame.lblFrameSize.setText("FrameSize: " + instance.mainFrame.getWidth() + "x" + instance.mainFrame.getHeight());
				instance.pnlGame.lblFieldSize.setText("FieldSize: " + instance.pnlField.width + "x" + instance.pnlField.height);
				instance.pnlGame.lblSizeOfAField.setText("SizeOfAField: " + instance.pnlField.fieldWidth + "x" + instance.pnlField.fieldHeight);
			}
			
			if(instance.gameOver != 'n') {
				//instance.pnlGame.btnStart.setText("Neustart");
			}
			if(instance.isRunning) {
				//System.out.println("update");
				//write all the debuginformatione
				instance.pnlGame.lblScore.setText(instance.lsg.getString("Score") + " :" + instance.snake.getLength());
				instance.snake.nextStep(instance);
				instance.pnlField.repaint();
			}
			try {
				Thread.sleep(instance.settings.SLEEPTIME);
				//System.out.println("Sleeptime: " + instance.settings.SLEEPTIME);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
	
	private static void cleanUp() {
		if(instance.slowMotion) {
			instance.settings.SLEEPTIME /= 20;
		}
		instance.settings.writeFile();
		instance.mainFrame.dispose();
	}
	
	public static int findFieldLength(int disp, int world) {
		int field = disp;
		while((field-(2*instance.settings.BORDERWIDTH)) % world != 0) {
			field--;
		}
		return field;
	}
	
	static class LeftAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			instance.snake.direction = 'l';
			//System.out.println("l");
		}
	}
	
	static class RightAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public void actionPerformed(ActionEvent e) {
			instance.snake.direction = 'r';
			//System.out.println("r");
		}
	}
	
	static class UpAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public void actionPerformed(ActionEvent e) {
			instance.snake.direction = 'u';
			//System.out.println("u");
		}
	}
	
	static class DownAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public void actionPerformed(ActionEvent e) {
			instance.snake.direction = 'd';
			//System.out.println("d");
		}
	}

}
