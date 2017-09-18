package item;

import javafx.scene.image.Image;

public class Schild extends Verteidigung implements Hand{
	public Schild(String name, String beschreibung, int gewicht, int preis, Image image, int x, int y, double block) {
		super(name, beschreibung, gewicht, preis, image, x, y, block);
	}
}