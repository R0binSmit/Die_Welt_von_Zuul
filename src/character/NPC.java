package character;

import java.util.LinkedList;

import gegenstand.Gegenstand;
import ort.Raum;

public class NPC extends Character {
	public NPC(String name, int maxTraglast, Raum raum, LinkedList<Gegenstand> gegenstaende) {
		super(name, maxTraglast, raum, gegenstaende);
	}

	@Override
	public void interagieren() {

	}
}
