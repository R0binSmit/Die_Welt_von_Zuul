package character;

import java.util.LinkedList;

import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

public class Trader extends NPC {
	public Trader(String name, String description, Room room, int x, int y, Image image,
			GraphicsContext graphicsContext, LinkedList<Item> items) {
		super(name, description, room, x, y, image, graphicsContext, items);
	}

	public boolean trade(Character verkauf, Character kauf, String gegenstand) {
		Item ware = verkauf.getItem(gegenstand);
		if (ware != null && kauf.getMoney() >= ware.getPrice()) {
			kauf.pickUpItem(verkauf.dropItem(ware.getName()));
			verkauf.setMoney(verkauf.getMoney() + ware.getPrice());
			kauf.setMoney(kauf.getMoney() - ware.getPrice());
			return true;
		}
		return false;
	}
}