package character;

import java.util.LinkedList;

import gegenstand.Gegenstand;
import ort.Raum;

public class Gegner extends Character {
	public Gegner(int maxTraglast, Raum raum, LinkedList<Gegenstand> gegenstaende) {
		super(maxTraglast, raum, gegenstaende);
	}

	@Override
	public void interagieren() {
		
	}
}