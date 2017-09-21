package character;

import java.util.LinkedList;

import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import location.Landscape;
import location.Room;

public class Player extends Character {
	private int cooldown = 100;

	public Player(String name, String description, Room room, int x, int y, Image image,
			GraphicsContext graphicsContext, LinkedList<Item> items) {
		super(name, description, room, x, y, image, graphicsContext, items);
	}

	public void attack() {
		if (cooldown <= 0) {
			cooldown = 100;
			for (Enemy enemy : room.getGegnerList()) {
				if (position.distance(enemy.position) < 100) {
					int hp = enemy.getHP();
					int dmg = equipment.getDamage() + 100;
					hp -= dmg * (1 - enemy.getDefense());
					enemy.setHP(hp);
				}
			}
		}
	}

	@Override
	public void interact(Player Spieler) {

	}

	public void interagieren() {
		Item item = room.getClosestItem(position, (int) image.getWidth());
		Landscape landscape = room.getClosestLandscape(position, (int) image.getWidth());
		double itemDist = 0;
		double landscapeDist = 0;

		// TODO unterscheiden wenn beides 0?
		if (item != null)
			itemDist = position.distance(new Point2D(item.getX(), item.getY()));

		if (landscape != null)
			landscapeDist = position.distance(new Point2D(landscape.getX(), landscape.getY()));

		if (itemDist < landscapeDist)
			landscape.onUse(this);
		else
			pickUpItem(room.removeItem(item.getName()));
	}

	public void move(KeyCode key) {
		final int speed = 5;
		Point2D position = new Point2D(this.position.getX(), this.position.getY());
		switch (key) {
		case W:
			position = position.add(new Point2D(0, -speed));
			break;
		case A:
			position = position.add(new Point2D(-speed, 0));
			break;
		case S:
			position = position.add(new Point2D(0, speed));
			break;
		case D:
			position = position.add(new Point2D(speed, 0));
			break;
		default:
		}
		
		if (position.getX() - image.getWidth() * 0.5 > 0 &&
				position.getX() + image.getWidth() * 0.5 < graphicsContext.getCanvas().getWidth() && 
				position.getY() - image.getHeight() * 0.5 > 0 && 
				position.getY() + image.getHeight() * 0.5 < graphicsContext.getCanvas().getHeight()) {
			this.position = position;
		}
	}

	@Override
	public void show() {
		super.show();
		inventory.show();
		equipment.show();
		cooldown--;
	}
}
