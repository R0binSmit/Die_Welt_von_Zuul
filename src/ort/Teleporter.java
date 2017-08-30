package ort;

import character.Spieler;

public class Teleporter extends Landscape {

	public Teleporter(String name, String beschreibung) {
		super(name, beschreibung);
	}

	public void onUse(Spieler spieler) {
		// System.out.println("Du streichelst den Panther!");
	}

	public void onEnterRoom(Spieler spieler) {
		Raum destination;

		do {
			destination = getRaum().getLand().getRandomRoom();
		} while (destination == getRaum());

		spieler.setAktuellerRaum(destination);
		System.out.println();
		System.out.println(
				"Ein Panther springt aus der Dunkelheit, packt dich im Nacken und verschleppt dich in einen anderen Raum!");
		System.out.println(spieler.getAktuellerRaum().getLongDesciption());
		spieler.getAktuellerRaum().onEnterRoomEvent(spieler);
	}

}
