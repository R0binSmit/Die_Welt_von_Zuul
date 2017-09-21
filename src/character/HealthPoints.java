package character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.IShowable;
import main.Usefull;

/**
 * Klasse um die HP eines Charakters zu modellieren
 */
public class HealthPoints implements IShowable {
	private Character character;
	int currentHealthPoints;
	private GraphicsContext graphicsContext;
	boolean isUsable;
	int maxHealthPoints;

	/**
	 * Erstellen des Objekts
	 * 
	 * @param character
	 *            der Charakter zu dem die HP gehören
	 * @param graphicsContext
	 *            zum Darstellen der HP Leiste
	 */
	HealthPoints(Character character, GraphicsContext graphicsContext) {
		maxHealthPoints = 100;
		currentHealthPoints = maxHealthPoints;
		isUsable = true;
		this.graphicsContext = graphicsContext;
		this.character = character;
	}

	/**
	 * Erstellen des Objekts
	 * 
	 * @param character
	 *            der Charakter zu dem die HP gehören
	 * @param graphicsContext
	 *            zum Darstellen der HP Leiste
	 * @param maxHealPoints
	 *            maximale HP des Charakters
	 */
	HealthPoints(Character character, GraphicsContext graphicsContext, int maxHealPoints) {
		this.maxHealthPoints = maxHealPoints;
		currentHealthPoints = maxHealPoints;
		isUsable = true;
		this.graphicsContext = graphicsContext;
		this.character = character;
	}

	/**
	 * Momentane HP des Charakters erhöhen
	 * 
	 * @param healPoints
	 *            Dazuzuaddierende HP
	 */
	public void addHealPoints(int healPoints) {
		if ((currentHealthPoints + healPoints) > maxHealthPoints) {
			currentHealthPoints = maxHealthPoints;
			isUsable = true;
		} else {
			currentHealthPoints += healPoints;
			isUsable = true;
		}
	}

	/**
	 * Momentane HP des Charakters senken
	 * 
	 * @param healPoints
	 *            Zu entfernende HP
	 */
	public void dropHealPoints(int healPoints) {
		if ((currentHealthPoints - healPoints) <= 0) {
			currentHealthPoints = 0;
			isUsable = false;
		} else
			currentHealthPoints -= healPoints;
	}

	public int getCurrentHealthPoints() {
		return currentHealthPoints;
	}

	public boolean getIsUsable() {
		return isUsable;
	}

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public void setCurrentHealPoints(int healPoints) {
		this.currentHealthPoints = healPoints;
	}

	/**
	 * HP Leiste unter dem zugehörigen Charakter anzeigen
	 */
	@Override
	public void show() {
		double x = character.getPosition().getX() - 50;
		double y = character.getPosition().getY() + character.getHeight() * 0.5 + 20;
		double w = 100;

		Paint p = graphicsContext.getFill();
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillRect(x, y, w, 10);
		graphicsContext.setFill(Color.RED);
		graphicsContext.fillRect(x + 1, y + 1, Usefull.map(currentHealthPoints, 0, maxHealthPoints, 0, w - 2), 8);
		graphicsContext.setFill(p);
	}
}
