package item;
 
import character.Character;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.IShowable;

/**
 * Diese Klasse ist die abstrakte definition von ein Item Objekt.
 */
public abstract class Item implements IShowable {
	protected GraphicsContext graphicsContext;
	protected Image image;
	protected String name, description;
	protected Point2D position;
	protected int price; // Wird für eine spätere Erweiterung benötigt.

	/** 
	 * Konstruktor von Item.
	 *  
	 * @param name
	 *            Der Name von dem Item als String.
	 * @param description
	 *            Die Beschreibung des Items als String.
	 * @param price
	 *            Wird für eine spätere Erweiterung benötigt.
	 * @param image
	 *            Wird verwendet um Items zu visualisieren.
	 * @param position
	 *            Beschreibt die Position des Items in dem Fenster.
	 * @param graphicsContext
	 *            Wird verwendet um Items im Fenster zu Zeichnen.
	 */
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

	/**
	 * Wird verwendet um das Item in das Fenster gezeinet.
	 */
	@Override
	public void show() {
		graphicsContext.drawImage(image, position.getX(), position.getY());
	}

	/**
	 * Wird verwendet um ein Item an einer anderen stelle (als die aktuelle
	 * Position) anzuzeigen.
	 * 
	 * @param x
	 *            X Koordinate als Double
	 * @param y
	 *            Y Koordinate als Double
	 */
	public void showAt(double x, double y) {
		graphicsContext.drawImage(image, x, y);
	}

	/**
	 * Gibt eine Beschreibung von dem Items als String zurück
	 * 
	 * @return Beschreibung eines Item als String
	 */
	@Override
	public String toString() {
		return "Item = name: " + getName() + ", description: " + getDescription() + ", price: " + getPrice();
	}

	public abstract void use(Character character);
}
