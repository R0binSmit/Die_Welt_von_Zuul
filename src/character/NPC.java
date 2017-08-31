package character;

import java.util.LinkedList;

import gegenstand.Gegenstand;
import ort.Raum;

public class NPC extends Character {
	private String text;
	public NPC(String name, int maxTraglast, Raum raum, LinkedList<Gegenstand> gegenstaende) {
		super(name, maxTraglast, raum, gegenstaende);
	}

	@Override
	public void interagieren() {
		System.out.println(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
