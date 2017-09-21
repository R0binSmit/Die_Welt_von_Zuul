package character;

import java.util.LinkedList;

import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

/**
 * Klasse mit der alle Gegner dargestellt werden
 */
public class Enemy extends Character {
	int cooldown = 100;
	private double maxSpeed = 5, maxForce = 0.05;
	private Point2D velocity = new Point2D(0, 0), acceleration = new Point2D(0, 0);

	/**
	 * Konstruktor für alle Gegner
	 * 
	 * @param name
	 *            Name des Gegners
	 * @param description
	 *            Beschreibung des Gegners
	 * @param room
	 *            Raum in dem sich der Gegner befindet
	 * @param x
	 *            X Position des Gegners im Raum
	 * @param y
	 *            Y Position des Gegners im Raum
	 * @param image
	 *            Aussehen des Gegners
	 * @param graphicsContext
	 *            GraphicsContext zum anzeigen des Gegners
	 * @param items
	 *            Items die der Gegner von anfang an dabei hat
	 */
	public Enemy(String name, String description, Room room, int x, int y, Image image, GraphicsContext graphicsContext,
			LinkedList<Item> items) {
		super(name, description, room, x, y, image, graphicsContext, items);
	}

	/**
	 * Lenkkraft auf die Beschleunigung anwenden
	 * @param force
	 * Kraft mit der gelenkt wird
	 */
	private void applyForce(Point2D force) {
		acceleration = acceleration.add(force);
	}

	/**
	 * Wird aufgerufen sobald der Gegner einen Spieler angreifen soll
	 * @param target
	 * der Spieler der angegriffen werden soll
	 */
	public void attack(Player target) {
		int hp = target.getHP();
		int dmg = equipment.getDamage() + 10;
		hp -= dmg * (1 - target.getDefense());
		target.setHP(hp);
	}

	/**
	 * Dadurch können Charaktere mit dem Spieler interagieren
	 * @param spieler
	 * Der Spieler mit dem interagiert werden soll
	 */
	@Override
	public void interact(Player spieler) {

	}

	/**
	 * Hierdurch soll der Gegner den Charakter verfolgen.
	 * @param target
	 * Der zu verfolgende Spieler
	 */
	public void move(Player target) {
		//Prüfen ob der Gegner nah genug dran ist um anzugreifen, wenn ja angreifen
		if (target.getPosition().distance(position) < image.getWidth() * 0.5 && cooldown <= 0) {
			attack(target);
			cooldown = 100;
		}
		
		//Herausfinden wo sich der Gegner hinbewegen will und es an Maximalgeschwindigkeit anpassen
		Point2D desired = target.getPosition().subtract(position);
		desired = desired.normalize();
		desired = desired.multiply(maxSpeed);

		//Point2D erstellen der in die Richtung des Spielers lenkt
		Point2D steer = desired.subtract(velocity);
		if (steer.magnitude() > maxForce) {
			steer = steer.normalize();
			steer = steer.multiply(maxForce);
		}

		applyForce(steer);
	}

	/**
	 * Variablen des Gegners updaten
	 * @return
	 */
	public boolean update() {
		//Angriffscooldown reduzieren
		cooldown--;
		
		if (healthPoints.getCurrentHealthPoints() <= 0) {
			return false;
		}
		
		velocity = velocity.add(acceleration);
		if (velocity.magnitude() > maxSpeed) {
			velocity = velocity.normalize();
			velocity = velocity.multiply(maxSpeed);
		}
		
		Point2D position = this.position.add(velocity);
		if (position.getX() - image.getWidth() * 0.5 > 0
				&& position.getX() + image.getWidth() * 0.5 < graphicsContext.getCanvas().getWidth()
				&& position.getY() - image.getHeight() * 0.5 > 0
				&& position.getY() + image.getHeight() * 0.5 < graphicsContext.getCanvas().getHeight()) {
			this.position = position;
		}

		acceleration = acceleration.multiply(0);
		return true;
	}
}