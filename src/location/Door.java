package location;

import character.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.IShowable;

/**
 * Diese Klasse wird zur Modellierung von Türen verwendet
 */
public class Door implements IShowable {
	private GraphicsContext gc;
	private Image image;
	private Room nextRoom;
	private Point2D position, nextPos;

	/**
	 * Konstructor um Türen zu definieren
	 * 
	 * @param image
	 *            Aussehen der Tür
	 * @param gc
	 *            GraphicsContext zum zeichnen der Tür
	 * @param position
	 *            Koordinaten der Tür
	 * @param nextPos
	 *            Koordinaten zu den der Spieler beim betreten der Tür gebracht wird
	 * @param nextRoom
	 *            Raum zu dem der Spieler gebracht wird
	 */
	public Door(Image image, GraphicsContext gc, Point2D position, Point2D nextPos, Room nextRoom) {
		super();
		this.image = image;
		this.gc = gc;
		this.position = position;
		this.nextPos = nextPos;
		this.nextRoom = nextRoom;
	}

	/**
	 * Wird beim Raumwechsel aufgerufen
	 * @param player
	 * Spieler der den Raum wechselt
	 */
	public void changeRoom(Player player) {
		player.setRoom(nextRoom);
		player.setPosition(nextPos);
		nextRoom.onEnterRoomEvent(player);
	}

	public double getHeight() {
		return image.getHeight();
	}

	public double getWidth() {
		return image.getWidth();
	}

	public double getX() {
		return position.getX();
	}

	public double getY() {
		return position.getY();
	}

	/**
	 * Anzeigen der Türen
	 */
	@Override
	public void show() {
		gc.drawImage(image, position.getX(), position.getY());
	}
}