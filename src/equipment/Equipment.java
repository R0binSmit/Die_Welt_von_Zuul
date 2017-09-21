package equipment;
 
import item.Defense;
import item.Item;
import item.Weapon;

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

	/**
	 * Standard Konstruktor. 
	 */
	public Equipment() {
		
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
				currentUsedDefense = helmet;
			}
			breastplate = defense;
			break;
		case TROUSERS:
			if(trousers != null) {
				currentUsedDefense = helmet;
			}
			trousers = defense;
			break;
		case SHOES:
			if(shoes != null) {
				currentUsedDefense = helmet;
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
		Item currentUsedDefense = null;
		
		if (leftHand == null)
			leftHand = weapon;
		else if (rightHand == null)
			rightHand = weapon;
		else {
			currentUsedDefense = leftHand;
			leftHand = weapon;
		}
		return currentUsedDefense;
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
		if (helmet != null) {
			helmet.showAt(700, 600);
		}

		if (breastplate != null) {
			breastplate.showAt(700, 650);
		}

		if (leftHand != null) {
			leftHand.showAt(650, 650);
		}

		if (rightHand != null) {
			rightHand.showAt(750, 650);
		}

		if (trousers != null) {
			trousers.showAt(700, 700);
		}

		if (shoes != null) {
			shoes.showAt(700, 750);
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
