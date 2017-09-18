package item;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Crafting extends Item {
	public Crafting(String name, String beschreibung, int gewicht, int preis, Image image, int x, int y,
			boolean essbar) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x, y), essbar);
	}

	public void use(Character character) {
		// TODO Auto-generated method stub
		
	}
}