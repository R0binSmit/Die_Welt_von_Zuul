package character;
import main.IShowable;

public class HealPoints implements IShowable {
	int maxHealPoints;
	int currentHealPoints;
	boolean isUsable;
	
	HealPoints(){
		maxHealPoints = 100;
		currentHealPoints = maxHealPoints;
		isUsable =  true;
	}
	
	HealPoints(int maxHealPoints){
		this.maxHealPoints = maxHealPoints;
		currentHealPoints = maxHealPoints;
		isUsable = true;
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
		// TODO SHOW
		
	}
}
