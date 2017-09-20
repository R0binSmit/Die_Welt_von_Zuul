package character;

import java.util.LinkedList;
import java.util.Random;

import equipment.Equipment;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

public abstract class Character {
	protected String name;
	protected String description;
	protected Room room;
	protected Point2D position;
	protected Image image;
	protected Equipment equipment = new Equipment();
	protected Inventory inventory = new Inventory();
	protected HealPoints healPoints;
	protected GraphicsContext graphicsContext;
	protected int money;

	public Character(String name, String description, Room room, int x, int y, Image image,
			GraphicsContext graphicsContext, LinkedList<Item> items) {
		this.name = name;
		this.description = description;
		this.room = room;
		position = new Point2D(x, y);
		this.image = image;
		this.graphicsContext = graphicsContext;
		inventory.addItems(items);
		healPoints = new HealPoints(this, graphicsContext);
	}

	public void show() {
		double x = position.getX() - image.getWidth() * 0.5;
		double y = position.getY() - image.getHeight() * 0.5;
		graphicsContext.drawImage(image, x, y);

		healPoints.show();
	}

	public abstract void interact(Player spieler);

	public void pickUpItem(Item item) {
		inventory.addItem(item);
	}

	public Item dropItem(String itemName) {
		Item item = null;
		if (healPoints.getIsUsable()) {
			item = inventory.getFirstItemByName(itemName);
			if (item != null) {
				inventory.removeFirstItemByName(name);
			}
		}
		return item;
	}

	public Item getGegenstand(String itemName) {
		return inventory.getFirstItemByName(itemName);
	}
	
	public void dropItems() {
		Random r = new Random();
		for (Item item : inventory.getLinkedListFromItems()) {
			item.setPosition(new Point2D(position.getX() + (r.nextInt(101) - 50), position.getY() + (r.nextInt(101) - 50)));
			room.addItem(item);
		}
	}

	public String getStatus() {
		StringBuilder sb = new StringBuilder();
		sb.append("Geld: ");
		sb.append(money);
		sb.append(System.getProperty("line.separator"));
		sb.append("Zustand: ");
		// TODO fix no more zustand.
		// sb.append(zustand.getName());
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room aktuellerRaum) {
		this.room = aktuellerRaum;
	}

	public LinkedList<Item> getGegenstaende() {
		return inventory.getLinkedListFromItems();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Point2D getPosition() {
		return position;
	}

	public double getWidth() {
		return image.getWidth();
	}

	public double getHeight() {
		return image.getHeight();
	}

	public void setPosition(Point2D pos) {
		this.position = pos;
	}
}
