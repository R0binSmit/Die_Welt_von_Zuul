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

	public Player(String name, int maxTraglast, Room raum, int x, int y, Image image, GraphicsContext gc, LinkedList<Item> gegenstaende) {
		super(name, maxTraglast, raum, x, y, image, gc, gegenstaende);
		this.angriffsVerhalten = SpielerAngriffVerhalten.getInstance();
	}
	
	public void interagieren() {
		Item item = room.getClosestItem(pos, (int) image.getWidth());
		Landscape landscape = room.getClosestLandscape(pos, (int)image.getWidth());
		double itemDist = 0;
		double landscapeDist = 0;
		
		//TODO unterscheiden wenn beides 0?
		if(item != null)
			itemDist = pos.distance(new Point2D(item.getX(), item.getY()));
		
		if(landscape != null)
			landscapeDist = pos.distance(new Point2D(landscape.getX(), landscape.getY()));
		
		if(itemDist < landscapeDist)
			landscape.onUse(this);
		else
			pickUpItem(room.removeItem(item.getName()));
	}
	
	public void show() {
		super.show();
		int dist = 10;
		for (Item item : gegenstaende) {
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
			pos = pos.add(new Point2D(0, -speed));
			break;
		case A:
			pos = pos.add(new Point2D(-speed, 0));
			break;
		case S:
			pos = pos.add(new Point2D(0, speed));
			break;
		case D:
			pos = pos.add(new Point2D(speed, 0));
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
