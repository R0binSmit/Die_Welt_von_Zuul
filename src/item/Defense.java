package item;

import character.Character;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Diese Klasse deffiniert eine spezialisierung von Item.
 * Das hier beschriebene Objekt stellt ein Rüstungsgegenstand dar.
 */
public class Defense extends Item {
	private double armor;
	private EnumDefense defenseType;	// Wird werdendet, um die berschiedenen Typen von Rüstungsgegenständen zu unterscheiden. 

	/**
	 * Der Kontrtuktor von ein Rüstungsgegenstandes.
	 * @param name = Der Name der Rüstung als String.
	 * @param description = Die Beschreibung der Rüstung als String. 
	 * @param price = Der Preis der Rüstung als Integer (wird für eine später angedachte Erweiterung benötigt).
	 * @param defenseType = Beschreibt den Typen der Rüstung anhand von EnumDefense.
	 * @param image = Das aussehen der Rüstung.
	 * @param x = benötigt für die Positionierung der Rüstung.
	 * @param y = benötigt für die Positionierung der Rüstung.
	 * @param graphicsContext = Wird für die Implementation von dem Bild in das JavaFX Fenster benötigt.
	 * @param armor = Der Rüstungswert von der Rüstung.
	 */
	public Defense(String name, String description, int price, EnumDefense defenseType, Image image, int x, int y,
			GraphicsContext graphicsContext, int armor) {
		super(name, description, price, image, new Point2D(x, y), graphicsContext);
		this.armor = armor;
		this.defenseType = defenseType;
	}

	public double getArmor() {
		return armor;
	}

	public int getDamage() {
		return 0;
	}

	public EnumDefense getDefenseType() {
		return defenseType;
	}

	/**
	 * Rüstet die Rüstung aus.
	 */
	@Override
	public void use(Character character) {
		character.equipItem(this);
	}
}
