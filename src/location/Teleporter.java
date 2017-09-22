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

	public Teleporter(String name, String description, Image image, int x, int y, GraphicsContext gc,
			HashMap<LandscapeResponse, String> landscapeResponse, String key, ArrayList<Room> destination) {
		super(name, description, image, x, y, gc, landscapeResponse);
		this.key = key;
		this.destination = destination;
	}

	@Override
	public void onEnterRoom(Player spieler) {
		Room currentDestination;

		if (spieler.getItem(key) != null) {
			textbox.addText(landscapeResponse.get(LandscapeResponse.REMOVE_RESPONSE));
			spieler.dropItem(key);
			getRoom().removeLandscape(getName());
			return;
		}

		if (destination != null && destination.size() > 0) {
			Random randomNumber = new Random();
			int roomNumber = randomNumber.nextInt(destination.size());
			currentDestination = destination.get(roomNumber);
		}

		else {
			do {
				currentDestination = getRoom().getLand().getRandomRoom();
			} while (currentDestination == getRoom());
		}

		textbox.addText(getResponse(LandscapeResponse.ENTER_RESPONSE));
		spieler.setRoom(currentDestination);
		spieler.setPosition(new Point2D(400, 400));
		spieler.getRoom().onEnterRoomEvent(spieler);
	}

	@Override
	public void onUse(Player spieler) {
		textbox.addText(getResponse(LandscapeResponse.USE_RESPONSE));
	}
}