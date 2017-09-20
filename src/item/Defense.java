package item;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Defense extends Item{
	private double armor;
	
	public Defense(String name, String description, int price, Image image, int x, int y, GraphicsContext graphicsContext, int armor) {
		super(name, description, price, image, new Point2D(x, y), graphicsContext);
		this.armor = armor;
	}
	
	public void use(Character character) {
		
	}
	
	public double getArmor() {
		return armor;
	}
	
	public int getDamage() {
		return 0;
	}
}