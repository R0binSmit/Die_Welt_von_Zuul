package character;

import java.util.LinkedList;

import equipment.Equipment;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import location.Room;
import main.Usefull;

public abstract class Character {
	protected String name;
	protected String description;
	protected Room room;
	protected Point2D position;
	protected Image image;
	protected Equipment equipment = new Equipment();
	protected Inventory inventory = new Inventory();
	protected HealPoints healPoints = new HealPoints();
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
	}

	public void show() {
		double x = position.getX() - image.getWidth() * 0.5;
		double y = position.getY() - image.getHeight() * 0.5;
		graphicsContext.drawImage(image, x, y);

		x -= 50;
		y += image.getHeight() + 30;
		double w = 2 * 50 + image.getWidth();

		Paint p = graphicsContext.getFill();
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillRect(x, y, w, 10);
		graphicsContext.setFill(Color.RED);
		graphicsContext.fillRect(x + 1, y + 1,
				Usefull.map(healPoints.getCurrentHealPoints(), 0, healPoints.getMaxHealPoints(), 0, w - 2), 8);
		graphicsContext.setFill(p);
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
