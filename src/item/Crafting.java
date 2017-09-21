package item;

import character.Character;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Crafting extends Item {
	public Crafting(String name, String description, int price, Image image, int x, int y,
			GraphicsContext graphicsContext, boolean essbar) {
		super(name, description, price, image, new Point2D(x, y), graphicsContext);
	}

	@Override
	public void use(Character character) {

	}
}
