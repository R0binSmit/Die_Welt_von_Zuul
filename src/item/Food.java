package item;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Food extends Item{
	public Food(String name, String description, int price, Image image, int x, int y, GraphicsContext graphicsContext) {
		super(name, description, price, image, new Point2D(x,y ), graphicsContext);
	}
	
	public void use(Character character) {
		
	}
}