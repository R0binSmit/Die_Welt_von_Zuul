package character;

import java.util.LinkedList;

import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import location.Room;

public class NPC extends Character {
	protected String text;

	public NPC(String name, String description, Room room, int x, int y, Image image, GraphicsContext graphicsContext,
			LinkedList<Item> items) {
		super(name, description, room, x, y, image, graphicsContext, items);
	}

	public String getText() {
		return text;
	}

	@Override
	public void interact(Player spieler) {
		System.out.println(text);
	}

	public void setText(String text) {
		this.text = text;
	}
}
