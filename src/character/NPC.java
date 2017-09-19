package character;

import java.util.LinkedList;

import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ort.Raum;

public class NPC extends Character {
	public NPC(String name, int maxTraglast, Raum raum, int x, int y, Image image, GraphicsContext gc,
			LinkedList<Item> gegenstaende) {
		super(name, maxTraglast, raum, x, y, image, gc, gegenstaende);
	}

	protected String text;
	

	@Override
	public void interagieren(Player spieler) {
		System.out.println(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
