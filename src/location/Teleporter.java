package location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import character.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Definiert Objekte der Klasse Teleporter, die von Landscape erben und einen
 * Raum zu einem Teleporterraum machen, indem sie beim Betreten des Raumes den
 * Spieler in einen anderen Raum versetzen
 *
 */
public class Teleporter extends Landscape {
	private ArrayList<Room> destination;
	private String key;

	/**
	 * 
	 * @param name
	 *            Name des Objekts
	 * @param description
	 *            Beschreibung des Objekts
	 * @param image
	 *            Bild des Objekts in der 2D-Welt
	 * @param x
	 *            Position des Objekts in der 2D-Welt x-Achse
	 * @param y
	 *            Position des Objekts in der 2D-Welt y-Achse
	 * @param gc
	 *            Grafischer Kontext zum Zeichnen des Objekts
	 * @param landscapeResponse
	 *            Liste, die die Enums möglicher Interaktionen und die dazugehörigen
	 *            Texte speichert
	 * @param key
	 *            Name des Items, das den Teleporter deaktiviert
	 * @param destination
	 *            Liste von möglichen Zielorten der Teleportation
	 */
	public Teleporter(String name, String description, Image image, int x, int y, GraphicsContext gc,
			HashMap<LandscapeResponse, String> landscapeResponse, String key, ArrayList<Room> destination) {
		super(name, description, image, x, y, gc, landscapeResponse);
		this.key = key;
		this.destination = destination;
	}

	@Override
	/**
	 * Wird beim Betreten des Raumes auf allen Landscape-Objekten in diesem Raum
	 * aufgerufen
	 * 
	 * @param spieler
	 *            Spieler, der den Raum betreten hat
	 */
	public void onEnterRoom(Player spieler) {
		Room currentDestination;

		/*
		 * Wenn der Spieler das Item mit dem Namen = key besitzt, wird dieses entfernt
		 * und der Teleporter deaktiviert
		 */
		if (spieler.getItem(key) != null) {
			textbox.addText(landscapeResponse.get(LandscapeResponse.REMOVE_RESPONSE));
			spieler.dropItem(key);
			getRoom().removeLandscape(getName());
			return;
		}

		/*
		 * Wenn eine Liste mit Zielorten übergeben wurde, wird ein zufälliger Raum aus
		 * dieser Liste ausgewählt
		 */
		if (destination != null && destination.size() > 0) {
			Random randomNumber = new Random();
			int roomNumber = randomNumber.nextInt(destination.size());
			currentDestination = destination.get(roomNumber);
		}

		/*
		 * Wenn keine Liste von Räumen übergeben wurde, wird ein zufälliger Raum aus
		 * allen Räumen ausgewählt, der nicht der Raum ist, in dem der Teleporter
		 * aufgerufen wurde
		 */
		else {
			do {
				currentDestination = getRoom().getLand().getRandomRoom();
			} while (currentDestination == getRoom());
		}
		
		/*
		 * Gibt den Text des Teleporter aus und setzt den Spieler in den zufällig ausgewählten Raum
		 */
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