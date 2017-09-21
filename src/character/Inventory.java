package character;

import java.util.LinkedList;

import item.Item;
import main.IShowable;

public class Inventory implements IShowable {
	private int currentUsedSpace;
	private LinkedList<Item> items = new LinkedList<Item>();
	private int maxSpace;

	public Inventory() {
		maxSpace = 5;
		currentUsedSpace = 0;
	}

	Inventory(int maxSpace, LinkedList<Item> items) {
		this.maxSpace = maxSpace;
		currentUsedSpace = 0;
		addItems(items);
	}

	Inventory(LinkedList<Item> items) {
		this.maxSpace = 10;
		currentUsedSpace = 0;
		addItems(items);
	}

	public void addItem(Item item) {
		if (currentUsedSpace + 1 != maxSpace) {
			items.add(item);
		}
	}

	public void addItems(LinkedList<Item> items) {
		if (items != null) {
			for (Item item : items) {
				addItem(item);
			}
		}
	}

	public Item getFirstItemByName(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	public LinkedList<Item> getItems() {
		return items;
	}

	public void removeFirstItemByName(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				items.remove(item);
			}
		}
	}

	public void removeItemByIndex(int itemIndex) {
		if (items.get(itemIndex) != null) {
			items.remove(itemIndex);
		}
	}

	@Override
	public void show() {
		int dist = 10;
		for (Item item : items) {
			item.showAt(dist, 750);
		}
	}

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
