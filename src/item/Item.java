package item;

import character.Character;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.IShowable;

public abstract class Item implements IShowable {
	protected GraphicsContext graphicsContext;
	protected Image image;
	protected String name, description;
	protected Point2D position;
	protected int price;

	public Item(String name, String description, int price, Image image, Point2D position,
			GraphicsContext graphicsContext) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.position = position;
		this.graphicsContext = graphicsContext;
	}

	public String getDescription() {
		return description;
	}

	public double getHeight() {
		return image.getHeight();
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public double getWidth() {
		return image.getWidth();
	}

	public double getX() {
		return position.getX();
	}

	public double getY() {
		return position.getY();
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	@Override
	public void show() {
		graphicsContext.drawImage(image, position.getX(), position.getY());
	}

	public void showAt(double x, double y) {
		graphicsContext.drawImage(image, x, y);
	}

	@Override
	public String toString() {
		return "Item = name: " + getName() + ", description: " + getDescription() + ", price: " + getPrice();
	}

	public abstract void use(Character character);
}
