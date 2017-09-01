package ort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import character.Spieler;

public class Teleporter extends Landscape {
	private String key;
	private ArrayList<Raum> destination;

	public Teleporter(String name, String beschreibung, HashMap<LandscapeResponse, String> landscapeResponse, String key, ArrayList<Raum> destination) {
		super(name, beschreibung, landscapeResponse);
		this.key = key;
		this.destination = destination;
	}

	public void onUse(Spieler spieler) {
		System.out.println(getResponse(LandscapeResponse.USE_RESPONSE));
	}

	public void onEnterRoom(Spieler spieler) {
		Raum currentDestination;
		
		System.out.println();
		
		if (spieler.getGegenstand(key) != null) {
			System.out.println(landscapeResponse.get(LandscapeResponse.REMOVE_RESPONSE));
			spieler.gegenstandAblegen(key);
			getRaum().landschaftEntfernen(getName());
			return;
		}
		
		if (destination != null && destination.size() > 0) {
		Random randomNumber = new Random();
		int roomNumber = randomNumber.nextInt(destination.size());
		currentDestination = destination.get(roomNumber);
		}
		
		else {
		do {
			currentDestination = getRaum().getLand().getRandomRoom();
		} while (currentDestination == getRaum());
		}

		spieler.setAktuellerRaum(currentDestination);
		System.out.println(getResponse(LandscapeResponse.ENTER_RESPONSE));
		System.out.println(spieler.getAktuellerRaum().getLongDesciption());
		spieler.getAktuellerRaum().onEnterRoomEvent(spieler);
	}

}
