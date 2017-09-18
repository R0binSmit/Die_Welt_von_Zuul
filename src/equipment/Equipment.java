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
	
	public int getDamage() {
		int damage = 0;
		if(leftHand != null) 
			damage += leftHand.getDamage();
		
		if(rightHand != null) 
			damage+= rightHand.getDamage();

		return damage;
	}

	public int getArmor() {
		int armor = 0;
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
}
