package item;

import character.Character;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Defense extends Item {
	private double armor;
	private EnumDefense defenseType;

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

	@Override
	public void use(Character character) {
		character.equipItem(this);
	}
}
