package item;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import equipment.IEquipment;

public abstract class Defense extends Item implements IItem, IEquipment {
	private int armor;
	
	public Defense(String name, String beschreibung, int gewicht, int preis, Image image, int x, int y, int armor) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x, y), false);
		this.armor = armor;
	}
	
	public void use(Character character) {
		
	}
	
	public int getArmor() {
		return armor;
	}
	
	public int getDamage() {
		return 0;
	}
}