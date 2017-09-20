package character;

import java.util.LinkedList;

import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

public class Enemy extends Character {
	public Enemy(String name, int maxTraglast, Room raum, int x, int y, Image image, GraphicsContext gc,
			LinkedList<Item> gegenstaende) {
		super(name, maxTraglast, raum, x, y, image, gc, gegenstaende);
	}

	@Override
	public void interact(Player spieler) {

	}
}