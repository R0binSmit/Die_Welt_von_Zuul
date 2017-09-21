package character;

import java.util.LinkedList;

import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

/**
 * Klasse mit der alle NPCs dargestellt werden
 */
public class NPC extends Character {
	protected String text;

	/**
	 * Konstruktor für alle NPCs
	 * 
	 * @param name
	 *            Name des NPCs
	 * @param description
	 *            Beschreibung des NPCs
	 * @param room
	 *            Raum in dem sich der NPC befindet
	 * @param x
	 *            X Position des NPCs im Raum
	 * @param y
	 *            Y Position des NPCs im Raum
	 * @param image
	 *            Aussehen des NPCs
	 * @param graphicsContext
	 *            GraphicsContext zum anzeigen des NPCs
	 * @param items
	 *            Items die der NPC von anfang an dabei hat
	 */
	public NPC(String name, String description, Room room, int x, int y, Image image, GraphicsContext graphicsContext,
			LinkedList<Item> items) {
		super(name, description, room, x, y, image, graphicsContext, items);
	}
	
	public String getText() {
		return text;
	}

	/**
	 * Dadurch können NPCs mit dem Spieler interagieren
	 * @param spieler
	 * Der Spieler mit dem interagiert werden soll
	 */
	@Override
	public void interact(Player spieler) {
		System.out.println(text);
	}

	public void setText(String text) {
		this.text = text;
	}
}
