package item;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import equipment.IEquipment;

public class Weapon extends Item implements IItem, IEquipment {
	private int damage;
	
	public Weapon(String name, String beschreibung, int gewicht, int preis, Image image, int x, int y, int damage) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x, y), false);
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