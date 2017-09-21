package character;

import java.util.LinkedList;
import java.util.Random;

import equipment.Equipment;
import item.Defense;
import item.Item;
import item.Weapon;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

public abstract class Character {
	protected String description;
	protected Equipment equipment = new Equipment();
	protected GraphicsContext graphicsContext;
	protected HealthPoints healthPoints;
	protected Image image;
	protected Inventory inventory = new Inventory();
	protected int money;
	protected String name;
	protected Point2D position;
	protected Room room;

	public Character(String name, String description, Room room, int x, int y, Image image,
			GraphicsContext graphicsContext, LinkedList<Item> items) {
		this.name = name;
		this.description = description;
		this.room = room;
		position = new Point2D(x, y);
		this.image = image;
		this.graphicsContext = graphicsContext;
		inventory.addItems(items);
		healthPoints = new HealthPoints(this, graphicsContext);
	}

	public Item dropItem(String itemName) {
		Item item = null;
		if (healthPoints.getIsUsable()) {
			item = inventory.getFirstItemByName(itemName);
			if (item != null) {
				inventory.removeFirstItemByName(name);
			}
		}
		return item;
	}

	public void dropItems() {
		Random r = new Random();
		for (Item item : inventory.getItems()) {
			item.setPosition(
					new Point2D(position.getX() + (r.nextInt(101) - 50), position.getY() + (r.nextInt(101) - 50)));
			room.addItem(item);
		}
	}

	public void equipItem(Defense defense) {
		equipment.equipItem(defense);
	}

	public void equipItem(Weapon weapon) {
		equipment.equipItem(weapon);
	}

	public double getDefense() {
		return equipment.getArmor();
	}

	public String getDescription() {
		return description;
	}

	public LinkedList<Item> getItems() {
		return inventory.getItems();
	}

	public Item getItem(String itemName) {
		return inventory.getFirstItemByName(itemName);
	}

	public double getHeight() {
		return image.getHeight();
	}

	public int getHP() {
		return healthPoints.getCurrentHealthPoints();
	}

	public int getMoney() {
		return money;
	}

	public String getName() {
		return name;
	}

	public Point2D getPosition() {
		return position;
	}

	public Room getRoom() {
		return room;
	}

	public double getWidth() {
		return image.getWidth();
	}

	public abstract void interact(Player spieler);

	public void pickUpItem(Item item) {
		inventory.addItem(item);
	}

	public void setHP(int hp) {
		healthPoints.setCurrentHealPoints(hp);
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setPosition(Point2D pos) {
		this.position = pos;
	}

	public void setRoom(Room aktuellerRaum) {
		this.room = aktuellerRaum;
	}

	public void show() {
		double x = position.getX() - image.getWidth() * 0.5;
		double y = position.getY() - image.getHeight() * 0.5;
		graphicsContext.drawImage(image, x, y);

		healthPoints.show();
	}
}
