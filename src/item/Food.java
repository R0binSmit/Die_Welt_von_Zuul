package item;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import character.Character;

public class Food extends Item{
	private int changeHealthPoints;
	public Food(String name, String description, int price, Image image, int x, int y, GraphicsContext graphicsContext, int changeHealthPoints) {
		super(name, description, price, image, new Point2D(x,y ), graphicsContext);
		this.changeHealthPoints = changeHealthPoints;
	}
	
	public void use(Character character) {
		int newHealthPoints = (character.getHP() + changeHealthPoints);
		character.setHP(newHealthPoints);
	}
}
