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
	private int currentHealthPoints;
	private GraphicsContext graphicsContext;
	boolean isUsable;
	private int maxHealthPoints;

	/**
	 * Erstellen des Objekts
	 * 
	 * @param character
	 *            der Charakter zu dem die HP gehören
	 * @param graphicsContext
	 *            zum Darstellen der HP Leiste
	 */
	public HealthPoints(Character character, GraphicsContext graphicsContext) {
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
	 public HealthPoints(Character character, GraphicsContext graphicsContext, int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
		currentHealthPoints = maxHealthPoints;
		isUsable = true;
		this.graphicsContext = graphicsContext;
		this.character = character;
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

	public void setCurrentHealthPoints(int healPoints) { 
		if(healPoints > maxHealthPoints) {
			currentHealthPoints = maxHealthPoints;
		}
		else {
			currentHealthPoints = healPoints;
		}
		if(healPoints == 0) {
			isUsable = false;
		}
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
