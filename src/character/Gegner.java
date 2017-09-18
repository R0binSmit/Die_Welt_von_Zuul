package character;

import java.util.LinkedList;

import item.Gegenstand;
import javafx.scene.image.Image;
import ort.Raum;

public class Gegner extends Character {
	public Gegner(String name, int maxTraglast, Raum raum, int x, int y, Image image,
			LinkedList<Gegenstand> gegenstaende) {
		super(name, maxTraglast, raum, x, y, image, gegenstaende);
	}

	@Override
	public void interagieren(Spieler spieler) {

	}
}