package location;

import java.util.HashMap;

import character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Definiert Objekte der Klasse Healfountain, die den Spieler bei Interaktion
 * heilen
 *
 */
public class Healfountain extends Landscape {

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
	 */
	public Healfountain(String name, String description, Image image, int x, int y, GraphicsContext gc,
			HashMap<LandscapeResponse, String> landscapeResponse) {
		super(name, description, image, x, y, gc, landscapeResponse);
	}

	@Override
	public void onEnterRoom(Player player) {

	}

	@Override
	/**
	 * Heilt den Spieler
	 * @param spieler Spieler der mit dem Objekt interagiert
	 */
	public void onUse(Player player) {
		textbox.addText(getResponse(LandscapeResponse.USE_RESPONSE));
		player.setHP(player.getHP() + 20);
	}
}