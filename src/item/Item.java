package item;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.IShowable;
import character.Character;

public abstract class Item implements IShowable {
	protected String name, description;
	protected int price;
	protected Image image;
	protected Point2D position;
	protected GraphicsContext graphicsContext;
	
	public Item(String name, String description, int price, Image image, Point2D position, GraphicsContext graphicsContext) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.position = position;
		this.graphicsContext = graphicsContext;
	}

	public String toString() {
		return "Item = name: " + getName() + ", description: " + getDescription() + ", price: " + getPrice();
	}
	
	public void show() {
		graphicsContext.drawImage(image, position.getX(), position.getY());
	}
	
	public void showAt(double x, double y) {
		graphicsContext.drawImage(image, x, y);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}
	
	public double getX() {
		return position.getX();
	}
	
	public double getY() {
		return position.getY();
	}
	
	public double getWidth() {
		return image.getWidth();
	}
	
	public double getHeight() {
		return image.getHeight();
	}
	
	public abstract void use(Character character);
}
