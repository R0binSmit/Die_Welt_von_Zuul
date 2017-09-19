package item;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Crafting extends Item {
	public Crafting(String name, String beschreibung, int gewicht, int preis, Image image, int x, int y, GraphicsContext gc,
			boolean essbar) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x, y), gc, essbar);
	}

	public void use(Character character) {
		// TODO Auto-generated method stub
		
	}
}