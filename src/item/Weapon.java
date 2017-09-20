package item;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Weapon extends Item {
	private int damage;
	private int armor;
	
	public Weapon(String name, String description, int price, Image image, int x, int y, GraphicsContext graphicsContext, int damage) {
		super(name, description, price, image, new Point2D(x, y), graphicsContext);
		this.damage = damage;
		this.armor = 0;
	}
	
	public Weapon(String name, String description, int price, Image image, int x, int y, GraphicsContext graphicsContext, int damage, int armor) {
		super(name, description, price, image, new Point2D(x, y), graphicsContext);
		this.damage = damage;
		this.armor = armor;
	}
	
	public int getArmor() {
		return armor;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void use(Character character) {
		
	}
}