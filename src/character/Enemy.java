package character;

import java.util.LinkedList;

import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

public class Enemy extends Character {
	private Point2D velocity = new Point2D(0, 0), acceleration = new Point2D(0, 0);
	private double maxSpeed = 5, maxForce = 0.05;
	
	public Enemy(String name, String description, Room room, int x, int y, Image image, GraphicsContext graphicsContext, LinkedList<Item> items) {
		super(name, description, room, x, y, image, graphicsContext, items);
	}
	
	public void move(Point2D target) {
		Point2D desired = target.subtract(position);
		desired = desired.normalize();
		desired = desired.multiply(maxSpeed);
		
		Point2D steer = desired.subtract(velocity);
		if (steer.magnitude() > maxForce) {
			steer = steer.normalize();
			steer = steer.multiply(maxForce);
		}
		
		applyForce(steer);
	}
	
	private void applyForce(Point2D force) {
		acceleration = acceleration.add(force);
	}
	
	public void update() {
		velocity = velocity.add(acceleration);
		if(velocity.magnitude() > maxSpeed) {
			velocity = velocity.normalize();
			velocity = velocity.multiply(maxSpeed);
		}
		position = position.add(velocity);
		acceleration = acceleration.multiply(0);
	}

	@Override
	public void interact(Player spieler) {

	}
}