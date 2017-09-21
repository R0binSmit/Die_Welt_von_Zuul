package character;

import java.util.LinkedList;

import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

/**
 * Klasse mit der alle Trader dargestellt werden
 */
public class Trader extends NPC {
	/**
	 * Konstruktor für alle Trader
	 * 
	 * @param name
	 *            Name des Traders
	 * @param description
	 *            Beschreibung des Traders
	 * @param room
	 *            Raum in dem sich der Trader befindet
	 * @param x
	 *            X Position des Traders im Raum
	 * @param y
	 *            Y Position des Traders im Raum
	 * @param image
	 *            Aussehen des Traders
	 * @param graphicsContext
	 *            GraphicsContext zum anzeigen des Traders
	 * @param items
	 *            Items die der Trader von anfang an dabei hat
	 */
	public Trader(String name, String description, Room room, int x, int y, Image image,
			GraphicsContext graphicsContext, LinkedList<Item> items) {
		super(name, description, room, x, y, image, graphicsContext, items);
	}
}