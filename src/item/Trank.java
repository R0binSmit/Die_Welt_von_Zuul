package item;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Trank extends Item {
	public Trank(String name, String beschreibung, int gewicht, Image image, int x, int y, int preis) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x, y), true);
	}

	public void use(Character character) {
		// TODO Auto-generated method stub
		
	}
}