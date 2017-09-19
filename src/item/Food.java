package item;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Food extends Item implements IItem {
	public Food(String name, String beschreibung, int gewicht, Image image, int x, int y, GraphicsContext gc, int preis) {
		super(name, beschreibung, gewicht, preis, image, new Point2D(x,y ), gc, true);
	}
	
	public void use(Character character) {
		
	}

	public void use() {
		// TODO Auto-generated method stub
		
	}
}