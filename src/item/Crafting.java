package item;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Crafting extends Gegenstand {
	public Crafting(String name, String beschreibung, int gewicht, int preis, Image image, int x, int y,
			boolean essbar) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x, y), essbar);
	}
}