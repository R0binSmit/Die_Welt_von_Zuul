package equipment;
 
import item.Defense;
import item.Item;
import item.Weapon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Klasse die das Equipment des Spielers darstellt
 */
public class Equipment implements IEquipment {
	private Defense breastplate;
	private Defense helmet;
	private Weapon leftHand;
	private Weapon rightHand;
	private Defense shoes;
	private Defense trousers;
	private GraphicsContext graphicsContext;

	/**
	 * Standard Konstruktor. 
	 */
	public Equipment(GraphicsContext graphicsContext) {
		this.graphicsContext =graphicsContext;
	}
	
	/**
	 * Die Methode wird verwendet, wenn die Methode "use" bei einem Defense Objekt angewendet wird.
	 * Dabei wird entschiede an welcher Position das Objekt übergeben wird.
	 * @param defense
	 *            Rüstungsgegenstand
	 * @return Den Rüstungsgegenstand der ersetzt wird (falls schon angelegt).
	 */
	public Item equipItem(Defense defense) {
		Item currentUsedDefense = null;
		switch (defense.getDefenseType()) {
		case HELMET:
			if(helmet != null) {
				currentUsedDefense = helmet;
			}
			helmet = defense;
			break;
		case BREASTPLATE:
			if(breastplate != null) {
				currentUsedDefense = breastplate;
			}
			breastplate = defense;
			break;
		case TROUSERS:
			if(trousers != null) {
				currentUsedDefense = trousers;
			}
			trousers = defense;
			break;
		case SHOES:
			if(shoes != null) {
				currentUsedDefense = shoes;
			}
			shoes = defense;
			break;
		default:
		}
		return currentUsedDefense;
	}

	/**
	 * Die Methode wird verwendet, wenn die Methode "use" bei einem Weapon Objekt angewendet wird.
	 * Dabei wird entschiede an welcher Position das Objekt übergeben wird.
	 * 
	 * @param weapon
	 *            Ein Weapon Objekt.
	 * @return Die Waffe, die ersetzt wird (falls eine ersetzt werden muss).
	 */
	public Item equipItem(Weapon weapon) {
		Item currentUsedWeapon = null;
		
		if (leftHand == null)
			leftHand = weapon;
		else if (rightHand == null)
			rightHand = weapon;
		else {
			currentUsedWeapon = leftHand;
			leftHand = weapon;
		}
		return currentUsedWeapon;
	}

	/**
	 * Gibt die Rüstungswerte aller Gegenstände des Equpments zurück.
	 *@return Gesamtrüstung als double.
	 */
	@Override
	public double getArmor() {
		double armor = 0;
		if (helmet != null)
			armor += helmet.getArmor();

		if (breastplate != null)
			armor += breastplate.getArmor();

		if (trousers != null)
			armor += trousers.getArmor();

		if (shoes != null)
			armor += shoes.getArmor();

		return armor;
	}

	/**
	 * Gibt den Gesamtschaden des Equpments zurück.
	 *@return Gesamtschaden als Integer.
	 */
	@Override
	public int getDamage() {
		int damage = 0;
		if (leftHand != null)
			damage += leftHand.getDamage();

		if (rightHand != null)
			damage += rightHand.getDamage();

		return damage;
	}

	/**
	 * Equipment wird in dem JavaFX Fenster anzeigen
	 */
	public void show() {
		Paint p = graphicsContext.getFill();
		graphicsContext.setFill(Color.rgb(230, 230, 255, 0.5));
		graphicsContext.fillRect(590, 520, 210, 280);
		graphicsContext.setFill(p);
		graphicsContext.fillText("Ausrüstung", 670, 535);
		
		if (helmet != null) {
			helmet.showAt(660, 540);
		}

		if (breastplate != null) {
			breastplate.showAt(660, 600);
		}

		if (leftHand != null) {
			leftHand.showAt(600, 600);
		}

		if (rightHand != null) {
			rightHand.showAt(720, 600);
		}

		if (trousers != null) {
			trousers.showAt(660, 680);
		}

		if (shoes != null) {
			shoes.showAt(660, 740);
		}
	}

	/**
	 * Gibt alle angelegten Waffen/Rüstungen als String aus.
	 * @return String von allen Gegenständen.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Equipment :");
		stringBuilder.append("\r\n");
		stringBuilder.append(helmet.toString());
		stringBuilder.append("\r\n");
		stringBuilder.append(breastplate.toString());
		stringBuilder.append("\r\n");
		stringBuilder.append(trousers.toString());
		stringBuilder.append("\r\n");
		stringBuilder.append(shoes.toString());
		return stringBuilder.toString();
	}
}
