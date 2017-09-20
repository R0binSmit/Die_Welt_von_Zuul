package item;

import equipment.IEquipment;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Weapon extends Item {
	private int damage;
	
	public Weapon(String name, String description, int price, Image image, int x, int y, GraphicsContext graphicsContext, int damage) {
		super(name, description, price, image, new Point2D(x, y), graphicsContext);
		this.damage = damage;
	}
	
	public void use(Character character) {
		
	}
	
	
	public int getArmor() {
		return 0;
	}
	
	public int getDamage() {
		return damage;
	}
}