package item;
 
import character.Character;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Diese Klasse ist eine Spezialisierung von Item und repräsentiert eine Waffe.
 */
public class Weapon extends Item {
	private int armor;
	private int damage;

	/**
	 * Konstruktor eines der Waffen Klasse ohne Rüstungswert.
	 * 
	 * @param name
	 *            Name der Waffe
	 * @param description
	 *            Beschreibung der Waffe
	 * @param image
	 *            Die Visualisierung der Waffe (ein Bild).
	 * @param x
	 *            X Koordinaten (wo es im Fenster angezeigt werden soll).
	 * @param y
	 *            Y Koordianten (wo es im Fenster angezeigt werden soll).
	 * @param graphicsContext
	 *            Wird benötigt um das Objekt in das JavaFX Fenster zu Zeichnen.
	 * @param damage
	 *            Beschreibt den Schaden die die Waffe verursachen kann.
	 */
	public Weapon(String name, String description, Image image, int x, int y,
			GraphicsContext graphicsContext, int damage) {
		super(name, description, image, new Point2D(x, y), graphicsContext);
		this.damage = damage;
		this.armor = 0;
	}

	/**
	 * Konstruktor eines der Waffen Klasse mit Rüstungswert.
	 * 
	 * @param name
	 *            Name der Waffe
	 * @param description
	 *            Beschreibung der Waffe
	 * @param image
	 *            Die Visualisierung der Waffe (ein Bild).
	 * @param x
	 *            X Koordinaten (wo es im Fenster angezeigt werden soll).
	 * @param y
	 *            Y Koordianten (wo es im Fenster angezeigt werden soll).
	 * @param graphicsContext
	 *            Wird benötigt um das Objekt in das JavaFX Fenster zu Zeichnen.
	 * @param damage
	 *            Beschreibt den Schaden die die Waffe verursachen kann.
	 * @param armor
	 *            Beschreibt den Rüstungswert der Waffe.
	 */
	public Weapon(String name, String description, Image image, int x, int y,
			GraphicsContext graphicsContext, int damage, int armor) {
		super(name, description, image, new Point2D(x, y), graphicsContext);
		this.damage = damage;
		this.armor = armor;
	}

	public int getArmor() {
		return armor;
	}

	public int getDamage() {
		return damage;
	}
	  
	/**
	 * Rüstet die Waffe aus.
	 */
	@Override
	public void use(Character character) {
		character.equipItem(this);
	}
}
