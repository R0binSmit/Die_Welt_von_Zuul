package character;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.IShowable;
import main.Usefull;

public class HealPoints implements IShowable {
	int maxHealPoints;
	int currentHealPoints;
	boolean isUsable;
	private GraphicsContext graphicsContext;
	private Character character;
	
	HealPoints(Character character, GraphicsContext graphicsContext){
		maxHealPoints = 100;
		currentHealPoints = maxHealPoints;
		isUsable =  true;
		this.graphicsContext = graphicsContext;
		this.character = character;
	}
	
	HealPoints(Character character, GraphicsContext graphicsContext, int maxHealPoints){
		this.maxHealPoints = maxHealPoints;
		currentHealPoints = maxHealPoints;
		isUsable = true;
		this.graphicsContext = graphicsContext;
		this.character = character;
	}
	
	public int getMaxHealPoints() {
		return maxHealPoints;
	}
	
	public int getCurrentHealPoints() {
		return currentHealPoints;
	}
	
	public String toString() {
		return currentHealPoints + "/" + maxHealPoints;
	}
	
	public void addHealPoints(int healPoints) {
		if((currentHealPoints + healPoints) > maxHealPoints) {
			currentHealPoints = maxHealPoints;
			isUsable = true;
		}
		else{
			currentHealPoints += healPoints;
			isUsable = true;
		}
	}
	
	public void dropHealPoints(int healPoints) {
		if((currentHealPoints - healPoints) <= 0)
		{
			currentHealPoints = 0;
			isUsable = false;
		}
		else
			currentHealPoints -= healPoints;
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
				Usefull.map(currentHealPoints, 0, maxHealPoints, 0, w - 2), 8);
		graphicsContext.setFill(p);
	}
}
