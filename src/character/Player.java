package character;

import java.util.LinkedList;

import Verhalten.SpielerAngriffVerhalten;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import ort.Raum;

public class Player extends Character {
	boolean hauptSpieler = false;

	public Player(String name, int maxTraglast, Raum raum, int x, int y, Image image, LinkedList<Item> gegenstaende) {
		super(name, maxTraglast, raum, x, y, image, gegenstaende);
		this.angriffsVerhalten = SpielerAngriffVerhalten.getInstance();
	}

	public boolean isHauptSpieler() {
		return hauptSpieler;
	}

	public void move(KeyCode key) {
		final int speed = 5;
		switch (key) {
		case W:
			pos = pos.add(new Point2D(0, -speed));
			break;
		case A:
			pos = pos.add(new Point2D(-speed, 0));
			break;
		case S:
			pos = pos.add(new Point2D(0, speed));
			break;
		case D:
			pos = pos.add(new Point2D(speed, 0));
			break;
		default:
		}
	}

	public void setHauptSpieler(boolean hauptSpieler) {
		this.hauptSpieler = hauptSpieler;
	}

	@Override
	public void interagieren(Player Spieler) {

	}
}