package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JGameField extends JPanel{
	private static final long serialVersionUID = 1L;

public Color colorRect;
	
	//Grösse des Panel bzw. des Spielfelds
	int height;
	int width;
	
	int fieldWidth;
	int fieldHeight;
	
	boolean gridOn;
	
	//public char gameOver = 'n';	//Gibt Auskunft ob das Spiel vorbei ist und wenn ja warum
							// n = not Over / es läuft		w = wallcollision		s = selfcollision
	private Snake2 snake2;
	
	public JGameField(int width, int height, Snake2 snake2) {
		super();
		this.colorRect = Color.red;
		this.gridOn = true;
		this.snake2 = snake2;
		this.fieldWidth = (width-(2*snake2.settings.BORDERWIDTH))/snake2.world.worldWidth;
		this.fieldHeight = (height - (2*snake2.settings.BORDERWIDTH)) / snake2.world.worldHeight;
		
		//macht ein Feld quadratisch;
		if(fieldWidth < fieldHeight) {
			fieldHeight = fieldWidth;
			height = (snake2.world.worldHeight*fieldHeight)+(2*snake2.settings.BORDERWIDTH); 
		} else {
			fieldWidth = fieldHeight;
			width = (snake2.world.worldWidth*fieldWidth)+(2*snake2.settings.BORDERWIDTH);
		}
		
		setPreferredSize(new Dimension(width, height));
		
		this.height = height;
		this.width = width;
		//System.out.println("FieldSize: "+width +";"+height);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Umrandung zeichen 
		g.setColor(Color.red);
		//links
		g.fillRect(0, 0, snake2.settings.BORDERWIDTH, height-snake2.settings.BORDERWIDTH);
		//oben
		g.fillRect(0, 0, width, snake2.settings.BORDERWIDTH);
		//rechts
		g.fillRect(width-snake2.settings.BORDERWIDTH, 0, snake2.settings.BORDERWIDTH, height);
		//unten
		g.fillRect(0, height-snake2.settings.BORDERWIDTH, width, snake2.settings.BORDERWIDTH);	
		
		//Malt das Essen
		g.setColor(Color.yellow);
		g.fillRect((snake2.food.posX*fieldWidth)+snake2.settings.BORDERWIDTH, (snake2.food.posY*fieldHeight)+snake2.settings.BORDERWIDTH, fieldWidth, fieldHeight);
		
		//Malt die Schlange
		g.setColor(Color.black);
		for(int i = 0; i < snake2.snake.snakePointsX.size(); i++) {
			g.fillRect((snake2.snake.snakePointsX.get(i)*fieldWidth)+snake2.settings.BORDERWIDTH, (snake2.snake.snakePointsY.get(i)*fieldHeight)+snake2.settings.BORDERWIDTH, fieldWidth, fieldHeight);
		}
		
		//Gitter zeichnen
		if(gridOn) {
			g.setColor(Color.blue);
			
			//die Senkrechten Linie
			for(int x = snake2.settings.BORDERWIDTH+fieldWidth; x < (width-snake2.settings.BORDERWIDTH); x = x+fieldWidth) {
				g.drawLine(x, snake2.settings.BORDERWIDTH, x, height-(snake2.settings.BORDERWIDTH));
			}
			//die Waagerechten
			for(int y = snake2.settings.BORDERWIDTH+fieldHeight; y < (height-snake2.settings.BORDERWIDTH); y = y+fieldHeight) {
				g.drawLine(snake2.settings.BORDERWIDTH, y, width-(snake2.settings.BORDERWIDTH), y);
			}
		}
	}
}
