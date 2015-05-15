package objects;

import gui.Snake2;

import java.util.Random;

public class Food {

	public int posX;
	public int posY;
	
	public Random randomX;
	public Random randomY;
	
	public Food(Snake2 snake2) {
		
		randomX = new Random();		//Zahl bei Random an die (später) variable Fenstergrösse anpasse.
		randomY = new Random();
		
		posX = (randomX.nextInt(snake2.world.worldWidth));
		posY = (randomY.nextInt(snake2.world.worldWidth));
		
	}

	public void respawn(Snake2 snake2) {
		posX = (randomX.nextInt(snake2.world.worldWidth));
		posY = (randomY.nextInt(snake2.world.worldWidth));
		
		if(posX == snake2.snake.getPosX() && posY == snake2.snake.getPosY()) {
			respawn(snake2);
		}
		
	}
	
	public String getFoodPos() {
		return posX + ";" + posY;
	}
}
