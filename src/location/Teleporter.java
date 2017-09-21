package location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import character.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Teleporter extends Landscape {
	private ArrayList<Room> destination;
	private String key;

	public Teleporter(String name, String beschreibung, Image image, int x, int y, GraphicsContext gc,
			HashMap<LandscapeResponse, String> landscapeResponse, String key, ArrayList<Room> destination) {
		super(name, beschreibung, image, x, y, gc, landscapeResponse);
		this.key = key;
		this.destination = destination;
	}

	@Override
	public void onEnterRoom(Player spieler) {
		Room currentDestination;

		System.out.println();

		if (spieler.getGegenstand(key) != null) {
			tb.addText(landscapeResponse.get(LandscapeResponse.REMOVE_RESPONSE));
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
		spieler.setPosition(new Point2D(400, 400));
		tb.addText(getResponse(LandscapeResponse.ENTER_RESPONSE));
		tb.addText(spieler.getRoom().getLongDesciption());
		spieler.getRoom().onEnterRoomEvent(spieler);
	}

	@Override
	public void onUse(Player spieler) {
		tb.addText(getResponse(LandscapeResponse.USE_RESPONSE));
	}

}
