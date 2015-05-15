package objects;

import java.util.ArrayList;

import gui.Snake2;

public class World {

	public ArrayList<Obstacle> obstacles = new ArrayList<>();
	public int worldWidth;
	public int worldHeight;
	
	public World(Snake2 snake2) {
		//set Worldsize in amount of Fields
		this.worldWidth = 30;
		this.worldHeight = 30;
		//load Obstacles here with:
		//this.obstacles.add(new Obstacle(posX, posY, snake2));
	}
}
