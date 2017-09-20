package character;

import java.util.LinkedList;

import Verhalten.SpielerAngriffVerhalten;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import location.Landscape;
import location.Room;

public class Player extends Character {
	boolean hauptSpieler = false;

	public Player(String name, String description, Room room, int x, int y, Image image, GraphicsContext graphicsContext, LinkedList<Item> items) {
		super(name, description, room, x, y, image, graphicsContext, items);
	}
	
	public void interagieren() {
		Item item = room.getClosestItem(position, (int) image.getWidth());
		Landscape landscape = room.getClosestLandscape(position, (int)image.getWidth());
		double itemDist = 0;
		double landscapeDist = 0;
		
		//TODO unterscheiden wenn beides 0?
		if(item != null)
			itemDist = position.distance(new Point2D(item.getX(), item.getY()));
		
		if(landscape != null)
			landscapeDist = position.distance(new Point2D(landscape.getX(), landscape.getY()));
		
		if(itemDist < landscapeDist)
			landscape.onUse(this);
		else
			pickUpItem(room.removeItem(item.getName()));
	}
	
	public void show() {
		super.show();
		int dist = 10;
		for (Item item : inventory.getLinkedListFromItems()) {
			item.showAt(dist, 750);
		}
		
		equipment.show();
	}

	public boolean isHauptSpieler() {
		return hauptSpieler;
	}

	public void move(KeyCode key) {
		final int speed = 5;
		switch (key) {
		case W:
			position = position.add(new Point2D(0, -speed));
			break;
		case A:
			position = position.add(new Point2D(-speed, 0));
			break;
		case S:
			position = position.add(new Point2D(0, speed));
			break;
		case D:
			position = position.add(new Point2D(speed, 0));
			break;
		default:
		}
	}

	public void setHauptSpieler(boolean hauptSpieler) {
		this.hauptSpieler = hauptSpieler;
	}

	public void interact(Player Spieler) {

	}
}
