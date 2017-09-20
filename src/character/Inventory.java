package character;

import java.util.LinkedList;

import item.Item;
import main.IShowable;


public class Inventory implements IShowable {
	private int maxSpace;
	private int currentUsedSpace;
	private LinkedList<Item> listOfItems = new LinkedList<Item>();
	
	public Inventory(){
		maxSpace = 5;
		currentUsedSpace = 0;
	}
	
	Inventory(LinkedList<Item> items){
		this.maxSpace = 10;
		currentUsedSpace = 0;
		addItems(items);
	}
	
	Inventory(int maxSpace, LinkedList<Item> items){
		this.maxSpace = maxSpace;
		currentUsedSpace = 0;
		addItems(items);
	}
	
	public void addItem(Item item) {
		if(currentUsedSpace +1 != maxSpace) {
			listOfItems.add(item);
		}
	}
	
	public void addItems(LinkedList<Item> items) {
		for(Item item : items) {
			addItem(item);
		}
	}
	
	public void removeFirstItemByName(String itemName) {
		for(Item item : listOfItems) {
			if(item.getName().equals(itemName)) {
				listOfItems.remove(item);
			}
		}
	}
	
	public void removeItemByIndex(int itemIndex) {
		if(listOfItems.get(itemIndex) != null) {
			listOfItems.remove(itemIndex);
		}
	}
	
	public Item getFirstItemByName(String itemName) {
		for(Item item : listOfItems) {
			if(item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}
	
	public LinkedList<Item> getLinkedListFromItems(){
		return listOfItems;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(Item item : listOfItems) {
			stringBuilder.append(item.toString());
			stringBuilder.append("\r\n");
		}		
		return stringBuilder.toString();
	}

	public void show() {
		// TODO SHOW
		
	}
}
