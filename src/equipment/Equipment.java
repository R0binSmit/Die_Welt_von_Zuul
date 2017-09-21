package equipment;

import item.Defense;
import item.Weapon;

public class Equipment implements IEquipment {
	private Defense helmet;
	private Defense breastplate;
	private Defense trousers;
	private Defense shoes;
	private Weapon leftHand;
	private Weapon rightHand;
	
	public Equipment() {
		
	}
	
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
	
	public int getDamage() {
		int damage = 0;
		if(leftHand != null) 
			damage += leftHand.getDamage();
		
		if(rightHand != null) 
			damage+= rightHand.getDamage();

		return damage;
	}

	public double getArmor() {
		double armor = 0;
		if(helmet != null)
			armor += helmet.getArmor();
		
		if(breastplate != null)
			armor += breastplate.getArmor();
		
		if(trousers != null)
			armor += trousers.getArmor();
		
		if(shoes != null)
			armor += shoes.getArmor();
		
		return armor;
	}
	
	public void equipItem(Defense defense) {
		switch(defense.getDefenseType()) {
			case HELMET:
				helmet = defense;
				break;
			case BREASTPLATE:
				breastplate = defense;
				break;
			case TROUSERS:
				trousers = defense;
				break;
			case SHOES:
				shoes = defense;
				break;
			default:		
		}
	}
	
	public void equipItem(Weapon weapon) {
		if(leftHand == null)
			leftHand = weapon;
		else if(rightHand == null)
			rightHand = weapon;
		else
			leftHand = weapon;
	}
	
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
