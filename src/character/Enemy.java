package character;

import java.util.LinkedList;

import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

public class Enemy extends Character {
	int cooldown = 100;
	private double maxSpeed = 5, maxForce = 0.05;
	private Point2D velocity = new Point2D(0, 0), acceleration = new Point2D(0, 0);

	public Enemy(String name, String description, Room room, int x, int y, Image image, GraphicsContext graphicsContext,
			LinkedList<Item> items) {
		super(name, description, room, x, y, image, graphicsContext, items);
	}

	private void applyForce(Point2D force) {
		acceleration = acceleration.add(force);
	}

	public void attack(Player target) {
		int hp = target.getHP();
		int dmg = equipment.getDamage() + 10;
		hp -= dmg * (1 - target.getDefense());
		target.setHP(hp);
	}

	@Override
	public void interact(Player spieler) {

	}

	public void move(Player target) {
		if (target.getPosition().distance(position) < image.getWidth() * 0.5 && cooldown <= 0) {
			attack(target);
			cooldown = 100;
		}
		cooldown--;
		Point2D desired = target.getPosition().subtract(position);
		desired = desired.normalize();
		desired = desired.multiply(maxSpeed);

		Point2D steer = desired.subtract(velocity);
		if (steer.magnitude() > maxForce) {
			steer = steer.normalize();
			steer = steer.multiply(maxForce);
		}

		applyForce(steer);
	}

	public boolean update() {
		if (healthPoints.getCurrentHealthPoints() <= 0) {
			return false;
		}
		velocity = velocity.add(acceleration);
		if (velocity.magnitude() > maxSpeed) {
			velocity = velocity.normalize();
			velocity = velocity.multiply(maxSpeed);
		}
		Point2D position = this.position.add(velocity);
		if (position.getX() - image.getWidth() * 0.5 > 0 &&
				position.getX() + image.getWidth() * 0.5 < graphicsContext.getCanvas().getWidth() && 
				position.getY() - image.getHeight() * 0.5 > 0 && 
				position.getY() + image.getHeight() * 0.5 < graphicsContext.getCanvas().getHeight()) {
			this.position = position;
		}
		
		acceleration = acceleration.multiply(0);
		return true;
	}
}