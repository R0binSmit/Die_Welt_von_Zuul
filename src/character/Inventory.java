package character;

import java.util.LinkedList;

import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.IShowable;

/**
 * Klasse um das Inventar eines Spielers darzustellen
 */
public class Inventory implements IShowable {
	private int currentUsedSpace;
	private LinkedList<Item> items = new LinkedList<Item>();
	private int maxSpace;
	private GraphicsContext graphicsContext;

	/**
	 * Standardkonstruktor
	 */
	public Inventory(GraphicsContext graphicsContext) {
		maxSpace = 5;
		currentUsedSpace = 0;
		this.graphicsContext = graphicsContext;
	}

	/**
	 * Item zum Inventar hinzufügen
	 * 
	 * @param item
	 *            Item das hinzugefügt werden soll
	 */
	public void addItem(Item item) {
		if (currentUsedSpace + 1 != maxSpace) {
			items.add(item);
		}
	}

	/**
	 * Mehrere Items gleichzeitig zum Inventar hinzufügen
	 * 
	 * @param items
	 *            Items die hinzugefügt werden sollen
	 */
	public void addItems(LinkedList<Item> items) {
		if (items != null) {
			for (Item item : items) {
				addItem(item);
			}
		}
	}

	/**
	 * Item anhand des Namens zurückgeliefert bekommen
	 * 
	 * @param itemName
	 *            Name des Items
	 * @return Item mit dem Namen
	 */
	public Item getFirstItemByName(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Alle Items als Liste erhalten
	 * 
	 * @return Alle Items im Inventar
	 */
	public LinkedList<Item> getItems() {
		return items;
	}

	/**
	 * Item anhand des Namens Löschen
	 * 
	 * @param itemName
	 *            Name des Items
	 */
	public void removeFirstItemByName(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				items.remove(item);
				break;
			}
		}
	}

	/**
	 * Item anhand des Idexes Löschen
	 * 
	 * @param itemIndex
	 *            Index des Items
	 */
	public void removeItemByIndex(int itemIndex) {
		if (items.get(itemIndex) != null) {
			items.remove(itemIndex);
		}
	}

	/**
	 * Inventar anzeigen lassen
	 */
	@Override
	public void show() {
		Paint p = graphicsContext.getFill();
		graphicsContext.setFill(Color.rgb(230, 230, 255, 0.5));
		graphicsContext.fillRect(0, 700, 350, 100);
		graphicsContext.setFill(p);
		
		int dist = 10;
		for (Item item : items) {
			item.showAt(dist, 720);
			dist += item.getWidth() + 10;
		}
	}

	/**
	 * Diese Metode gibt den Inhalt von Inventory als String zurück.
	 * @return Alle Items aus Inventory als String.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Item item : items) {
			stringBuilder.append(item.toString());
			stringBuilder.append("\r\n");
		}
		return stringBuilder.toString();
	}
}
