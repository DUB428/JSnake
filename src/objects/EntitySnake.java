package objects;

import gui.Snake2;

import java.util.ArrayList;

/*
 * author: Samuel Martin
 * date: 07.10.2014
 * 
 * Objekt, welches die Schlange darstellt im Spiel Snake2
 * 
 */
public class EntitySnake {

	public ArrayList<Integer> snakePointsX = new ArrayList<>(); 
	public ArrayList<Integer> snakePointsY = new ArrayList<>(); 
	
	public char direction;
		
	public boolean grow = false;
	
	public EntitySnake(Snake2 snake2) {
		direction = 'n';
		snakePointsX.add((int)(snake2.world.worldWidth/2));
		snakePointsY.add((int)(snake2.world.worldWidth/2));
	}
	
	public boolean nextStep(Snake2 snake2) {
		
		//Kolissionsabfrage
		//mit dem Essen
		if(snakePointsX.get(snakePointsX.size()-1) == snake2.food.posX && snakePointsY.get(snakePointsY.size()-1) == snake2.food.posY) {
			grow(snake2);
		}
		//mit der Wand (groesse sollte noch verstallbar sein/Zentral)
		if(getPosX() == snake2.world.worldWidth || getPosX() == -1 || getPosY() == snake2.world.worldHeight || getPosY() == -1) {
			//System.out.println("Game Over; Wandkollision");
			snake2.gameOver = 'w';
			snake2.newBestScore = snake2.settings.isNewHighScore(snakePointsX.size());
			snake2.pnlGame.setVisible(false);
			snake2.pnlGameOver.setVisible(true);
			snake2.pnlGameOver.showGameOver(snake2, snake2.gameOver ,snakePointsX.size(), snake2.newBestScore);
			snake2.isRunning = false;
			return false;
		}
		//mit der Schlange selbts
		for(int i = 0; i < snakePointsX.size(); i++) {
			for(int i2 = 0; i2 < snakePointsX.size(); i2++) {
				if(i == i2 || i - 1 == i2 || i + 1 == i2) {
					
				}else if(snakePointsX.get(i).equals(snakePointsX.get(i2)) && snakePointsY.get(i).equals(snakePointsY.get(i2))) {
					//System.out.println("Game Over; in den eigenen Schwanz gebissen");
					snake2.gameOver = 's';
					snake2.newBestScore = snake2.settings.isNewHighScore(snakePointsX.size());
					snake2.pnlGame.setVisible(false);
					snake2.pnlGameOver.setVisible(true);
					snake2.pnlGameOver.showGameOver(snake2, snake2.gameOver ,snakePointsX.size(), snake2.newBestScore);
					snake2.isRunning = false;
					return false;
				}	
			}
		}
		//Entscheidung welche Richtung (aus direction auslesen)
		//hängt den neuen Wert für die x-Koordinate an, in dem es den alten ausliest und eine entsprechende Zahl dazu
		//addiert bzw. subtrahiert
		switch (direction) {
		case 'l':		//l = left
			snakePointsX.add(snakePointsX.get(snakePointsX.size()-1) - 1);
			snakePointsY.add(snakePointsY.get(snakePointsY.size() -1));
			break;
		case 'u':		//u = up
			snakePointsX.add(snakePointsX.get(snakePointsX.size()-1));
			snakePointsY.add(snakePointsY.get(snakePointsY.size() -1) - 1);
			break;
		case 'r':		//r = right
			snakePointsX.add(snakePointsX.get(snakePointsX.size()-1) + 1);
			snakePointsY.add(snakePointsY.get(snakePointsY.size() -1));
			break;
		case 'd':		//d = down
			snakePointsX.add(snakePointsX.get(snakePointsX.size()-1));
			snakePointsY.add(snakePointsY.get(snakePointsY.size() -1) + 1);
			break;
		case 'n':		//n= no direction (stehend)
			break;
		}
		
		if(direction != 'n' || grow == true) {
			//Entfernt die Punkte die zu viel sind
			snakePointsX.remove(0);
			snakePointsY.remove(0);
		}
		return true;
	}
	
	public void grow(Snake2 snake2) {
		snakePointsX.add(snake2.food.posX);
		snakePointsY.add(snake2.food.posY);
		String oldPos = snake2.food.getFoodPos();
		snake2.food.respawn(snake2);
		String newPos = snake2.food.getFoodPos();
	}
	
	public void respawn(Snake2 snake2) {
		snakePointsX.clear();
		snakePointsY.clear();
		
		direction = 'n';
		snakePointsX.add((int)(snake2.world.worldWidth/2));
		snakePointsY.add((int)(snake2.world.worldWidth/2));
	}
	
	public int getPosX() {
		//liefert die X-Koordinate des Kopfs zurueck
		return snakePointsX.get(snakePointsX.size() - 1);
	}
	
	public int getPosY() {
		//liefert die Y-Koordinate des Kopfs zurueck
		return snakePointsY.get(snakePointsY.size() - 1);
	}
	
	public int getLength() {
		//liefert die Länge der Schlange zurueck
		return snakePointsX.size();
	}

}
