package item;
 
import character.Character;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
 
/**
 * Diese Klasse definiert ein einfaches Item Objekt (da Item abstrakt ist).
 */
public class BasicItem extends Item {
	public BasicItem(String name, String description, int price, Image image, int x, int y,
			GraphicsContext graphicsContext) {
		super(name, description, price, image, new Point2D(x, y), graphicsContext);
	}

	@Override
	public void use(Character character) {

	} 
}
