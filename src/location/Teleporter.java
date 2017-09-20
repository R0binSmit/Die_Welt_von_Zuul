package location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Teleporter extends Landscape {
	private String key;
	private ArrayList<Room> destination;

	public Teleporter(String name, String beschreibung, Image image, int x, int y, GraphicsContext gc, HashMap<LandscapeResponse, String> landscapeResponse, String key, ArrayList<Room> destination) {
		super(name, beschreibung, image, x, y, gc, landscapeResponse);
		this.key = key;
		this.destination = destination;
	}

	public void onUse(Player spieler) {
		System.out.println(getResponse(LandscapeResponse.USE_RESPONSE));
	}

	public void onEnterRoom(Player spieler) {
		Room currentDestination;
		
		System.out.println();
		
		if (spieler.getGegenstand(key) != null) {
			System.out.println(landscapeResponse.get(LandscapeResponse.REMOVE_RESPONSE));
			spieler.dropItem(key);
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

		spieler.setRoom(currentDestination);
		System.out.println(getResponse(LandscapeResponse.ENTER_RESPONSE));
		System.out.println(spieler.getRoom().getLongDesciption());
		spieler.getRoom().onEnterRoomEvent(spieler);
	}

}
