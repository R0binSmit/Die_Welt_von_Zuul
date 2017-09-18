package gegenstand;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Waffe extends Gegenstand implements Hand {
	private int schaden;
	public Waffe(String name, String beschreibung, int gewicht, int preis, Image image, int x, int y, int schaden) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x, y), false);
		this.schaden = schaden;
	}
}