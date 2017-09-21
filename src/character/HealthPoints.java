package character;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.IShowable;
import main.Usefull;

public class HealthPoints implements IShowable {
	int maxHealthPoints;
	int currentHealthPoints;
	boolean isUsable;
	private GraphicsContext graphicsContext;
	private Character character;
	
	HealthPoints(Character character, GraphicsContext graphicsContext){
		maxHealthPoints = 100;
		currentHealthPoints = maxHealthPoints;
		isUsable =  true;
		this.graphicsContext = graphicsContext;
		this.character = character;
	}
	
	HealthPoints(Character character, GraphicsContext graphicsContext, int maxHealPoints){
		this.maxHealthPoints = maxHealPoints;
		currentHealthPoints = maxHealPoints;
		isUsable = true;
		this.graphicsContext = graphicsContext;
		this.character = character;
	}
	
	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}
	
	public int getCurrentHealthPoints() {
		return currentHealthPoints;
	}
	
	public void setCurrentHealPoints(int healPoints) {
		this.currentHealthPoints = healPoints;
	}
	
	public String toString() {
		return currentHealthPoints + "/" + maxHealthPoints;
	}
	
	public void addHealPoints(int healPoints) {
		if((currentHealthPoints + healPoints) > maxHealthPoints) {
			currentHealthPoints = maxHealthPoints;
			isUsable = true;
		}
		else{
			currentHealthPoints += healPoints;
			isUsable = true;
		}
	}
	
	public void dropHealPoints(int healPoints) {
		if((currentHealthPoints - healPoints) <= 0)
		{
			currentHealthPoints = 0;
			isUsable = false;
		}
		else
			currentHealthPoints -= healPoints;
	}
	
	public boolean getIsUsable() {
		return isUsable;
	}

	public void show() {
		double x = character.getPosition().getX() - 50;
		double y = character.getPosition().getY() + character.getWidth() + 20;
		double w = 100;

		Paint p = graphicsContext.getFill();
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillRect(x, y, w, 10);
		graphicsContext.setFill(Color.RED);
		graphicsContext.fillRect(x + 1, y + 1,
				Usefull.map(currentHealthPoints, 0, maxHealthPoints, 0, w - 2), 8);
		graphicsContext.setFill(p);
	}
}
