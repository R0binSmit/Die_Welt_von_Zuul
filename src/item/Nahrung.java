package item;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Nahrung extends Gegenstand {
	public Nahrung(String name, String beschreibung, int gewicht, Image image, int x, int y, int preis) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x,y ), true);
	}
	
	public Nahrung(String name, String beschreibung, int gewicht, int preis, Image image, int x, int y, boolean essbar) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x, y), essbar);
	}
}