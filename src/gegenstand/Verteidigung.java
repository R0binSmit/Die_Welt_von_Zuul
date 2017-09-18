package gegenstand;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class Verteidigung extends Gegenstand {
	protected double block;
	public Verteidigung(String name, String beschreibung, int gewicht, int preis, Image image, int x, int y, double block) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x, y), false);
		this.block = block;
	}
}